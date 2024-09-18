package com.example.compose.navigation.ui.list

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.detail.MovieGenre
import com.example.compose.navigation.ui.director.Director
import com.example.compose.navigation.ui.producer.Producer

object MovieGenerator {

    fun generateTop3MovieListFromImdb(): List<Movie> {
        return listOf(
            Movie(
                id = "1",
                title = "The Shawshank Redemption",
                yearReleased = 1994,
                genre = MovieGenre.Drama,
                director = Director("Frank", "Darabont"),
                actors = listOf(Actor("Tim", "Robbins"), Actor("Morgan", "Freeman")),
                producers = listOf(Producer("Niki", "Marvin"))
            ),
            Movie(
                id = "2",
                title = "The Godfather",
                yearReleased = 1972,
                genre = MovieGenre.Crime,
                director = Director("Francis Ford", "Coppola"),
                actors = listOf(Actor("Marlon", "Brando"), Actor("Al", "Pacino")),
                producers = listOf(Producer("Albert", "Ruddy"))
            ),
            Movie(
                id = "3",
                title = "The Dark Knight",
                yearReleased = 2008,
                genre = MovieGenre.Action,
                director = Director("Christopher", "Nolan"),
                actors = listOf(Actor("Christian", "Bale"), Actor("Heath", "Ledger")),
                producers = listOf(Producer("Emma", "Thomas"))
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
                director = Director("Frank", "Darabont"),
                actors = listOf(Actor("Tim", "Robbins"), Actor("Morgan", "Freeman")),
                producers = listOf(Producer("Niki", "Marvin"))
            ),
            Movie(
                id = "2",
                title = "The Godfather",
                yearReleased = 1972,
                genre = MovieGenre.Crime,
                director = Director("Francis Ford", "Coppola"),
                actors = listOf(Actor("Marlon", "Brando"), Actor("Al", "Pacino")),
                producers = listOf(Producer("Albert", "Ruddy"))
            ),
            Movie(
                id = "3",
                title = "The Dark Knight",
                yearReleased = 2008,
                genre = MovieGenre.Action,
                director = Director("Christopher", "Nolan"),
                actors = listOf(Actor("Christian", "Bale"), Actor("Heath", "Ledger")),
                producers = listOf(Producer("Emma", "Thomas"))
            ),
            Movie(
                id = "4",
                title = "The Godfather: Part II",
                yearReleased = 1974,
                genre = MovieGenre.Crime,
                director = Director("Francis Ford", "Coppola"),
                actors = listOf(Actor("Al", "Pacino"), Actor("Robert", "De Niro")),
                producers = listOf(Producer("Francis Ford", "Coppola"))
            ),
            Movie(
                id = "5",
                title = "The Lord of the Rings: The Return of the King",
                yearReleased = 2003,
                genre = MovieGenre.Adventure,
                director = Director("Peter", "Jackson"),
                actors = listOf(Actor("Elijah", "Wood"), Actor("Viggo", "Mortensen")),
                producers = listOf(Producer("Barrie M.", "Osborne"))
            ),
            Movie(
                id = "6",
                title = "Pulp Fiction",
                yearReleased = 1994,
                genre = MovieGenre.Crime,
                director = Director("Quentin", "Tarantino"),
                actors = listOf(Actor("John", "Travolta"), Actor("Uma", "Thurman")),
                producers = listOf(Producer("Lawrence", "Bender"))
            ),
            Movie(
                id = "7",
                title = "Schindler's List",
                yearReleased = 1993,
                genre = MovieGenre.Biography,
                director = Director("Steven", "Spielberg"),
                actors = listOf(Actor("Liam", "Neeson"), Actor("Ben", "Kingsley")),
                producers = listOf(Producer("Steven", "Spielberg"))
            ),
            Movie(
                id = "8",
                title = "The Lord of the Rings: The Fellowship of the Ring",
                yearReleased = 2001,
                genre = MovieGenre.Adventure,
                director = Director("Peter", "Jackson"),
                actors = listOf(Actor("Elijah", "Wood"), Actor("Ian", "McKellen")),
                producers = listOf(Producer("Barrie M.", "Osborne"))
            ),
            Movie(
                id = "9",
                title = "Forrest Gump",
                yearReleased = 1994,
                genre = MovieGenre.Drama,
                director = Director("Robert", "Zemeckis"),
                actors = listOf(Actor("Tom", "Hanks"), Actor("Robin", "Wright")),
                producers = listOf(Producer("Wendy", "Finerman"))
            ),
            Movie(
                id = "10",
                title = "Inception",
                yearReleased = 2010,
                genre = MovieGenre.Action,
                director = Director("Christopher", "Nolan"),
                actors = listOf(Actor("Leonardo", "DiCaprio"), Actor("Joseph", "Gordon-Levitt")),
                producers = listOf(Producer("Emma", "Thomas"))
            ),
            Movie(id = "11",
                title = "The Lord of the Rings: The Two Towers",
                yearReleased = 2002,
                genre = MovieGenre.Adventure,
                director = Director("Peter", "Jackson"),
                actors = listOf(Actor("Elijah", "Wood"), Actor("Ian", "McKellen")),
                producers = listOf(Producer("Barrie M.", "Osborne"))
            ),
            Movie(id = "12",
                title = "Star Wars: Episode V - The Empire Strikes Back",
                yearReleased = 1980,
                genre = MovieGenre.Action,
                director = Director("Irvin", "Kershner"),
                actors = listOf(Actor("Mark", "Hamill"), Actor("Harrison", "Ford")),
                producers = listOf(Producer("Gary", "Kurtz"))
            ),
            Movie(id = "13",
                title = "Fight Club",
                yearReleased = 1999,
                genre = MovieGenre.Drama,
                director = Director("David", "Fincher"),
                actors = listOf(Actor("Brad", "Pitt"), Actor("Edward", "Norton")),
                producers = listOf(Producer("Art", "Linson"))
            ),
            Movie(id = "14",
                title = "The Good, the Bad and the Ugly",
                yearReleased = 1966,
                genre = MovieGenre.Western,
                director = Director("Sergio", "Leone"),
                actors = listOf(Actor("Clint", "Eastwood"), Actor("Eli", "Wallach")),
                producers = listOf(Producer("Alberto", "Grimaldi"))
            ),
            Movie(id = "15",
                title = "Se7en",
                yearReleased = 1995,
                genre = MovieGenre.Crime,
                director = Director("David", "Fincher"),
                actors = listOf(Actor("Morgan", "Freeman"), Actor("Brad", "Pitt")),
                producers = listOf(Producer("Arnold", "Kopelson"))
            ),
            Movie(id = "16",
                title = "Interstellar",
                yearReleased = 2014,
                genre = MovieGenre.Adventure,
                director = Director("Christopher", "Nolan"),
                actors = listOf(Actor("Matthew", "McConaughey"), Actor("Anne", "Hathaway")),
                producers = listOf(Producer("Emma", "Thomas"))
            ),
            Movie(id = "17",
                title = "Good Fellas",
                yearReleased = 1990,
                genre = MovieGenre.Biography,
                director = Director("Martin", "Scorsese"),
                actors = listOf(Actor("Robert", "De Niro"), Actor("Ray", "Liotta")),
                producers = listOf(Producer("Irwin", "Winkler"))
            ),
            Movie(
                id = "18",
                title = "The Matrix",
                yearReleased = 1999,
                genre = MovieGenre.Sci_Fi,
                director = Director("Lana", "Wachowski"),
                actors = listOf(Actor("Keanu", "Reeves"), Actor("Carrie-Anne", "Moss")),
                producers = listOf(Producer("Joel", "Silver"))
                ),
            Movie(
                id = "19",
                title = "The Matrix Reloaded",
                yearReleased = 2003,
                genre = MovieGenre.Sci_Fi,
                director = Director("Lana", "Wachowski"),
                actors = listOf(Actor("Keanu", "Reeves"), Actor("Carrie-Anne", "Moss")),
                producers = listOf(Producer("Joel", "Silver"))
            ),
            Movie(
                id = "20",
                title = "The Matrix Revolutions",
                yearReleased = 2003,
                genre = MovieGenre.Sci_Fi,
                director = Director("Lana", "Wachowski"),
                actors = listOf(Actor("Keanu", "Reeves"), Actor("Carrie-Anne", "Moss")),
                producers = listOf(Producer("Joel", "Silver"))
            )
        )
    }
}