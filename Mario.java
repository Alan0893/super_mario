import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Mario {
    private int x;
    private int y;
    private int width;
    private int height;
    private int vx;
    private int vy;
    private boolean jump;
    private boolean left;
    private boolean right;
    private boolean down;
    private int screenWidth;
    private int screenHeight;
    private boolean canJump;
    private boolean canFall;
    private double score;
    private boolean floorUnderMe;
    private ImageIcon marioImageIcon1;
    private ImageIcon marioImageIcon2;
    private ImageIcon marioImageIcon3;
    private ImageIcon marioImageIcon4;
    private ImageIcon marioImageIcon5;
    private Image marioImage1;
    private Image marioImage2;
    private Image marioImage3;
    private Image marioImage4;
    private Image marioImage5;
    private ImageIcon marioImageIcon6;
    private ImageIcon marioImageIcon7;
    private ImageIcon marioImageIcon8;
    private ImageIcon marioImageIcon9;
    private ImageIcon marioImageIcon10;
    private Image marioImage6;
    private Image marioImage7;
    private Image marioImage8;
    private Image marioImage9;
    private Image marioImage10;
    private long runningTime;

    public Mario(int argX, int argY, int argScreenWidth, int argScreenHeight) {
        this.x = argX;
        this.y = argY;
        this.screenWidth = argScreenWidth;
        this.screenHeight = argScreenHeight;
        this.vx = 0;
        this.vy = 0;
        this.width = 34;
        this.height = 50;
        this.canJump = false;
        this.canFall = false;
        this.left = false;
        this.right = false;
        this.down = false;
        this.floorUnderMe = true;
        this.score = 0.0D;
        this.runningTime = 0L;
        this.marioImageIcon1 = new ImageIcon(Mario.class.getResource("./assets/rightIdle.png"));
        this.marioImage1 = this.marioImageIcon1.getImage();
        this.marioImageIcon2 = new ImageIcon(Mario.class.getResource("./assets/rightRunning1.png"));
        this.marioImage2 = this.marioImageIcon2.getImage();
        this.marioImageIcon3 = new ImageIcon(Mario.class.getResource("./assets/rightRunning2.png"));
        this.marioImage3 = this.marioImageIcon3.getImage();
        this.marioImageIcon4 = new ImageIcon(Mario.class.getResource("./assets/rightRunning3.png"));
        this.marioImage4 = this.marioImageIcon4.getImage();
        this.marioImageIcon5 = new ImageIcon(Mario.class.getResource("./assets/rightRunning4.png"));
        this.marioImage5 = this.marioImageIcon5.getImage();
        this.marioImageIcon6 = new ImageIcon(Mario.class.getResource("./assets/leftIdle.png"));
        this.marioImage6 = this.marioImageIcon6.getImage();
        this.marioImageIcon7 = new ImageIcon(Mario.class.getResource("./assets/leftRunning1.png"));
        this.marioImage7 = this.marioImageIcon7.getImage();
        this.marioImageIcon8 = new ImageIcon(Mario.class.getResource("./assets/leftRunning2.png"));
        this.marioImage8 = this.marioImageIcon8.getImage();
        this.marioImageIcon9 = new ImageIcon(Mario.class.getResource("./assets/leftRunning3.png"));
        this.marioImage9 = this.marioImageIcon9.getImage();
        this.marioImageIcon10 = new ImageIcon(Mario.class.getResource("./assets/leftRunning4.png"));
        this.marioImage10 = this.marioImageIcon10.getImage();
    }

    public void setFloorUnderMe(boolean f) {
        this.floorUnderMe = f;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getXandWidth() {
        return this.x + this.width;
    }

    public int getVx() {
        return this.vx;
    }

    public boolean getRight() {
        return this.right;
    }

    public double getScore() {
        return this.score;
    }

    public void setJump(boolean argJump) {
        this.jump = argJump;
    }

    public void setLeft(boolean argLeft) {
        this.left = argLeft;
    }

    public void setRight(boolean argRight) {
        this.right = argRight;
    }

    public void setDown(boolean argDown) {
        this.down = argDown;
    }

    public void setVX(int vxP) {
        this.vx = vxP;
    }

    public void setVY(int vyP) {
        this.vy = vyP;
    }

    public void setCanFall(boolean argFall) {
        this.canFall = argFall;
    }

    public void drawMario(Graphics g, long currentTime) {
        Graphics2D g2d = (Graphics2D)g;
        if (!this.right && !this.left) {
            g2d.drawImage(this.marioImage1, this.x, this.y, this.width, this.height, null);
        } else if (this.right) {
            if (this.runningTime + 100L > currentTime) {
                g2d.drawImage(this.marioImage2, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 200L > currentTime) {
                g2d.drawImage(this.marioImage3, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 300L > currentTime) {
                g2d.drawImage(this.marioImage4, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 400L > currentTime) {
                g2d.drawImage(this.marioImage5, this.x, this.y, this.width, this.height, null);
            } else {
                this.runningTime = currentTime;
                g2d.drawImage(this.marioImage5, this.x, this.y, this.width, this.height, null);
            } 
        } else if (this.left) {
            if (this.runningTime + 100L > currentTime) {
                g2d.drawImage(this.marioImage6, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 200L > currentTime) {
                g2d.drawImage(this.marioImage7, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 300L > currentTime) {
                g2d.drawImage(this.marioImage8, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 400L > currentTime) {
                g2d.drawImage(this.marioImage9, this.x, this.y, this.width, this.height, null);
            } else if (this.runningTime + 500L > currentTime) {
                g2d.drawImage(this.marioImage10, this.x, this.y, this.width, this.height, null);
            } else {
                this.runningTime = currentTime;
                g2d.drawImage(this.marioImage10, this.x, this.y, this.width, this.height, null);
            } 
        } 
    }

    public void movement(Platform[] plats) {
        this.x += this.vx;
        this.y += this.vy;
        if (this.right)
            if (this.x + 30 <= this.screenWidth / 2)
                this.x += 5;  
        if (this.left)
            if (this.x - 5 >= 0)
                this.x -= 5;  
        Platform platUnderMe = getPlatformUnderMe(plats);
        if (platUnderMe != null) {
            this.floorUnderMe = true;
        } else {
            this.floorUnderMe = false;
        } 
        if (this.vy > 0 && this.floorUnderMe && this.y + this.height >= platUnderMe.getY()) {
            this.y = platUnderMe.getY() - this.height;
            this.canJump = true;
            this.vy = 3;
        } else {
            this.canJump = false;
            if (this.down) {
                this.vy += 3;
            } else {
                this.vy++;
            } 
        } 
    }

    private double distance(int x1, int y1, int x2, int y2) {
        double ans = Math.sqrt(Math.pow((x2 - x1), 2.0D) + Math.pow((y2 - y1), 2.0D));
        return ans;
    }

    public boolean collect(Coin coin, int rectX) {
        for (int i = this.x; i <= this.x + this.width; i++) {
            for (int j = this.y; j <= this.y + this.height; j++) {
                if (distance(i, j, coin.getCenterXCoin() + rectX, coin.getCenterYCoin()) < (coin.getDiam() / 2)) {
                    this.score += 10.0D;
                    return true;
                } 
            } 
        } 
        return false;
    }

    public boolean canJump() {
        return this.canJump;
    }

    public void jump() {
        this.vy = -15;
    }

    private boolean platUnderMe(Platform p) {
        if (this.y < p.getY()) {
            if ((this.x >= p.getX() && this.x <= p.getX() + p.getWidth()) || (this.x + this.width >= p.getX() && this.x + this.width <= p.getX() + p.getWidth()))
                return true; 
            return false;
        } 
        return false;
    }

    public Platform getPlatformUnderMe(Platform[] plats) {
        int count = 0;
        for (int i = 0; i < plats.length; i++) {
            Platform curr = plats[i];
            if (platUnderMe(curr))
                count++; 
        } 
        if (count == 0)
            return null; 
        Platform[] platsUnderMe = new Platform[count];
        int index = 0;
        for (int j = 0; j < plats.length; j++) {
            Platform curr = plats[j];
            if (platUnderMe(curr)) {
                platsUnderMe[index] = curr;
                index++;
            } 
        } 
        if (platsUnderMe.length == 1)
            return platsUnderMe[0]; 
        Platform min = platsUnderMe[0];
        for (int k = 0; k < platsUnderMe.length; k++) {
            if (platsUnderMe[k].getY() < min.getY())
                min = platsUnderMe[k]; 
        } 
        return min;
    }

    public boolean squishGoomba(Goomba goomba) {
        for (int i = this.x; i <= this.x + this.width; i++) {
            if (distance(i, this.y + this.height, goomba.getX() + goomba.getDiam() / 2, goomba.getY() + goomba.getDiam() / 2) < (goomba.getDiam() / 2)) {
                this.score += 20.0D;
                return true;
            } 
        } 
        return false;
    }

    public boolean goombaEndGame(Goomba goomba) {
        for (int i = this.x; i <= this.x + this.width; i++) {
            for (int j = this.y; j < this.y + this.height; j++) {
            if (distance(i, j, goomba.getX() + goomba.getDiam() / 2, goomba.getY() + goomba.getDiam() / 2) < (goomba.getDiam() / 2))
                return true; 
            } 
        } 
        return false;
    }
}
