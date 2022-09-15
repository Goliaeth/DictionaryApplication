package com.goliaeth.dictionaryapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.goliaeth.dictionaryapplication.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val queue = Volley.newRequestQueue(this)
        binding.findButton.setOnClickListener {

            val url = getUrl()
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    try {
                        extractDefinitionFromJson(response)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })

            queue.add(stringRequest)
        }
    }

    private fun getUrl(): String {
        val word = binding.wordEditText.text
        val apiKey = "60ef21cb-ed0b-4dd4-b8e9-8e6314833b19"
        val url =
            "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
        return  url
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDef = firstIndex.getJSONArray("shortdef")
        val firstShortDef = getShortDef.get(0)

        val intent = Intent(this,WordDefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDef.toString())
        startActivity(intent)
    }
}