package sample.web.ui.Service.strategy

import java.util

import sample.web.ui.domain.Movie.{BaseMovie, Movie}
import sample.web.ui.domain.tvShow.TvShow

/**
  * Created by Ids van der Zee on 2-4-2017.
  */

trait SearchStrategy {
  def searchMovies(searchString: String, movies: java.lang.Iterable[BaseMovie]) : java.lang.Iterable[BaseMovie]
  def searchTvShows(searchString: String, tvShow: java.lang.Iterable[TvShow]) : java.lang.Iterable[TvShow]
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
        case `title` if title.contains(searchString.toLowerCase) => movie
        case _ => null
      }
      if(searchedMovie != null){
        searchedMovies.add(searchedMovie)
      }
    }
    searchedMovies
  }

  override def searchTvShows(searchString: String, tvShows: java.lang.Iterable[TvShow]): java.lang.Iterable[TvShow] = {
    val searchedTvShows :  util.ArrayList[TvShow] = new util.ArrayList[TvShow]()
    for (tvShow: TvShow <- tvShows.asScala){
      val title = tvShow.asInstanceOf[Movie].getTitle.toLowerCase
      val searchedTvShow = title match{
        case `title` if title.contains(searchString.toLowerCase) => tvShow
        case _ => null
      }
      if(searchedTvShow != null){
        searchedTvShows.add(searchedTvShow)
      }
    }
    searchedTvShows
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
    for (movie: BaseMovie <- movies.asScala) {
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

  override def searchTvShows(searchString: String, tvShows: java.lang.Iterable[TvShow]): java.lang.Iterable[TvShow] = {
    val searchedTvShows :  util.ArrayList[TvShow] = new util.ArrayList[TvShow]()
    for (tvShow: TvShow <- tvShows.asScala){
      val title = tvShow.asInstanceOf[Movie].getTitle.toLowerCase
      val searchedTvShow = title match{
        case `title` if title.contains(searchString.toLowerCase) => tvShow
        case _ => null
      }
      if(searchedTvShow != null){
        searchedTvShows.add(searchedTvShow)
      }
    }
    searchedTvShows
  }
}
