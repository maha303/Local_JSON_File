package com.example.local_json_file

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain:RecyclerView
     var items= arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        rvMain=findViewById(R.id.rvMain)

        var json:String? = null
        try {
            val inputStream:InputStream=assets.open("data.json")
            json=inputStream.bufferedReader().use { it.readText() }
            val jsonA=JSONArray(json)
            for(i in 0 until jsonA.length()){
                val jsonO=jsonA.getJSONObject((i))
                items.add(jsonO.getString("url"))
            }
            rvMain.adapter=RVAdapter(items)
            rvMain.layoutManager=LinearLayoutManager(this)
        }catch (e:IOException){

        }
    }
}