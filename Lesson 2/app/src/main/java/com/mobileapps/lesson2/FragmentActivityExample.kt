package com.mobileapps.lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.widget.Button

class FragmentActivityExample : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        button = findViewById(R.id.button)

        if (savedInstanceState == null) {
            replaceFragment(FragmentA())
            button.text = "Switch to Fragment B"
        }

        button.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (currentFragment is FragmentA) {
                replaceFragment(FragmentB())
                button.text = "Switch to Fragment A"
            } else {
                replaceFragment(FragmentA())
                button.text = "Switch to Fragment B"
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}
