import ID.ID;

import java.awt.*;

/**
 * Created by nigelfrnds on 2016-07-29.
 */
public class EnemyTrail extends GameObject {

    private float alpha = 1;
    private GameHandler handler;
    private Color color;
    private int width, height;
    private float life;

    public EnemyTrail(float x, float y, ID id, Color color,int width, int height, float life, GameHandler handler){
        super(x,y,id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick(){
        if(alpha > life){
            alpha -= (life - 0.001f);
        }
        else{
            handler.removeObject(this);
        }

    }

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x,(int)y,width,height);

        g2.setComposite(makeTransparent(1));


    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    public Rectangle getBounds(){
        return null;
    }

}