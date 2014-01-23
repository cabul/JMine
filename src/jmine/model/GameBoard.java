package jmine.model;

import jmine.Debug;
import jmine.ui.Drawable;

public final class GameBoard {

    private int rows;
    private int columns;
    private int mines;

    private Tile[][] tiles;
    
    public GameBoard(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        tiles = new Tile[rows][columns];
    }

    private static class Mine extends Tile{

        private Tile[] mines;

        public Mine(Position position, Tile[] mines) {
            super(position);
            this.mines = mines;
        }
        
        @Override
        protected int turn() {
            for(Tile tile : mines){
                tile.setDrawable(Drawable.TILE_BOMB);
            }
            return -1;
        }
        
    }
    
    private static class Sand extends Tile{

        private Tile[] adjacents;

        public Sand(Position position) {
            super(position);
        }
        
        @Override
        protected int turn() {
            int turned = 1;
            int mines = 0;
            for(Tile tile : adjacents){
                if(tile instanceof Mine){
                    mines++;
                }
            }
            switch(mines){
                case 0:
                    setDrawable(Drawable.TILE_EMPTY);
                    break;
                case 1:
                    setDrawable(Drawable.TILE_ONE);
                    break;
                case 2:
                    setDrawable(Drawable.TILE_TWO);
                    break;
                case 3:
                    setDrawable(Drawable.TILE_THREE);
                    break;
                case 4:
                    setDrawable(Drawable.TILE_FOUR);
                    break;
                case 5:
                    setDrawable(Drawable.TILE_FIVE);
                    break;
                case 6:
                    setDrawable(Drawable.TILE_SIX);
                    break;
                case 7:
                    setDrawable(Drawable.TILE_SEVEN);
                    break;
                case 8:
                    setDrawable(Drawable.TILE_EIGHT);
                    break;
            }
            if(mines == 0){
                for(Tile tile : adjacents){
                    turned += tile.select();
                }
            }
            return turned;
        }

        private void setAdjacents(Tile[] adjacents){
            this.adjacents = adjacents;
        }
        
    }
    
    public void build(Position seed){
        Tile[] mineTiles = new Tile[mines];
        Position[] adj = seed.getAdjacents(new Position(rows,columns));
        Debug.log("GameBoard");
        while(mines > 0){
            final Position mine = Position.random(rows, columns);
            boolean overlay = false;
            for(Position pos : adj){
                if(mine.equals(pos)){
                    overlay = true;
                    break;
                }
            }
            if(!overlay && !mine.equals(seed) && tiles[mine.getRow()][mine.getColumn()] == null){
                final Tile tile = new Mine(new Position(mine.getRow(),mine.getColumn()),mineTiles);
                tiles[mine.getRow()][mine.getColumn()] = tile;
                mineTiles[--mines] = tile;
            }
        }
        
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                if(tiles[row][column] == null){
                    tiles[row][column] = new Sand(new Position(row,column));
                }
            }
        }
        
        link(tiles);
    }
    
    private void link(Tile[][] tiles){
        Position max = new Position(rows-1,columns-1);
        for(Tile[] row : tiles){
            for(Tile tile : row){
                if(tile instanceof Mine){
                    break;
                }
                final Position[] adj = tile.getPosition().getAdjacents(max);
                Debug.log("Adjacents: ",adj.length+"");
                final Tile[] link = new Tile[adj.length];
                for(int i = 0; i< adj.length; i++){
                    link[i] = get(adj[i]);
                }
                ((Sand)tile).setAdjacents(link);
            }
        }
    }
    
    private void linkCorners(Tile[][] tiles){
        if(tiles[0][0] instanceof Sand){
            final Tile[] adj = new Tile[3];
            adj[0] = tiles[0][1];
            adj[1] = tiles[1][1];
            adj[2] = tiles[1][0];
            ((Sand)tiles[0][0]).setAdjacents(adj);
        }
        if(tiles[rows-1][0] instanceof Sand){
            final Tile[] adj = new Tile[3];
            adj[0] = tiles[rows-2][0];
            adj[1] = tiles[rows-2][1];
            adj[2] = tiles[rows-1][1];
            ((Sand)tiles[rows-1][0]).setAdjacents(adj);
        }
        if(tiles[0][columns-1] instanceof Sand){
            final Tile[] adj = new Tile[3];
            adj[0] = tiles[0][columns-2];
            adj[1] = tiles[1][columns-2];
            adj[2] = tiles[1][columns-1];
            ((Sand)tiles[0][columns-1]).setAdjacents(adj);
        }
        if(tiles[rows-1][columns-1] instanceof Sand){
            final Tile[] adj = new Tile[3];
            adj[0] = tiles[rows-1][columns-2];
            adj[1] = tiles[rows-2][columns-2];
            adj[2] = tiles[rows-2][columns-1];
            ((Sand)tiles[rows-1][columns-1]).setAdjacents(adj);
        }
    }
    
    private void linkEdges(Tile[][] tiles){
        int row = 0;
        int column;
        for(column = 1; column < columns-1; column++){
            if(tiles[row][column] instanceof Sand){
                final Tile[] adj = new Tile[5];
                adj[0] = tiles[row][column-1];
                adj[1] = tiles[row][column+1];
                adj[2] = tiles[row+1][column-1];
                adj[3] = tiles[row+1][column];
                adj[4] = tiles[row+1][column+1];
                ((Sand)tiles[row][column]).setAdjacents(adj);
            }
        }
        row = rows-1;
        for(column = 1; column < columns-1; column++){
            if(tiles[row][column] instanceof Sand){
                final Tile[] adj = new Tile[5];
                adj[0] = tiles[row][column-1];
                adj[1] = tiles[row][column+1];
                adj[2] = tiles[row-1][column-1];
                adj[3] = tiles[row-1][column];
                adj[4] = tiles[row-1][column+1];
                ((Sand)tiles[row][column]).setAdjacents(adj);
            }
        }
        column = 0;
        for(row = 1; row < rows-1; row++){
            if(tiles[row][column] instanceof Sand){
                final Tile[] adj = new Tile[5];
                adj[0] = tiles[row-1][column];
                adj[1] = tiles[row+1][column];
                adj[2] = tiles[row-1][column+1];
                adj[3] = tiles[row][column+1];
                adj[4] = tiles[row+1][column+1];
                ((Sand)tiles[row][column]).setAdjacents(adj);
            }
        }
        column = columns-1;
        for(row = 1; row < rows-1; row++){
            if(tiles[row][column] instanceof Sand){
                final Tile[] adj = new Tile[5];
                adj[0] = tiles[row-1][column];
                adj[1] = tiles[row+1][column];
                adj[2] = tiles[row-1][column-1];
                adj[3] = tiles[row][column-1];
                adj[4] = tiles[row+1][column-1];
                ((Sand)tiles[row][column]).setAdjacents(adj);
            }
        }
    }
    
    private void linkCenter(Tile[][] tiles){
        for(int row = 1; row < rows-1; row++){
            for(int column = 1; column < columns-1; column++){
                if(tiles[row][column] instanceof Sand){
                    final Tile[] adj = new Tile[8];
                    adj[0] = tiles[row-1][column-1];
                    adj[1] = tiles[row-1][column];
                    adj[2] = tiles[row][column-1];
                    adj[3] = tiles[row+1][column-1];
                    adj[4] = tiles[row-1][column+1];
                    adj[5] = tiles[row+1][column+1];
                    adj[6] = tiles[row][column+1];
                    adj[7] = tiles[row+1][column];
                ((Sand)tiles[row][column]).setAdjacents(adj);
            }
            }
        }
    }
 
    public Tile[][] getAll(){
        return tiles;
    }
    
    public Tile get(Position position){
        return tiles[position.getRow()][position.getColumn()];
    }
    
    public boolean isMine(Position position){
        return get(position) instanceof Mine;
    }
    
    public boolean isOpen(Position position){
        return get(position).isOpen();
    }
    
}
