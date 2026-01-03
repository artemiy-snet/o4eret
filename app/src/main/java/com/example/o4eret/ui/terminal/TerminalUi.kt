package com.example.o4eret.ui.terminal

import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.o4eret.R

object TerminalUi {

    fun asciiBox(title: String, lines: List<String>, width: Int = 26): String {
        val longestLine = lines.maxOfOrNull { it.length } ?: 0
        val innerWidth = maxOf(width, title.length, longestLine)
        val horizontal = "+${"-".repeat(innerWidth)}+"

        fun center(text: String): String {
            val trimmed = text.take(innerWidth)
            val totalPadding = innerWidth - trimmed.length
            val startPadding = totalPadding / 2
            val endPadding = totalPadding - startPadding
            return "|${" ".repeat(startPadding)}$trimmed${" ".repeat(endPadding)}|"
        }

        fun padLine(text: String): String {
            val trimmed = text.take(innerWidth)
            val padding = innerWidth - trimmed.length
            return "|$trimmed${" ".repeat(padding)}|"
        }

        val body = lines.map { padLine(it) }
        val framed = buildList {
            add(horizontal)
            add(center(title))
            add(horizontal)
            addAll(body)
            add(horizontal)
        }
        return framed.joinToString("\n")
    }

    fun setTerminalText(tv: TextView) {
        tv.typeface = Typeface.MONOSPACE
        tv.setTextColor(ContextCompat.getColor(tv.context, R.color.o4_green_bright))
        tv.setLineSpacing(tv.resources.displayMetrics.density * 2f, 1f)
        tv.includeFontPadding = false
    }
}
