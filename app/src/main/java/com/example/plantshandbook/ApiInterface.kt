package com.example.plantshandbook




import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {


@GET ("all.json")
    fun findHeroesList(): Single<CharacterResponse>
}