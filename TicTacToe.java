import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private String currentPlayer = "X";
    private boolean gameActive = true;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(102, 126, 234));

        // Title
        JLabel title = new JLabel("Tic-Tac-Toe", JLabel.CENTER);
        title.setFont(new Font("Baloo", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Status Label
        statusLabel = new JLabel("Player X's turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel, BorderLayout.SOUTH);

        // Game board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 10, 10));
        boardPanel.setBackground(new Color(86, 77, 142));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(boardPanel, BorderLayout.CENTER);

        Font cellFont = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(cellFont);
            buttons[i].setBackground(new Color(122, 115, 195));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            boardPanel.add(buttons[i]);
        }

        // Restart button
        JButton restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.setBackground(new Color(138, 121, 247));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        restartButton.addActionListener(e -> restartGame());
        add(restartButton, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameActive) return;

        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("")) return;

        clickedButton.setText(currentPlayer);
        clickedButton.setEnabled(false);

        if (checkWin()) {
            statusLabel.setText("Player " + currentPlayer + " wins! 🎉");
            gameActive = false;
            highlightWinningCells();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw! 🤝");
            gameActive = false;
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private boolean checkWin() {
        int[][] wins = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // cols
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };
        for (int[] win : wins) {
            if (buttons[win[0]].getText().equals(currentPlayer) &&
                buttons[win[1]].getText().equals(currentPlayer) &&
                buttons[win[2]].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (JButton btn : buttons) {
            if (btn.getText().equals("")) return false;
        }
        return true;
    }

    private void highlightWinningCells() {
        int[][] wins = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] win : wins) {
            if (buttons[win[0]].getText().equals(currentPlayer) &&
                buttons[win[1]].getText().equals(currentPlayer) &&
                buttons[win[2]].getText().equals(currentPlayer)) {
                for (int i : win) {
                    buttons[i].setBackground(Color.ORANGE);
                    buttons[i].setForeground(Color.BLACK);
                }
                break;
            }
        }
    }

    private void restartGame() {
        for (JButton btn : buttons) {
            btn.setText("");
            btn.setEnabled(true);
            btn.setBackground(new Color(122, 115, 195));
            btn.setForeground(Color.WHITE);
        }
        currentPlayer = "X";
        gameActive = true;
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}