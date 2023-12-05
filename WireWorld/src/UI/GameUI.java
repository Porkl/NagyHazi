package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;

import game.CellType;
import game.GameFieldMatrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    
    private Window window;
    private boolean currentlyPlaying;

    private JPanel gameUIPanel;

    private JPanel gamePanel;
    private JButton[][] buttonGrid;

    private int rowCount;
    private int columnCount;

    public GameUI(Window window) {
        this.window = window;

        rowCount = window.getLogic().getGameFieldMatrix().getOptions().getRowCount();
        columnCount = window.getLogic().getGameFieldMatrix().getOptions().getColumnCount();

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
                buttonGrid[row][col].setBackground(colorFromCellType(window.getLogic().getGameFieldMatrix().getMatrix().get(row + 1).get(col + 1)));
                gamePanel.add(buttonGrid[row][col]);
            }
        }

        gameUIPanel.add(gamePanel, BorderLayout.CENTER);
    }

    private Color colorFromCellType(CellType c) {
        if (c == CellType.EMPTY) {
            return Color.BLACK;
        } else if (c == CellType.CONDUCTOR) {
            return Color.YELLOW;
        } else if (c == CellType.HEAD) {
            return Color.BLUE;
        } else {
            return Color.RED;
        }
    }

    private class MenuButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Reset Table":
                    window.getLogic().resetTable();
                    window.getGameUI().reDrawBoard(window.getLogic().getGameFieldMatrix());
                    window.revalidate();
                    break;

                case "Back to Main Menu":
                    gameUIPanel.setVisible(false);
                    window.remove(gameUIPanel);
                    window.add(window.getMainMenu().getMainPanel());
                    window.getMainMenu().getMainPanel().setVisible(true);
                    break;
                
                case "||":
                    currentlyPlaying = false;
                    break;

                default: //PLAY BUTTON!!!
                    currentlyPlaying = true;
                    Thread t = new Thread() {
                        public void run() {
                            while(currentlyPlaying) {
                                window.getLogic().step();
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        window.getGameUI().reDrawBoard(window.getLogic().getGameFieldMatrix());
                                        window.revalidate();
                                    }
                                });

                                try {
                                    Thread.sleep(400);
                                } catch(InterruptedException ie){}
                            }
                        }
                    };
                    t.start();
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
                        window.getLogic().clickStep(row, col);
                    }
                }
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
