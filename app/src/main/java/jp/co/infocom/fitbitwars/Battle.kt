package jp.co.infocom.fitbitwars

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_battle.*
import kotlin.random.Random
import kotlin.concurrent.thread
import kotlin.system.exitProcess
import android.support.v4.os.HandlerCompat.postDelayed



class Battle : AppCompatActivity() {

    //初期値定義
    var player_1 = Battler("ごりら",1,500,500,500,500)
    var player_2 = Battler("しろくま",2,500,500,500,500)
    var turn = 0
    var endflag = 0 // 1になったら終了
    var winner:String = ""

    //ダメージ表示
    fun damageDisplay(attack: Battler,defence: Battler,damage:Int,end:Int):Unit{
        text_1row.setText("")
        text_2row.setText("")
        text_1row.setText(attack.name+"の攻撃!")

        if (end==0) {
            text_2row.setText(defence.name + "に" + damage.toString() + "のダメージを与えた")
        }
        else{
            text_2row.setText(damage.toString() + "のダメージを与えた！" + defence.name+"は力尽きた")

//            if (defence.name == "ごりら"){
//                player1.setImageDrawable(R.drawable.animal_gorilla_drumming_2)
//            }
//            else {
//                player2.setImageDrawable(R.drawable.animal_bear_hokkyoku_2)
//            }
        }

    }

    //素早さ計算（あとで適当な場所に移動）
    fun turnCalc(player1:Battler, player2:Battler):Pair<Battler,Battler> {
        var speed_1_rand = 0
        var speed_2_rand = 0

        while(true) {
            var random_1 = Random.nextInt(32) + 32
            var random_2 = Random.nextInt(32) + 32

            //乱数を掛ける
            var speed_1_rand = random_1 * player1.speed
            var speed_2_rand = random_2 * player2.speed

            //素早さが異なる場合はループを解除
            if (speed_1_rand != speed_2_rand) break
        }

        if (speed_1_rand > speed_2_rand) {
            return Pair(player1,player2)
        }
        else {
            return Pair(player2,player1)
        }
    }

    //ダメージ計算
    fun damageCalc(attack:Battler, defence:Battler):Int {
        var flag = 0
        var random_1 = Random.nextInt(54) + 99
        var damage = (attack.attack - defence.defence / 2) * random_1
        var result = damage / 256

        //ダメージが負の数の場合は0とする
        if (result <= 0){
            result = 0
        }

        //ダメージはHPを上限とする
        if (result >= defence.hp){
            result = defence.hp
        }

        return result
    }

    //ダメージ計算
    fun judgeEnd(attack:Battler,defence: Battler): Int{
        var end:Int = 0
        var damage = damageCalc(attack,defence)
        defence.hp = defence.hp - damage

        if (defence.hp <= 0){
            end = 1
        }

        damageDisplay(attack,defence,damage,end)

        return end
    }

    //画面更新
    fun updateParameter(player_1:Battler, player_2:Battler, turn:Int):Unit {
        //画像更新
        name1.setText(player_1.name);
        hp1.setText(player_1.hp.toString());

        name2.setText(player_2.name);
        hp2.setText(player_2.hp.toString());

        turn_display.setText(turn.toString());
    }

    //画面更新
    fun finishedDisplay(player_1:Battler, player_2:Battler, winner:String):Unit {
        //画像更新
        name1.setText(player_1.name);
        hp1.setText(player_1.hp.toString());

        name2.setText(player_2.name);
        hp2.setText(player_2.hp.toString());

        turn_display.setText(winner);
        turn_display2.setText("勝者");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)
        battleStart.setOnClickListener{onBattleButtonTapped(it)}

        updateParameter(player_1,player_2,turn)
    }

    //進むボタンを押された時の処理
    fun onBattleButtonTapped(view: android.view.View?) {
        if (endflag == 0){
            //戦闘処理開始
            //初期データ定義
            turn = turn + 1

            //素早さを計算
            var turnBattle = turnCalc(player_1,player_2)
            var turn_1 = turnBattle.first
            var turn_2 = turnBattle.second

            //先手の攻撃
            var a = judgeEnd(turn_1,turn_2)
            if (a == 1){
                endflag = 1
                winner = turn_1.name
            }



            if (endflag == 0) {
                //後手の攻撃
                var b = judgeEnd(turn_2, turn_1)
                if (b == 1) {
                    endflag = 1
                    winner = turn_2.name
                }
            }

            if(endflag ==1) {
                finishedDisplay(player_1, player_2, winner)
            }
            else{
                updateParameter(player_1, player_2, turn)
            }

        }

    }
}
