package sample.web.ui.Service.strategy

import java.util
import scala.collection.JavaConverters._
import sample.web.ui.domain.Movie.{BaseMovie, Movie}

/**
  * Created by Ids van der Zee on 2-4-2017.
  */

trait SearchStrategy {
  def searchMovies(searchString: String, movies: java.lang.Iterable[BaseMovie]) : java.lang.Iterable[BaseMovie]
}

class TitleStrategy extends SearchStrategy{
  /**
    * doorzoekt een lijst van films op titel
    * @param searchString zoekwoord
    * @param movies films
    * @return lijst met films
    */
  override def searchMovies(searchString: String, movies: java.lang.Iterable[BaseMovie]): java.lang.Iterable[BaseMovie] = {
    val searchedMovies :  util.ArrayList[BaseMovie] = new util.ArrayList[BaseMovie]()
    for (movie: BaseMovie <- movies.asScala){
      val title = movie.asInstanceOf[Movie].getTitle.toLowerCase
      val searchedMovie = title match{
        case `title` if title.contains(searchString) => movie
        case _ => null
      }
      if(searchedMovie != null){
        searchedMovies.add(searchedMovie)
      }
    }
    searchedMovies
  }
}

class ReleaseDateStrategy extends SearchStrategy{
  /**
    * doorzoekt een lijst van films op jaar
    * @param searchString zoekwoord
    * @param movies films
    * @return lijst met films
    */
  override def searchMovies(searchString: String, movies: java.lang.Iterable[BaseMovie]): java.lang.Iterable[BaseMovie] = {
    val searchedMovies :  util.ArrayList[BaseMovie] = new util.ArrayList[BaseMovie]()
    for (movie: BaseMovie <- movies.asScala){
      val releaseDate = movie.asInstanceOf[Movie].getReleaseDate
      val searchedMovie = releaseDate match{
        case `releaseDate` if releaseDate.contains(searchString) => movie
        case _ => null
      }
      if(searchedMovie != null){
        searchedMovies.add(searchedMovie)
      }
    }
    searchedMovies
  }
}
