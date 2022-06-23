package be.uantwerpen.fti.ei.Graphic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class CommonGraph {
    private static CommonGraph INSTANCE;

    public int screenWidth;

    public int screenHeight;

    public int tileSize;


    public CommonGraph() throws IOException {
        FileInputStream fileinputstream = new FileInputStream("src/Resource/graphconfig.properties");
        Properties prop = new Properties();
        prop.load(fileinputstream);

        this.screenWidth = Integer.parseInt(prop.getProperty("screenWidth"));
        this.screenHeight = Integer.parseInt(prop.getProperty("screenHeight"));
        this.tileSize = Integer.parseInt(prop.getProperty("tileSize"));

    }
    /**
     * Singleton klasse, dit is voor het openen van deze klasse
     */
    public static CommonGraph Getinstance() throws IOException {
        if (INSTANCE == null){
            INSTANCE = new CommonGraph();
        }
        return INSTANCE;
    }

}
