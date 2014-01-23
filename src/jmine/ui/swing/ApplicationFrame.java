package jmine.ui.swing;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import jmine.Application;
import jmine.ui.GameView;
import jmine.ui.StatusView;

public class ApplicationFrame extends JFrame{

    private GameView gameView;
    private ActionListenerFactory factory;
    private StatusView statusView;

    public ApplicationFrame(ActionListenerFactory factory) {
        super("Java Mine");
        this.factory = factory;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setupComponents();
    }

    public GameView getGameView() {
        return gameView;
    }
    
    public StatusView getStatusView(){
        return statusView;
    }

    private void setupComponents() {
        this.setJMenuBar(createMenuBar());
        this.add(createGamePanel());
        this.add(createStatusPanel(),BorderLayout.SOUTH);
    }

    private JPanel createGamePanel() {
        GamePanel panel = new GamePanel(); 
        gameView = panel;
        return panel;
    }
    
    private JPanel createStatusPanel(){
        StatusPanel panel = new StatusPanel();
        statusView = panel;
        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(createNewMenu());
        
        return menuBar;
    }

    private JMenu createNewMenu() {
        JMenu menu = new JMenu("New");
        menu.add(createMenuItem(Application.NEW_EASY));
        menu.add(createMenuItem(Application.NEW_NORMAL));
        menu.add(createMenuItem(Application.NEW_HARD));
        return menu;
    }
    
    private JMenuItem createMenuItem(String label) {
        JMenuItem item = new JMenuItem(label);
        item.addActionListener(factory.create(label));
        return item;
    }
}
