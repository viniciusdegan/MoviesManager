package br.edu.ifsp.scl.moviesmanager.presentation.component

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.edu.ifsp.scl.moviesmanager.R
import br.edu.ifsp.scl.moviesmanager.databinding.MovieAdapterBinding
import br.edu.ifsp.scl.moviesmanager.domain.entity.Movie

class MovieAdapter(private val movieOnClickListener: MovieListener) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(movieDiff) {
    private lateinit var context: Context

    inner class MovieViewHolder(private val binding: MovieAdapterBinding) : ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                movieName.text = movie.name
                movieProducer.text = movie.ownerProduction
                durationMovieYear.text = movie.getReleaseYearWithDuration()
                checkWatchedMovie.isChecked = movie.ifWatched
                movieRate.text = movie.getRatingFormatted()
                buttonDelete.setOnClickListener {
                    movieOnClickListener.onDeleteClick(movie)
                }
                checkWatchedMovie.setOnCheckedChangeListener { _, isChecked ->
                    movieOnClickListener.onWatchedClick(movie, isChecked)
                }
                root.setOnClickListener {
                    movieOnClickListener.onClick(movie)
                }
                root.setOnLongClickListener {
                    val popupMenu = PopupMenu(it.context, it)

                    popupMenu.menuInflater.inflate(R.menu.action_menu, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.movieChanges -> movieOnClickListener.onUpdateClick(
                                movie
                            )
                            R.id.movieRating -> movieOnClickListener.onRatingClick(
                                movie
                            )
                        }
                        true
                    }
                    popupMenu.show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieAdapterBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val movieDiff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}