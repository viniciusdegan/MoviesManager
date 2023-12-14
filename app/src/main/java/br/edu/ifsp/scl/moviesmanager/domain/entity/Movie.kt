package br.edu.ifsp.scl.moviesmanager.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long = 0,
    val name: String,
    val releaseYear: Long,
    val ownerProduction: String,
    val duration: Long,
    val gender: Gender,
    val ifWatched: Boolean = false,
    val rate: Int? = null
) : Parcelable {
    fun getReleaseYearWithDuration() = "$releaseYear - ${duration}min"
    fun getRatingFormatted() = if (rate == null) "N/A" else "$rate/10"
}