package jmine.model;

import java.util.HashMap;
import jmine.Debug;

public class MarkerPool {
    
    private final int max;
    private int capacity;
    
    private HashMap<Position,Boolean> pool;
    
    public MarkerPool(int capacity) {
        max = capacity;
        this.capacity = capacity;
        pool = new HashMap<Position, Boolean>(capacity);
    }
    
    public boolean hasMarker(Position position){
        return pool.containsKey(position);
    }
    
    public void deleteMarker(Position position){
        capacity++;
        pool.remove(position);
    }
    
    public void setMarker(Position position, boolean mine){
        capacity--;
        pool.put(position, mine);
    }
    
    public boolean checkVictory(){
        if(capacity > 0){
            return false;
        }
        for(Position pos : pool.keySet()){
            if(!pool.get(pos)){
                return false;
            }
        }
        return true;
    }
    
    public boolean hasCapacity(){
        return capacity > 0;
    }
    
    public int markersLeft(){
        return capacity;
    }
    
}
