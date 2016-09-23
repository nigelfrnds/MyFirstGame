import ID.ID;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.RecursiveAction;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class Player extends GameObject {

    private final int PLAYER_WIDTH = 75;
    private final int PLAYER_HEIGHT = 45;
    private Image sprite = null;
    private File sourceImage;


    private GameHandler handler;

    public Player(float x, float y, ID id, GameHandler handler){
        super(x,y,id);
        this.handler = handler;
        canShoot = false;
        getSprite();


    }

    public void getSprite(){
        try{
            sourceImage = new File("ship_test.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void collision(){
        for(int i =0; i<handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if(temp.getID() == ID.Enemy || temp.getID() == ID.FastEnemy || temp.getID() == ID.SmartEnemy){
                //collision code
                if(getBounds().intersects(temp.getBounds()) && HUD.canIntersect){
                    HUD.HEALTH -= 2;
                    temp.setVelX(temp.getVelX() + 0.3f);

                }
            }

            if(temp.getID() == ID.HealthDrop && HUD.HEALTH < 100){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.HEALTH +=20;
                    handler.removeObject(temp);
                    System.out.println("Health Drop Collected");
                }
            }

            if(temp.getID() == ID.ShieldDrop ){
                if(getBounds().intersects(temp.getBounds())){
                    HUD.canIntersect = false;
                    handler.removeObject(temp);
                    System.out.println("Shield Drop Collected");
                }
            }

            if(temp.getID() == ID.SpeedDrop && KeyInput.boostOffset < 3){
                if(getBounds().intersects(temp.getBounds())){
                    handler.removeObject(temp);
                        KeyInput.boostOffset += 2;

                    System.out.println("Current Speed: " + this.getVelX() + ", " + this.getVelY());
                    System.out.println("BoostOffset: " + KeyInput.boostOffset);

                }
            }

            if(temp.getID() == ID.AmmoDrop){
                if(getBounds().intersects(temp.getBounds())){
                    handler.removeObject(temp);
                    this.canShoot = true;
                    if(ammoCount == 1){
                        this.ammoCount +=2;
                    }
                    else if(ammoCount == 2){
                        this.ammoCount +=1;
                    }
                    else if(ammoCount == 3){
                        this.ammoCount +=0;
                    }
                    else {
                        this.ammoCount += 3;
                    }
                    System.out.println("Weapons Enabled!");
                }
            }

            if(temp.getID() == ID.NukeDrop){
                if(getBounds().intersects(temp.getBounds())){
                    handler.removeObject(temp);
                    if(nukeCount == 0){
                        nukeCount += 1;
                        System.out.println("Nuke Collected!");
                    }

                }
            }
        }
    }
    public void tick(){
        x += velX;
        y += velY;

        x = Game.clamp(x,0, Game.WIDTH-75);
        y = Game.clamp(y,0,Game.HEIGHT-93);

        //handler.addObject(new EnemyTrail(x,y,ID.Trail, new Color(30,30,30), 32,32,0.1f, handler));
        //handler.addObject(new Bullets(x,y,ID.Bullets, handler));


        collision();
        ///fuck regeneration
    }

    public void render(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if(id == ID.Player) {
            g.setColor(Color.white);
        }
        /**
        g.fillRect(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("1",x+14,y+20);
        **/

        //g.drawRect((int)x,(int)y,PLAYER_WIDTH,PLAYER_HEIGHT);
        g.drawImage(sprite,(int)x,(int)y,null);
        //g.setColor(Color.WHITE);


    }

    public int getWidth(){
        return PLAYER_WIDTH;
    }

    public int getHeight(){
        return PLAYER_HEIGHT;
    }


}
