package sample.web.ui.Service.concrete;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.IMovieService;
import sample.web.ui.dataAcces.BaseMovieRepository;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.Movie.Movie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


@Service
public class MovieService implements IMovieService {

    private final BaseMovieRepository baseMovieRepository;

    @Autowired
    public MovieService(BaseMovieRepository baseMovieRepository) {
        this.baseMovieRepository = baseMovieRepository;
    }

    /**
     * haalt alle films op uit de db
     * @return lijst met films
     */
    public Iterable<BaseMovie> getAllMovies(){
        return baseMovieRepository.findAll();
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
}
