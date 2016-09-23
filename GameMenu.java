import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by nigelfrnds on 2016-07-31.
 */
public class GameMenu extends MouseAdapter {

    private final int MENU_WIDTH = 400, MENU_HEIGHT = 60;
    private int offset = 50;

    private Game game;
    private GameHandler handler;
    private HUD hud;

    private Random r = new Random();

    private File sourceImage;
    private Image sprite = null;

    public GameMenu(Game game, GameHandler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        getSprite();
    }

    public void getSprite(){
        try{
            sourceImage = new File("menu_background.png");
            sprite = ImageIO.read(sourceImage);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }

    public void mousePressed(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        if(game.gameState == Game.STATE.Menu) {
            //Play Button
            if (mouseOver(x, y, Game.WIDTH / 2 - (MENU_WIDTH / 2), Game.HEIGHT / 2 - (MENU_HEIGHT * 3)+offset, MENU_WIDTH, MENU_HEIGHT)) {
                System.out.println("clicked play!");
                game.gameState = Game.STATE.Game;

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.Enemy, handler));
            }
            //Help Button
            else if (mouseOver(x, y, Game.WIDTH / 2 - (MENU_WIDTH / 2), Game.HEIGHT / 2 - (MENU_HEIGHT * 2) + 20+offset, MENU_WIDTH, MENU_HEIGHT)) {
                game.gameState = Game.STATE.Help;

            }
            //Quit Button
            else if (mouseOver(x, y, Game.WIDTH / 2 - (MENU_WIDTH / 2), Game.HEIGHT / 2 - (MENU_HEIGHT) + 40+offset, MENU_WIDTH, MENU_HEIGHT)) {
                System.exit(0);
            }
            //Help Button


        }
        else if(game.gameState == Game.STATE.Help){
            if(mouseOver(x,y,Game.WIDTH / 2 - (MENU_WIDTH / 4), Game.HEIGHT / 2 + MENU_HEIGHT*2, MENU_WIDTH/2, MENU_HEIGHT)){
                System.out.println("back");
                game.gameState = Game.STATE.Menu;
            }
        }
        else if (game.gameState == Game.STATE.GameOver){
            if(mouseOver(x,y,Game.WIDTH / 2 - (MENU_WIDTH / 4)-10, Game.HEIGHT / 2 + MENU_HEIGHT, MENU_WIDTH/2 +50, MENU_HEIGHT)){
                System.out.println("Replay");
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);

                handler.objects.clear();

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.Enemy, handler));
            }
            else if(mouseOver(x,y,Game.WIDTH / 2 - (MENU_WIDTH / 4)-10, (Game.HEIGHT / 2) + MENU_HEIGHT*2 + 15, MENU_WIDTH/2 +50, MENU_HEIGHT )){
                game.gameState = Game.STATE.Menu;
            }

        }
        else if(game.gameState == Game.STATE.Pause){
            if(mouseOver(x,y,Game.WIDTH / 2 - (MENU_WIDTH / 4)-10, Game.HEIGHT / 2 + MENU_HEIGHT, MENU_WIDTH/2 +50, MENU_HEIGHT )){
                game.gameState = Game.STATE.Game;
            }
        }

    }

    public void mouseReleased(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

    }

    public void mouseDragged(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if( my > y && my < y + height){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void tick(){

    }

    public void render(Graphics g){

        if(game.gameState == Game.STATE.Menu) {
            Font title = new Font("arial", 1, 70);
            Font body = new Font("arial", 1, 50);

            //g.drawImage(sprite, Game.WIDTH, Game.HEIGHT, null);

            g.setFont(title);
            g.setColor(Color.WHITE);
            g.drawString("Main Menu", (Game.WIDTH / 2 - 185), 120);

            g.setFont(body);
            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 2), Game.HEIGHT / 2 - (MENU_HEIGHT * 3)+offset, MENU_WIDTH, MENU_HEIGHT);
            g.drawString("Play", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - (MENU_HEIGHT * 3) + 45+offset);

            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 2), Game.HEIGHT / 2 - (MENU_HEIGHT * 2) + 20 + offset , MENU_WIDTH, MENU_HEIGHT);
            g.drawString("Help", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - (MENU_HEIGHT * 2) + 65 + offset);

            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 2), Game.HEIGHT / 2 - (MENU_HEIGHT) + 40 + offset, MENU_WIDTH, MENU_HEIGHT);
            g.drawString("Quit", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - (MENU_HEIGHT) + 85 + offset);
        }
        else if(game.gameState == Game.STATE.Help){

            Font title = new Font("arial", 1, 50);
            Font body = new Font("arial",1, 25);
            g.setFont(title);
            g.setColor(Color.WHITE);

            g.drawString("Help", (Game.WIDTH / 2 - 75), 70);

            g.setFont(body);
            String controls = "Controls: Use the WASD keys to move around and dodge the Asteroids!";
            g.drawString(controls, (Game.WIDTH/2) - 425, 200);

            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 4), Game.HEIGHT / 2 + MENU_HEIGHT*2, MENU_WIDTH/2, MENU_HEIGHT);
            g.setFont(title);
            String back = "Back";
            g.drawString(back,Game.WIDTH/2 -55, Game.HEIGHT/2 + MENU_HEIGHT*2 + +46);

        }
        else if (game.gameState == Game.STATE.GameOver){
            Font title = new Font("arial", 1, 75);
            Font body = new Font("arial",1, 45);
            Font again = new Font("arial", 1, 50);

            g.setFont(title);
            g.setColor(Color.WHITE);

            g.drawString("Game Over!",(Game.WIDTH / 2 - 190), 100 );

            g.setFont(body);
            String score = "Final Score: " +hud.getScore();
            g.drawString(score,Game.WIDTH / 2 - 137, Game.HEIGHT / 2 - (MENU_HEIGHT * 2) );

            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 4)-10, Game.HEIGHT / 2 + MENU_HEIGHT, MENU_WIDTH/2 +50, MENU_HEIGHT);
            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 4)-10, (Game.HEIGHT / 2) + MENU_HEIGHT*2 + 15, MENU_WIDTH/2 +50, MENU_HEIGHT);

            g.setFont(again);
            String tryAgain = "Try again";
            g.drawString(tryAgain,Game.WIDTH/2 -95, Game.HEIGHT/2 + MENU_HEIGHT + +46);
            g.drawString("Menu",Game.WIDTH/2 -50, Game.HEIGHT/2 + MENU_HEIGHT + +120);

        }
        else if(game.gameState == Game.STATE.Pause){


            Font title = new Font("arial", 1, 50);
            Font body = new Font("arial",1, 25);
            Font info = new Font("arial", 1, 35);

            g.setFont(title);
            g.setColor(Color.WHITE);

            g.drawString("Paused",(Game.WIDTH / 2 - 85), 100 );

            g.drawRect(Game.WIDTH / 2 - (MENU_WIDTH / 4)-10, Game.HEIGHT / 2 + MENU_HEIGHT, MENU_WIDTH/2 +50, MENU_HEIGHT);

            g.setFont(info);
            String score = "Current Score: " + hud.getScore();
            g.drawString(score,Game.WIDTH / 2 - 135, Game.HEIGHT / 2 - (MENU_HEIGHT * 2) );

            g.setFont(body);
            g.drawString("Back to Game",Game.WIDTH/2 -65, Game.HEIGHT/2 + MENU_HEIGHT+37);





        }



    }
}
