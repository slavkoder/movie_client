package dev.slavko.movieclient.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.slavko.movieclient.BuildConfig
import dev.slavko.movieclient.api.MovieApiClient
import dev.slavko.movieclient.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    // can be replaced with DI
    private val apiClient = MovieApiClient.instance

    private val _latestMovies = MutableLiveData<List<Movie>>()
    val latestMovies: LiveData<List<Movie>>
        get() = _latestMovies

    val compositeDisposable = CompositeDisposable()

    fun getLatestMovies() {
        apiClient.getTopRatedMovies(BuildConfig.API_KEY)
            .doOnSubscribe { compositeDisposable.add(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _latestMovies.postValue(it.results) },
                { e -> Log.e("MainViewModel", "Something went wrong", e) })

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
