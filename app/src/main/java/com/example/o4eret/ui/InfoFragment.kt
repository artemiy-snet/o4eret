package com.example.o4eret.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.o4eret.R

class InfoFragment : Fragment(R.layout.fragment_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleText = view.findViewById<TextView>(R.id.titleText)
        val bodyText = view.findViewById<TextView>(R.id.bodyText)

        titleText.text = "Diagnostic Info"
        bodyText.text = """
            +-----------------------------+
            | o4eret :: INFO              |
            |                             |
            | Status: READY               |
            | Build                       |
            | device                      |
            | diagnostics                 |
            +-----------------------------+
        """.trimIndent()
    }
}
