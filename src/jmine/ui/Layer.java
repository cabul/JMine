package jmine.ui;

import jmine.model.GameBoard;
import jmine.model.Tile;

public class Layer {
    
    private final Drawable[][] drawables;

    private final int rows;
    private final int columns;
    
    private final GameBoard board;
    
    public Layer(int rows, int columns, GameBoard board) {
        this.rows = rows;
        this.columns = columns;
        drawables = new Drawable[rows][columns];
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                drawables[row][column] = Drawable.TILE_CLOSED;
            }
        }
        this.board = board;
    }
    
    public void refresh(){
        Tile[][] tiles = board.getAll();
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                drawables[row][column] = tiles[row][column].getDrawable();
            }
        }
    }
    
    public Drawable[][] getDrawables(){
        return drawables;
    }
    
}
