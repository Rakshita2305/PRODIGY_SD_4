package rakshita;

import javax.swing.*;
import java.awt.*;

public class SudokuGame extends JFrame {

    private SudokuGrid grid;

    public SudokuGame() {
        setTitle("Sudoku");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        grid = new SudokuGrid();
        getContentPane().add(grid, BorderLayout.CENTER);

        JButton checkButton = new JButton("Check Solution");
        checkButton.setBackground(new Color(255, 0, 255));
        checkButton.addActionListener(e -> checkSolution());
        getContentPane().add(checkButton, BorderLayout.SOUTH);
    }

    private void checkSolution() {
        if (grid.isSolutionValid()) {
            JOptionPane.showMessageDialog(this, "Congratulations! The solution is correct.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The solution is incorrect. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuGame game = new SudokuGame();
            game.setVisible(true);
        });
    }
}
