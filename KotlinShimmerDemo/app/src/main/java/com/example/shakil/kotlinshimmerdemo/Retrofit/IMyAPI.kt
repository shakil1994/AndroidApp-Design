package com.example.shakil.kotlinshimmerdemo.Retrofit

import com.example.shakil.kotlinshimmerdemo.Model.Model
import io.reactivex.Observable
import retrofit2.http.GET

interface IMyAPI {
    @get:GET("photos")
    val data: Observable<List<Model>>
}