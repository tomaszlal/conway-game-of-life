package pl.cba.lalewicz.convaygameoflife.model;

import org.springframework.stereotype.Component;

@Component
public class Cell {

    private int id;

    private boolean live;
    private boolean futureLive;

    private int[] listOfNeighbors = new int[8];

    public Cell(boolean isLive,int id) {

        this.live = isLive;
        this.id = id;
    }

    public Cell(int id, boolean live, boolean futureLive, int[] listOfNeighbors) {
        this.id = id;
        this.live = live;
        this.futureLive = futureLive;
        this.listOfNeighbors = listOfNeighbors;
    }

    public Cell() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isFutureLive() {
        return futureLive;
    }

    public void setFutureLive(boolean futureLive) {
        this.futureLive = futureLive;
    }

    public int[] getListOfNeighbors() {
        return listOfNeighbors;
    }

    public void setListOfNeighbors(int[] listOfNeighbors) {
        this.listOfNeighbors = listOfNeighbors;
    }


}
