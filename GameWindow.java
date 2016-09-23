/**
 * Created by nigelfrnds on 2016-07-26.
 */
import javax.swing.*;
import java.awt.*;

public class GameWindow extends Canvas {

    private static final long serialVersionUID = 5446208496818554929L;

    public GameWindow(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
