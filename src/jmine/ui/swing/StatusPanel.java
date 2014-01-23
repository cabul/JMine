package jmine.ui.swing;

import javax.swing.JLabel;
import javax.swing.JPanel;
import jmine.ui.StatusView;

public class StatusPanel extends JPanel implements StatusView{

    private JLabel label;

    public StatusPanel() {
        label = new JLabel("Status");
        this.add(label);
    }
    
    @Override
    public void print(String status) {
        label.setText(status);
    }
    
}
