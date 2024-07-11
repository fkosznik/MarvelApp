package com.example.marvelapp1




import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var comicsAdapter: ComicsAdapter
    private val comicList = mutableListOf<Comic>()

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchComics()





        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        comicsAdapter = ComicsAdapter(comicList, this)
        recyclerView.adapter = comicsAdapter

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            performSearch()
        }


    }

    private fun fetchComics() {
        val apiKey ="7d1aef42a9737062300b5a4c7cf5569d"
        val timestamp = System.currentTimeMillis().toString()
        val hash = Utils.md5("${timestamp}3a26ffa93c727da1a13ae312d83abbda0852d41b$apiKey")

        MarvelApiClient.marvelApiService.getComics(apiKey, timestamp, hash)
            .enqueue(object : Callback<ComicsResponse> {
                override fun onResponse(call: Call<ComicsResponse>, response: Response<ComicsResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.data?.results?.let { comics ->
                            comicList.addAll(comics)
                            comicsAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<ComicsResponse>, t: Throwable) {

                }
            })
    }

    private fun performSearch() {
        val query = searchEditText.text.toString().trim()
        if (query.isNotEmpty()) {

            comicList.clear()
            comicsAdapter.notifyDataSetChanged()

            val apiKey ="7d1aef42a9737062300b5a4c7cf5569d"
            val timestamp = System.currentTimeMillis().toString()
            val hash = Utils.md5("${timestamp}3a26ffa93c727da1a13ae312d83abbda0852d41b$apiKey")

            MarvelApiClient.marvelApiService.searchComics(apiKey, timestamp, hash, query)
                .enqueue(object : Callback<ComicsResponse> {
                    override fun onResponse(call: Call<ComicsResponse>, response: Response<ComicsResponse>) {
                        if (response.isSuccessful) {
                            val comicsResponse = response.body()?.data?.results
                            if (comicsResponse.isNullOrEmpty()) {

                                Toast.makeText(this@MainActivity, "No comics found for '$query'", Toast.LENGTH_SHORT).show()
                            } else {

                                comicList.addAll(comicsResponse)
                                comicsAdapter.notifyDataSetChanged()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ComicsResponse>, t: Throwable) {

                        Toast.makeText(this@MainActivity, "Failed to search comics", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            Toast.makeText(this@MainActivity, "Please enter a search query", Toast.LENGTH_SHORT).show()
        }
    }


}
