package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.ServiceInterfaces.IMovieService;
import sample.web.ui.ServiceInterfaces.ITvShowService;
import sample.web.ui.ServiceInterfaces.IUserService;

/**
 * @author Rob Winch
 * @author Doo-Hwan Kwak
 */
@RestController
@RequestMapping("/api")
public class MessageController {

	private final IMovieService movieService;
	private final ITvShowService tvShowService;
	private final IUserService userService;

	@Autowired
	public MessageController(IMovieService movieService, ITvShowService tvShowService, IUserService userService) {
		this.movieService = movieService;
		this.tvShowService = tvShowService;
		this.userService = userService;
	}



}
