package com.example.o4eret

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.start_node_button).setOnClickListener {
            Toast.makeText(this, "Starting node...", Toast.LENGTH_SHORT).show()
            Log.d("MainActivity", "Start node tapped")
        }
    }
}
