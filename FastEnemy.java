import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class FastEnemy extends GameObject {

    private final int ENEMY_WIDTH = 20;
    private final int ENEMY_HEIGHT = 20;
    private Image sprite = null;
    private File sourceImage;
    private GameHandler handler;

    public FastEnemy(float x, float y, ID id, GameHandler handler) {
        super(x, y, id);

        velX =6;
        velY = 6;
        this.handler = handler;
        //getSprite();
    }

    public void getSprite(){
        try{
            sourceImage = new File("asteroid.jpg");
            sprite = ImageIO.read(sourceImage);
            System.out.println(sprite);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
    }
    public void tick(){
        x += velX;
        y += velY;

        if( y<=0 || y>= Game.HEIGHT-32){
            velY *= -1;
        }
        if(x<=0 || x>= Game.WIDTH-16){
            velX *= -1;
        }

        handler.addObject(new EnemyTrail(x,y,ID.Trail, Color.MAGENTA, 32,32,0.05f, handler));

    }

    public void render(Graphics g){
        g.setColor(Color.MAGENTA);
        g.fillRect((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
        //g.drawImage(sprite,x,y,null);


    }
}