import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import sample.web.ui.Service.concrete.MovieService;
import sample.web.ui.dataAcces.BaseMovieRepository;
import sample.web.ui.dataAcces.UserRepository;
import sample.web.ui.domain.Movie.BaseMovie;
import sample.web.ui.domain.Movie.Movie;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

/**
 * Created by Ids van der Zee on 3-4-2017.
 */

public class TestMovieService {



    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testSearchMoviesByTitleSingleResult(){
        ArrayList<BaseMovie> movies = new ArrayList<>();
        Movie movieToSearch = new Movie("the movie to test", "some content", "some url", 123, "2017");

        movies.add(new Movie("some other movie1", "some content1", "some url", 1234, "2016"));
        movies.add(new Movie("some other movie2", "some content2", "some url", 1234, "2015"));
        movies.add(new Movie("some other movie3", "some content3", "some url", 1234, "2014"));
        movies.add(new Movie("some other movie4", "some content4", "some url", 1234, "2013"));
        movies.add(movieToSearch);

        BaseMovieRepository movieRepository = Mockito.mock(BaseMovieRepository.class);
        when(movieRepository.findAll()).thenReturn(movies);

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        MovieService movieService = new MovieService(movieRepository, userRepository);

        ArrayList<BaseMovie> testSearch = (ArrayList<BaseMovie>) movieService.searchMovies("test", 1);
        Assert.assertTrue(testSearch.contains(movieToSearch));
    }

    @Test
    public void testSearchMoviesByTitleMultipleResults(){
        ArrayList<BaseMovie> movies = new ArrayList<>();
        Movie movieToSearch = new Movie("the movie to test", "some content", "some url", 123, "2017");
        Movie otherMovieToSearch = new Movie("the other movie to test", "some other content", "some other url", 321, "2016");
        Movie yetAnOtherMovieToSearch = new Movie("the last movie to test", "some more content", "some more url", 321654, "2010");

        movies.add(new Movie("some other movie1", "some content1", "some url", 1234, "2016"));
        movies.add(new Movie("some other movie2", "some content2", "some url", 1234, "2015"));
        movies.add(new Movie("some other movie3", "some content3", "some url", 1234, "2014"));
        movies.add(new Movie("some other movie4", "some content4", "some url", 1234, "2013"));
        movies.add(movieToSearch);
        movies.add(otherMovieToSearch);
        movies.add(yetAnOtherMovieToSearch);

        ArrayList<BaseMovie> listToTest = new ArrayList<>();
        listToTest.add(movieToSearch);
        listToTest.add(otherMovieToSearch);
        listToTest.add(yetAnOtherMovieToSearch);

        BaseMovieRepository movieRepository = Mockito.mock(BaseMovieRepository.class);
        when(movieRepository.findAll()).thenReturn(movies);

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        MovieService movieService = new MovieService(movieRepository, userRepository);

        ArrayList<BaseMovie> testSearch = (ArrayList<BaseMovie>) movieService.searchMovies("test", 1);
        Assert.assertTrue(testSearch.size() == 3);
        Assert.assertTrue(testSearch.containsAll(listToTest));
    }

    @Test
    public void testSearchMoviesByReleaseYearSingleResult(){
        ArrayList<BaseMovie> movies = new ArrayList<>();
        Movie movieToSearch = new Movie("the movie to test", "some content", "some url", 123, "2017");

        movies.add(new Movie("some other movie1", "some content1", "some url", 1234, "2016"));
        movies.add(new Movie("some other movie2", "some content2", "some url", 1234, "2015"));
        movies.add(new Movie("some other movie3", "some content3", "some url", 1234, "2014"));
        movies.add(new Movie("some other movie4", "some content4", "some url", 1234, "2013"));
        movies.add(movieToSearch);

        BaseMovieRepository movieRepository = Mockito.mock(BaseMovieRepository.class);
        when(movieRepository.findAll()).thenReturn(movies);

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        MovieService movieService = new MovieService(movieRepository, userRepository);

        ArrayList<BaseMovie> testSearch = (ArrayList<BaseMovie>) movieService.searchMovies("2017", 2);
        Assert.assertTrue(testSearch.contains(movieToSearch));
    }

    @Test
    public void testSearchMoviesByReleaseDateMultipleResult(){
        ArrayList<BaseMovie> movies = new ArrayList<>();
        Movie movieToSearch = new Movie("some movie", "some content", "some url", 123, "2017");

        movies.add(new Movie("some other movie1", "some content1", "some url", 1234, "2017"));
        movies.add(new Movie("some other movie2", "some content2", "some url", 1234, "2017"));
        movies.add(new Movie("some other movie3", "some content3", "some url", 1234, "2017"));
        movies.add(new Movie("some other movie4", "some content4", "some url", 1234, "2013"));
        movies.add(movieToSearch);

        ArrayList<BaseMovie> testArray = new ArrayList<>();
        testArray.addAll(movies.subList(0,3));

        BaseMovieRepository movieRepository = Mockito.mock(BaseMovieRepository.class);
        when(movieRepository.findAll()).thenReturn(movies);

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        MovieService movieService = new MovieService(movieRepository, userRepository);

        ArrayList<BaseMovie> testSearch = (ArrayList<BaseMovie>) movieService.searchMovies("2017", 2);
        Assert.assertTrue(testSearch.size() == 4);
        Assert.assertTrue(testSearch.containsAll(testArray));
    }

    @Test
    public void testCheckMoviesNoMovies(){
        BaseMovieRepository movieRepository = Mockito.mock(BaseMovieRepository.class);
        when(movieRepository.count()).thenReturn(0L);

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        MovieService movieService = new MovieService(movieRepository, userRepository);

        Assert.assertFalse(movieService.checkForMovies());
    }

    @Test
    public void testCheckMoviesMoreThanZeroMovies(){
        BaseMovieRepository movieRepository = Mockito.mock(BaseMovieRepository.class);

        ArrayList<BaseMovie> testMovies = new ArrayList<>();
        testMovies.add(new Movie());
        testMovies.add(new Movie());

        when(movieRepository.count()).thenReturn(2L);

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        MovieService movieService = new MovieService(movieRepository, userRepository);

        Assert.assertTrue(movieService.checkForMovies());
    }
}
