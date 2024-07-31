package rakshita;

import javax.swing.*;
import java.awt.*;

class SudokuGrid extends JPanel {
    private JTextField[][] cells = new JTextField[9][9];

    public SudokuGrid() {
        setLayout(new GridLayout(9, 9));
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                add(cells[i][j]);
            }
        }
        setInitialValues();  // Set the entire grid with values for testing
    }

    private void setInitialValues() {
        int[][] solution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setCellValue(i, j, solution[i][j]);
            }
        }
    }

    public void setCellValue(int row, int col, int value) {
        cells[row][col].setText(value == 0 ? "" : String.valueOf(value));
    }

    public int getCellValue(int row, int col) {
        String text = cells[row][col].getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    public boolean isSolutionValid() {
        return areRowsValid() && areColumnsValid() && areSubGridsValid();
    }

    private boolean areRowsValid() {
        for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int num = getCellValue(i, j);
                if (num == 0 || seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }

    private boolean areColumnsValid() {
        for (int j = 0; j < 9; j++) {
            boolean[] seen = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int num = getCellValue(i, j);
                if (num == 0 || seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }

    private boolean areSubGridsValid() {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!isSubGridValid(row, col)) return false;
            }
        }
        return true;
    }

    private boolean isSubGridValid(int row, int col) {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = getCellValue(row + i, col + j);
                if (num == 0 || seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }
}
