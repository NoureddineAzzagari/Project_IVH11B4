package sample.web.ui.domain.Movie.memento;

/**
 * Created by Ids van der Zee on 2-4-2017.
 */
public class Memento {
    private final long state;

    public Memento(long stateToState){
        state = stateToState;
    }

    /**
     * haalt de huidige state op
     * @return huidige state
     */
    public long getState(){
        return this.state;
    }
}
