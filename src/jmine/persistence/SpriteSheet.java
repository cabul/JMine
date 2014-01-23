package jmine.persistence;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import jmine.ui.Drawable;

public class SpriteSheet {
    
    private static SpriteSheet instance;

    HashMap<Drawable,BufferedImage> sprites;
    HashMap<Drawable,BufferedImage> highlight;
    
    private SpriteSheet() {
        sprites = new HashMap<Drawable, BufferedImage>();
        highlight = new HashMap<Drawable, BufferedImage>();
    }
    
    public static SpriteSheet getInstance(){
        if(instance==null){
            instance = new SpriteSheet();
        }
        return instance;
    }
    
    public void load(String path) throws IOException{
        BufferedImage fullImage = ImageIO.read(new File(path));
        fullImage.getHeight();
        sprites.put(Drawable.TILE_EMPTY, fullImage.getSubimage(0, 0, 32, 64));
        sprites.put(Drawable.TILE_ONE, fullImage.getSubimage(32, 0, 32, 64));
        sprites.put(Drawable.TILE_TWO, fullImage.getSubimage(64, 0, 32, 64));
        sprites.put(Drawable.TILE_THREE, fullImage.getSubimage(96, 0, 32, 64));
        sprites.put(Drawable.TILE_FOUR, fullImage.getSubimage(128, 0, 32, 64));
        sprites.put(Drawable.TILE_FIVE, fullImage.getSubimage(160, 0, 32, 64));
        sprites.put(Drawable.TILE_SIX, fullImage.getSubimage(192, 0, 32, 64));
        sprites.put(Drawable.TILE_SEVEN, fullImage.getSubimage(224, 0, 32, 64));
        sprites.put(Drawable.TILE_EIGHT, fullImage.getSubimage(256, 0, 32, 64));
        sprites.put(Drawable.TILE_CLOSED, fullImage.getSubimage(288, 0, 32, 64));
        sprites.put(Drawable.TILE_MARKED, fullImage.getSubimage(320, 0, 32, 64));
        sprites.put(Drawable.TILE_BOMB, fullImage.getSubimage(352, 0, 32, 64));
        
        highlight.put(Drawable.TILE_EMPTY, fullImage.getSubimage(0, 32, 32, 32));
        highlight.put(Drawable.TILE_ONE, fullImage.getSubimage(32, 32, 32, 32));
        highlight.put(Drawable.TILE_TWO, fullImage.getSubimage(64, 32, 32, 32));
        highlight.put(Drawable.TILE_THREE, fullImage.getSubimage(96, 32, 32, 32));
        highlight.put(Drawable.TILE_FOUR, fullImage.getSubimage(128, 32, 32, 32));
        highlight.put(Drawable.TILE_FIVE, fullImage.getSubimage(160, 32, 32, 32));
        highlight.put(Drawable.TILE_SIX, fullImage.getSubimage(192, 32, 32, 32));
        highlight.put(Drawable.TILE_SEVEN, fullImage.getSubimage(224, 32, 32, 32));
        highlight.put(Drawable.TILE_EIGHT, fullImage.getSubimage(256, 32, 32, 32));
        highlight.put(Drawable.TILE_CLOSED, fullImage.getSubimage(288, 32, 32, 32));
        highlight.put(Drawable.TILE_MARKED, fullImage.getSubimage(320, 32, 32, 32));
        highlight.put(Drawable.TILE_BOMB, fullImage.getSubimage(352, 32, 32, 32));
        
        
        /*
        sprites.put(Drawable.TILE_ONE, fullImage.getSubimage(64, 0, 64, 64));
        sprites.put(Drawable.TILE_TWO, fullImage.getSubimage(128, 0, 64, 64));
        sprites.put(Drawable.TILE_THREE, fullImage.getSubimage(192, 0, 64, 64));
        sprites.put(Drawable.TILE_FOUR, fullImage.getSubimage(256, 0, 64, 64));
        sprites.put(Drawable.TILE_FIVE, fullImage.getSubimage(320, 0, 64, 64));
        sprites.put(Drawable.TILE_SIX, fullImage.getSubimage(384, 0, 64, 64));
        sprites.put(Drawable.TILE_SEVEN, fullImage.getSubimage(448, 0, 64, 64));
        sprites.put(Drawable.TILE_EIGHT, fullImage.getSubimage(512, 0, 64, 64));
        sprites.put(Drawable.TILE_EMPTY, fullImage.getSubimage(0, 0, 64, 64));
        sprites.put(Drawable.TILE_CLOSED, fullImage.getSubimage(640, 0, 64, 64));
        sprites.put(Drawable.TILE_MARKED, fullImage.getSubimage(704, 0, 64, 64));
        sprites.put(Drawable.TILE_BOMB, fullImage.getSubimage(576, 0, 64, 64));
        */
    }
    
    public BufferedImage get(Drawable drawable){
        return sprites.get(drawable);
    }
    
    public BufferedImage getHighlight(Drawable drawable){
        return highlight.get(drawable);
    }
    
}
