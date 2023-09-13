package com.example.homeworkrecyclerview

import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET ("/superhero-api/api/all.json")
    fun getSuperHero() : Single<MutableList<Superhero>>
}
    