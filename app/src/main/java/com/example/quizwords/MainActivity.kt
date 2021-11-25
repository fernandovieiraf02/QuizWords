package com.example.quizwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val colorsListMock = listOf(
        "Azul",
        "Vermelho",
        "Branco",
        "Amarelo",
        "Cinza",
        "Verde",
        "Rosa"
    )

    private val inputList = mutableSetOf<String>()

    private val etInput by lazy { findViewById<AppCompatEditText>(R.id.etInput) }
    private val tvScore by lazy { findViewById<TextView>(R.id.tvScore) }
    private val tvTimer by lazy { findViewById<TextView>(R.id.tvTimer) }
    private val tvList by lazy { findViewById<TextView>(R.id.tvList) }
    private val btSend by lazy { findViewById<Button>(R.id.btSend) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        btSend?.setOnClickListener {
            val input = etInput.text?.toString() ?: ""
            findColor(input)
        }
    }

    private fun findColor(input: String) {
        colorsListMock.any { color -> color.equals(input, true) }
            .takeIf { it }
            ?.let {
                updateQuizList(input)
                refreshScore()
            }
    }

    private fun refreshScore() {
        tvScore.text = "${inputList.size}"
    }

    private fun updateQuizList(input: String) {
        inputList.add(input)
        val sb = StringBuilder()
        inputList.forEach { item ->
            sb.append(item)
            sb.append("\n")
        }
        tvList.text = sb
    }
}