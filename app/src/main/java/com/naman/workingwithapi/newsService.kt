package com.naman.workingwithapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BaseUrl="https://newsapi.org/"
const val apiKey="4b58098ea2f2452f9df96ea40178e18f"
interface newsInterface {

    @GET("v2/top-headlines?apiKey=$apiKey")
    fun getHeadLines(@Query("country") country: String, @Query("page")page:Int):Call<News>



}

object NewsService{
    val newsInstance:newsInterface
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(newsInterface::class.java)
    }
}