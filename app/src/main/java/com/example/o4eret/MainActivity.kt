package com.example.o4eret

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.o4eret.databinding.ActivityMainBinding
import com.example.o4eret.core.AppStore
import com.example.o4eret.ui.AcousticFragment
import com.example.o4eret.ui.BrowserFragment
import com.example.o4eret.ui.ChatFragment
import com.example.o4eret.ui.InfoFragment
import com.example.o4eret.ui.NetworkFragment
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private val appStore by lazy { AppStore(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        lifecycleScope.launch {
            appStore.ensureNodeId()
        }

        b.bottomNav.setOnItemSelectedListener(navListener)

        val initialSelection = savedInstanceState?.getInt(KEY_SELECTED_TAB) ?: R.id.nav_browser
        b.bottomNav.selectedItemId = initialSelection
    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        switchFragment(item.itemId)
    }

    private fun switchFragment(itemId: Int): Boolean {
        val tag = tabTags[itemId] ?: return false
        val fm = supportFragmentManager
        val fragment = fm.findFragmentByTag(tag) ?: createFragmentForItem(itemId)

        fm.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, fragment, tag)
            .commit()

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SELECTED_TAB, b.bottomNav.selectedItemId)
    }

    companion object {
        private const val KEY_SELECTED_TAB = "selected_tab"

        private const val TAG_NETWORK = "tab_network"
        private const val TAG_BROWSER = "tab_browser"
        private const val TAG_ACOUSTIC = "tab_acoustic"
        private const val TAG_CHAT = "tab_chat"
        private const val TAG_INFO = "tab_info"

        private val tabTags = mapOf(
            R.id.nav_network to TAG_NETWORK,
            R.id.nav_browser to TAG_BROWSER,
            R.id.nav_acoustic to TAG_ACOUSTIC,
            R.id.nav_chat to TAG_CHAT,
            R.id.nav_info to TAG_INFO
        )
    }
}
