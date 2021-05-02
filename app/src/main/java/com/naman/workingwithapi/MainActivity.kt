package com.naman.workingwithapi

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.red
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val right:ImageView=findViewById(R.id.right)
        val left:ImageView=findViewById(R.id.left)

        val volleyText: TextView =findViewById(R.id.text1)
        val retrofitText: TextView =findViewById(R.id.text2)

        volleyText.setTextColor(resources.getColor(R.color.purple_700))
        volleyText.setTypeface(volleyText.typeface,Typeface.BOLD)


        right.setOnClickListener {
         fetchMeme()
        }
        left.setOnClickListener {

        }
        retrofitText.setOnClickListener {
            val i: Intent =  Intent(this,retrofit::class.java)
            startActivity(i)
        }
        fetchMeme()
    }
    private fun fetchMeme(){
// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                val urL = response.getString("url")

                loadImage(urL)
            },
            { error ->
            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    private fun loadImage(url: String) {
        val image:ImageView=findViewById(R.id.image)

        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .into(image)

    }
}