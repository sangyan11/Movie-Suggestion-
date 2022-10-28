package com.sangyan.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.sangyan.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    private lateinit var topAdapter : TopRatedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        prepareRecyclerView()
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieAdapter.setMovieList(movieList)
        })
        prepareTopRatedRecyclerView()
        viewModel.getTopRatedMovies()
        viewModel.observeTopRatedLiveData().observe(this , Observer {  movieList ->
           topAdapter.setTopRatedList(movieList)
        })








        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.movies -> {

                    }
                    R.id.tv_shows -> {


                    }
                    R.id.artist -> {

                    }
                }
                true
            }
        }
    }

    private fun prepareTopRatedRecyclerView() {
        topAdapter = TopRatedAdapter()
        binding.recyclerViewTopRated.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            adapter = topAdapter
        }
    }


    private fun prepareRecyclerView() {
            movieAdapter = MovieAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            adapter = movieAdapter
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }


}