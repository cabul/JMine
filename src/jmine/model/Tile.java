package jmine.model;

import jmine.ui.Drawable;

public abstract class Tile {
    
    private boolean open;
    private Drawable drawable;
    private Position position;
    
    public Tile(Position position) {
        this.position = position;
        open = false;
        drawable = Drawable.TILE_CLOSED;
    }
    
    protected abstract int turn();
    
    public int select(){
        if(open){
            return 0;
        }
        open = true;
        return this.turn();
    }

    public Drawable getDrawable() {
        return drawable;
    }
    
    public Position getPosition(){
        return position;
    }
    
    protected void setDrawable(Drawable draw){
        this.drawable = draw;
    }
    
    public void toggle(){
        if(drawable == Drawable.TILE_CLOSED){
            drawable = Drawable.TILE_MARKED;
        } else if(drawable == Drawable.TILE_MARKED){
            drawable = Drawable.TILE_CLOSED;
        }
    }
    
    public boolean isOpen(){
        return open;
    }
    
}
