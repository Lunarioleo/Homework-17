package com.example.plantshandbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantshandbook.databinding.ActivityMainBinding

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val dataModel: DataModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myAdapter: RecyclerViewAdapter

        dataModel
        val heroesData = ApiClient.client.create(ApiInterface::class.java)
            heroesData.findHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        val items = it
                        myAdapter = RecyclerViewAdapter(items as ArrayList<Character>) {
                            dataModel.message.value = it
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment, FragmentDetails())
                                .commit()
                            binding.rcView.isClickable = false  





                        }
                        binding.rcView.adapter = myAdapter
                    }



                }, {})

       binding.rcView.layoutManager = GridLayoutManager(this, 4)

    }
}

class  Helper : FragmentActivity(){
    companion object{
        fun openFragment(){
            val temp  =  FragmentActivity()
            temp.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, FragmentDetails())
                .commit()
        }
    }
}



typealias CharacterResponse= ArrayList<Character>

data class Character(
    val id: Long,
    val name: String,
    val biography: Biography,
    val images: Images
)

data class Biography(
    val fullName: String,
    val alterEgos: String,
    val placeOfBirth: String,
    val firstAppearance: String,
)

data class Images(
    val xs: String,
    val sm: String,
    val md: String,
    val lg: String,
)


