import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class NukeDrop extends GameObject {

    private final int ENEMY_WIDTH = 50;
    private final int ENEMY_HEIGHT = 30;
    private Image sprite = null;
    private File sourceImage;
    private GameHandler handler;

    private Random r = new Random();

    public NukeDrop(float x, float y, ID id, GameHandler handler) {
        super(x, y, id);

        velX =(r.nextInt(3 - -3) + -3);
        velY = 2;
        this.handler = handler;
        getSprite();
    }



    public void getSprite(){
        try{
            sourceImage = new File("nuke_drop.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file" + getClass());
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
    }

    public void collision(){}

    public void tick(){
        x += velX;
        y += velY;



        if(x<=0 || x>= Game.WIDTH-20){
            velX *= -1;
        }

        if(y > Game.HEIGHT){
            handler.removeObject(this);
            System.out.println("Lost a drop!");
        }

    }

    public void render(Graphics g){
        //g.setColor(Color.ORANGE);
        //g.drawRect((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
        g.drawImage(sprite,(int)x,(int)y,null);


    }
}
