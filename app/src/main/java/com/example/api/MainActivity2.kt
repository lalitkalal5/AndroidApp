package com.example.api

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import com.squareup.picasso.Picasso


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val product = intent.getParcelableExtra<Product>("product")
        val image = findViewById<ImageView>(R.id.vastuphoto)
        val title = findViewById<TextView>(R.id.tvtitle)
        val price = findViewById<TextView>(R.id.tvprice)
        val description = findViewById<TextView>(R.id.tvdesc)

if (product!= null){
    Picasso.get().load(product.image).into(image)
}



        if (product != null) {
            title.text = product.title
        }
        if (product != null) {
            price.text = product.price.toString()
        }
        if (product != null) {
            description.text = product.description
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}