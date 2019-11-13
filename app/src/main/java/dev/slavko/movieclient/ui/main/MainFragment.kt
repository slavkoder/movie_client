package dev.slavko.movieclient.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.slavko.movieclient.R
import dev.slavko.movieclient.ui.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val listAdapter = MovieListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        movieList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            movieList.adapter = listAdapter
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.latestMovies.observe(this, Observer {
            Snackbar.make(main, "Received ${it.size} movies", Snackbar.LENGTH_SHORT).show()

            listAdapter.apply {
                setData(it)
                notifyDataSetChanged()
            }
        })

        viewModel.getLatestMovies()
    }

}
