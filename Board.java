package Snek;
//game logic
import Snek.Player;
import Snek.Gems;
//gems = aliens, player = player, bombs = null
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board extends JPanel {
    private Dimension d;//cannot have void in function prototype. define function type in function
    private List<Gems> gems; //runs a list
    private Player player;

    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String message = "Game Over";

    private Timer timer;

    public Board() {
        initBoard();
        //AgameInit();
    }

    private void initBoard() {//making the board!
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons. BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    private void gameInit() {
        gems = new ArrayList<>();//create new array, I guess;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {

                var gem = new Gems(Commons.GEM_INIT_X + 18 * j, Commons.GEM_INIT_Y + 18 * i);//new object?? with dimensions??
                gems.add(gem);
            }
        }
        player = new Player();
    }
    private void drawGems(Graphics g) {//java swing type
        for (Gems gem : gems) {//enhanced for loop! data type item : collection
            if (gem.isVisible()) {
                g.drawImage(gem.getImage(), gem.getX(), gem.getY(), this);
            }
            if (gem.isDying()) {
                gem.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        if (player.isDying()) {
            player.die();
            inGame = false;
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {
            g.drawLine(0, Commons.GROUND, Commons.BOARD_WIDTH, Commons.GROUND);

            drawGems(g);//call functions
            drawPlayer(g);
        } else {
            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    //Java Swing constructors
    private void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));//making new color with RGB
        g.fillRect(50, Commons.BOARD_WIDTH / 2 -30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH / 2);
    }

    private void update() {
        if (deaths == Commons.NUMBER_OF_GEMS_TO_EAT) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        //player
        player.act();

        //player eats gem logic
        for (Gems gem : gems) {//fix logic
            if (deaths == Commons.NUMBER_OF_GEMS_TO_EAT) {
                int gemX = gem.getX();
                int gemY = gem.getY();
                message = "You Win!";
            }
//dont need explosion
            if (gem.isVisible()) {
                //player eats gem

               // gem.removeGem(true);
                deaths++;
            }
        }
        //gems
        //fix movement
        for (Gems gem : gems) {
            int x = gem.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction!= -1) {//fix in place

                direction = -1;

                Iterator<Gems> i1 = gems.iterator();

                while (i1.hasNext()) {

                    Gems a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {//fix in place

                direction = 1;

                Iterator<Gems> i2 = gems.iterator();

                while (i2.hasNext()) {
                    Gems a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }
        Iterator<Gems> it = gems.iterator();

        while (it.hasNext()) {//move down logic, change to stay put
            Gems gem = it.next();

            if (gem.isVisible()) {

                int y = gem.getY();

                if (y > Commons.GROUND - Commons.GEM_HEIGHT) {
                    inGame = false;
                    message = "Invasion!"; //change font
                }

                gem.act(direction);
            }
        }
    }

    //frames
    private void doGameCycle() {

        update();
        repaint();
    }

    private class GameCycle implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

//            if (key == KeyEvent.VK_SPACE) {
//                if (inGame) {
//                    if (!shot.isVisible()) {
//                        shot = new Shot(x,y);
//                    }
//                }
//            }
        }
    }
}
