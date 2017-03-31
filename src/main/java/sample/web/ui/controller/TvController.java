package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.Service.concrete.TvShowService;
import sample.web.ui.domain.tvShow.TvShow;

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
    @RequestMapping("/")
    public Iterable<TvShow> getAllTvShows(){
        return tvShowService.getAllTvShows();
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

}
