package sample.web.ui.domain.Movie.memento;

import java.util.ArrayList;

/**
 * Created by Ids van der Zee on 2-4-2017.
 */
public class CareTaker {
    private final ArrayList<Memento> savedStates = new ArrayList<>();

    /**
     * voegt een nieuwe memento toe
     * @param m nieuwe memento
     */
    public void addMemento(Memento m){
        savedStates.add(m);
    }

    /**
     * haalt een memento op voor de opgegeven index
     * @param index index van de op te halen memento
     * @return memento
     */
    public Memento getMemento(int index){
        return savedStates.get(index);
    }

    /**
     * haalt alle opgeslagen states op
     * @return lijst met memento's
     */
    public ArrayList<Memento> getSavedStates() {
        return savedStates;
    }
}
