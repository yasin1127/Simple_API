package com.example.apisample

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.apisample.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val url: String="https://meme-api.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {


        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getMeme()

        binding.btn.setOnClickListener{
            getMeme()
        }
    }
        private fun getMeme(){

// ...

// Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)


// Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    var responseObject=JSONObject(response)
                    binding.MemeTitle.text=responseObject.getString("title")
                    binding.MemeAuthor.text=responseObject.getString("author")

                    Glide.with(this@MainActivity).load(responseObject.getString("url")).into(binding.MemeImg)
                    Log.e(
                     "response from","getMeme : ${ response.toString()}"
                    )
                },
                {
                    error-> Toast.makeText(this@MainActivity, error.toString(), Toast.LENGTH_LONG).show()
                })

// Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

    }
