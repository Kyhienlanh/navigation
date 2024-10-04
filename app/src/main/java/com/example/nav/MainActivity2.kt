package com.example.nav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val info=Information()
        val change=changePassWord()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,info)
            commit()
        }
        var button1=findViewById<Button>(R.id.button1)
        var button2=findViewById<Button>(R.id.button2)
        var button3=findViewById<Button>(R.id.Logout)
        button1.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,info)
                commit()
            }
        }
        button2.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,change)
                commit()
            }
        }
        button3.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}