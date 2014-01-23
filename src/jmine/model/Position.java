package jmine.model;

import jmine.Debug;

public final class Position {
    
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static Position random(int rows, int columns){
        return new Position(
                (int) (Math.random() * rows),
                (int) (Math.random() * columns) );
    }

    @Override
    public int hashCode() {
        return 100*this.row+this.column;
    }

    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Position)){
            return false;
        }
        final Position other = (Position) obj;
        return other.row == this.row
                && other.column == this.column;
    }

    @Override
    public String toString() {
        return "("+row+","+column+")";
    }
    
    public Position[] getAdjacents2(Position max){
        final Position[] adj;
        if(row == 0 && column == 0){
            adj = new Position[3];
            adj[0] = new Position(row+1,column);
            adj[1] = new Position(row+1,column+1);
            adj[2] = new Position(row,column+1);
        }else if(row == 0 && column == max.column){
            adj = new Position[3];
            adj[0] = new Position(row+1,column);
            adj[1] = new Position(row+1,column-1);
            adj[2] = new Position(row,column-1);
        }else if(row == max.row && column == 0){
            adj = new Position[3];
            adj[0] = new Position(row,column+1);
            adj[1] = new Position(row-1,column+1);
            adj[2] = new Position(row-1,column);
        }else if(row == max.row && column == max.column){
            adj = new Position[3];
            adj[0] = new Position(row,column-1);
            adj[1] = new Position(row-1,column-1);
            adj[2] = new Position(row-1,column);
        }else if(row == 0){
            adj = new Position[5];
            adj[0] = new Position(row,column-1);
            adj[1] = new Position(row,column+1);
            adj[2] = new Position(row+1,column-1);
            adj[3] = new Position(row+1,column);
            adj[4] = new Position(row+1,column+1);
        }else if(row == max.row){
            adj = new Position[5];
            adj[0] = new Position(row,column-1);
            adj[1] = new Position(row,column+1);
            adj[2] = new Position(row-1,column-1);
            adj[3] = new Position(row-1,column);
            adj[4] = new Position(row-1,column+1);
        }else if(column == 0){
            adj = new Position[5];
            adj[0] = new Position(row-1,column);
            adj[1] = new Position(row+1,column);
            adj[2] = new Position(row-1,column+1);
            adj[3] = new Position(row,column+1);
            adj[4] = new Position(row+1,column+1);
        }else if(column == max.column){
            adj = new Position[5];
            adj[0] = new Position(row-1,column);
            adj[1] = new Position(row+1,column);
            adj[2] = new Position(row-1,column-1);
            adj[3] = new Position(row,column-1);
            adj[4] = new Position(row+1,column-1);
        }else{
            adj = new Position[8];
            adj[0] = new Position(row-1,column-1);
            adj[1] = new Position(row-1,column);
            adj[2] = new Position(row-1,column+1);
            adj[3] = new Position(row,column-1);
            adj[4] = new Position(row,column+1);
            adj[5] = new Position(row+1,column-1);
            adj[6] = new Position(row+1,column);
            adj[7] = new Position(row+1,column+1);
        }
        
        return adj;
    }
    
    public Position[] getAdjacents(Position max){
        int length = (isCorner(max)?3:isEdge(max)?5:8);
        final Position[] adj = new Position[length];
        fill(max,adj);
        return adj;
    }
    
    private boolean isCorner(Position max){
        return row==0 && column==0 ||
                row==0 && column==max.column ||
                row==max.row && column==0 ||
                row==max.row && column==max.column;
                
    }
    
    private boolean isEdge(Position max){
        return row==0 || column==0 ||
                row==max.row || column==max.column;
                
    }
    
    private void fill(Position max, Position[] adj){
        int count = 0;
        for(int r = row-1; r <= row+1; r++){
            for(int c = column-1; c <= column+1; c++){
                Position pos = new Position(r,c);
                if(pos.inBounds(max) && !pos.equals(this)){
                    adj[count++] = pos;
                }
            }
        }
    }
    
    public boolean inBounds(Position max){
        return row <= max.row && column <= max.column &&
                row >= 0 && column >= 0;
    }
}

