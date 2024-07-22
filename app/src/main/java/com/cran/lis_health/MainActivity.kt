package com.cran.lis_health

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cran.lis_health.databinding.ActivityMainBinding
import com.cran.lis_health.fragments.Home
import com.cran.lis_health.fragments.Profile
import com.cran.lis_health.fragments.Registers
import com.cran.lis_health.fragments.Settings


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        replaceFragment(Home())
        binding.bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.menu -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.register -> replaceFragment(Registers())
                R.id.settings -> replaceFragment((Settings()))
                else -> {
               }


            }

            true
        }
        /* enableEdgeToEdge()
         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             insets
         }*/
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }


}