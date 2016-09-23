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
public class AmmoDrop extends GameObject {

    private final int WIDTH = 50;
    private final int HEIGHT = 30;
    private Image sprite = null;
    private File sourceImage;
    private GameHandler handler;

    private Random r = new Random();

    public AmmoDrop(float x, float y, ID id, GameHandler handler) {
        super(x, y, id);

        velX =(r.nextInt(3 - -3) + -3);
        velY = 2;

        //velX = Game.clamp(r.nextInt(3),-3,3);
        //velY = Game.clamp(r.nextInt(3),-3,3);

        this.handler = handler;
        getSprite();
    }



    public void getSprite(){
        try{
            sourceImage = new File("ammo_test.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
    }

    public void tick(){
        x += velX;
        y += velY;



        /**
         if( y<=0 || y>= Game.HEIGHT-32){
         velY *= -1;
         }**/
        if(x<=0 || x>= Game.WIDTH-50){
            velX *= -1;
        }

        if(y > Game.HEIGHT){
            handler.removeObject(this);
            System.out.println("Lost a drop!");
        }



    }

    public void render(Graphics g){

        //g.setColor(Color.MAGENTA);
        //g.drawRect((int)x,(int)y,WIDTH,HEIGHT);

        g.drawImage(sprite,(int)x,(int)y,null);



    }
}
