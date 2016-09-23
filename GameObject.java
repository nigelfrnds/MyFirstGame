import ID.ID;
import java.awt.*;

/**
 * Created by nigelfrnds on 2016-07-27.
 */
public abstract class GameObject {

    protected float x, y;
    protected ID id;
    protected float velX, velY;
    protected float prevVelX = velX, prevVelY = velY;
    protected static boolean canShoot = false;
    protected static int ammoCount;
    protected static int nukeCount;

    private Thread thread;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return id;
    }
    public void setVelX( float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    public float getVelX(){return velX;}
    public float getVelY(){return velY;}

    public boolean hasAmmo(){
        return ammoCount > 0;
    }
    public boolean hasNuke(){ return nukeCount > 0;}

    public void stopMovement(){
        velX = 0;
        velY = 0;
    }

    public void resumeMovement(){
        velX = prevVelX;
        velY = prevVelY;
    }



}
