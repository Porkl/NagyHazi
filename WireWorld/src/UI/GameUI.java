package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import game.CellType;
import game.GameFieldMatrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameUI {
    
    private Window window;
    private GameFieldMatrix matrix;

    private JPanel gameUIPanel;

    private JPanel gamePanel;
    private JButton[][] buttonGrid;

    private int rowCount;
    private int columnCount;

    public GameUI(Window window, GameFieldMatrix matrix) {
        this.window = window;
        this.matrix = matrix;

        rowCount = matrix.getOptions().getRowCount();
        columnCount = matrix.getOptions().getColumnCount();

        gameUIPanel = new JPanel(new BorderLayout());
        buttonGrid = new JButton[rowCount][columnCount];

        ActionListener menuButtonListener = new MenuButtonListener();

        JPanel upperPanel = new JPanel(new GridLayout(1,4));
        JButton resetButton = new JButton("Reset Table");
        JButton backToMenuButton = new JButton("Back to Main Menu");
        
        resetButton.addActionListener(menuButtonListener);
        backToMenuButton.addActionListener(menuButtonListener);

        upperPanel.add(resetButton);
        upperPanel.add(backToMenuButton);

        JButton startButton = new BasicArrowButton(BasicArrowButton.EAST);
        JButton pauseButton = new JButton("||");

        startButton.addActionListener(menuButtonListener);
        pauseButton.addActionListener(menuButtonListener);

        upperPanel.add(startButton);
        upperPanel.add(pauseButton);

        gameUIPanel.add(upperPanel, BorderLayout.NORTH);


        gamePanel = new JPanel(new GridLayout(rowCount,columnCount));
        GameButtonActionListener gameButtonListener = new GameButtonActionListener();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].addActionListener(gameButtonListener);
                buttonGrid[row][col].setBackground(Color.BLACK);
                gamePanel.add(buttonGrid[row][col]);
            }
        }
        
        gameUIPanel.add(gamePanel, BorderLayout.CENTER);
    }

    public void reDrawBoard(GameFieldMatrix matrix) {
        rowCount = matrix.getOptions().getRowCount();
        columnCount = matrix.getOptions().getColumnCount();
        buttonGrid = new JButton[rowCount][columnCount];

        gameUIPanel.remove(gamePanel);
        gamePanel.removeAll();
        gamePanel.setLayout(new GridLayout(rowCount, columnCount));

        GameButtonActionListener gameButtonListener = new GameButtonActionListener();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].addActionListener(gameButtonListener);
                buttonGrid[row][col].setBackground(Color.BLACK);
                gamePanel.add(buttonGrid[row][col]);
            }
        }

        gameUIPanel.add(gamePanel, BorderLayout.CENTER);
    }

    private class MenuButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Reset Table":
                    window.getGameUI().reDrawBoard(matrix);
                    window.revalidate();
                    break;

                case "Back to Main Menu":
                    gameUIPanel.setVisible(false);
                    window.remove(gameUIPanel);
                    window.add(window.getMainMenu().getMainPanel());
                    window.getMainMenu().getMainPanel().setVisible(true);
                    break;
                
                case "||":
                    //TODO
                    break;

                default: //PLAY BUTTON!!!
                    //TODO
                    System.out.println("Hello");
                    break;
            }        
        }
    }

    private class GameButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton b1 = (JButton)e.getSource();
            b1.setBackground(nextColor(b1.getBackground()));
            
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    if (buttonGrid[row][col] == e.getSource()) {
                        System.out.println(row + " " + col);
                        // matrix.getMatrix().get(row).set(col, b1);
                    }
                }
            }

            for (ArrayList<CellType> arrayList : matrix.getMatrix()) {
                for (CellType cellType : arrayList) {
                    System.out.printf(cellType.toString() + " ");
                }
                System.out.println();
            }
        }
        
    }

    private Color nextColor(Color prewiousColor) {
        if (prewiousColor == Color.BLACK) {
            return Color.YELLOW;
        } else if (prewiousColor == Color.YELLOW) {
            return Color.BLUE;
        } else if (prewiousColor == Color.BLUE) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    public JPanel getGameUIPanel() {
        return gameUIPanel;
    }
}
