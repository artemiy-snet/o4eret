package com.example.o4eret.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.o4eret.R

class BrowserFragment : Fragment() {

    private var running = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_browser, container, false)

        val tvBox = v.findViewById<TextView>(R.id.tvBox)
        val btn = v.findViewById<Button>(R.id.btnAction)

        fun render() {
            val status = if (running) "RUNNING" else "READY"
            val action = if (running) "STOP" else "OPEN"

            // ASCII-рамка “старого інтернету”
            val line = "+----------------------------+"
            val title = "|          BROWSER           |"
            val st = String.format("|  status: %-17s|", status)
            val act = String.format("|  action: %-17s|", action)

            tvBox.text = listOf(line, title, st, act, line).joinToString("\n")
            btn.text = action
        }

        btn.setOnClickListener {
            running = !running
            render()
        }

        render()
        return v
    }
}
