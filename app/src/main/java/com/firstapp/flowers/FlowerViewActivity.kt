package com.firstapp.flowers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_flower_view.*

class FlowerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_view)
        init()

    }
    private fun init(){
        flowerRes.layoutManager = LinearLayoutManager(this)
        val flowers = ArrayList<Flowers>()
        for (i in 0 until 15){
            flowers.add(Flowers("https://upload.wikimedia.org/wikipedia/commons/4/4e/Bridal_pink_-_morwell_rose_garden.jpg",
            "rose","A rose is a woody perennial flowering plant of the genus Rosa, in the family Rosaceae, or the flower it bears.",
            "20$"))

        }
        flowerRes.adapter = FlowerAdapter(flowers)
    }
}