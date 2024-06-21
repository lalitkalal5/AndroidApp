//package com.example.api
//
//import retrofit2.http.GET
//import retrofit2.Call
//
//
//interface Apiinterface {
//      @GET
//      fun getproductdata() :Call<product>
//}

// ApiService.kt
package com.example.api

import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface{
      @GET("products")
      fun getPosts(): Call<List<Product>>
}
