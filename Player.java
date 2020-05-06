package Snek;

import Snek.Commons;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite{

    private int width;//function prototype

    public Player() {

        initPlayer();
    }

    private void initPlayer() {
        var playerHeadImage = "src/images/SnakeHead.png";//get image
        var ii = new ImageIcon(playerHeadImage);//sets image as object

        width = ii.getImage().getWidth(null);//width is now field
        setImage(ii.getImage());//function call

        int START_X = 270;//sets x coordinate spawn point
        setX(START_X);

        int START_Y = 280;//sets y coordinate spawn point
        setY(START_Y);
    }

    public void act() {

        x += dx;

        if (x <= 2) {
            x = 2;
        }

        if (x >= Commons.BOARD_HEIGHT - 2 * width) {//common "interface" with "BOARD_HEIGHT as a field
            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {//KeyEvent import class

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {//KeyEvent class and field
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx =  0;
        }
    }

    public void keyReleased(KeyEvent e) {//"e" parameter running through function from call. Key Event is the parameter.
        int key = e.getKeyChar();//get keycode calls

        if (key == KeyEvent.VK_LEFT) {//setting  up functions for later calls
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }
}