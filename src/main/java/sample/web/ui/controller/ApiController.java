package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.Service.interfaces.ITvShowService;
import sample.web.ui.Service.interfaces.IUserService;
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


	/**
	 * methode die login gegevens controleerd
	 * @param userName: gebruikers naam
	 * @param password: wachtwoord
	 * @return true als correct anders false
	 */
	@RequestMapping("/login")
	public boolean Authenticate(String userName, String password){
		return userService.authenticate(userName, password);
	}

	/**
	 * methode die een nieuw account aanmaakt
	 * @param firstName voornaam
	 * @param lastName achternaam
	 * @param address adres
	 * @param emailAddress email
	 * @param userName gebruikers naam
	 * @param password wachtwoord
	 * @param age leeftijd
	 * @param telephoneNumber tel nr
	 */
	@RequestMapping("/signup")
	public  void  signUp(String firstName, String lastName, String address,
							String emailAddress, String userName,
							String password, int age, int telephoneNumber){
		User.UserBuilder userBuilder = new User.UserBuilder(firstName, lastName, address, age,
				userName, password,emailAddress, false);
		if(telephoneNumber > 0) userBuilder.telephoneNumber(telephoneNumber);
		User user = userBuilder.build();
		userService.addUser(user);
	}

	/**
	 * methode om alle films mee op te halen
	 * @return alle beschikbare films
	 */
	@RequestMapping("/movies")
	public Iterable<BaseMovie> getAllMovies(){
		if(!movieService.checkForMovies()) fillMovieDb();
		return movieService.getAllMovies();
	}

	/**
	 * methode om een film op te halen aan de hand van een id
	 * @param id id van de op te halen film
	 * @return alle informatie over de opgevraagde film
	 */
	@RequestMapping("/movies/{id}")
	public BaseMovie getMovieById(@PathVariable("id") long id){
		return movieService.getMovieById(id);
	}

	/**
	 * haalt informatie op over een film aan de hand van de titel en het jaar van de omdb api
	 * http://www.omdbapi.com/
	 * plaatst de nieuwe film daarna in de db
	 * @param title titel van de film
	 * @param year jaar dat de film uit is gekomen
	 */
	@RequestMapping("movies/add")
	public void addMovie(String title, int year){
		movieService.addMovie(movieService.getNewMovie(title, year));
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

	/**
	 * methode om films in de database te zetten omdat jpa na het starten van de applicatie de db leeg gooit
	 */
	private void fillMovieDb(){
		HashMap<String, Integer> map = new HashMap<>();
		map.put("Logan", 2017);
		map.put("Kong:+Skull+Island", 2017);
		map.put("Beauty+and+the+Beast", 2017);

		map.put("Pirates+of+the+Caribbean:+Dead+Men+Tell+No+Tales", 2017);
		map.put("Thor:+Ragnarök", 2017);
		map.forEach((String title, Integer year) -> {
			BaseMovie movie = movieService.getNewMovie(title, year);
			if(movie != null) movieService.addMovie(movie);
		});
	}

}
