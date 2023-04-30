package com.example.yandex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData(recyclerView)
    }

    private fun fetchData(recyclerView: RecyclerView) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/users"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                val userList = Gson().fromJson(response.toString(), Array<User>::class.java).toList()
                recyclerView.adapter = UserAdapter(userList)
            },
            {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
            })

        queue.add(request)
    }
}