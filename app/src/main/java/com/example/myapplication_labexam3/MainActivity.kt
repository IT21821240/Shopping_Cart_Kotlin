package com.example.myapplication_labexam3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

   private val homeFragment = HomeFragment()
   private val shoppingFragment = ShoppingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnhome)
        val button2:Button = findViewById(R.id.btnshop)

        button.setOnClickListener {
            loadHome()
        }
        button2.setOnClickListener {
            loadShopping()
        }

    }

    private fun loadHome(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null){

            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,homeFragment).commit()
        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,homeFragment).commit()
        }
    }

    private fun loadShopping(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null){

            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,shoppingFragment).commit()
        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,shoppingFragment).commit()
        }
    }
}