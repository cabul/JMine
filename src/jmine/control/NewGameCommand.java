package jmine.control;

import jmine.model.Game;
import jmine.ui.swing.ApplicationFrame;

public class NewGameCommand extends MenuCommand{

    private final int rows;
    private final int columns;
    private final int mines;
    
    private NewGameCommand(ApplicationFrame frame, int rows, int columns, int mines) {
        super(frame);
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
    }
    
    public static NewGameCommand Easy(ApplicationFrame frame){
        return new NewGameCommand(frame,8,8,10);
    }
    
    public static NewGameCommand Normal(ApplicationFrame frame){
        return new NewGameCommand(frame,12,12,25);
    }
    
    public static NewGameCommand Hard(ApplicationFrame frame){
        return new NewGameCommand(frame,16,16,40);
    }

    @Override
    public void execute() {
        frame.getGameView().setup(new Game(rows,columns,mines,frame.getStatusView()));
        frame.setResizable(true);
        frame.pack();
        frame.setResizable(false);
    }
    
}
