package be.uantwerpen.fti.ei.Game;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/*
Hierin gaan we alle algemene instellingen uitlezen uit een config bestand en het doorgeven
 */
public final class CommonGame {
    /*
    Dit hebben we nodig om alle instellingen in te lezen en te verwerken
     */
    private static CommonGame Instance;

    public static int gameDimWidth;
    public static int gameDimHeight;

    public double speedPlayerx;
    public double speedPlayery;
    public double playerWidth;
    public double playerHeight;

    public double MoveEnemySpeedX;
    public double MoveEnemySpeedY;
    public int EnemyWidth;
    public int EnemyHeight;
    public int BeginPotition;

    public static int lives;
    public double WorldX;
    public double WorldY;

    /**
     * Hier maken we de functie van de CommonGame.
     * We gaan het configbestand inlezen en deze kopplene aan de variabele van hierboven.
     * Deze hebben we verder nog nodig in de code
     */

    public CommonGame() throws IOException{
        FileInputStream fileInputStream = new FileInputStream("src/Resource/config.properties");
        Properties prop = new Properties();
        prop.load(fileInputStream);
        //grote scherm
        this.gameDimWidth = Integer.parseInt(prop.getProperty("gameDimWidth"));
        this.gameDimHeight = Integer.parseInt(prop.getProperty("gameDimHeight"));
        //player
        this.playerWidth = Double.parseDouble(prop.getProperty("playerWidth"));
        this.playerHeight = Double.parseDouble(prop.getProperty("playerHeight"));
        this.speedPlayerx = Double.parseDouble(prop.getProperty("speedPlayerx"));
        this.speedPlayery = Double.parseDouble(prop.getProperty("speedPlayery"));
        this.BeginPotition = Integer.parseInt(prop.getProperty("BeginPotition"));
        // enemy
        this.EnemyHeight = Integer.parseInt(prop.getProperty("EnemyHeight"));
        this.EnemyWidth = Integer.parseInt(prop.getProperty("EnemyWidth"));
        this.MoveEnemySpeedX = Double.parseDouble(prop.getProperty("speedMoveEnemyx"));
        this.MoveEnemySpeedY = Double.parseDouble(prop.getProperty("speedMoveEnemyy"));

        //rand info
        this.lives = Integer.parseInt(prop.getProperty("lives"));
        this.WorldX = Double.parseDouble(prop.getProperty("WorldX"));
        this.WorldY = Double.parseDouble(prop.getProperty("WorldY"));
    }

    public static CommonGame getInstance() throws IOException {
        if(Instance == null)
        {
            Instance = new CommonGame();
        }
        return Instance;
    }


}
