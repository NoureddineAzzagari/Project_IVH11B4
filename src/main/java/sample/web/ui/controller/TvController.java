package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sample.web.ui.Service.concrete.TvShowService;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.Movie.memento.CareTaker;
import sample.web.ui.domain.Movie.memento.Memento;
import sample.web.ui.domain.tvShow.TvShow;
import sample.web.ui.viewModel.MovieViewModel;
import sample.web.ui.viewModel.TvShowViewModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/tv")
public class TvController {

    private final TvShowService tvShowService;

    @Autowired
    public TvController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    /**
     * methode om alle tv shows op te halen
     * @return informatie over alle beschikbare tv shows
     */
    @RequestMapping("")
    public TvShowViewModel getAllTvShows(@CookieValue(value = "userInfo", defaultValue = "")String userCookie){
        try{
            if(!tvShowService.checkForTvShows())
                fillTvShowDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userCookie != null && !userCookie.equals("")) return tvShowService.getAllTvShows(Long.valueOf(userCookie.split("~~~")[3]));
        return null;
    }

    /**
     * methode om informatie op te halen over een enkele tv show aan de hand van een id
     * @param id id van de op te halen tv show
     * @return 1 tv show
     */
    @RequestMapping("/{id}")
    public TvShow getTvShowById(@PathVariable("id") long id){
        return tvShowService.getTvShowById(id);
    }

    /**
     * methode om tv series in de database te zetten
     */
    private void fillTvShowDb(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Penny+Dreadful", 2014);
        map.put("Marco+Polo", 2014);
        map.put("Luke+Cage", 2016);
        map.put("Daredevil", 2015);

        map.forEach((String title, Integer year) -> {
            TvShow tvShow = tvShowService.getNewTvShow(title, year);
            if(tvShow != null) {
                tvShowService.addTvShow(tvShow);
            }
        });
    }

    /**
     * toont films die voldoen aan de megeven zoek opties
     * @param searchString de text waarop gezocht wordt
     * @param searchOption of er gezocht moet worden op titel of op acteur
     * @return lijst met films
     */
    @RequestMapping("/search")
    public Iterable<TvShow> searchMovies(String searchString, int searchOption){
        return tvShowService.searchTvShows(searchString, searchOption);
    }

    /**
     * haalt alle recent bekeken films op
     * @param req httprequest
     * @return lijst met films
     */
    @RequestMapping("/recent")
    public Iterable<TvShow> getRecentMovies(HttpServletRequest req){
        CareTaker careTaker = req.getSession().getAttribute("memento") != null ? (CareTaker)req.getSession().getAttribute("memento") : new CareTaker();
        ArrayList<TvShow> tvShows = new ArrayList<>();
        if(careTaker.getSavedStates().size() > 0){
            careTaker.getSavedStates().forEach((Memento state) -> {
                long id = state.getState();
                tvShows.add(tvShowService.getTvShowById(id));
            });
        }
        return tvShows;
    }

}
