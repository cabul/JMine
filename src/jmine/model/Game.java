package jmine.model;

import jmine.Debug;
import jmine.ui.Layer;
import jmine.ui.StatusView;

public class Game {
    
    private final int rows;
    private final int columns;
    
    private final int mines;
    
    private boolean init;
    
    private boolean gameover;
    
    private Layer layer;
    
    private GameBoard board;
    
    private MarkerPool pool;
    
    private StatusView statusView;
    
    private int tilesToDiscover;
    
    public Game(int rows, int columns, int mines, StatusView statusView) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        
        this.statusView = statusView;
        
        board = new GameBoard(rows, columns, mines);
        
        layer = new Layer(rows, columns, board);
        
        pool = new MarkerPool(mines);
        
        init = false;
        
        gameover = false;
        
        tilesToDiscover = rows*columns - mines;
        
        Debug.log("Creating Game",rows+"x"+columns+" Mines: "+mines);
        
        statusView.print("New Game");
        statusView.setup();
    }
    
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    
    public void selectTile(Position position){
        if(gameover){
            return;
        }
        if(!checkBounds(position)){
            return;
        }
        Debug.log("Select",position.getRow()+":"+position.getColumn());
        if(!init){
            initialize(position);
        }
        final int status = board.get(position).select();
        if( status == -1){
            gameover(false);
        }else {
            tilesToDiscover -= status;
            if(tilesToDiscover == 0){
                gameover(true);
            }
        }
        layer.refresh();
    }
    
    public void checkTile(Position position){
        if(gameover){
            return;
        }
        if(!init){
            return;
        }
        if(pool.hasMarker(position)){
            pool.deleteMarker(position);
            board.get(position).toggle();
            markerStatus();
        }else{
            if(pool.hasCapacity() && !board.isOpen(position)){
                pool.setMarker(position, board.isMine(position));
                board.get(position).toggle();
                markerStatus();
                if(pool.checkVictory()){
                    gameover(true);
                }
            }
        }
        layer.refresh();
    }
    
    public Layer getLayer(){
        return layer;
    }

    private boolean checkBounds(Position position) {
        return position.getRow() < rows &&
                position.getColumn() < columns;
    }
    
    private void gameover(boolean win){
        gameover = true;
        if(win){
            statusView.print("Victory!");
        }else{
            statusView.print("Game Over");
        }
        statusView.stopCounter();
    }
    
    private void markerStatus(){
        statusView.print("Markers Left: " + pool.markersLeft());
    }

    private void initialize(Position position) {
        Debug.log("Building Game Board");
        board.build(position);
        Debug.log("Finished Building");
        init = true;
        markerStatus();
        statusView.startCounter();
    }
    
}
