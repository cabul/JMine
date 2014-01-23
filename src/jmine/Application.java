package jmine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import jmine.control.Command;
import jmine.control.NewGameCommand;
import jmine.persistence.SpriteSheet;
import jmine.ui.swing.ActionListenerFactory;
import jmine.ui.swing.ApplicationFrame;


public class Application {

    public static final String NEW_EASY = "Easy";
    public static final String NEW_NORMAL = "Normal";
    public static final String NEW_HARD = "Hard";
    
    private ApplicationFrame frame;
    
    private HashMap<String,Command> commands;
    
    public static void main(String[] args) throws IOException {
        SpriteSheet.getInstance().load("assets/spritesheet.png");
        new Application().start();
    }

    private void start() {
        frame = new ApplicationFrame(createFactory());
 
        setupCommands();
        
        frame.setVisible(true);
        
        commands.get(NEW_EASY).execute();
        
        frame.setLocationRelativeTo(null);
        
    }

    private ActionListenerFactory createFactory(){
        return new ActionListenerFactory() {

            @Override
            public ActionListener create(final String action) {
                return new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Command command = commands.get(action);
                        if(command == null){
                            return;
                        }
                        Debug.log("Executing Action \""+action+"\"");
                        command.execute();
                    }
                };
            }
        };
    }
    
    
    private void setupCommands() {
        commands = new HashMap<String, Command>();
        commands.put(NEW_EASY, NewGameCommand.Easy(frame));
        commands.put(NEW_NORMAL, NewGameCommand.Normal(frame));
        commands.put(NEW_HARD, NewGameCommand.Hard(frame));
    }
    
}
