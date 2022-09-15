package com.goliaeth.dictionaryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goliaeth.dictionaryapplication.databinding.ActivityWordDefinitionBinding

class WordDefinitionActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    private lateinit var binding: ActivityWordDefinitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordDefinitionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.definitionTextView.text = intent.getStringExtra(KEY)

        binding.backImageView.setOnClickListener { finish() }

    }
}