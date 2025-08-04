package com.example.alegerealeatorie

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnSave: Button
    private lateinit var btnRandom: Button
    private val PREFS_NAME = "RandomPickerPrefs"
    private val KEY_LIST = "SavedList"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        btnSave = findViewById(R.id.btnSave)
        btnRandom = findViewById(R.id.btnRandom)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editText.setText(prefs.getString(KEY_LIST, ""))

        btnSave.setOnClickListener {
            val text = editText.text.toString()
            prefs.edit().putString(KEY_LIST, text).apply()
            Toast.makeText(this, "Lista a fost salvată", Toast.LENGTH_SHORT).show()
        }

        btnRandom.setOnClickListener {
            val text = editText.text.toString()
            val lines = text.split("\n".toRegex()).map { it.trim() }.filter { it.isNotEmpty() }
            if (lines.isNotEmpty()) {
                val randomLine = lines.random()
                Toast.makeText(this, randomLine, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Lista este goală", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
