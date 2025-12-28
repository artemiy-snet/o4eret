package com.example.o4eret.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.o4eret.R

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val handler = Handler(Looper.getMainLooper())
    private var liveText: TextView? = null
    private var seconds = 0

    private val liveRunnable = object : Runnable {
        override fun run() {
            liveText?.text = "LIVE: Chat t=${seconds}s"
            seconds++
            handler.postDelayed(this, 1000)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleText = view.findViewById<TextView>(R.id.titleText)
        val bodyText = view.findViewById<TextView>(R.id.bodyText)
        liveText = view.findViewById(R.id.liveText)

        titleText.text = "Chat Relay"
        bodyText.text = """
            +-----------------------------+
            | o4eret :: CHAT              |
            |                             |
            | Status: READY               |
            | MSG v1                      |
            | groups                      |
            | delivery                    |
            +-----------------------------+
        """.trimIndent()
    }

    override fun onResume() {
        super.onResume()
        seconds = 0
        liveText?.text = "LIVE: Chat t=${seconds}s"
        handler.post(liveRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(liveRunnable)
    }
}
