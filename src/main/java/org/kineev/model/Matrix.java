package org.kineev.model;

import org.kineev.service.Print;
import org.kineev.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private final char point = '.';
    private final char[][] matrix = new char[Service.getSize()][Service.getSize()];


    public void setX(int x, int y, char Char) {
        matrix[x][y] = Char;
    }

    public void setO(int x, int y, char Char) {
        matrix[x][y] = Char;
    }

    public void resetMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = point;
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<FreeX> freeXES() {
        List<FreeX> xes = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == point) {
                    xes.add(new FreeX(i, j));
                }
            }
        }
        return xes;
    }

    public boolean isWin() {
        if (matrix[0][0] != point & matrix[0][0] == (matrix[0][1]) & matrix[0][0] == (matrix[0][2])) {
            return false;
        } else if (matrix[1][0] != point & matrix[1][0] == (matrix[1][1]) & matrix[1][0] == (matrix[1][2])) {
            return false;
        } else if (matrix[2][0] != point & matrix[2][0] == (matrix[2][1]) & matrix[2][0] == (matrix[2][2])) {
            return false;
        } else if (matrix[0][0] != point & matrix[0][0] == (matrix[1][0]) & matrix[0][0] == (matrix[2][0])) {
            return false;
        } else if (matrix[0][1] != point & matrix[0][1] == (matrix[1][1]) & matrix[0][1] == (matrix[2][1])) {
            return false;
        } else if (matrix[0][2] != point & matrix[0][2] == (matrix[1][2]) & matrix[0][2] == (matrix[2][2])) {
            return false;
        } else if (matrix[0][0] != point & matrix[0][0] == (matrix[1][1]) & matrix[0][0] == (matrix[2][2])) {
            return false;
        } else if (matrix[0][2] != point & matrix[0][2] == (matrix[1][1]) & matrix[0][2] == (matrix[2][0])) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkIsFree(int one, int two) {
        if (matrix[one][two] == point) {
            return true;
        } else {
            Print.getPrint("Повторите ввод!");
            return false;
        }
    }
}
