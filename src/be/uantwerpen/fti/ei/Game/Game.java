
package be.uantwerpen.fti.ei.Game;
import be.uantwerpen.fti.ei.Entity.Entity;
import be.uantwerpen.fti.ei.Entity.map.Tiles;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.Input.Inputs;
import be.uantwerpen.fti.ei.Graphic.CommonGraph;
import be.uantwerpen.fti.ei.Graphic.J2Dinput;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


import static be.uantwerpen.fti.ei.Entity.Entity.getMovement;
import static be.uantwerpen.fti.ei.Graphic.J2DTile.*;


public class Game {

    private final CommonGame commonGame = CommonGame.getInstance();
    private final AFact F;

    private static Input input;
    static int coordinaatX = 1;
    static int coordinaatY = 8;  //game height


    private boolean isRunning = true;

    private int score = 0;
    public double downY = 0;
    private int lives = CommonGame.lives;

    public static Player player;
    private ArrayList<Input.Inputs> buttonPressed = new ArrayList<>();
    private ArrayList<Movement> movementList = new ArrayList<Movement>();

    private int level = 1;
    private static int beginCoordinaaty = 10;
    private double deltatimer;
    public boolean canFall;
    public boolean canJump;
    private Tiles map;
    boolean isPaused = false;


    float worldY;

    public Game(AFact fact) throws IOException {
        this.F = fact;
        input = F.createInput();

    }



    /**
     * Dit is de lus die het spel gaat blijven runnen.
     * 1ste zetten we de variabele juist om hierop verder op te kunnen bouwen
     * Daarna maken we een player en enemy aan
     *
     * @throws IOException
     */
    public void play() throws IOException {
        //benoem de beginnende parameters
        Input.Inputs direction;

        F.setGameDim(CommonGame.gameDimWidth, CommonGame.gameDimHeight);
        Timer.start();
        this.map = F.CreateTiles();
        //player

        player = F.CreatePlayer();

        getMovement().setDx((float) (10)); // start positie van de player
        getMovement().setDy((float) (97));
        worldY = getMovement().getDy();

        //run the game
        while (isRunning) {
            while(!isPaused) {

                playerMovement();
                checkTile();

                visualElements();
                Gravity();
            }
            pauseInputHandler();
            F.showStatus(lives, level);
            F.render();



        }


    }
    public void playerMovement(){
        if (input.inputAvaible()) {
            Input.Inputs dir = input.getInput();
            if(!buttonPressed.contains(dir)){
                buttonPressed.add(dir);
            }
            if(!buttonPressed.isEmpty()) {
                for(int i = 0; i<buttonPressed.size(); i++){
                    // It checks if the button pressed is right and if the tile is not a collision tile.
                    if (buttonPressed.get(i) == Inputs.Right && !Objects.equals(checkTile(), "Collision Right")){
                        player.move("Right",false,0);
                        if (WorldX - (64 * (coordinaatX)) >= 0) {
                            coordinaatX += 1;
                        }
                    }
                    if (buttonPressed.get(i) == Inputs.Left && !checkTile().equals("Collision Left")){
                        player.move("Left",false,0);
                        if ((64 * (coordinaatX - 1) - WorldX) >= 0) {
                            coordinaatX -= 1;
                        }
                    }
                    if (buttonPressed.get(i) == Inputs.Jump && canJump){ // uitbreiding -> timer om ervoor te zorgen dat er niet te snel achter elkaar gesprongen kan worden
                        if (Entity.onGround) {
                            Entity.onGround = false;
                            player.move("Jump",true,0);
                            coordinaatY -= 2;
                            Timer.update();
                            deltatimer = Timer.getCurrentTime();
                            canFall = true;
                        }
                    }
                }
                if(input.removalAvailable()) {
                    Input.Inputs toRemove = input.getRemoval();
                    buttonPressed.remove(toRemove);
                }
            }


        }
    }
    public void Gravity(){

        if(!Entity.onGround && coordinaatY <= 9) {
            Timer.update();
            double timer_down = Timer.getCurrentTime();
            if (timer_down -deltatimer >= 0.2) {
                System.out.println(downY);

                player.move("Down",false,downY);
                Entity.onGround = true;
                if(downY == 1.5){
                    coordinaatY += 1;

                }else
                    coordinaatY += 2;

            }

        }
    }
    public void pauseInputHandler(){
        if(input.inputAvaible()){
            isPaused = false;

            Input.Inputs direction = input.getInput();
            if(input.removalAvailable()){
                Input.Inputs toRemove = input.getRemoval();
                buttonPressed.remove(toRemove);
            }
        }
    }



    public String checkTile() {
        float leftTile = tileCoordinateX[coordinaatX];
        float rightTile = tileCoordinateX[coordinaatX] + CommonGraph.tileSize;
        float bottomTile = tileCoordinateY[coordinaatY];
        float topTile =  tileCoordinateY[coordinaatY];
        float leftPlayer = (float) (player.WorldX+ player.solidArea.x);
        float rightPlayer = (float) ( player.WorldX+player.solidArea.x+ player.solidArea.width );// + player.solidArea.getWidth());
        float bottomPlayer = bottomTile +player.solidArea.y ;
        float topPlayer = bottomTile+player.solidArea.y+player.solidArea.height;

        if(mapTileNum[coordinaatX+1][coordinaatY] == 1) {
            if (rightPlayer > leftTile) {
                //return "Collision Right";
            }
        }
        if(mapTileNum[coordinaatX][coordinaatY] == 1) {

            if (leftPlayer < rightTile) {
                return "Collision Left";

            }

        } // mario landt op een blok
        if(mapTileNum[coordinaatX][coordinaatY+1] == 1 ){
            if(!Entity.onGround ){ //&& bottomPlayer> topTile
                Entity.onGround = true;
            }
        }

        if(mapTileNum[coordinaatX+1][coordinaatY] == 1  && mapTileNum[coordinaatX][coordinaatY-1] != 1){
            //downY = 1.5; // kijken naar of de volgende tile 2 of 1 heeft -> voor de grote van de sprong te bepalen
        } if (mapTileNum[coordinaatX+1][coordinaatY]!= 1){
            //downY = 1;
            }


        // mario gaat naar beneden
        if(mapTileNum[coordinaatX][coordinaatY+1]!=1){
            if (Entity.onGround && canFall && (leftPlayer <rightTile || rightPlayer >leftTile )) {
                Entity.onGround = false;
                canFall = false;
            }
        }

        if(mapTileNum[coordinaatX][coordinaatY-1] == 1){
            canJump = false;

        } else
            canJump = true;



        return "false";
}


    private void visualElements() throws IOException {

        F.render();
        map.Vis();

        player.Vis();

    }

    private void updateplayer() throws IOException {
    }


    /**
     * We gaan alle waardes resetten zodat we het spel terug opnieuw kunnen spelen
     */
    private void reset() {
        input.clearInput();
        movementList.clear();
        lives = CommonGame.lives;
        score = 0;
        isRunning = true;

    }



}

