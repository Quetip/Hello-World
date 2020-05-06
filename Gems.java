package Snek;

import javax.swing.ImageIcon;

public class Gems extends Sprite {

    public Gems(int x, int y) {
        initGems(x, y);
    }

    private void initGems(int x, int y){
        this.x = x;
        this.y = y;

        var gemImg = "src/image/gem.png";
        var ii = new ImageIcon(gemImg);

        setImage(ii.getImage());
    }

    public void act(int direction) {
        this.x += direction;
    }
}
