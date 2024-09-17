package com.example.compose.navigation.ui.list

import com.example.compose.navigation.ui.detail.FullName
import com.example.compose.navigation.ui.detail.MovieGenre

object MovieGenerator {

    fun generateTop3MovieListFromImdb(): List<Movie> {
        return listOf(
            Movie(
                id = "1",
                title = "The Shawshank Redemption",
                yearReleased = 1994,
                genre = MovieGenre.Drama,
                director = FullName("Frank", "Darabont"),
                actors = listOf(FullName("Tim", "Robbins"), FullName("Morgan", "Freeman")),
                producers = listOf(FullName("Niki", "Marvin"))
            ),
            Movie(
                id = "2",
                title = "The Godfather",
                yearReleased = 1972,
                genre = MovieGenre.Crime,
                director = FullName("Francis Ford", "Coppola"),
                actors = listOf(FullName("Marlon", "Brando"), FullName("Al", "Pacino")),
                producers = listOf(FullName("Albert", "Ruddy"))
            ),
            Movie(
                id = "3",
                title = "The Dark Knight",
                yearReleased = 2008,
                genre = MovieGenre.Action,
                director = FullName("Christopher", "Nolan"),
                actors = listOf(FullName("Christian", "Bale"), FullName("Heath", "Ledger")),
                producers = listOf(FullName("Emma", "Thomas"))
            )
        )
    }

    fun generateTop20MovieListFromImdb(): List<Movie> {
        return listOf(
            Movie(
                id = "1",
                title = "The Shawshank Redemption",
                yearReleased = 1994,
                genre = MovieGenre.Drama,
                director = FullName("Frank", "Darabont"),
                actors = listOf(FullName("Tim", "Robbins"), FullName("Morgan", "Freeman")),
                producers = listOf(FullName("Niki", "Marvin"))
            ),
            Movie(
                id = "2",
                title = "The Godfather",
                yearReleased = 1972,
                genre = MovieGenre.Crime,
                director = FullName("Francis Ford", "Coppola"),
                actors = listOf(FullName("Marlon", "Brando"), FullName("Al", "Pacino")),
                producers = listOf(FullName("Albert", "Ruddy"))
            ),
            Movie(
                id = "3",
                title = "The Dark Knight",
                yearReleased = 2008,
                genre = MovieGenre.Action,
                director = FullName("Christopher", "Nolan"),
                actors = listOf(FullName("Christian", "Bale"), FullName("Heath", "Ledger")),
                producers = listOf(FullName("Emma", "Thomas"))
            ),
            Movie(
                id = "4",
                title = "The Godfather: Part II",
                yearReleased = 1974,
                genre = MovieGenre.Crime,
                director = FullName("Francis Ford", "Coppola"),
                actors = listOf(FullName("Al", "Pacino"), FullName("Robert", "De Niro")),
                producers = listOf(FullName("Francis Ford", "Coppola"))
            ),
            Movie(
                id = "5",
                title = "The Lord of the Rings: The Return of the King",
                yearReleased = 2003,
                genre = MovieGenre.Adventure,
                director = FullName("Peter", "Jackson"),
                actors = listOf(FullName("Elijah", "Wood"), FullName("Viggo", "Mortensen")),
                producers = listOf(FullName("Barrie M.", "Osborne"))
            ),
            Movie(
                id = "6",
                title = "Pulp Fiction",
                yearReleased = 1994,
                genre = MovieGenre.Crime,
                director = FullName("Quentin", "Tarantino"),
                actors = listOf(FullName("John", "Travolta"), FullName("Uma", "Thurman")),
                producers = listOf(FullName("Lawrence", "Bender"))
            ),
            Movie(
                id = "7",
                title = "Schindler's List",
                yearReleased = 1993,
                genre = MovieGenre.Biography,
                director = FullName("Steven", "Spielberg"),
                actors = listOf(FullName("Liam", "Neeson"), FullName("Ben", "Kingsley")),
                producers = listOf(FullName("Steven", "Spielberg"))
            ),
            Movie(
                id = "8",
                title = "The Lord of the Rings: The Fellowship of the Ring",
                yearReleased = 2001,
                genre = MovieGenre.Adventure,
                director = FullName("Peter", "Jackson"),
                actors = listOf(FullName("Elijah", "Wood"), FullName("Ian", "McKellen")),
                producers = listOf(FullName("Barrie M.", "Osborne"))
            ),
            Movie(
                id = "9",
                title = "Forrest Gump",
                yearReleased = 1994,
                genre = MovieGenre.Drama,
                director = FullName("Robert", "Zemeckis"),
                actors = listOf(FullName("Tom", "Hanks"), FullName("Robin", "Wright")),
                producers = listOf(FullName("Wendy", "Finerman"))
            ),
            Movie(
                id = "10",
                title = "Inception",
                yearReleased = 2010,
                genre = MovieGenre.Action,
                director = FullName("Christopher", "Nolan"),
                actors = listOf(FullName("Leonardo", "DiCaprio"), FullName("Joseph", "Gordon-Levitt")),
                producers = listOf(FullName("Emma", "Thomas"))
            ),
            Movie(id = "11",
                title = "The Lord of the Rings: The Two Towers",
                yearReleased = 2002,
                genre = MovieGenre.Adventure,
                director = FullName("Peter", "Jackson"),
                actors = listOf(FullName("Elijah", "Wood"), FullName("Ian", "McKellen")),
                producers = listOf(FullName("Barrie M.", "Osborne"))
            ),
            Movie(id = "12",
                title = "Star Wars: Episode V - The Empire Strikes Back",
                yearReleased = 1980,
                genre = MovieGenre.Action,
                director = FullName("Irvin", "Kershner"),
                actors = listOf(FullName("Mark", "Hamill"), FullName("Harrison", "Ford")),
                producers = listOf(FullName("Gary", "Kurtz"))
            ),
            Movie(id = "13",
                title = "Fight Club",
                yearReleased = 1999,
                genre = MovieGenre.Drama,
                director = FullName("David", "Fincher"),
                actors = listOf(FullName("Brad", "Pitt"), FullName("Edward", "Norton")),
                producers = listOf(FullName("Art", "Linson"))
            ),
            Movie(id = "14",
                title = "The Good, the Bad and the Ugly",
                yearReleased = 1966,
                genre = MovieGenre.Western,
                director = FullName("Sergio", "Leone"),
                actors = listOf(FullName("Clint", "Eastwood"), FullName("Eli", "Wallach")),
                producers = listOf(FullName("Alberto", "Grimaldi"))
            ),
            Movie(id = "15",
                title = "Se7en",
                yearReleased = 1995,
                genre = MovieGenre.Crime,
                director = FullName("David", "Fincher"),
                actors = listOf(FullName("Morgan", "Freeman"), FullName("Brad", "Pitt")),
                producers = listOf(FullName("Arnold", "Kopelson"))
            ),
            Movie(id = "16",
                title = "Interstellar",
                yearReleased = 2014,
                genre = MovieGenre.Adventure,
                director = FullName("Christopher", "Nolan"),
                actors = listOf(FullName("Matthew", "McConaughey"), FullName("Anne", "Hathaway")),
                producers = listOf(FullName("Emma", "Thomas"))
            ),
            Movie(id = "17",
                title = "Good Fellas",
                yearReleased = 1990,
                genre = MovieGenre.Biography,
                director = FullName("Martin", "Scorsese"),
                actors = listOf(FullName("Robert", "De Niro"), FullName("Ray", "Liotta")),
                producers = listOf(FullName("Irwin", "Winkler"))
            ),
            Movie(
                id = "18",
                title = "The Matrix",
                yearReleased = 1999,
                genre = MovieGenre.Sci_Fi,
                director = FullName("Lana", "Wachowski"),
                actors = listOf(FullName("Keanu", "Reeves"), FullName("Carrie-Anne", "Moss")),
                producers = listOf(FullName("Joel", "Silver"))
                ),
            Movie(
                id = "19",
                title = "The Matrix Reloaded",
                yearReleased = 2003,
                genre = MovieGenre.Sci_Fi,
                director = FullName("Lana", "Wachowski"),
                actors = listOf(FullName("Keanu", "Reeves"), FullName("Carrie-Anne", "Moss")),
                producers = listOf(FullName("Joel", "Silver"))
            ),
            Movie(
                id = "20",
                title = "The Matrix Revolutions",
                yearReleased = 2003,
                genre = MovieGenre.Sci_Fi,
                director = FullName("Lana", "Wachowski"),
                actors = listOf(FullName("Keanu", "Reeves"), FullName("Carrie-Anne", "Moss")),
                producers = listOf(FullName("Joel", "Silver"))
            )
        )
    }
}