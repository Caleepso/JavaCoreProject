package models.entities;

import services.UtilsService;

import java.util.Arrays;

public class Island {
    private Cell[][] field;
    private int rows;
    private int cols;
    private int days = 0;
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public Cell[][] getField() {
        return field;
    }
    public Island(Cell[][] field) {
        this.field = field;
        this.rows = field.length;
        this.cols = field[0].length;
    }

    public static Island initialize(){
        UtilsService.setHeader();
        int rows = UtilsService.getRows();
        int cols = UtilsService.getCols();

        Island island = new Island(new Cell[rows][cols]);

        for (int i = 0; i < island.field.length; i++) {
            for (int j = 0; j < island.field[i].length; j++){
                island.field[i][j] = new Cell(i,j);
            }
        }
        System.out.println(String.format(" ➔ Инициализирован остров с размерами поля %d x %d", rows, cols));
        return island;
    }

    @Override
    public String toString() {
        return "Island{" +
                "field=" + Arrays.toString(field) +
                '}';
    }
}
