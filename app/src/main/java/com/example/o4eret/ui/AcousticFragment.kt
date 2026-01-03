package com.example.o4eret.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.o4eret.R
import com.example.o4eret.ui.terminal.TerminalUi
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AcousticFragment : Fragment(R.layout.fragment_acoustic) {

    private lateinit var titleText: TextView
    private lateinit var panelText: TextView
    private lateinit var statusText: TextView
    private lateinit var actionButton: MaterialButton

    private var statusLine = READY
    private var actionLine = ACTION_READY
    private var stateJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            statusLine = it.getString(KEY_STATUS, statusLine) ?: statusLine
            actionLine = it.getString(KEY_ACTION, actionLine) ?: actionLine
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleText = view.findViewById(R.id.terminalTitle)
        panelText = view.findViewById(R.id.terminalPanel)
        statusText = view.findViewById(R.id.terminalStatus)
        actionButton = view.findViewById(R.id.terminalButton)

        titleText.text = "Acoustic"
        actionButton.text = ACTION_READY

        TerminalUi.setTerminalText(panelText)
        TerminalUi.setTerminalText(statusText)
        renderPanel()

        actionButton.setOnClickListener { handleAction() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_STATUS, statusLine)
        outState.putString(KEY_ACTION, actionLine)
    }

    private fun renderPanel() {
        val lines = listOf(
            "status: $statusLine",
            "action: $actionLine",
            "channel: ultrasonic",
            "codec: triplets",
            "zones: mapped"
        )
        panelText.text = TerminalUi.asciiBox("ACOUSTIC", lines, width = 30)
        statusText.text = "status: $statusLine"
    }

    private fun handleAction() {
        stateJob?.cancel()
        statusLine = WORKING
        actionLine = ACTION_WORKING
        renderPanel()

        stateJob = viewLifecycleOwner.lifecycleScope.launch {
            delay(700)
            statusLine = READY
            actionLine = ACTION_DONE
            renderPanel()
        }
    }

    companion object {
        private const val KEY_STATUS = "acoustic_status"
        private const val KEY_ACTION = "acoustic_action"
        private const val READY = "READY"
        private const val WORKING = "WORKING"
        private const val ACTION_READY = "SCAN"
        private const val ACTION_WORKING = "SCANNING"
        private const val ACTION_DONE = "DONE"
    }
}
