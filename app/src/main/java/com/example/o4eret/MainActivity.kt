package com.example.o4eret

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.o4eret.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.bottomNav.setOnItemSelectedListener(navListener)

        // дефолтна вкладка
        if (savedInstanceState == null) {
            b.bottomNav.selectedItemId = R.id.nav_browser
        }
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        val f: Fragment = when (item.itemId) {
            R.id.nav_network -> NetworkFragment()
            R.id.nav_browser -> BrowserFragment()
            R.id.nav_acoustic -> AcousticFragment()
            R.id.nav_chat -> ChatFragment()
            R.id.nav_info -> InfoFragment()
            else -> BrowserFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, f)
            .commit()
        true
    }
}
