package com.sangyan.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()
    private var topRatedLiveData = MutableLiveData<List<Result>>()
  fun getPopularMovies() {
      RetrofitInstance.api.getPopularMovies("69d66957eebff9666ea46bd464773cf0").enqueue(object  :
          Callback<Movies> {
          override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
              if (response.body()!=null){
                  movieLiveData.value = response.body()!!.results
              }
              else{
                  return
              }
          }

          override fun onFailure(call: Call<Movies>, t: Throwable) {
              Log.d("TAG" , t.message.toString())
          }

      })
  }
    fun getTopRatedMovies() {
        RetrofitInstance.api.getTopRatedMovies("69d66957eebff9666ea46bd464773cf0").enqueue(object  :
            Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    topRatedLiveData.value = response.body()!!.results
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG" , t.message.toString())
            }

        })
    }
    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }
    fun observeTopRatedLiveData() : LiveData<List<Result>>{
        return topRatedLiveData
    }

}