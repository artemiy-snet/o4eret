package com.example.o4eret

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.o4eret.databinding.ActivityMainBinding
import com.example.o4eret.ui.AcousticFragment
import com.example.o4eret.ui.BrowserFragment
import com.example.o4eret.ui.ChatFragment
import com.example.o4eret.ui.InfoFragment
import com.example.o4eret.ui.NetworkFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.bottomNav.setOnItemSelectedListener(navListener)

        val initialSelection = savedInstanceState?.getInt(KEY_SELECTED_TAB) ?: R.id.nav_browser
        b.bottomNav.selectedItemId = initialSelection
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        val selected = item.itemId
        switchFragment(selected)
    }

    private fun switchFragment(itemId: Int): Boolean {
        val tag = tagForItem(itemId) ?: return false
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        fragmentManager.fragments.forEach { fragment ->
            transaction.hide(fragment)
        }

        var fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = createFragmentForItem(itemId)
            transaction.add(R.id.fragment_container, fragment, tag)
        } else {
            transaction.show(fragment)
        }

        transaction.commit()
        return true
    }

    private fun createFragmentForItem(itemId: Int): Fragment {
        return when (itemId) {
            R.id.nav_network -> NetworkFragment()
            R.id.nav_acoustic -> AcousticFragment()
            R.id.nav_chat -> ChatFragment()
            R.id.nav_info -> InfoFragment()
            else -> BrowserFragment()
        }
    }

    private fun tagForItem(itemId: Int): String? {
        return when (itemId) {
            R.id.nav_network -> TAG_NETWORK
            R.id.nav_browser -> TAG_BROWSER
            R.id.nav_acoustic -> TAG_ACOUSTIC
            R.id.nav_chat -> TAG_CHAT
            R.id.nav_info -> TAG_INFO
            else -> null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SELECTED_TAB, b.bottomNav.selectedItemId)
    }

    override fun onResume() {
        super.onResume()
        switchFragment(b.bottomNav.selectedItemId)
    }

    companion object {
        private const val KEY_SELECTED_TAB = "selected_tab"
        private const val TAG_NETWORK = "tab_network"
        private const val TAG_BROWSER = "tab_browser"
        private const val TAG_ACOUSTIC = "tab_acoustic"
        private const val TAG_CHAT = "tab_chat"
        private const val TAG_INFO = "tab_info"
    }
}
