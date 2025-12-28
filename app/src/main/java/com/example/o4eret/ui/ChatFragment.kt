package com.example.o4eret.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.o4eret.R

class ChatFragment : Fragment(R.layout.fragment_chat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleText = view.findViewById<TextView>(R.id.titleText)
        val bodyText = view.findViewById<TextView>(R.id.bodyText)

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
}
