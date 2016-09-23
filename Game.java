import ID.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by nigelfrnds on 2016-07-26.
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1848923404889557054L;
    public static final int WIDTH = 940, HEIGHT = WIDTH/12*9;

    private Thread thread;
    private boolean running = false;

    private GameHandler handler;
    private HUD hud;
    private Spawner spawner;

    private GameMenu menu;

    private Random r;

    private File sourceImage;
    private File sourceImage_2;
    private Image sprite = null;
    private Image sprite_2 = null;

    public enum STATE{
        Menu,
        Help,
        Game,
        Pause,
        GameOver;

    }


    public static STATE gameState = STATE.Menu;

    public Game(){
        handler = new GameHandler();
        hud = new HUD();
        menu = new GameMenu(this, handler,hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);


        new GameWindow(WIDTH, HEIGHT,"Game thing",this);


        spawner = new Spawner(handler, hud);

        r = new Random();


        getSprite();


    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }
        catch (Exception e){

        }

    }

    public void run(){
        this.requestFocus(); // no need to click on screen
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();

    }

    public void getSprite(){
        try{
            sourceImage = new File("menu_back.png");
            sourceImage_2 = new File("background.png");

            sprite = ImageIO.read(sourceImage);
            sprite_2 = ImageIO.read(sourceImage_2);
        }
        catch(IOException e){
            System.out.println("cant find file");
        }
    }

    private void tick(){
        // if start is selected
        if(gameState == STATE.Game) {
            handler.tick();

            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0){
                HUD.HEALTH = 100;

                handler.objects.clear();
                gameState = STATE.GameOver;
            }
        }
        else if(gameState == STATE.Pause){}


    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //g.setColor(Color.BLACK);
        //g.fillRect(0,0, WIDTH, HEIGHT);
        //g.drawImage(sprite,0,0,null);

        //handler.render(g);

        if(gameState == STATE.Game) {
            g.drawImage(sprite_2,0,0,null);

            handler.render(g);
            hud.render(g);


        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver){
            g.drawImage(sprite,0,0,null);

            menu.render(g);

        }
        else if(gameState == STATE.Pause){
            menu.render(g);
        }


        g.dispose();
        bs.show();

    }

    public static float clamp( float var, float min, float max){
        if(var >= max){
            return var = max;
        }
        else if( var <= min){
            return var = min;
        }
        return var;
    }
    public static void main (String args[]){
        new Game();

    }
}
