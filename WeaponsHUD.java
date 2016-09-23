import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class WeaponsHUD {

    private Image sprite = null;
    private Image sprite_2 = null;

    private File sourceImage;
    private File sourceImage_2;

    public void getSprite(){
        try{
            sourceImage = new File("bullet.png");
            sourceImage_2 = new File("nuke.png");

            sprite = ImageIO.read(sourceImage);
            sprite_2 = ImageIO.read(sourceImage_2);
        }
        catch(IOException e){
            System.out.println("cant find file " + getClass());
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        Font font = new Font("arial", 1, 20);
        getSprite();


        if(Player.canShoot && Player.ammoCount > 0){
            g.setColor(Color.WHITE);
            g.drawString("Ammo: ", 15, 110);

            int x_offset  = 15;
            for(int i =0; i<Player.ammoCount; i++){
                g.setColor(Color.WHITE);
                //g.drawRect(10,115,75,40);
                g.drawImage(sprite,x_offset,120,null);
                x_offset += 25;
            }
            //g.drawString("Ammo Left: " + Player.ammoCount, 15, 120);

        }
        else if(Player.canShoot && Player.ammoCount == 0){
            g.setColor(Color.RED);
            g.drawString("No Ammo!", 15, 110);

        }

        if(Player.nukeCount > 0){
            //g.setColor(Color.orange);
            g.drawImage(sprite_2,15,160,null);


        }

        g.dispose();

    }


}
