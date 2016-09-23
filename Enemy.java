import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class Enemy extends GameObject {

    private final int ENEMY_WIDTH = 52;
    private final int ENEMY_HEIGHT = 50;
    private Image sprite = null;
    private File sourceImage;
    private GameHandler handler;

    public Enemy(float x, float y, ID id, GameHandler handler) {
        super(x, y, id);

        velX =2;
        velY = 2;
        this.handler = handler;
        getSprite();
    }



    public void getSprite(){
        try{
            sourceImage = new File("asteroid_1.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
    }

    /**
     * /What if the enemies collide???
    private void collision(){
        for(int i =0; i<handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);
            if (handler.objects.size() > 3) {
                if ( temp.getID() == ID.FastEnemy || temp.getID() == ID.SmartEnemy) {
                    if (getBounds().intersects(temp.getBounds())) {
                        System.out.println("missle collision");
                        handler.objects.remove(temp);
                        handler.objects.remove(this);
                    }

                }
            }
        }
    }
     **/
    public void tick(){
        if(Game.gameState == Game.STATE.Game) {


            x += velX;
            y += velY;

            if (y <= 0 || y >= Game.HEIGHT - 60) {
                velY *= -1;
            }
            if (x <= 0 || x >= Game.WIDTH - 48) {
                velX *= -1;
            }
        }
        else if(Game.gameState == Game.STATE.Pause){

            velX = 0;
            velY = 0;
        }


        //handler.addObject(new EnemyTrail(x,y,ID.Trail, Color.RED, 16,16,0.02f, handler));


    }

    public void render(Graphics g){
        //g.setColor(Color.RED);
        //g.drawRect((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
        g.drawImage(sprite,(int)x,(int)y,null);


    }
}
