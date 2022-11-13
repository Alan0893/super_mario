import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Goomba {
    private int x;
    private int y;
    private int diam;
    private int vx;
    private boolean facingRight;
    private ImageIcon goombaImageIcon1;
    private ImageIcon goombaImageIcon2;
    private ImageIcon goombaImageIcon3;
    private ImageIcon goombaImageIcon4;
    private ImageIcon goombaImageIcon5;
    private ImageIcon goombaImageIcon6;
    private Image goombaImage1;
    private Image goombaImage2;
    private Image goombaImage3;
    private Image goombaImage4;
    private Image goombaImage5;
    private Image goombaImage6;
    private long goombaWalkTime;
    
    private Platform currentPlat;
    
    public Goomba(Platform argPlat, int loc) {
        this.currentPlat = argPlat;
        if (loc == 0) {
            this.x = this.currentPlat.getX() + this.currentPlat.getWidth() / 4;
        } else if (loc == 1) {
            this.x = this.currentPlat.getX() + this.currentPlat.getWidth() / 2;
        } else if (loc == 2) {
            this.x = this.currentPlat.getX() + 3 * this.currentPlat.getWidth() / 4;
        } 
        this.diam = 25;
        this.y = this.currentPlat.getY() - this.diam;
        this.vx = 3;
        this.facingRight = false;
        this.goombaWalkTime = 0L;
        this.goombaImageIcon1 = new ImageIcon(Goomba.class.getResource("./assets/rightGoomba1.png"));
        this.goombaImage1 = this.goombaImageIcon1.getImage();
        this.goombaImageIcon2 = new ImageIcon(Goomba.class.getResource("./assets/rightGoomba2.png"));
        this.goombaImage2 = this.goombaImageIcon2.getImage();
        this.goombaImageIcon3 = new ImageIcon(Goomba.class.getResource("./assets/rightGoomba3.png"));
        this.goombaImage3 = this.goombaImageIcon3.getImage();
        this.goombaImageIcon4 = new ImageIcon(Goomba.class.getResource("./assets/leftGoomba1.png"));
        this.goombaImage4 = this.goombaImageIcon4.getImage();
        this.goombaImageIcon5 = new ImageIcon(Goomba.class.getResource("./assets/leftGoomba2.png"));
        this.goombaImage5 = this.goombaImageIcon5.getImage();
        this.goombaImageIcon6 = new ImageIcon(Goomba.class.getResource("./assets/leftGoomba3.png"));
        this.goombaImage6 = this.goombaImageIcon6.getImage();
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getVx() {
        return this.vx;
    }
    
    public int getDiam() {
        return this.diam;
    }
    
    public void drawGoomba(Graphics g, long currentTime) {
        Graphics2D g2d = (Graphics2D)g;
        if (this.facingRight) {
        if (this.goombaWalkTime + 200L > currentTime) {
            g2d.drawImage(this.goombaImage1, this.x, this.y, this.diam, this.diam, null);
        } else if (this.goombaWalkTime + 300L > currentTime) {
            g2d.drawImage(this.goombaImage2, this.x, this.y, this.diam, this.diam, null);
        } else if (this.goombaWalkTime + 400L > currentTime) {
            g2d.drawImage(this.goombaImage3, this.x, this.y, this.diam, this.diam, null);
        } else {
            this.goombaWalkTime = currentTime;
            g2d.drawImage(this.goombaImage3, this.x, this.y, this.diam, this.diam, null);
        } 
        } else if (this.goombaWalkTime + 200L > currentTime) {
            g2d.drawImage(this.goombaImage4, this.x, this.y, this.diam, this.diam, null);
        } else if (this.goombaWalkTime + 300L > currentTime) {
            g2d.drawImage(this.goombaImage5, this.x, this.y, this.diam, this.diam, null);
        } else if (this.goombaWalkTime + 400L > currentTime) {
            g2d.drawImage(this.goombaImage6, this.x, this.y, this.diam, this.diam, null);
        } else {
            this.goombaWalkTime = currentTime;
            g2d.drawImage(this.goombaImage6, this.x, this.y, this.diam, this.diam, null);
        } 
    }
    
    public void move(int speed) {
        this.x += speed;
    }
    
    public void movement() {
        if (this.facingRight) {
            move(2);
        } else {
            move(-2);
        } 
        if (this.facingRight && this.x + this.diam > this.currentPlat.getX() + this.currentPlat.getWidth()) {
            this.facingRight = false;
        } else if (!this.facingRight && this.x < this.currentPlat.getX()) {
            this.facingRight = true;
        } 
    }
    
    public void killGoomba() {
        this.x = 0;
        this.y = 0;
        this.vx = 0;
        this.diam = 0;
    }
}
