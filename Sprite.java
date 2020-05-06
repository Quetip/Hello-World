package Snek;

import java.awt.Image;

public class Sprite {
    //method parameters vv
    private boolean visible;
    private Image image;//created a type?? and a function name
    private boolean dying;

    int x;
    int y;
    int dx;

    public Sprite() {
        visible = true;//visible check
    }

    public void die() {
        visible = false;//visible off
    }

    public boolean isVisible() {//boolean
        return visible;
    }

    protected void setVisible(boolean visible) {//access modifier for method an. It can be accessed from : Within the enclosing class
        this.visible = visible;//this. is a keyword. accesses values in method parameter
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }
}
