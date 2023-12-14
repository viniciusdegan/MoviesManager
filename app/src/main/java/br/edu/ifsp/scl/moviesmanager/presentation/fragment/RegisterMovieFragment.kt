package br.edu.ifsp.scl.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.edu.ifsp.scl.moviesmanager.databinding.FragmentRegisterBinding
import br.edu.ifsp.scl.moviesmanager.domain.entity.Gender
import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie
import br.edu.ifsp.scl.moviesmanager.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates
import android.R
class RegisterMovieFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val args: RegisterMovieFragmentArgs by navArgs()
    private var isEdit by Delegates.notNull<Boolean>()
    private val movieViewModel: MovieViewModel by viewModel<MovieViewModel>()
    private var isAllFieldsChecked: MutableLiveData<Boolean> = MutableLiveData(false)
    private lateinit var genderSelected: Gender
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isEdit = args.movie != null
        val movie = args.movie
        if (movie != null) {
            populateForm(movie)
        }
        setupSpinnerGender()
        setupButtonSave()
    }

    private fun populateForm(movie: Movie) {
        binding.apply {
            titleMovie.editText?.setText(movie.name)
            titleMovie.isEnabled = false
            ownerMovie.editText?.setText(movie.ownerProduction)
            releaseMovie.editText?.setText(movie.releaseYear.toString())
            movieDuration.editText?.setText(movie.duration.toString())
            checkWatched.isChecked = movie.ifWatched
            movie.rate?.let {
                ratingBar.rating = it.toFloat() / 2.0F
            }
        }
    }

    private fun setupSpinnerGender() {
        val spinnerAdapter: ArrayAdapter<Gender> = ArrayAdapter<Gender>(
            requireContext(), R.layout.simple_spinner_item, Gender.entries.toTypedArray()
        )
        spinnerAdapter.setDropDownViewResource(
            R.layout.simple_spinner_dropdown_item
        )
        binding.spinnerGender.apply {
            onItemSelectedListener = this@RegisterMovieFragment
            adapter = spinnerAdapter
        }
    }

    private fun setupButtonSave() {
        binding.saveButton.setOnClickListener {
            if (checkAllFields()) {
                val movie = Movie(
                    name = binding.titleMovie.editText?.text.toString(),
                    releaseYear = binding.releaseMovie.editText?.text.toString().toLong(),
                    ownerProduction = binding.ownerMovie.editText?.text.toString(),
                    duration = binding.movieDuration.editText?.text.toString().toLong(),
                    gender = genderSelected,
                    ifWatched = binding.checkWatched.isChecked,
                    rate = if (binding.ratingBar.rating != 0.0F) (binding.ratingBar.rating * 2).toInt() else null
                )
                if (isEdit) {
                    movieViewModel.updateMovie(movie)
                    findNavController().popBackStack()
                } else {
                    movieViewModel.createMovie(movie)
                    findNavController().popBackStack()
                }
            } else return@setOnClickListener
        }
    }

    private fun checkAllFields(): Boolean {
        return when {
            binding.titleMovie.editText?.text.isNullOrBlank() -> {
                binding.titleMovie.editText?.error = "Obrigatório inserir o nome"
                binding.titleMovie.editText?.requestFocus()
                false
            }

            binding.ownerMovie.editText?.text.isNullOrBlank() -> {
                binding.ownerMovie.editText?.error = "Obrigatório inserir a produtora"
                binding.ownerMovie.editText?.requestFocus()
                false
            }

            binding.releaseMovie.editText?.text.isNullOrBlank() -> {
                binding.releaseMovie.editText?.error = "Obrigatório inserir o ano"
                binding.releaseMovie.editText?.requestFocus()
                false
            }

            binding.movieDuration.editText?.text.isNullOrBlank() -> {
                binding.movieDuration.editText?.error = "Obrigatório inserir a duração"
                binding.movieDuration.editText?.requestFocus()
                false
            }

            else -> true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        genderSelected = Gender.entries.toTypedArray()[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}