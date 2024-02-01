package com.example.plantshandbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantshandbook.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myAdapter: RecyclerViewAdapter
        val heroesData = ApiClient.client.create(ApiInterface::class.java)
            heroesData.findHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        val items = it
                        myAdapter = RecyclerViewAdapter(items as ArrayList<Character>)
                        binding.rcView.adapter = myAdapter
                    }



                }, {})


       binding.rcView.layoutManager = GridLayoutManager(this, 4)

    }






}


typealias CharacterResponse= ArrayList<Character>

data class Character(
    val id: Long,
    val name: String,
    val slug: String,
    val images: Images,
)

data class Images(
    val xs: String,
    val sm: String,
    val md: String,
    val lg: String,
)


