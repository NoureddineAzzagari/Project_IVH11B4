package sample.web.ui.Service.concrete;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.ITvShowService;
import sample.web.ui.Service.strategy.ReleaseDateStrategy;
import sample.web.ui.Service.strategy.SearchStrategy;
import sample.web.ui.Service.strategy.TitleStrategy;
import sample.web.ui.dataAcces.EpisodeRepository;
import sample.web.ui.dataAcces.SeasonRepository;
import sample.web.ui.dataAcces.TvShowRepository;
import sample.web.ui.dataAcces.UserRepository;
import sample.web.ui.domain.Favourites;
import sample.web.ui.domain.User.User;
import sample.web.ui.domain.tvShow.Episode;
import sample.web.ui.domain.tvShow.Season;
import sample.web.ui.domain.tvShow.TvShow;
import sample.web.ui.viewModel.TvShowViewModel;

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
public class TvShowService implements ITvShowService {

    private static final Logger logger = LoggerFactory.getLogger(TvShowService.class);

    private final TvShowRepository tvShowRepository;
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;
    private final UserRepository userRepository;
    private SearchStrategy searchStrategy;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository, SeasonRepository seasonRepository, EpisodeRepository episodeRepository, UserRepository userRepository) {
        this.tvShowRepository = tvShowRepository;
        this.seasonRepository = seasonRepository;
        this.episodeRepository = episodeRepository;
        this.userRepository = userRepository;
    }

    /**
     * haalt alle tv shows op uit de db
     *
     * @return lijst met tv shows
     */
    @Override
    public TvShowViewModel getAllTvShows(long userId) {
        ArrayList<TvShow> allTvShows = (ArrayList<TvShow>) tvShowRepository.findAll();
        User user = userRepository.findOne(userId);
        if (user != null) {
            Favourites fav = user.getFavourites();
            List<TvShow> favouriteTvShows;
            if (fav != null && fav.getMovies() != null && fav.getMovies().size() > 0) {
                favouriteTvShows = fav.getShows();

                ArrayList<TvShow> duplicates = new ArrayList<>();
                for (TvShow tvShow : allTvShows) {
                    if (favouriteTvShows.contains(tvShow)) duplicates.add(tvShow);
                }
                allTvShows.removeAll(duplicates);
            } else {
                return new TvShowViewModel(allTvShows, null);
            }

            return new TvShowViewModel(allTvShows, favouriteTvShows);
        }
        return null;
    }

    /**
     * haalt een tv show op aan de hand van een id
     *
     * @param id id van de op te halen tv show
     * @return een tv show
     */
    @Override
    public TvShow getTvShowById(long id) {
        return tvShowRepository.findOne(id);
    }

    /**
     * haalt alle seasons op uit de db
     *
     * @return lijst met seasons
     */
    @Override
    public Iterable<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    /**
     * haalt een season op aan de hand van een id
     *
     * @param id id van de op te halen season
     * @return een season
     */
    @Override
    public Season getSeasonById(long id) {
        return seasonRepository.findOne(id);
    }

    /**
     * haalt alle afleveringen op
     *
     * @return lijst met afleveringen
     */
    @Override
    public Iterable<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    /**
     * haalt een aflevering op aan de hand van een id
     *
     * @param id id van de op te halen aflevering
     * @return een aflevering
     */
    @Override
    public Episode getEpisodeById(long id) {
        return episodeRepository.findOne(id);
    }

    /**
     * controleert of er tv series aanwezig zijn  in de db
     * @return true als meer dan nul tv series in de db staan
     */
    public boolean checkForTvShows() {
        return tvShowRepository.count() > 0;
    }

    @Override
    public TvShow getNewTvShow(String title, int year) {
        InputStream inputStream;
        String response = "";

        try {
            URL url = new URL("http://omdbapi.com/?t=" + title + "&y=" + year);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (response.equals("")) return null;
        JSONObject jsonObject = new JSONObject(response);
        String time = jsonObject.getString("Runtime").replace(" min", "");

        return new TvShow(null, jsonObject.getString(title), jsonObject.getString("Plot"),
                jsonObject.getString("Poster"),
                String.valueOf(year));
    }

    @Override
    public void addTvShow(TvShow tvShow) {
        tvShowRepository.save(tvShow);
    }

    /**
     * veranderd een inputstream in een stirng
     *
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

    /**
     * zoekt door alle films om de juiste te vinden
     * @param searchString zoekwoord
     * @param searchOption zoek optie
     * @return lijst van films
     */
    @Override
    public Iterable<TvShow> searchTvShows(String searchString, int searchOption) {
        if(searchOption == 2) this.setStrategy(new ReleaseDateStrategy());
        else this.setStrategy(new TitleStrategy());
        return searchStrategy.searchTvShows(searchString, tvShowRepository.findAll());
    }

}
