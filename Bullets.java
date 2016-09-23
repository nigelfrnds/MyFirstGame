import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class Bullets extends GameObject {

    private final int ENEMY_WIDTH = 10;
    private final int ENEMY_HEIGHT = 30;
    private Image sprite = null;
    private File sourceImage;
    private GameHandler handler;

    public Bullets(float x, float y, ID id, GameHandler handler) {
        super(x, y, id);

        velX =0;
        velY = -7;
        this.handler = handler;
        getSprite();
    }



    public void getSprite(){
        try{
            sourceImage = new File("bullet.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
    }

    public void collision(){
        for(int i =0; i<handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if(temp.getID() == ID.Enemy || temp.getID() == ID.FastEnemy){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.score += 25;
                    System.out.println("Impact!");
                    handler.removeObject(temp);
                    handler.removeObject(this);
                }
            }
            else if(temp.getID() == ID.SmartEnemy){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.score += 50;
                    System.out.println("Impact!");
                    handler.removeObject(temp);
                    handler.removeObject(this);
                }
            }
        }
    }


    public void tick(){
        x += velX;
        y += velY;


        if(y <0){
            handler.removeObject(this);
            System.out.println("Bullet Discarded!");
        }
        collision();




    }

    public void render(Graphics g){
        //g.drawRect((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
        g.drawImage(sprite,(int)x,(int)y,null);


    }
}
