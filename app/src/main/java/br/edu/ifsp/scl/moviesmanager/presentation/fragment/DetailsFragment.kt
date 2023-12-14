package br.edu.ifsp.scl.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.edu.ifsp.scl.moviesmanager.databinding.DetailFragmentBinding

class DetailsFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        binding.apply {
            movieName.text = movie.name
            ownerMovie.text = movie.ownerProduction
            releaseMovie.text = movie.getReleaseYearWithDuration()
            movieRating.text = movie.getRatingFormatted()
            movie.rate?.let {
                ratingBar.rating = it.toFloat() / 2.0F
            }
            watchedMovie.isChecked = movie.ifWatched
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}