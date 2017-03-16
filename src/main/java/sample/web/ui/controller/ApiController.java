package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.Service.interfaces.ITvShowService;
import sample.web.ui.Service.interfaces.IUserService;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.tvShow.TvShow;

@RestController
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

	@RequestMapping("/login")
	public boolean Authenticate(String userName, String password){
		return userService.authenticate(userName, password);
	}

	@RequestMapping("/movies")
	public Iterable<BaseMovie> getAllMovies(){
		return movieService.getAllMovies();
	}

	@RequestMapping("/movies/{id}")
	public BaseMovie getMovieById(@RequestParam("id") long id){
		return movieService.getMovieById(id);
	}

	@RequestMapping("/tv")
	public Iterable<TvShow> getAllTvShows(){
		return tvShowService.getAllTvShows();
	}

	@RequestMapping("/tv/{id}")
	public TvShow getTvShowById(@RequestParam("id") long id){
		return tvShowService.getTvShowById(id);
	}



}
