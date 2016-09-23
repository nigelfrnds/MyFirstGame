import java.awt.*;
import java.util.LinkedList;

/**
 * Created by nigelfrnds on 2016-07-26.
 */
public class GameHandler{

    int count = 0;

    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
        for(int i=0; i<objects.size(); i++){
            GameObject temp = objects.get(i);

            temp.tick();
            count ++;

        }
        //System.out.println("Objects: "+ count);
    }
    public void render(Graphics g){
        for(int i =0; i<objects.size(); i++){
            GameObject temp = objects.get(i);

            temp.render(g);
        }
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }

}
