package com.example.compose.navigation.ui.list


interface MovieProvider {
    fun addOrUpdate(movie: Movie)
    fun add(all: List<Movie>)
    fun remove(movie: Movie)
    fun getMovies(): List<Movie>
    fun getMovieById(id: String): Movie? {
        return getMovies().firstOrNull { it.id == id }
    }
}

class MovieProviderImpl(private val movieGenerator: MovieGenerator) : MovieProvider {

    private val movies = mutableListOf<Movie>()

    init {
        val threeMovies = movieGenerator.generateTop3MovieListFromImdb()
        movies.addAll(threeMovies)
    }

    override fun addOrUpdate(movie: Movie) {
        remove(movie)
        movies.add(movie)
    }

    override fun add(all: List<Movie>) {
        movies.addAll(all)
    }

    override fun remove(movie: Movie) {
        val movieToBeRemoved = movies.firstOrNull { it.id == movie.id }
        if (movieToBeRemoved != null) {
            movies.remove(movieToBeRemoved)
        }
    }

    override fun getMovies(): List<Movie> {
        return movies.sortedBy { it.title }
    }
}