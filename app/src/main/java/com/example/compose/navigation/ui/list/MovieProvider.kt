package com.example.compose.navigation.ui.list


interface MovieListProvider {
    fun add(movie: Movie)
    fun add(all: List<Movie>)
    fun remove(movie: Movie)
    fun update(movie: Movie)
    fun getMovies(): List<Movie>
    fun getMovieById(id: String): Movie? {
        return getMovies().firstOrNull { it.id == id }
    }
}

class MovieListProviderImpl(private val movieGenerator: MovieGenerator) : MovieListProvider {

    private val movies = mutableListOf<Movie>()

    init {
        movies.addAll(movieGenerator.generateTop3MovieListFromImdb())
    }

    override fun add(movie: Movie) {
        movies.add(movie)
    }

    override fun add(all: List<Movie>) {
        movies.addAll(all)
    }

    override fun remove(movie: Movie) {
        movies.remove(movie)
    }

    override fun update(movie: Movie) {
        val movieToBeRemoved = movies.firstOrNull { it.id == movie.id }
        if (movieToBeRemoved != null) {
            movies.remove(movieToBeRemoved)
        }
        movies.add(movie)
    }

    override fun getMovies(): List<Movie> {
        return movies
    }
}