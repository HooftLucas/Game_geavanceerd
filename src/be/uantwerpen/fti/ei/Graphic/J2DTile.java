package be.uantwerpen.fti.ei.Graphic;
import be.uantwerpen.fti.ei.Entity.Entity;
import be.uantwerpen.fti.ei.Entity.personage.Enemy;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Entity.map.Tiles;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class J2DTile extends Tiles {
    private J2DFact fact;

    public CommonGraph commongraph = CommonGraph.Getinstance();
    public Tiles[] tiles;
    public int[][] mapTileNum;
    public float[] tileCoordinateX;
    public float[] tileCoordinateY;
    public int height_input_line;
    public int width_input_line;
    public int screenX;
    public int tileNum;
    public int x_image = 0;
    public int y_image = 0;
    public int screenY;
    public Player player;
    public Entity entity;
    private boolean remove = false;
    public ArrayList<Integer> EnemyArrayX = new ArrayList<>();
    public ArrayList<Integer> EnemyArrayY = new ArrayList<>();
    public boolean reloadMap = false;

    public J2DTile(J2DFact j2dfact, Player p) throws IOException {
        this.fact = j2dfact;
        this.player = p;
        tiles = new Tiles[10];
        mapTileNum = new int[500][50];
        tileCoordinateX = new float[10000];
        tileCoordinateY = new float[10000];

        try {
            //blok
            tiles[1] = new Tiles();
            tiles[1].Image = J2DFact.loadImages("src/Resource/Level1/block.jpg");
            tiles[1].Image = J2DFact.resizeImage(tiles[1].Image, commongraph.tileSize, commongraph.tileSize);
            //end block

            tiles[2] = new Tiles();
            tiles[2].Image = J2DFact.loadImages("src/Resource/Level1/end.jpg");
            tiles[2].Image = J2DFact.resizeImage(tiles[2].Image, commongraph.tileSize, commongraph.tileSize);
            //enemy
            tiles[3] = new Tiles();
            tiles[3].Image = J2DFact.loadImages("src/Resource/Level1/Enemy.jpeg");
            tiles[3].Image = J2DFact.resizeImage(tiles[3].Image, commongraph.tileSize, commongraph.tileSize);

            tiles[9] = new Tiles();
            tiles[9].Image = J2DFact.loadImages("src/Resource/empty.jpg");
            tiles[9].Image = J2DFact.resizeImage(tiles[9].Image, commongraph.tileSize, commongraph.tileSize);


        } catch (IOException e) {
            e.printStackTrace();
        }

        loadmap("src/Resource/World/world.txt");

    }

    public void loadmap(String path) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    break;
                } else
                    lines.add(line);

                this.width_input_line = Math.max(width_input_line, line.length());
            }
            this.height_input_line = lines.size();
            for (int y = 0; y < height_input_line; y++) {
                String line = lines.get(y);
                for (int x = 0; x < line.length(); x++) {
                    char ch = line.charAt(x);
                    if (ch == '1') {
                        mapTileNum[x][y] = 1;
                    } else if (ch == '2') {
                        mapTileNum[x][y] = 2;
                    } else if (ch == '3') {
                        EnemyArrayX.add(x);
                        EnemyArrayY.add(y);
                        mapTileNum[x][y] = 3 ;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void removeObject(int coordinateX, int coordinateY) {

        if (EnemyArrayX.contains(coordinateX)) {
            System.out.println("Test");
            mapTileNum[coordinateX][coordinateY] = 3;
            reloadMap = true;

        }
    }

    @Override
    public void Vis() {
        Graphics2D g2d = fact.get2d();

        for (int y = 0; y < height_input_line; y++) {
            for (int x = 0; x < width_input_line; x++) {

                tileNum = mapTileNum[x][y];
                if (tileNum != 0 && tileNum != 3) {

                    y_image += (commongraph.tileSize * y);
                    x_image += (commongraph.tileSize * x);
                    screenX = (int) (x_image - player.getWorldX() + commongraph.screenWidth / 100);
                    screenY = y_image - 10 + commongraph.screenHeight / 45;

                    g2d.drawImage(tiles[tileNum].Image, screenX, screenY, commongraph.tileSize, commongraph.tileSize, null);

                    tileCoordinateX[x] = x_image;
                    tileCoordinateY[y] = y_image;
                }
                y_image = 0;
                x_image = 0;


            }

        }


    }
}

