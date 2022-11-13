    import java.applet.Applet;
    import java.applet.AudioClip;
    import java.awt.Color;
    import java.awt.Component;
    import java.awt.Dimension;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.Image;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    import java.awt.event.MouseMotionListener;
    import javax.swing.ImageIcon;
    import javax.swing.JComponent;
    import javax.swing.JFrame;

    public class MarioGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener {
    private int WIDTH;
    private int HEIGHT;
    private int rectX;
    private int rectY;
    private int rectWidth;
    private Mario mario;
    private int rectVx;
    private ImageIcon groundImageIcon;
    private Image groundImage;
    private ImageIcon skyImageIcon;
    private ImageIcon smallCloudImageIcon;
    private ImageIcon bigCloudImageIcon;
    private Image skyImage;
    private Image smallCloudImage;
    private Image bigCloudImage;
    private Coin coin;
    private Coin[] coins;
    private Goomba goomba;
    private Goomba goomba2;
    private Goomba[] goombas;
    private long startTime;
    private long currentTime;
    private Platform floor1;
    private Platform[] allPlats;
    private boolean gameOver;
    private int cloudX;
    private int cloudY;
    private int cloudW;
    private int cloudH;
    private int cloudVX;
    private int bigCloudX;
    private int bigCloudY;
    private int bigCloudW;
    private int bigCloudH;
    private int cloudCounter;
    private int level;
    private int coinCounter;
    private ImageIcon introImageIcon;
    private ImageIcon flagpoleImageIcon;
    private ImageIcon arrowImageIcon;
    private Image introImage;
    private Image flagpoleImage;
    private Image arrowImage;
    private ImageIcon coinImageIcon;
    private Image coinImage;
    private ImageIcon smallBushImageIcon;
    private ImageIcon midBushImageIcon;
    private ImageIcon bigBushImageIcon;
    private Image smallBushImage;
    private Image midBushImage;
    private Image bigBushImage;
    private AudioClip themeSong;
    private AudioClip deathSong;
    private AudioClip winningSong;
    private AudioClip levelUpSong;
    private AudioClip coinEffect;
    private AudioClip goombaEffect;
	
    public MarioGraphics() {
    this.WIDTH = 1000;
    this.HEIGHT = 500;
    this.rectX = 0;
    this.rectY = 400;
    this.rectVx = 0;
    this.rectWidth = this.WIDTH + 300;
    this.cloudX = this.WIDTH + 50;
    this.cloudY = 50;
    this.cloudW = 149;
    this.cloudH = 100;
    this.cloudVX = -2;
    this.bigCloudX = this.WIDTH + 50;
    this.bigCloudY = 50;
    this.bigCloudW = 219;
    this.bigCloudH = 108;
    this.cloudCounter = 0;
    this.level = 1;
    this.coinCounter = 0;
    this.skyImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/Sky.jpeg"));
    this.skyImage = this.skyImageIcon.getImage();
    this.smallCloudImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/smallCloud.png"));
    this.smallCloudImage = this.smallCloudImageIcon.getImage();
    this.bigCloudImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/bigCloud.png"));
    this.bigCloudImage = this.bigCloudImageIcon.getImage();
    this.introImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/intro.jpg"));
    this.introImage = this.introImageIcon.getImage();
    this.flagpoleImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/flagpole.png"));
    this.flagpoleImage = this.flagpoleImageIcon.getImage();
    this.arrowImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/arrow.png"));
    this.arrowImage = this.arrowImageIcon.getImage();
    this.coinImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/coin.png"));
    this.coinImage = this.coinImageIcon.getImage();
    this.smallBushImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/smallBush.png"));
    this.smallBushImage = this.smallBushImageIcon.getImage();
    this.midBushImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/midBush.png"));
    this.midBushImage = this.midBushImageIcon.getImage();
    this.bigBushImageIcon = new ImageIcon(MarioGraphics.class.getResource("./assets/bigBush.png"));
    this.bigBushImage = this.bigBushImageIcon.getImage();
    this.startTime = System.currentTimeMillis();
    this.currentTime = 0L;
    this.gameOver = false;
    createObjects();
    this.themeSong = Applet.newAudioClip(getClass().getResource("./sound/themeSong.wav"));
    this.deathSong = Applet.newAudioClip(getClass().getResource("./sound/died.wav"));
    this.winningSong = Applet.newAudioClip(getClass().getResource("./sound/winning.wav"));
    this.levelUpSong = Applet.newAudioClip(getClass().getResource("./sound/levelUp.wav"));
    this.coinEffect = Applet.newAudioClip(getClass().getResource("./sound/coin.wav"));
    this.goombaEffect = Applet.newAudioClip(getClass().getResource("./sound/goomba.wav"));
    this.themeSong.loop();
    JFrame gui = new JFrame();
    gui.setDefaultCloseOperation(3);
    gui.setTitle("* SUPER MARIO *");
    gui.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
    gui.setResizable(false);
    gui.getContentPane().add(this);
    gui.pack();
    gui.setLocationRelativeTo((Component)null);
    gui.setVisible(true);
    gui.addKeyListener(this);
    gui.addMouseListener(this);
    gui.addMouseMotionListener(this);
    }

    public int getRectX() {
    return this.rectX;
    }

    public int getRectY() {
    return this.rectY;
    }

    public int getRectVx() {
    return this.rectVx;
    }

    public void paintComponent(Graphics g) {
    if (!this.gameOver) {
        if (this.level != 5) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.skyImage, 0, 0, this.WIDTH, this.HEIGHT, null);
        g2d.drawImage(this.smallCloudImage, this.cloudX, this.cloudY, this.cloudW, this.cloudH, null);
        g2d.drawImage(this.bigCloudImage, this.bigCloudX, this.bigCloudY, this.bigCloudW, this.bigCloudH, null);
        g2d.drawImage(this.arrowImage, 10 + this.allPlats[0].getX(), 315, 90, 105, null);
        g2d.drawImage(this.introImage, 150 + this.allPlats[0].getX(), 50, 300, 150, null);
        g2d.drawImage(this.smallBushImage, 900 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.smallBushImage, 2250 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.smallBushImage, 5300 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.smallBushImage, 5680 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.smallBushImage, 6550 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.midBushImage, 300 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.midBushImage, 2550 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.midBushImage, 4850 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.bigBushImage, 1800 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.bigBushImage, 4550 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.bigBushImage, 6200 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.bigBushImage, 8800 + this.allPlats[0].getX(), 230, 300, 300, null);
        g2d.drawImage(this.flagpoleImage, this.allPlats[39].getX() + this.allPlats[39].getWidth() / 2 - 60, 90, 86, 319, null);
        int i;
        for (i = 0; i < this.allPlats.length; i++)
            this.allPlats[i].drawSelf(g); 
        this.mario.drawMario(g, this.currentTime);
        this.coin.drawCoins(g, this.rectX);
        for (i = 0; i < this.coins.length; i++)
            this.coins[i].drawCoins(g, this.rectX); 
        this.goomba.drawGoomba(g, this.currentTime);
        for (i = 0; i < this.goombas.length; i++)
            this.goombas[i].drawGoomba(g, this.currentTime); 
        Font font = new Font("Arial", 1, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("MARIO", 15, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("" + (int)this.mario.getScore(), 15, 55);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("TIME", this.WIDTH - 60, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("" + (this.currentTime / 1000L), this.WIDTH - 60, 55);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("WORLD", this.WIDTH - 300, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("1 - " + this.level, this.WIDTH - 281, 55);
        g2d.drawImage(this.coinImage, 253, 15, 17, 17, null);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("x " + this.coinCounter, 275, 30);
        } else {
        Graphics2D g2d = (Graphics2D)g;
        long usersTime = this.currentTime / 1000L;
        g2d.drawImage(this.skyImage, 0, 0, this.WIDTH, this.HEIGHT, null);
        Font font = new Font("Helvetica", 1, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("YOU WIN!", 385, 100);
        Font font2 = new Font("Helvetica", 1, 30);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Time: " + usersTime, 355, 200);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + (int)this.mario.getScore(), 355, 250);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Coins: " + this.coinCounter, 355, 300);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Level: " + this.level, 355, 350);
        } 
    } else {
        Graphics2D g2d = (Graphics2D)g;
        long usersTime = this.currentTime / 1000L;
        g2d.drawImage(this.skyImage, 0, 0, this.WIDTH, this.HEIGHT, null);
        g2d.drawImage(this.smallCloudImage, this.cloudX, this.cloudY, this.cloudW, this.cloudH, null);
        g2d.drawImage(this.bigCloudImage, this.bigCloudX, this.bigCloudY, this.bigCloudW, this.bigCloudH, null);
        Font font = new Font("Helvetica", 1, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("GAME OVER", 350, 100);
        Font font2 = new Font("Helvetica", 1, 30);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Time: " + usersTime, 355, 200);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + (int)this.mario.getScore(), 355, 250);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Coins: " + this.coinCounter, 355, 300);
        g.setFont(font2);
        g.setColor(Color.BLACK);
        g.drawString("Level: " + this.level, 355, 350);
    } 
    }

    public void loop() {
    if (!this.gameOver && this.level != 5)
        this.currentTime = System.currentTimeMillis() - this.startTime; 
    this.cloudX += this.cloudVX;
    this.bigCloudX += this.cloudVX;
    if (this.cloudCounter % 2 == 0) {
        if (this.bigCloudX + this.bigCloudW < 0 && this.cloudX + this.cloudW < 0) {
        this.cloudX = this.WIDTH + 50;
        this.cloudY = (int)(Math.random() * 100.0D + 50.0D);
        this.cloudCounter++;
        } 
    } else if (this.cloudX + this.cloudW < 0 && this.bigCloudX + this.bigCloudW < 0) {
        this.bigCloudX = this.WIDTH + 50;
        this.bigCloudY = (int)(Math.random() * 100.0D + 50.0D);
        this.cloudCounter++;
    } 
    this.mario.movement(this.allPlats);
    marioFell();
    groundMovement();
    if (this.mario.collect(this.coin, this.allPlats[0].getX()) == true) {
        this.coin = new Coin();
        this.coinCounter++;
        this.coinEffect.play();
    } 
    int i;
    for (i = 0; i < this.coins.length; i++) {
        if (this.mario.collect(this.coins[i], this.allPlats[0].getX()) == true) {
        this.coins[i] = new Coin();
        this.coinCounter++;
        this.coinEffect.play();
        } 
    } 
    this.goomba.movement();
    for (i = 0; i < this.goombas.length; i++)
        this.goombas[i].movement(); 
    if (!this.gameOver)
        if (this.mario.squishGoomba(this.goomba) == true) {
        this.goomba.killGoomba();
        this.goombaEffect.play();
        }  
    if (this.mario.goombaEndGame(this.goomba) == true && !this.gameOver) {
        this.gameOver = true;
        this.mario = new Mario(0, 0, 0, 0);
        this.themeSong.stop();
        this.deathSong.play();
    } 
    if (!this.gameOver)
        for (i = 0; i < this.goombas.length; i++) {
        if (this.mario.squishGoomba(this.goombas[i]) == true) {
            this.goombas[i].killGoomba();
            this.goombaEffect.play();
        } 
        }  
    for (i = 0; i < this.goombas.length; i++) {
        if (this.mario.goombaEndGame(this.goombas[i]) == true && !this.gameOver) {
        this.gameOver = true;
        this.themeSong.stop();
        this.deathSong.play();
        } 
    } 
    if (this.mario.getXandWidth() >= this.allPlats[4].getX() + this.allPlats[4].getWidth() && this.level == 1) {
        this.level = 2;
        this.levelUpSong.play();
    } 
    if (this.mario.getXandWidth() >= this.allPlats[17].getX() + this.allPlats[17].getWidth() && this.level == 2) {
        this.level = 3;
        this.levelUpSong.play();
    } 
    if (this.mario.getXandWidth() >= this.allPlats[26].getX() + this.allPlats[26].getWidth() / 2 && this.level == 3) {
        this.level = 4;
        this.levelUpSong.play();
    } 
    if (this.mario.getXandWidth() >= this.allPlats[39].getX() + this.allPlats[39].getWidth() / 2 && this.level == 4) {
        this.level = 5;
        this.themeSong.stop();
        this.winningSong.play();
    } 
    repaint();
    }

    public void groundMovement() {
    if (this.mario.getX() + 25 == this.WIDTH / 2 && this.mario.getRight() == true) {
        int i;
        for (i = 0; i < this.coins.length; i++)
        this.coins[i].move(-5); 
        for (i = 0; i < this.allPlats.length; i++)
        this.allPlats[i].move(-5); 
        this.coin.move(-5);
        this.goomba.move(-5);
        for (i = 0; i < this.goombas.length; i++)
        this.goombas[i].move(-5); 
    } 
    }

    public void marioFell() {
    if (this.mario.getY() > this.HEIGHT && !this.gameOver) {
        this.gameOver = true;
        this.themeSong.stop();
        this.deathSong.play();
    } 
    }

    public void keyPressed(KeyEvent e) {
    if ((e.getKeyCode() == 32 || e.getKeyCode() == 38 || e.getKeyCode() == 87) && this.mario.canJump()) {
        this.mario.jump();
        this.mario.setDown(false);
    } else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
        this.mario.setLeft(true);
    } else if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
        this.mario.setRight(true);
    } else if ((e.getKeyCode() == 40 || e.getKeyCode() == 83) && !this.mario.canJump()) {
        this.mario.setDown(true);
    } 
    }

    public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
        this.mario.setLeft(false);
    } else if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
        this.mario.setRight(false);
    } else if (e.getKeyCode() == 40 || (e.getKeyCode() == 83 && !this.mario.canJump())) {
        this.mario.setDown(false);
    } 
    }

    public void keyTyped(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void start(final int ticks) {
        Thread guiThread = new Thread() {
            public void run() {
                while (true) {
                    loop();
                    try {
                        Thread.sleep(1000 / ticks);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } // end while
            } // end run
        }; // end guiThread
        guiThread.start();
    }

    private void createObjects() {
    this.mario = new Mario(100, 50, this.WIDTH, this.HEIGHT);
    this.coin = new Coin(400, 350);
    this.coins = new Coin[116];
    this.coins[0] = new Coin(450, 350);
    this.coins[1] = new Coin(400, 300);
    this.coins[2] = new Coin(450, 300);
    this.coins[3] = new Coin(450, 250);
    this.coins[4] = new Coin(500, 300);
    this.coins[5] = new Coin(400, 250);
    this.coins[6] = new Coin(350, 300);
    this.coins[7] = new Coin(500, 250);
    this.coins[8] = new Coin(500, 350);
    this.coins[9] = new Coin(300, 300);
    this.coins[10] = new Coin(850, 350);
    this.coins[11] = new Coin(900, 350);
    this.coins[12] = new Coin(950, 350);
    this.coins[13] = new Coin(1000, 350);
    this.coins[14] = new Coin(1350, 350);
    this.coins[15] = new Coin(1300, 350);
    this.coins[16] = new Coin(960, 250);
    this.coins[17] = new Coin(990, 250);
    this.coins[18] = new Coin(1020, 250);
    this.coins[19] = new Coin(1216, 175);
    this.coins[20] = new Coin(1282, 175);
    this.coins[21] = new Coin(1750, 350);
    this.coins[22] = new Coin(1850, 350);
    this.coins[23] = new Coin(1950, 350);
    this.coins[24] = new Coin(2050, 350);
    this.coins[25] = new Coin(1750, 250);
    this.coins[26] = new Coin(1800, 250);
    this.coins[27] = new Coin(1900, 175);
    this.coins[28] = new Coin(1950, 175);
    this.coins[29] = new Coin(2050, 100);
    this.coins[30] = new Coin(2100, 100);
    this.coins[31] = new Coin(2430, 350);
    this.coins[32] = new Coin(2560, 350);
    this.coins[33] = new Coin(2690, 350);
    this.coins[34] = new Coin(2920, 350);
    this.coins[35] = new Coin(2750, 100);
    this.coins[36] = new Coin(2810, 100);
    this.coins[37] = new Coin(3130, 80);
    this.coins[38] = new Coin(3190, 80);
    this.coins[39] = new Coin(3420, 150);
    this.coins[40] = new Coin(3360, 150);
    this.coins[41] = new Coin(3650, 250);
    this.coins[42] = new Coin(3750, 250);
    this.coins[43] = new Coin(3950, 175);
    this.coins[44] = new Coin(4050, 175);
    this.coins[45] = new Coin(4250, 250);
    this.coins[46] = new Coin(4350, 250);
    this.coins[47] = new Coin(4630, 350);
    this.coins[48] = new Coin(4760, 350);
    this.coins[49] = new Coin(4890, 350);
    this.coins[50] = new Coin(5020, 350);
    this.coins[51] = new Coin(5365, 350);
    this.coins[52] = new Coin(5430, 350);
    this.coins[53] = new Coin(5495, 350);
    this.coins[54] = new Coin(5560, 350);
    this.coins[55] = new Coin(5625, 350);
    this.coins[56] = new Coin(5690, 350);
    this.coins[57] = new Coin(5755, 350);
    this.coins[58] = new Coin(5820, 350);
    this.coins[59] = new Coin(5885, 350);
    this.coins[60] = new Coin(4975, 250);
    this.coins[61] = new Coin(5025, 250);
    this.coins[62] = new Coin(5075, 250);
    this.coins[63] = new Coin(5300, 175);
    this.coins[64] = new Coin(5350, 175);
    this.coins[65] = new Coin(5800, 10);
    this.coins[66] = new Coin(5825, 10);
    this.coins[67] = new Coin(5850, 10);
    this.coins[68] = new Coin(6015, -100);
    this.coins[69] = new Coin(6040, -100);
    this.coins[70] = new Coin(6065, -100);
    this.coins[71] = new Coin(6090, -100);
    this.coins[72] = new Coin(6115, -100);
    this.coins[73] = new Coin(6140, -100);
    this.coins[74] = new Coin(5975, 100);
    this.coins[75] = new Coin(6025, 100);
    this.coins[76] = new Coin(6250, 175);
    this.coins[77] = new Coin(6275, 175);
    this.coins[78] = new Coin(6300, 175);
    this.coins[79] = new Coin(6325, 175);
    this.coins[80] = new Coin(6350, 175);
    this.coins[81] = new Coin(6275, 10);
    this.coins[82] = new Coin(6300, 10);
    this.coins[83] = new Coin(6325, 10);
    this.coins[84] = new Coin(6350, 10);
    this.coins[85] = new Coin(6375, 10);
    this.coins[86] = new Coin(6400, 10);
    this.coins[87] = new Coin(6250, 10);
    this.coins[88] = new Coin(6200, 350);
    this.coins[89] = new Coin(6300, 350);
    this.coins[90] = new Coin(6400, 350);
    this.coins[91] = new Coin(6500, 350);
    this.coins[92] = new Coin(6600, 350);
    this.coins[93] = new Coin(6700, 350);
    this.coins[94] = new Coin(7000, 375);
    this.coins[95] = new Coin(7030, 375);
    this.coins[96] = new Coin(7015, 425);
    this.coins[97] = new Coin(7045, 425);
    this.coins[98] = new Coin(7395, 275);
    this.coins[99] = new Coin(7420, 275);
    this.coins[100] = new Coin(7445, 275);
    this.coins[101] = new Coin(7153, 145);
    this.coins[102] = new Coin(7186, 145);
    this.coins[103] = new Coin(7170, 65);
    this.coins[104] = new Coin(7625, 65);
    this.coins[105] = new Coin(7650, 65);
    this.coins[106] = new Coin(7675, 65);
    this.coins[107] = new Coin(8170, 165);
    this.coins[108] = new Coin(8200, 165);
    this.coins[109] = new Coin(8230, 165);
    this.coins[110] = new Coin(8260, 165);
    this.coins[111] = new Coin(8504, 350);
    this.coins[112] = new Coin(8558, 350);
    this.coins[113] = new Coin(8612, 350);
    this.coins[114] = new Coin(8666, 350);
    this.coins[115] = new Coin(8720, 350);
    this.allPlats = new Platform[40];
    this.allPlats[0] = new Platform(0, 400, 650, this.HEIGHT - this.rectY, 1);
    this.allPlats[2] = new Platform(-400, 300, 200, 40, 2);
    this.allPlats[1] = new Platform(750, 400, 650, 100, 1);
    this.allPlats[5] = new Platform(900, 300, 200, 40, 2);
    this.allPlats[6] = new Platform(1150, 225, 200, 40, 2);
    this.allPlats[3] = new Platform(1500, 400, 650, 100, 1);
    this.allPlats[7] = new Platform(1700, 300, 200, 40, 2);
    this.allPlats[8] = new Platform(1850, 225, 200, 40, 2);
    this.allPlats[9] = new Platform(2000, 150, 200, 40, 2);
    this.allPlats[4] = new Platform(2250, 400, 650, 100, 1);
    this.allPlats[10] = new Platform(2700, 150, 150, 40, 2);
    this.allPlats[11] = new Platform(2900, 225, 100, 40, 2);
    this.allPlats[12] = new Platform(3000, 300, 200, 40, 2);
    this.allPlats[13] = new Platform(3300, 200, 200, 40, 2);
    this.allPlats[14] = new Platform(3050, 130, 200, 40, 2);
    this.allPlats[15] = new Platform(3600, 300, 200, 40, 2);
    this.allPlats[16] = new Platform(3900, 225, 200, 40, 2);
    this.allPlats[17] = new Platform(4200, 300, 200, 40, 2);
    this.allPlats[18] = new Platform(4500, 400, 650, 100, 1);
    this.allPlats[19] = new Platform(5300, 400, 650, 100, 1);
    this.allPlats[20] = new Platform(4950, 300, 150, 40, 2);
    this.allPlats[21] = new Platform(5250, 225, 150, 40, 2);
    this.allPlats[22] = new Platform(5550, 125, 150, 40, 2);
    this.allPlats[23] = new Platform(5775, 50, 100, 40, 2);
    this.allPlats[24] = new Platform(6025, -60, 200, 40, 2);
    this.allPlats[25] = new Platform(5925, 150, 150, 40, 2);
    this.allPlats[26] = new Platform(6125, 400, 650, 100, 1);
    this.allPlats[27] = new Platform(6250, 50, 150, 40, 2);
    this.allPlats[28] = new Platform(6225, 225, 150, 40, 2);
    this.allPlats[29] = new Platform(6965, 400, 100, 20, 2);
    this.allPlats[30] = new Platform(6980, 460, 100, 20, 2);
    this.allPlats[31] = new Platform(7120, 430, 100, 20, 2);
    this.allPlats[32] = new Platform(7370, 300, 100, 20, 2);
    this.allPlats[33] = new Platform(7120, 170, 100, 20, 2);
    this.allPlats[34] = new Platform(7120, 90, 100, 20, 2);
    this.allPlats[35] = new Platform(7400, 90, 100, 20, 2);
    this.allPlats[36] = new Platform(7600, 90, 100, 20, 2);
    this.allPlats[37] = new Platform(7825, -16, 100, 20, 2);
    this.allPlats[38] = new Platform(8150, 190, 150, 40, 2);
    this.allPlats[39] = new Platform(8450, 400, 650, 100, 1);
    this.goomba = new Goomba(this.allPlats[0], 2);
    this.goombas = new Goomba[23];
    this.goombas[0] = new Goomba(this.allPlats[1], 0);
    this.goombas[1] = new Goomba(this.allPlats[6], 1);
    this.goombas[2] = new Goomba(this.allPlats[3], 1);
    this.goombas[3] = new Goomba(this.allPlats[3], 0);
    this.goombas[4] = new Goomba(this.allPlats[8], 0);
    this.goombas[5] = new Goomba(this.allPlats[4], 0);
    this.goombas[6] = new Goomba(this.allPlats[4], 2);
    this.goombas[7] = new Goomba(this.allPlats[11], 0);
    this.goombas[8] = new Goomba(this.allPlats[12], 0);
    this.goombas[9] = new Goomba(this.allPlats[16], 0);
    this.goombas[10] = new Goomba(this.allPlats[16], 1);
    this.goombas[11] = new Goomba(this.allPlats[17], 0);
    this.goombas[12] = new Goomba(this.allPlats[18], 1);
    this.goombas[13] = new Goomba(this.allPlats[18], 0);
    this.goombas[14] = new Goomba(this.allPlats[22], 0);
    this.goombas[15] = new Goomba(this.allPlats[25], 0);
    this.goombas[16] = new Goomba(this.allPlats[28], 1);
    this.goombas[17] = new Goomba(this.allPlats[26], 0);
    this.goombas[18] = new Goomba(this.allPlats[26], 1);
    this.goombas[19] = new Goomba(this.allPlats[26], 2);
    this.goombas[20] = new Goomba(this.allPlats[30], 1);
    this.goombas[21] = new Goomba(this.allPlats[34], 0);
    this.goombas[22] = new Goomba(this.allPlats[38], 2);
    }

    public static void main(String[] args) {
        MarioGraphics g = new MarioGraphics();
        g.start(60);
    }
}
