package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.CellType;
import game.GameFieldMatrix;
import game.Options;

public class MainMenu {

    private Window window;
    private JPanel mainPanel;

    public MainMenu(Window window) {
        this.window = window;
        mainPanel = new JPanel(new GridLayout(5,1));

        JLabel headerLabel = new JLabel("WireWorld", JLabel.CENTER);
        JButton startButton = new JButton("New WireWorld Map");
        JButton loadButton = new JButton("Load Previous Map");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit the Game");

        ActionListener buttonListener = new ButtonListener();

        startButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        settingsButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        mainPanel.add(headerLabel);
        mainPanel.add(startButton);
        mainPanel.add(loadButton);
        mainPanel.add(settingsButton);
        mainPanel.add(exitButton);
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "New WireWorld Map":
                    mainPanel.setVisible(false);
                    window.remove(mainPanel);
                    window.getGameUI().reDrawBoard(window.getLogic().getGameFieldMatrix());
                    window.add(window.getGameUI().getGameUIPanel());
                    window.getGameUI().getGameUIPanel().setVisible(true);
                    break;

                case "Load Previous Map":
                    loadPreviousGame();
                    mainPanel.setVisible(false);
                    window.remove(mainPanel);
                    window.getGameUI().reDrawBoard(window.getLogic().getGameFieldMatrix());
                    window.add(window.getGameUI().getGameUIPanel());
                    window.getGameUI().getGameUIPanel().setVisible(true);
                    break;

                case "Settings":
                    mainPanel.setVisible(false);
                    window.remove(mainPanel);
                    window.add(window.getSettingsMenu().getSettingsPanel());
                    window.getSettingsMenu().getSettingsPanel().setVisible(true);
                    break;

                case "Exit the Game":
                    save();
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }
    }

    private void save() {
        try {
            FileOutputStream f = new FileOutputStream("SavedMatrix.txt");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(window.getLogic().getGameFieldMatrix().getOptions());
            out.writeObject(window.getLogic().getGameFieldMatrix().getMatrix());
            out.close();
        } catch (IOException ex) {
            System.err.println("IOEception2");
        }
    } 

    private void loadPreviousGame() {
        try {
            FileInputStream f = new FileInputStream("SavedMatrix.txt");
            ObjectInputStream in = new ObjectInputStream(f);
            window.getLogic().getGameFieldMatrix().setOptions((Options)in.readObject());
            window.getLogic().getGameFieldMatrix().setMatrix((ArrayList<ArrayList<CellType>>)in.readObject());
            in.close();
        } catch (IOException ex) {
            System.err.println("IOEception1");
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException");
        }    
    }
    
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
