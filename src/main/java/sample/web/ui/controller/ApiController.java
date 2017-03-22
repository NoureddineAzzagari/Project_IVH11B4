package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.Service.interfaces.ITvShowService;
import sample.web.ui.Service.interfaces.IUserService;
import sample.web.ui.domain.Configuration;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.User.User;
import sample.web.ui.domain.tvShow.TvShow;
import java.util.HashMap;

/**
 * api controller die alle requests naar /api/** afhandeld
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

	private final IMovieService movieService;
	private final ITvShowService tvShowService;
	private final IUserService userService;

	@Autowired
	public ApiController(IMovieService movieService, ITvShowService tvShowService, IUserService userService) {
		this.movieService = movieService;
		this.tvShowService = tvShowService;
		this.userService = userService;
	}




	@RequestMapping("/config")
	public Configuration getConfiguration(){
		return Configuration.getConfiguration();
	}



	/**
	 * methode om alle tv shows op te halen
	 * @return informatie over alle beschikbare tv shows
	 */
	@RequestMapping("/tv")
	public Iterable<TvShow> getAllTvShows(){
		return tvShowService.getAllTvShows();
	}

	/**
	 * methode om informatie op te halen over een enkele tv show aan de hand van een id
	 * @param id id van de op te halen tv show
	 * @return 1 tv show
	 */
	@RequestMapping("/tv/{id}")
	public TvShow getTvShowById(@PathVariable("id") long id){
		return tvShowService.getTvShowById(id);
	}



}
