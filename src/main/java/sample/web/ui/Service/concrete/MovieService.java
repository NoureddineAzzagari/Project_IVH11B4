package sample.web.ui.Service.concrete;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.Service.strategy.ReleaseDateStrategy;
import sample.web.ui.Service.strategy.SearchStrategy;
import sample.web.ui.Service.strategy.TitleStrategy;
import sample.web.ui.dataAcces.BaseMovieRepository;
import sample.web.ui.dataAcces.UserRepository;
import sample.web.ui.domain.Favourites;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.Movie.Movie;
import sample.web.ui.domain.User.User;
import sample.web.ui.viewModel.MovieViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@Service
public class MovieService implements IMovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final BaseMovieRepository baseMovieRepository;
    private final UserRepository userRepository;

    private SearchStrategy searchStrategy;

    @Autowired
    public MovieService(BaseMovieRepository baseMovieRepository, UserRepository userRepository) {
        this.baseMovieRepository = baseMovieRepository;
        this.userRepository = userRepository;
    }

    /**
     * haalt alle films op uit de db
     * @return lijst met films
     */
    public MovieViewModel getAllMovies(long userId){
        ArrayList<BaseMovie> allMovies = (ArrayList<BaseMovie>)baseMovieRepository.findAll();
        User user = userRepository.findOne(userId);
        if(user != null){
            Favourites fav = user.getFavourites();
            List<BaseMovie> favouriteMovies;
            if(fav != null && fav.getMovies() != null && fav.getMovies().size() > 0){
                favouriteMovies = fav.getMovies();

                ArrayList<BaseMovie> duplicates = new ArrayList<>();
                for(BaseMovie movie: allMovies){
                    if(favouriteMovies.contains(movie)) duplicates.add(movie);
                }
                allMovies.removeAll(duplicates);
            }
            else{
                return new MovieViewModel(allMovies, null);
            }

            return new MovieViewModel(allMovies, favouriteMovies);
        }
        return null;
    }

    /**
     * haalt een film op uit de db aan de hand van een id
     * @param id id van de op te halen film
     * @return een film
     */
    public BaseMovie getMovieById(long id){
        return baseMovieRepository.findOne(id);
    }

    /**
     * controleerd of er films aanwezig zijn  in de db
     * @return true als meer dan nul films in de db staan
     */
    public boolean checkForMovies(){
        return baseMovieRepository.count() > 0;
    }

    /**
     * zoekt door alle films om de juiste te vinden
     * @param searchString zoekwoord
     * @param searchOption zoek optie
     * @return lijst van films
     */
    @Override
    public Iterable<BaseMovie> searchMovies(String searchString, int searchOption) {
        if(searchOption == 2) this.setStrategy(new ReleaseDateStrategy());
        else this.setStrategy(new TitleStrategy());
        return searchStrategy.searchMovies(searchString, baseMovieRepository.findAll());
    }

    /**
     * voegt een nieuwe film toe in de db
     * @param movie toe te voegen film
     */
    @Override
    public void addMovie(BaseMovie movie) {
        baseMovieRepository.save(movie);
    }

    /**
     * haalt een nieuwe film op aan de hand van een titel en een jaar door middel van de omdb api
     * http://www.omdbapi.com/
     * @param title titel van de op te halen film
     * @param year release jaar van de op te halen film
     * @return nieuwe film
     */
    @Override
    public BaseMovie getNewMovie(String title, int year) {
        InputStream inputStream;
        String response = "";

        try{
            URL url = new URL("http://omdbapi.com/?t=" + title + "&y=" + year);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if(httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        if(response.equals(""))return null;
        JSONObject jsonObject = new JSONObject(response);
        String time = jsonObject.getString("Runtime").replace(" min", "");
        return new Movie(jsonObject.getString("Title"), jsonObject.getString("Plot"),
               jsonObject.getString("Poster"),
               time.equals("N/A") ? 100 : Integer.valueOf(time),
               String.valueOf(year));
    }

    /**
     * veranderd een inputstream in een stirng
     * @param is te veranderen inputstream
     * @return input stream als string
     */
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * set de huidige strategy
     * @param strategy nieuwe strategy
     */
    private void setStrategy(final SearchStrategy strategy){
        this.searchStrategy = strategy;
    }
}
