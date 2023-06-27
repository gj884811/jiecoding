package com.example.frogapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import api.FrogAPIService
import com.coderus.codingchallenge.rocketlaunchlist.MovieListAdapter
import com.example.frogapplication.data.repository.MovieViewModel
import com.example.frogapplication.databinding.FragmentListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repository.MovieRepository
import repository.MovieViewModelFactory


class MoveListFragment : Fragment() {
    lateinit var movieViewModel: MovieViewModel
    private lateinit var factory: MovieViewModelFactory
    private val title = "love"
    private val year = ">2000"
    private val apiKey = "ca81a8a5"

   private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = FrogAPIService()
        val repository = MovieRepository(api)
        factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        _binding?.progress?.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            movieViewModel.getMovieList(apiKey,title,year)
        }
        movieViewModel.movieList.observe(viewLifecycleOwner, Observer { movie ->
            _binding?.progress?.visibility = View.GONE
            _binding?.recyclerviewList.also {
                it?.layoutManager = LinearLayoutManager(requireContext())
                it?.setHasFixedSize(true)
                // Filter movies based on year > 2000
                val filteredMovies = movie.Search.filter { it.Year.toIntOrNull() ?: 0 > 2000 }
                // Sort movies in descending order
                val sortedMovies = filteredMovies.sortedByDescending { it.Year.toIntOrNull() }
                it?.adapter = MovieListAdapter(sortedMovies)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
