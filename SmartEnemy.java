import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class SmartEnemy extends GameObject {

    private final int ENEMY_WIDTH = 60;
    private final int ENEMY_HEIGHT = 35;

    private GameObject player;

    private Image sprite = null;
    private File sourceImage;
    private GameHandler handler;

    public SmartEnemy(float x, float y, ID id, GameHandler handler) {
        super(x, y, id);

        this.handler = handler;

        for(int i =0; i<handler.objects.size(); i++){
            if(handler.objects.get(i).getID() == ID.Player){
                player = handler.objects.get(i);
            }

        }
        getSprite();
    }

    public void getSprite(){
        try{
            sourceImage = new File("smart_space.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,ENEMY_WIDTH-5,ENEMY_HEIGHT-5);
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

        //handler.addObject(new EnemyTrail(x,y,ID.Trail, Color.GREEN, 32,32,0.05f, handler));

        float difX = x - player.getX();
        float difY = y - player.getY();
        float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())* (y-player.getY()));

        velX = ((-1/distance)*difX);
        velY = ((-1/distance)*difY);

    }

    public void render(Graphics g){
        //g.setColor(Color.GREEN);
        //g.drawRect((int)x,(int)y,ENEMY_WIDTH,ENEMY_HEIGHT);
        g.drawImage(sprite,(int)x,(int)y,null);


    }
}
