package pl.cba.lalewicz.convaygameoflife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.convaygameoflife.model.Cell;

import java.util.List;

@Service
public class CellService {
    @Autowired
    private List<Cell> cellList;

    public void generateList(int maxCols){
        cellList.clear();
        boolean islife=false;

        for (int i = 0 ; i < maxCols*maxCols; i++){
                cellList.add(new Cell(islife,i));
       }
        //generowanie kazdemu polu listy sasiadow
        cellList.forEach(cell -> {
            int[] tableOfNeighbors = new int[8];
            int startNumRow = cell.getId()/maxCols*maxCols;
            int endNumRow = startNumRow + maxCols;
            tableOfNeighbors[3] = cell.getId()+1;
            if (tableOfNeighbors[3] >= endNumRow) tableOfNeighbors[3]-=maxCols;
            tableOfNeighbors[7] = cell.getId()-1;
            if (tableOfNeighbors[7] < startNumRow) tableOfNeighbors[7]+=maxCols;
            tableOfNeighbors[0] = (tableOfNeighbors[7] - maxCols + maxCols*maxCols) % (maxCols*maxCols);
            tableOfNeighbors[1] = (cell.getId() - maxCols+ maxCols*maxCols) % (maxCols*maxCols);
            tableOfNeighbors[2] = (tableOfNeighbors[3] - maxCols+ maxCols*maxCols) % (maxCols*maxCols);
            tableOfNeighbors[4] = (tableOfNeighbors[3] + maxCols + maxCols*maxCols) % (maxCols*maxCols);
            tableOfNeighbors[5] = (cell.getId() + maxCols+ maxCols*maxCols) % (maxCols*maxCols);
            tableOfNeighbors[6] = (tableOfNeighbors[7] + maxCols+ maxCols*maxCols) % (maxCols*maxCols);
            cell.setListOfNeighbors(tableOfNeighbors);
        });

    }

    public List<Cell> printList() {
        return cellList;
    }

    public List<Cell> nextGameTurn() {
        setFutureLifeInCells();
        cellList.forEach(cell -> {
            cell.setLive(cell.isFutureLive());
            cell.setFutureLive(false);
        });
        return printList();
    }

    private void setFutureLifeInCells() {
        // zasady gry rodzi się jeżeli na pustym polu sasiaduje z trzema zyjącymi
        // jezeli pole zyje to przezyje jeżeli bedzie sasiadowal z dwoma lub trzema zyjacymi
        cellList.forEach(cell -> {

            if (!cell.isLive()) {
                int countNeighborLive = 0;
                for (int neighborId : cell.getListOfNeighbors()) {
                    if (cellList.get(neighborId).isLive()) countNeighborLive++;
                }
                if (countNeighborLive == 3) cell.setFutureLive(true);
                else cell.setFutureLive(false);
            }
            if (cell.isLive()){
                int countNeighborLive = 0;
                for (int neighborId : cell.getListOfNeighbors()){
                    if (cellList.get(neighborId).isLive()) countNeighborLive++;
                }
                if (countNeighborLive == 3 || countNeighborLive ==2) cell.setFutureLive(true);
                else cell.setFutureLive(false);
            }
        });
    }

    public void generateListFromFront(List<Cell> cellListFront) {
        cellList = cellListFront;
    }


}
