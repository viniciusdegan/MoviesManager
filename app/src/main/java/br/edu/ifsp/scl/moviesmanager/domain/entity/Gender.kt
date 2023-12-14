package br.edu.ifsp.scl.moviesmanager.domain.entity

enum class Gender(val description: String) {
    COMEDY("Comédia"), ACTION("AÇÃO") , ROMANCE("Romance"), HORROR("Terror");

    override fun toString(): String {
        return description
    }
}
