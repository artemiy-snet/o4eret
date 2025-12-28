package com.example.o4eret.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.o4eret.R
import com.example.o4eret.core.NodeService

class NetworkFragment : Fragment(R.layout.fragment_network) {

    private var statusTextView: TextView? = null
    private var startButton: Button? = null
    private var stopButton: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusTextView = view.findViewById(R.id.node_status_text)
        startButton = view.findViewById(R.id.button_start_node)
        stopButton = view.findViewById(R.id.button_stop_node)

        startButton?.setOnClickListener {
            NodeService.startNode()
            updateUi()
        }

        stopButton?.setOnClickListener {
            NodeService.stopNode()
            updateUi()
        }

        updateUi()
    }

    private fun updateUi() {
        val isRunning = NodeService.isNodeRunning()
        statusTextView?.text = if (isRunning) {
            getString(R.string.node_running)
        } else {
            getString(R.string.node_stopped)
        }
        startButton?.isEnabled = !isRunning
        stopButton?.isEnabled = isRunning
    }
}
