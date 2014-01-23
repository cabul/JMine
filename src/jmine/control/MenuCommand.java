package jmine.control;

import jmine.ui.swing.ApplicationFrame;


public abstract class MenuCommand implements Command{

    protected final ApplicationFrame frame;
    
    public MenuCommand(ApplicationFrame frame) {
        this.frame = frame;
    }
}
