package br.edu.ifsp.scl.moviesmanager.presentation.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import br.edu.ifsp.scl.moviesmanager.databinding.RateBinding
import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie

class Rate(
    context: Context,
    private val movie: Movie,
    private val sendRateValue: (Movie, Int) -> Unit
) : Dialog(context) {
    private val binding: RateBinding by lazy {
        RateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        binding.apply {
            cancelButton.setOnClickListener { dismiss() }
            sendButton.setOnClickListener {
                sendRateValue(movie, ratingScale.rating.toInt())
                dismiss()
            }
        }
    }
}