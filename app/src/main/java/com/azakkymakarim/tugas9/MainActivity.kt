package com.azakkymakarim.tugas9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.azakkymakarim.tugas9.api.APIConfig
import com.azakkymakarim.tugas9.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APIConfig.getService().getBreeds().enqueue(object : Callback<ResponseCat> {
            override fun onResponse(call: Call<ResponseCat>, response: Response<ResponseCat>) {
                if (response.isSuccessful) {
                    val responseCat = response.body()
                    val dataItem = responseCat?.data
                    val adapterCat = AdapterCat(dataItem)
                    binding.rvCat.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapterCat.notifyDataSetChanged()
                        adapter = adapterCat
                    }
                }
            }

            override fun onFailure(call: Call<ResponseCat>, t: Throwable) {
                Toast.makeText(applicationContext, "Data not Found", Toast.LENGTH_SHORT).show()
            }

        })
    }
}