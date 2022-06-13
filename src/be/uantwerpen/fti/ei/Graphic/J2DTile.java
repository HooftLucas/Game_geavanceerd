package be.uantwerpen.fti.ei.Graphic;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.Game;
import be.uantwerpen.fti.ei.Graphic.CommonGraph;
import be.uantwerpen.fti.ei.Entity.map.Tiles;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static be.uantwerpen.fti.ei.Game.Game.player;

public class J2DTile extends Tiles {
    J2DFact fact;
    public static Tiles[] tiles;
    public static int [][] mapTileNum;
    public static float [] tileCoordinateX;
    public static float [] tileCoordinateY;
    public static int height_input_line;
    public static int width_input_line;
    int TilesSize = CommonGraph.Getinstance().tileSize;
    public static int screenX;
    public static int tileNum;
    public static int x_image = 0;
    public static int y_image = 0;
    public static int screenY ;

    public J2DTile(J2DFact fact) throws IOException {
        this.fact = fact;
        tiles = new Tiles[10];
        mapTileNum = new int [500][50];
        tileCoordinateX = new float[10000];
        tileCoordinateY = new float[10000];


        try{
            tiles[1] = new Tiles();
            tiles[1].Image = fact.loadImages("src/Resource/block.jpg");
            tiles[1].Image = fact.resizeImage(tiles[1].Image,TilesSize,TilesSize);

            tiles[2] = new Tiles();
            tiles[2].Image = fact.loadImages("src/Resource/end.jpg");
            tiles[2].Image = fact.resizeImage(tiles[2].Image,TilesSize,TilesSize);

            tiles[9] = new Tiles();
            tiles[9].Image = fact.loadImages("src/Resource/empty.jpg");
            tiles[9].Image = fact.resizeImage(tiles[9].Image,TilesSize,TilesSize);


        }catch(IOException e){
            e.printStackTrace();
        }
        loadmap("src/Resource/World/world.txt");

    }
    public void loadmap(String path){
        ArrayList lines = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    break;
                } else
                    lines.add(line);

                this.width_input_line = Math.max(width_input_line, line.length());
            }
            this.height_input_line = lines.size();
            for(int y=0; y< height_input_line; y++){
                String line = (String)lines.get(y);
                for(int x = 0;x<line.length(); x++){
                    char ch = line.charAt(x);
                    if (ch == '1'){
                        mapTileNum[x][y]= 1;
                    } else if (ch == '2'){
                        mapTileNum[x][y]= 2;
                    }
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void Vis()  {
        Graphics2D g2d = fact.get2d();

        for(int  y = 0; y < height_input_line; y++){
            for(int x = 0; x < width_input_line; x++){

                tileNum = mapTileNum[x][y];
                if (tileNum != 0) {
                    y_image += (TilesSize*y);
                    x_image += (TilesSize*x);
                    screenX = (int) (x_image - player.WorldX + CommonGraph.screenWidth/100);
                    screenY = (int) (y_image - player.WorldY + CommonGraph.screenHeight/45);
                    g2d.drawImage(tiles[tileNum].Image, screenX, screenY, TilesSize, TilesSize, null);
                    tileCoordinateX[x]= x_image;
                    tileCoordinateY[y]=y_image;
                }
                y_image = 0;
                x_image = 0;

            }

        }




    }
}
