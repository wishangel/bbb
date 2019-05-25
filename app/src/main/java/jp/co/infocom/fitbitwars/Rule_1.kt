package jp.co.infocom.fitbitwars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rule_1.*

class Rule_1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule_1)

        //ボタンが押された時の動作
        rule1_0.setOnClickListener{onBackButtonTapped(it)}
        rule1_2.setOnClickListener{onNextButtonTapped(it)}
    }

    //戻るボタンを押された時の処理
    fun onBackButtonTapped(view: android.view.View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //進むボタンを押された時の処理
    fun onNextButtonTapped(view: android.view.View?) {
        val intent = Intent(this, Rule_2::class.java)
        startActivity(intent)
    }

}