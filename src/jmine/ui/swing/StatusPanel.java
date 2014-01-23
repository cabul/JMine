package jmine.ui.swing;

import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jmine.ui.StatusView;

public class StatusPanel extends JPanel implements StatusView{

    private JLabel clock;
    
    private JLabel label;

    private int time;
    
    private int tick;
    
    private Thread thread;
    
    private boolean running;
    
    private static final long DELTA = 100;
    
    public StatusPanel() {
        clock = new JLabel();
        this.add(clock);
        label = new JLabel();
        this.add(label);
        
    }
    
    @Override
    public void print(String status) {
        label.setText(status);
    }

    @Override
    public void startCounter() {
        thread.start();
    }
    
    public void setup(){
        clock.setText("");
        clock.repaint();
        time = 0;
        running = true;
        tick = 0;
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                update(clock);
                while(running){
                    try {
                        Thread.sleep(DELTA);
                    } catch (InterruptedException ex) {
                    }
                    tick += DELTA;
                    if(tick >= 1000){
                        time++;
                        update(clock);
                        tick = 0;
                    }
                }
                
            }
        });
    }

    @Override
    public void stopCounter() {
        running = false;
    }
    
    private String getThreeDigits(int time){
        if(time > 999){
            return "999";
        }
        String text = "";
        if(time < 100){
            text = "0";
        }
        if(time < 10){
            text += "0";
        }
        text += time;
        return text;
    }
    
    private void update(JLabel clock){
        clock.setText(getThreeDigits(time));
        clock.repaint();
    }
    
}
