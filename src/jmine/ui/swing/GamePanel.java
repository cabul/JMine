package jmine.ui.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import jmine.model.Game;
import jmine.model.Position;
import jmine.persistence.SpriteSheet;
import jmine.ui.Drawable;
import jmine.ui.GameView;

public class GamePanel extends JPanel implements GameView{

    private final static int TILE_SIZE = 32;
    
    private Game game;

    private Position highlight;
    
    public GamePanel() {
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON1){
                    game.selectTile(getPosition(me.getX(),me.getY()));
                }else if(me.getButton() == MouseEvent.BUTTON3){
                    game.checkTile(getPosition(me.getX(),me.getY()));
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
                highlight = null;
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent me) {
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                final Position pos = getPosition(me.getX(),me.getY());
                if(!pos.equals(highlight)){
                    highlight = pos;
                    repaint();
                }
            }
        });
        
    }
    
    @Override
    public void setup(Game game) {
        this.game = game;
        this.setPreferredSize(getDimension());
        repaint();
    }

    private Dimension getDimension() {
        return new Dimension(game.getRows()*TILE_SIZE, game.getColumns()*TILE_SIZE);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if(game == null){
            return;
        }
        Drawable[][] draws = game.getLayer().getDrawables();
        SpriteSheet sprites = SpriteSheet.getInstance();
        int row;
        int column;
        for(row = 0; row < game.getRows(); row++){
            for(column = 0; column < game.getColumns(); column++){
                graphics.drawImage(
                        sprites.get(draws[row][column]),
                        column*TILE_SIZE,row*TILE_SIZE,null);
            }
        }
        if(highlight == null){
            return;
        }
        row = highlight.getRow();
        column = highlight.getColumn();
        graphics.drawImage(
                        sprites.getHighlight(draws[row][column]),
                        column*TILE_SIZE,row*TILE_SIZE,null);
    }
    
    private Position getPosition(int x, int y){
        int row = (int) y / TILE_SIZE;
        int column = (int) x / TILE_SIZE;
        return new Position(row, column);
    }

}
