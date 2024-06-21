package com.example.api

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.api.ui.theme.ApiTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        hello2()
                }
            }
        }
    }
}

@Composable
fun lastvalafun (product :Product){
    val context = LocalContext.current
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
                .clickable {
                  val i = Intent(context,MainActivity2::class.java)
                    i.putExtra("product",product)
                    context.startActivity(i)
                },
//            elevation = 4.dp
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Image(
                    painter = rememberImagePainter(data = product.image),
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )
//                Spacer(modifier = Modifier.width(1.dp))
                Column {
                    Text(
                        text = product.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom =1.dp)
                    )
                    Text(
                        text = "$${product.price}",
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
@Composable
fun recycleviewvala(vastue : List<Product>){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
            )
    {
        items(vastue){
            vastu ->
            lastvalafun(product = vastu)
        }
    }
}

//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun hello2(){
//    var producttitles by remember { mutableStateOf("") }
    var Allthethings by remember { mutableStateOf<List<Product>>(emptyList()) }
    var errormsg by remember { mutableStateOf("") }




    val retrofitbuilder = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Apiinterface::class.java)

    val retrofitData = retrofitbuilder.getPosts()



    retrofitData.enqueue(object : Callback<List<Product>> {
        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
            if(response.isSuccessful){
                Allthethings = response.body()?: emptyList()
            }else{
                errormsg = "failed to load data"
            }
        }

        override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
//            producttitles = "Failed to load cateoory"
            Log.d("MainActivity", "onFailure: ${t.message}")
        }
    })
    if(errormsg.isNotEmpty()){
        Text(text = errormsg, modifier = Modifier.padding(10.dp))
    }else{
        recycleviewvala(vastue = Allthethings)
    }
//    Text(
//        text = producttitles ,
//        modifier = Modifier
//            .verticalScroll(ScrollState(0))
//            .padding(16.dp)
//    )

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiTheme {
        hello2()
    }
}