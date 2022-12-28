package pl.cba.lalewicz.convaygameoflife.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cba.lalewicz.convaygameoflife.model.Cell;
import pl.cba.lalewicz.convaygameoflife.service.CellService;

import java.util.List;

@CrossOrigin
@RestController
public class CellController {

    @Autowired
    private CellService cellService;

    @PostConstruct
    public void generateData(){
        int maxCols = 10;

        cellService.generateList(maxCols);
    }

    @GetMapping("/listcells")
    public List<Cell> getListOfCells(){
        return cellService.printList();
    }

    @GetMapping("/generate/{cols}")
    public List<Cell> generateBoard(@PathVariable int cols){
        cellService.generateList(cols);
        return cellService.printList();
    }

    @PostMapping("/updatelist")
    public List<Cell> updateList(@RequestBody List<Cell> cellListFront){
        cellService.generateListFromFront(cellListFront);
        return cellService.printList();
    }

    @GetMapping("/nextgameturn")
    public List<Cell> nextGameTurn() {
        return cellService.nextGameTurn();
    }
}
