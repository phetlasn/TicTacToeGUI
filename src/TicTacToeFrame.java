import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    Border empty;

    JPanel mainPanel;
    JPanel titlePanel;
    JLabel title;
    ImageIcon image;

    JPanel messagePanel;
    JLabel message;

    JPanel gamePanel;
    JPanel controlPanel;
    JLabel oWins;
    JLabel xWins;
    JLabel tieCountLabel;
    JTextField xWinField;
    JTextField oWinField;
    JTextField tieField;

    int xWinCount = 0;
    int oWinCount = 0;
    int tieCount = 0;
    int turnsCount = 0;

    JPanel controlPnl;
    JButton quitButton;
    JButton clearButton;

    private static final int COL = 3;
    private static final int ROW = 3;

    public static String[][] board = new String[ROW][COL];
    private static final JButton[][] GUIboard = new JButton[ROW][COL];
    String player = "X";
    boolean again = false;

    public TicTacToeFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1));

        createTitlePanel();
        mainPanel.add(titlePanel);

        createMessagePanel();
        mainPanel.add(message);

        createDisplay();
        mainPanel.add(gamePanel);

        createControlPanel();
        mainPanel.add(controlPanel);

        createControlPnl();
        mainPanel.add(controlPnl);

        add(mainPanel);
        mainPanel.setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
        setLocationRelativeTo(null);

    }

    private void createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        title = new JLabel("Tic Tac Toe", image, JLabel.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 45));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        titlePanel.add(title);
    }

    private void createDisplay() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        gamePanel.setBackground(Color.WHITE);
        CreateGame();
    }

    private void createMessagePanel() {
        messagePanel = new JPanel();
        messagePanel.setBackground(Color.WHITE);
        message = new JLabel("Player " + player + ", it's your turn.", null, JLabel.CENTER);
        message.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        message.setHorizontalTextPosition(JLabel.CENTER);
        message.setVerticalTextPosition(JLabel.BOTTOM);
        messagePanel.add(message);
    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setLayout(new GridLayout(0, 1));

        xWins = new JLabel("Player X Win Count", JLabel.CENTER);
        xWins.setFont(new Font("Dialog", Font.PLAIN, 16));
        xWins.setVerticalTextPosition(JLabel.BOTTOM);
        xWins.setHorizontalTextPosition(JLabel.CENTER);

        xWinField = new JTextField();
        xWinField.setHorizontalAlignment(JTextField.CENTER);
        empty = BorderFactory.createEmptyBorder();
        xWinField.setBorder(empty);
        xWinField.setFont(new Font("Dialog", Font.PLAIN, 18));
        xWinField.setEditable(false);

        tieCountLabel = new JLabel("Tie Count", JLabel.CENTER);
        tieCountLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        tieCountLabel.setVerticalTextPosition(JLabel.BOTTOM);
        tieCountLabel.setHorizontalTextPosition(JLabel.CENTER);

        tieField = new JTextField();
        tieField.setHorizontalAlignment(JTextField.CENTER);
        tieField.setBorder(empty);
        tieField.setFont(new Font("Dialog", Font.PLAIN, 18));
        tieField.setEditable(false);

        oWins = new JLabel("Player o Win Count", JLabel.CENTER);
        oWins.setFont(new Font("Dialog", Font.PLAIN, 16));
        oWins.setVerticalTextPosition(JLabel.BOTTOM);
        oWins.setHorizontalTextPosition(JLabel.CENTER);

        oWinField = new JTextField();
        oWinField.setHorizontalAlignment(JTextField.CENTER);
        oWinField.setBorder(empty);
        oWinField.setFont(new Font("Dialog", Font.PLAIN, 18));
        oWinField.setEditable(false);

        controlPanel.add(xWins);
        controlPanel.add(xWinField);
        controlPanel.add(tieCountLabel);
        controlPanel.add(tieField);
        controlPanel.add(oWins);
        controlPanel.add(oWinField);

        oWinField.setText("0");
        xWinField.setText("0");
        tieField.setText("0");

    }

    private void createControlPnl() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2));
        controlPnl.setBackground(Color.WHITE);
        clearButton = new JButton("Reset");
        clearButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        clearButton.addActionListener((ActionEvent ae) -> {
            int res = JOptionPane.showOptionDialog(null, "Are you sure you want to reset the game board and scores?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                clearBoard();
                for (int row1 = 0; row1 <= 2; row1++) {
                    for (int col1 = 0; col1 <= 2; col1++) {
                        GUIboard[row1][col1].setText(" ");
                    }
                }

                oWinField.setText("0");
                xWinField.setText("0");
                tieField.setText("0");
                oWinCount = 0;
                xWinCount = 0;
                tieCount = 0;
                JOptionPane.showMessageDialog(null, "Board and Scores reset", "Message", JOptionPane.INFORMATION_MESSAGE, image);
            }
            else if (res == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Canceled reset request", "Message", JOptionPane.INFORMATION_MESSAGE, image);
            }
            else if (res == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Canceled reset request", "Message", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        quitButton.addActionListener((ActionEvent ae) -> {
            int res = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            else if (res == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Canceled quit request", "Message", JOptionPane.INFORMATION_MESSAGE, image);
            }
            else if (res == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Canceled reset request", "Message", JOptionPane.INFORMATION_MESSAGE, image);
            }
        });
        controlPnl.add(quitButton);
        controlPnl.add(clearButton);
    }

    public static void validMove(String player, int row, int col) {
        board[row][col] = player;
    }

    public static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";

            }
        }
    }

    public static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X")) {
                xFlag = true;
            }if (board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O")) {
                oFlag = true;
            }if (! (xFlag && oFlag) ) {
                return false;
            }
            xFlag = oFlag = false;
        }
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X")) {
                xFlag = true;
            }if (board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O")) {
                oFlag = true;
            } if (! (xFlag && oFlag) ) {
                return false;
            }
        }

        xFlag = oFlag = false;
        if (board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") ) {
            xFlag = true;
        }
        if (board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") ) {
            oFlag = true;
        } if (! (xFlag && oFlag) ) {
            return false;
        }
        xFlag = oFlag = false;
        if (board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") ) {
            xFlag = true;
        } if (board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") ) {
            oFlag = true;
        } if (! (xFlag && oFlag) ) {
            return false;
        }
        return true;
    }

    public static boolean isWin (String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
                    return true;
                }
                if (board[0][2].equals(player) && board[1][1].equals(player) && board [2][0].equals(player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void CreateGame() {
        do {
            clearBoard();
            turnsCount = 0;
            for (int row = 0; row <= 2; row++) {
                for (int col = 0; col <= 2; col++) {
                    GUIboard[row][col] = new TicTacToeButton(row, col);
                    GUIboard[row][col].setBorder(new LineBorder(Color.BLUE));
                    GUIboard[row][col].setForeground(Color.BLACK);
                    GUIboard[row][col].setFont(new Font("Times New Roman", Font.ITALIC, 25));
                    gamePanel.add(GUIboard[row][col]);
                    GUIboard[row][col].setText(" ");
                    GUIboard[row][col].addActionListener(e -> {
                        TicTacToeButton clicked = (TicTacToeButton) e.getSource();
                        JFrame frame = new JFrame("JOptionPane");
                        turnsCount++;

                        if (!clicked.getText().isBlank()) {
                            JOptionPane.showMessageDialog(frame, "Spot taken. Make a valid move.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        clicked.setText(String.valueOf(player));
                        validMove(player, clicked.getRow(), clicked.getCol());

                        if (turnsCount >= 5 ) {
                            if (isWin(player)) {
                                int res = JOptionPane.showOptionDialog(frame, "Player " + player + " Wins!\nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    again = true;
                                } else if (res == JOptionPane.NO_OPTION) {
                                    clearBoard();
                                    turnsCount = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                } else if (res == JOptionPane.CLOSED_OPTION) {
                                    clearBoard();
                                    turnsCount = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                }
                                if (player.equals("X")) {
                                    xWinCount = xWinCount + 1;
                                    xWinField.setText(String.valueOf(xWinCount));
                                }
                                if (player.equals("O")) {
                                    oWinCount = oWinCount + 1;
                                    oWinField.setText(String.valueOf(oWinCount));
                                }
                                clearBoard();
                                turnsCount = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                player = "O";
                                message.setText("Player " + player + ", it's your turn.");
                            }
                        } if (turnsCount >= 7 ) {
                            if (isWin(player)) {
                                int res = JOptionPane.showOptionDialog(frame, "Player " + player +" Wins!\nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    again = true;
                                } else if (res == JOptionPane.NO_OPTION) {
                                    clearBoard();
                                    turnsCount = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                } else if (res == JOptionPane.CLOSED_OPTION) {
                                    clearBoard();
                                    turnsCount = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                }
                                if (player.equals("X")) {
                                    xWinCount = xWinCount + 1;
                                    xWinField.setText(String.valueOf(xWinCount));
                                }
                                if (player.equals("O")) {
                                    oWinCount = oWinCount + 1;
                                    oWinField.setText(String.valueOf(oWinCount));
                                }
                                clearBoard();
                                turnsCount = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                player = "O";
                                message.setText("Player " + player + ", it's your turn.");
                            }
                            if (isTie()) {
                                int res = JOptionPane.showOptionDialog(frame, "Tie Game! \nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                                if (res == JOptionPane.YES_OPTION) {
                                    again = true;
                                } else if (res == JOptionPane.NO_OPTION) {
                                    clearBoard();
                                    turnsCount = 0;
                                    for (int row1 = 0; row1 <= 2; row1++) {
                                        for (int col1 = 0; col1 <= 2; col1++) {
                                            GUIboard[row1][col1].setText(" ");
                                        }
                                    }
                                    System.exit(0);
                                }
                                tieCount = tieCount + 1;
                                tieField.setText(String.valueOf(tieCount));
                                clearBoard();
                                turnsCount = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                player = "O";
                                message.setText("Player " + player + ", it's your turn.");
                            }
                        }
                        if (player.equals("X")) {
                            player = "O";
                        } else {
                            player = "X";
                        }
                        message.setText("Player " + player + ", it's your turn.");
                    });
                }
            }
        }while (again);
    }



}
