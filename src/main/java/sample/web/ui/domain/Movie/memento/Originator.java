package sample.web.ui.domain.Movie.memento;

import lombok.Setter;

/**
 * Created by Ids van der Zee on 2-4-2017.
 */
@Setter
public class Originator {
    private long state;

    /**
     * maakt een nieuwe memento
     * @return nieuwe memento
     */
    public Memento saveToMemento(){
        return new Memento(state);
    }

    /**
     * hersteld de state aan de hand van een memento
     * @param m memento
     */
    public void restoreFromMemento(Memento m){
        state = m.getState();
    }
}
