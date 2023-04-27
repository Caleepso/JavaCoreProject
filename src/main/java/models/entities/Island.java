package models.entities;

import services.Utils;

import java.util.Arrays;

public class Island {
    private final Cell[][] field;
    private final int rows;
    private final int cols;
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
        Utils.setHeader();
        int rows = Utils.getRows();
        int cols = Utils.getCols();

        Island island = new Island(new Cell[rows][cols]);

        for (int i = 0; i < island.field.length; i++) {
            for (int j = 0; j < island.field[i].length; j++){
                island.field[i][j] = new Cell(i,j);

            }
        }
        System.out.printf(" ➔ Инициализирован остров с размерами поля %d x %d%n", rows, cols);
        return island;
    }

    @Override
    public String toString() {
        return "Island{" +
                "field=" + Arrays.toString(field) +
                '}';
    }
}
