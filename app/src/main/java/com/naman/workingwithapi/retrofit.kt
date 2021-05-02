package com.naman.workingwithapi

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//4b58098ea2f2452f9df96ea40178e18f
class retrofit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        val right: ImageView =findViewById(R.id.right)
        val left: ImageView =findViewById(R.id.left)


        val volleyText: TextView =findViewById(R.id.text1)
        val retrofitText: TextView =findViewById(R.id.text2)

        retrofitText.setTextColor(resources.getColor(R.color.purple_700))
        retrofitText.setTypeface(volleyText.typeface, Typeface.BOLD)

        volleyText.setOnClickListener {
            val i:Intent =  Intent(this,MainActivity::class.java)
            startActivity(i)

        }


        right.setOnClickListener {
            getArticle()
        }
        left.setOnClickListener {

        }


        getArticle()
    }


    private fun getArticle() {
        val news: Call<News> = NewsService.newsInstance.getHeadLines("in",1)
        news.enqueue(object: Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val num=(1..10).random()
                val articleList= response.body()?.articles
                val url= articleList?.get(num)?.urlToImage
                var head: TextView =findViewById(R.id.head)
                head.text=articleList?.get(num)?.title





                val image:ImageView=findViewById(R.id.image)

                Glide
                    .with(applicationContext)
                    .load(url)
                    .centerCrop()
                    .into(image)


            }

            override fun onFailure(call: Call<News>, t: Throwable) {
            }
        })
    }

}