import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Coin {
    private int x;
    private int y;
    private int diam;
    private int centerX;
    private int centerY;
    private ImageIcon coinImageIcon;
    private Image coinImage;
    
    public Coin() {
        this.x = 0;
        this.y = 0;
        this.diam = 0;
        this.centerX = 0;
        this.centerY = 0;
    }
    
    public Coin(int argX, int argY) {
        this.x = argX;
        this.y = argY;
        this.diam = 20;
        this.centerX = this.x + this.diam / 2;
        this.centerY = this.y + this.diam / 2;
        this.coinImageIcon = new ImageIcon(Coin.class.getResource("./assets/coin.png"));
        this.coinImage = this.coinImageIcon.getImage();
    }
    
    public int getCx() {
        return this.x;
    }
    
    public int getCy() {
        return this.y;
    }
    
    public int getCenterXCoin() {
        return this.centerX;
    }
    
    public int getCenterYCoin() {
        return this.centerY;
    }
    
    public int getDiam() {
        return this.diam;
    }
    
    public void drawCoins(Graphics g, int argX) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.coinImage, this.x + argX, this.y, this.diam, this.diam, null);
    }
    
    public void move(int speed) {
        this.x += speed;
    }
}
