package com.example.shakil.androidshimmerdemo.Retrofit;

import com.example.shakil.androidshimmerdemo.Model.Model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyApi {
    @GET("photos")
    Observable<List<Model>> getDate();
}
