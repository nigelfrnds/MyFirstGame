import ID.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by nigelfrnds on 2016-07-28.
 */
public class KeyInput extends KeyAdapter {

    private GameHandler handler;
    private boolean[] keyPressed = new boolean[4];
    public static int boostOffset = 0;




    public KeyInput(GameHandler handler){
        this.handler = handler;

        keyPressed[0] = false;
        keyPressed[1] = false;
        keyPressed[2] = false;
        keyPressed[3] = false;


    }


    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i =0; i<handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (Game.gameState == Game.STATE.Game) {
                if (temp.getID() == ID.Player) {
                    // Keys for first player

                    if (key == KeyEvent.VK_W) {
                        temp.setVelY(-5 - boostOffset);
                        keyPressed[0] = true;
                    }
                    if (key == KeyEvent.VK_S) {
                        temp.setVelY(+5 + boostOffset);
                        keyPressed[1] = true;
                    }
                    if (key == KeyEvent.VK_A) {
                        temp.setVelX(-5 - boostOffset);
                        keyPressed[2] = true;
                    }
                    if (key == KeyEvent.VK_D) {
                        temp.setVelX(+5 + boostOffset);
                        keyPressed[3] = true;
                    }
                    if (key == KeyEvent.VK_SPACE) {
                        if(temp.canShoot && temp.hasAmmo()) {
                            handler.addObject(new Bullets((temp.getX() + 75 / 2) - 5, temp.getY(), ID.Bullets, handler));
                            temp.ammoCount--;
                        }
                        else{
                            System.out.println("No Ammo!");
                        }
                    }
                    if(key == KeyEvent.VK_SHIFT){
                        if(temp.hasNuke()){
                            for(int j =0; j<handler.objects.size(); j++){
                                GameObject temp_2 = handler.objects.get(j);

                                if(temp_2.getID() == ID.Enemy || temp_2.getID() == ID.SmartEnemy || temp_2.getID() == ID.FastEnemy)
                                    handler.removeObject(temp_2);
                            }
                            System.out.println("kaboom!");
                            temp.nukeCount--;
                        }
                    }

                }

            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            //System.exit(0);
            if(Game.gameState == Game.STATE.Game) {
                Game.gameState = Game.STATE.Pause;
            }
            else if(Game.gameState == Game.STATE.Pause){
                Game.gameState = Game.STATE.Game;
            }

        }



    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i =0; i<handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if(temp.getID() == ID.Player){
                // Keys for first player

                if(key == KeyEvent.VK_W){
                    keyPressed[0] = false;

                }
                if(key == KeyEvent.VK_S){
                    keyPressed[1] = false;

                }
                if(key == KeyEvent.VK_A){
                    keyPressed[2] = false;

                }
                if(key == KeyEvent.VK_D){
                    keyPressed[3] = false;

                }

                if(!keyPressed[0] && !keyPressed[1]){
                    temp.setVelY(0);
                }
                if(!keyPressed[2] && !keyPressed[3]){
                    temp.setVelX(0);
                }

            }

        }
    }


}
