package com.example.movieapp.MoviesList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MoviesList.MoviesViewModel
import com.example.movieapp.R
import com.example.movieapp.common.presentation.Event
import com.example.movieapp.common.presentation.MoviesAdapter
import com.example.movieapp.common.utils.getJsonFromAssets
import com.example.movieapp.databinding.FragmentMoviesListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private val binding get() = _binding!!
    private var _binding: FragmentMoviesListBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      setupUI()
        requestInitialMovieList()
    }


    private fun requestInitialMovieList() {
        viewModel.onEvent(MovieListEvent.RequestInitialListOfMovies)
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
    }

    private fun observeViewStateUpdates(adapter: MoviesAdapter) {
        viewModel.state.observe(viewLifecycleOwner) {
            updateScreenState(it, adapter)
        }
    }


    private fun updateScreenState(state: MovieListState, adapter: MoviesAdapter) {
        binding.progressBar.isVisible = state.loading
        adapter.submitList(state.movies.toList())
        handleNoMovies(state.noMoviesAvailable)
        handleFailures(state.failure)
    }

    private fun handleNoMovies(noMoviesAvailable: Boolean) {

    }

    private fun setupRecyclerView(moviesAdapter: MoviesAdapter) {
        binding.moviesRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
    }
    }

    private fun handleFailures(failure: Event<Throwable>?) {
        val unhandledFailure = failure?.getContentIfNotHandled() ?: return

        val fallbackMessage = getString(R.string.an_error_occurred)

        val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        }
        else {
            unhandledFailure.message!! }
        if (snackbarMessage.isNotEmpty()) {
            Snackbar.make(requireView(), snackbarMessage, Snackbar.LENGTH_SHORT).show()
        }
    }
    private fun createAdapter(): MoviesAdapter = MoviesAdapter()



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}