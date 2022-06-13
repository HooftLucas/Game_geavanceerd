package be.uantwerpen.fti.ei.Graphic;

import be.uantwerpen.fti.ei.Entity.map.Tiles;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class J2DFact extends AFact {
    public static int ScreenWidth;
    public static int ScreenHeight;
    private int size;

    public static int GameDimWidth;
    public static int GameDimHeight;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;
    private Graphics2D g2dgraph;
    public BufferedImage backgroundimagine;


    public J2DFact() throws IOException {
        this.ScreenHeight = CommonGraph.Getinstance().screenHeight;
        this.ScreenWidth = CommonGraph.Getinstance().screenWidth;

        frame = new JFrame();
        panel = new JPanel(true){
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing(g);
        }
        };
        frame.setFocusable(true);
        frame.add(panel);
        this.frame.setTitle("mario bros 2.0");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Graphics2D get2d() {return g2dgraph;}

    public JFrame getFrame(){return frame;}

    public int getSize(){return size;}



    @Override
    public void render(){
        panel.repaint();
    }
    private void doDrawing(Graphics g){
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0,0, null);
        graph2d.dispose();
        if(g2dgraph != null)
            g2dgraph.drawImage(backgroundimagine,0,0, null);
    }



    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeigt){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeigt, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeigt, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage,0,0, null);
        return outputImage;
    }

    public static BufferedImage loadImages(String Path) throws IOException {
        BufferedImage bufferImage = ImageIO.read(new File(Path));
        return bufferImage;
    }

    public static Coordinate CoordinateConverter(BufferedImage image, float x, float y){
        float xPercent = x/ (GameDimWidth);
        float x_cord = xPercent * ScreenWidth;

        float yPercent = y/(GameDimHeight);
        float y_cord = yPercent *ScreenHeight;
        //y_cord += (float) (image.getHeight()/10.0);
        return new Coordinate(x_cord, y_cord);
    }



    @Override
    protected void setGameDim(int gameWidth, int gameheigt ) throws IOException {
       this.GameDimWidth = gameWidth;
       this.GameDimHeight = gameheigt;
       size = Math.min(ScreenWidth/gameWidth, ScreenHeight/gameheigt);
        frame.setLocation(100,100); //set the location where the game started
        frame.setSize(ScreenWidth, ScreenHeight);
        backgroundimagine = loadImages("src/Resource/achtergrond.jpeg");
        try
        {
            backgroundimagine = resizeImage(backgroundimagine,frame.getWidth(), frame.getHeight());

        }catch(Exception e){
            System.out.println(e.getStackTrace());
       }
        g2dimage = new BufferedImage(ScreenWidth, ScreenHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2dgraph = g2dimage.createGraphics();
        g2dgraph.drawImage(backgroundimagine,0,0, null);
        }



    @Override
    protected void showStatus(int lives, int level) {
        g2dgraph.setColor(Color.WHITE);

        g2dgraph.fillRect(2,0,ScreenWidth,size/4);
        g2dgraph.drawString("Level: "+level, (float)(ScreenWidth/60), (float)(ScreenHeight/100));

    }

    @Override
    protected Player CreatePlayer() throws IOException {
        return new J2DPlayer(this);
    }

    @Override
    protected Tiles CreateTiles() throws IOException {
        return new J2DTile(this);
    }


    @Override
    protected Input createInput() {
        return new J2Dinput(this);
    }


}
class Coordinate {
    public static float x;
    public static float y;

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
