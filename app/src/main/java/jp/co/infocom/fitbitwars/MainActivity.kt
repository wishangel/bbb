package jp.co.infocom.fitbitwars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View as AndroidViewView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rules.setOnClickListener {onRuleButtonTapped(it)}
        battleStart.setOnClickListener {onBattleStartButtonTapped(it)}
    }

    fun onRuleButtonTapped(view: android.view.View?) {
        val intent = Intent(this, Rule_1::class.java)
        startActivity(intent)
    }

    fun onBattleStartButtonTapped(view: android.view.View?) {
        val intent = Intent(this, Battle::class.java)
        startActivity(intent)
    }

}
