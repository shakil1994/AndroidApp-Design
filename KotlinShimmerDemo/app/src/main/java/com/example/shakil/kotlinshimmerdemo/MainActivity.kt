package com.example.shakil.kotlinshimmerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.shakil.kotlinshimmerdemo.Adapter.MyAdapter
import com.example.shakil.kotlinshimmerdemo.Model.Model
import com.example.shakil.kotlinshimmerdemo.Retrofit.IMyAPI
import com.example.shakil.kotlinshimmerdemo.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myAPI: IMyAPI
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(IMyAPI::class.java)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData() {
        shimmerLayout.startShimmerAnimation()
        compositeDisposable.add(myAPI.data.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { photos -> displayData(photos) })
    }

    private fun displayData(photos: List<Model>?) {
        val adapter = MyAdapter(this, photos!!)
        recycler_view.adapter = adapter
        shimmerLayout.stopShimmerAnimation()
        shimmerLayout.visibility = View.GONE
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}
