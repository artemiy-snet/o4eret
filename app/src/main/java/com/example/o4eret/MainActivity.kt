package com.example.o4eret

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusText = findViewById<TextView>(R.id.node_status_text)
        val startButton = findViewById<MaterialButton>(R.id.start_node_button)
        val stopButton = findViewById<MaterialButton>(R.id.stop_node_button)

        startButton.setOnClickListener {
            Toast.makeText(this, "Starting node...", Toast.LENGTH_SHORT).show()
            Log.d("MainActivity", "Start node tapped")

            statusText.text = "Node: RUNNING"
            startButton.isEnabled = false
            startButton.text = "Node running"
            stopButton.isEnabled = true
        }

        stopButton.setOnClickListener {
            Log.d("MainActivity", "Stop node tapped")

            statusText.text = "Node: STOPPED"
            startButton.isEnabled = true
            startButton.text = "Start node"
            stopButton.isEnabled = false
        }
    }
}
