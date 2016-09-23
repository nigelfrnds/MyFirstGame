import ID.ID;

import java.util.Random;

/**
 * Created by nigelfrnds on 2016-07-30.
 */
public class Spawner {
    private GameHandler handler;
    private HUD hud;

    private int scoreKeep = 0;
    private Random r = new Random();


    public Spawner(GameHandler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        if(scoreKeep >= 300) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            //handler.addObject(new SpeedDrop(r.nextInt(Game.WIDTH), 0, ID.SpeedDrop, handler));


            //handler.addObject(new HealthDrop(r.nextInt(Game.WIDTH), 0, ID.HealthDrop, handler));


            //handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));

            //handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));







            if (hud.getLevel() == 2) {


                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));

            }
            else if(hud.getLevel() == 3){


                handler.addObject(new AmmoDrop(r.nextInt(Game.WIDTH-50), 0, ID.AmmoDrop, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));


            }
            else if(hud.getLevel() == 4){

                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));
                handler.addObject(new SpeedDrop(r.nextInt(Game.WIDTH), 0, ID.SpeedDrop, handler));



            }
            else if(hud.getLevel() % 5 == 0){
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.Enemy, handler));
                handler.addObject(new HealthDrop(r.nextInt(Game.WIDTH), 0, ID.HealthDrop, handler));
                handler.addObject(new AmmoDrop(r.nextInt(Game.WIDTH-50), 0, ID.AmmoDrop, handler));



            }
            else if (hud.getLevel() %10 == 0){
                handler.addObject(new HealthDrop(r.nextInt(Game.WIDTH), 0, ID.HealthDrop, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                handler.addObject(new NukeDrop(r.nextInt(Game.WIDTH),0, ID.NukeDrop, handler));


            }
        }


    }
}
