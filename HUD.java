import java.awt.*;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class HUD {

    //private Game game;
    public static int HEALTH = 100;
    public static int prevHealth;
    private int greenValue = 255;
    private String healthValue = "";

    private WeaponsHUD w_HUD = new WeaponsHUD();

    public static boolean canIntersect = true;


    public static int score = 0;
    public static int prevScore =0;
    private int level = 1;

    //public static boolean collision = false;
    /**
    public HUD(Game game){
        this.game = game;
    }**/

    public void tick(){
        HEALTH = (int)Game.clamp(HEALTH,0,100);
        prevHealth = HEALTH;
        greenValue = (int)Game.clamp(greenValue, 0, 255);

        greenValue = HEALTH*2;
        healthValue= "" +HEALTH +"%";


        score++;
        prevScore = score;


        //checkHealth();
    }

    public void render(Graphics g){
        Font font = new Font("arial", 1, 20);
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);

        g.setColor(new Color(100, greenValue, 0));
        g.fillRect(15,15,HEALTH*2,32);

        g.setColor(Color.WHITE);
        g.drawRect(15,15,200,32);

        g.setFont(font);
        g.drawString(healthValue,90,38);

        //Level and score
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);

        if(HEALTH <= 20){
            g.setColor(Color.red);
            g.drawString("Caution Low Health!", Game.WIDTH/2-32, 20);
        }

        w_HUD.render(g);
        /**
        if(Player.canShoot && Player.ammoCount > 0){
            g.setColor(Color.RED);
            g.drawString("Weapons Enabled!", 15, 100);
            g.drawString("Ammo Left: " + Player.ammoCount, 15, 120);

        }
        else if(Player.canShoot && Player.ammoCount == 0){
            g.setColor(Color.RED);
            g.drawString("No Ammo!", 15, 100);

        }**/

        //collision warning
        /**
        if(collision){
            System.out.println("Collision");
            g.setColor(Color.RED);
            g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);

            collision = false;
        }
        else{
            collision = false;

        }**/
        g.dispose();

    }



    public void checkHealth(){
        if(HEALTH <= 0){
            Game.gameState = Game.STATE.GameOver;
        }
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }




}

