
package be.uantwerpen.fti.ei.Game;
import be.uantwerpen.fti.ei.Entity.Projectile.Bullet;
import be.uantwerpen.fti.ei.Entity.Projectile.ProjectileEntity;
import be.uantwerpen.fti.ei.Entity.map.Tiles;
import be.uantwerpen.fti.ei.Entity.personage.Enemy;
import be.uantwerpen.fti.ei.Entity.personage.Player;
import be.uantwerpen.fti.ei.Game.Input.Inputs;
import be.uantwerpen.fti.ei.Graphic.CommonGraph;
import be.uantwerpen.fti.ei.Graphic.J2DTile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class Game {
    private final CommonGame commonGame = CommonGame.getInstance();
    private final AFact F;
    private CommonGraph commonGraph;
    public Player player;
    J2DTile j2dTile;
    private boolean bulletAlive = false;
    private final Input input;
    private boolean scoreBool = false;
    int coordinaatX = 7;
    int coordinaatY = 8;  //game height

    //private MovementUpdater movementUpdater = new MovementUpdater();
    private boolean isRunning = true;

    private int score = 0;
    public double downY = 0;
    private int lives = CommonGame.lives;
    private boolean Dead = false;
    private final ArrayList<Input.Inputs> buttonPressed = new ArrayList<>();
    private final ArrayList<Movement> movementList = new ArrayList<Movement>();
    private final ArrayList<Bullet> playerBullet = new ArrayList<>();
    private final List Enemyarray = new Vector();
    private final ArrayList<Integer> enemylist = new ArrayList<>();
    private int level = 1;
    private double deltatimer;
    public boolean canFall;
    public boolean canJump = true;
    private Tiles map;
    boolean isPaused = false;
    private int teller =8;
    private boolean directionPlayer =false;
    private int xcoordinatebullet;
    Enemy enemy;
    boolean enemydead = false;
    // This is a boolean that is used to check if the player is colliding with the left side of the tile.
    private boolean coll_left = false;
    private boolean coll_right = false;
    private boolean coll_jump = false;
    int xpositie = 7 ;

    public Game(AFact fact) throws IOException {
        this.F = fact;
        input = F.createInput();

    }


    public void play() throws IOException {
        //benoem de beginnende parameters
        Input.Inputs direction;

        F.setGameDim(commonGame.gameDimWidth, commonGame.gameDimHeight);
        this.player = F.CreatePlayer();
        //player



        player.getMovement().setDx((float) (52.5)); // start positie van de player
        player.getMovement().setDy((float) (97));
        player.setWorldX(player.getMovement().getDx());

        this.map = F.CreateTiles(player);
        this.j2dTile = F.CreateTiles(player);
        enemy = F.createEnemy(player);
        int tellerx = 0;
        for (Integer enemyArrayX : j2dTile.EnemyArrayX) {
            enemy.setxList(enemyArrayX);
            Enemyarray.add(enemyArrayX);
            enemylist.add(enemyArrayX);
        }
        for (Integer integer : j2dTile.EnemyArrayY) {
            enemylist.add(integer);
            enemy.setyList(integer);

        }



        Timer.start();
        //run the game
        while (isRunning) {
            while (!isPaused) {

                playerMovement();
                checkTile();
                visualElements();
                handleEntities();
                Gravity();

            }
            pauseInputHandler();
            F.showStatus(lives, level);
            F.render();


        }


    }

    public void playerMovement() throws IOException {
        float leftTile = coordinaatX *64;
        float rightTile = coordinaatX *64+64;
        float bottomTile = coordinaatY *64+64;
        float topTile = coordinaatY *64;

        float leftPlayer = (float) (player.getWorldX()+ player.solidArea.x);
        float rightPlayer = (float) (player.getWorldX() + player.solidArea.x + player.solidArea.width+329);
        float bottomPlayer = bottomTile + player.solidArea.y;
        float topPlayer = bottomTile - player.solidArea.y - player.solidArea.height;
        float BulletRight = (float) (player.getWorldX()+xcoordinatebullet);
        TileEnemy( leftPlayer, rightPlayer, leftTile, rightTile, bottomPlayer, topTile);

        if (input.inputAvaible()) {
            Input.Inputs dir = input.getInput();
            if (!buttonPressed.contains(dir)) {
                buttonPressed.add(dir);
            }
            for (int i = 0; i < buttonPressed.size(); i++) {
                if (buttonPressed.get(i) == Inputs.Right  && RightTile(rightPlayer, leftTile)) {

                    player.move("Right", false, 0);
                    directionPlayer= false;
                    teller += 1;
                    if (teller == 16){
                        coordinaatX += 1;
                        teller = 8;
                    }

                }
                if (buttonPressed.get(i) == Inputs.Left&& player.getWorldX() > 9 && LeftTile(leftPlayer,rightTile) ) {
                    player.move("Left", false, 0);
                    directionPlayer = true;

                    teller -= 1;
                    if (teller == 0){
                        coordinaatX -= 1;
                        teller = 8;
                    }


                }
                if (buttonPressed.get(i) == Inputs.Jump && canJump&& JumpTile(leftPlayer,rightPlayer,rightTile, leftTile)){ // uitbreiding -> timer om ervoor te zorgen dat er niet te snel achter elkaar gesprongen kan worden
                    if (player.isOnGround()) {
                        player.setOnGround(false);
                        player.move("Jump", true, 0);
                        coordinaatY -= 2;
                        Timer.update();
                        deltatimer = Timer.getCurrentTime();
                        canFall = true;
                        coll_right = false;
                        coll_left = false;

                    }
                }
                if (buttonPressed.get(i) == Inputs.fire) {
                    Bullet B = F.CreateBullet(player);
                    B.getMovement().setDx(player.getMovement().getDx()+20);
                    B.getMovement().setDy(player.getMovement().getDy() +5);
                    B.move("Fire", false,0);
                    playerBullet.add(B);

                }
            }
            while (input.removalAvailable()) {
                Inputs toRemove = input.getRemoval();
                buttonPressed.remove(toRemove);
            }

        }

    }

    public void Gravity() {

        if (!player.isOnGround() && coordinaatY <= 9) {
            Timer.update();
            double timer_down = Timer.getCurrentTime();
            if (timer_down - deltatimer >= 0.2) {
                player.move("Down", false, downY);
                player.setOnGround(true);
                if (downY == 1.5) {
                    coordinaatY += 1;

                } else
                    coordinaatY += 2;

            }
        canFall = false;
        }
    }

    public void pauseInputHandler() {
        if (input.inputAvaible()) {
            isPaused = false;

            Input.Inputs direction = input.getInput();
            if (input.removalAvailable()) {
                Input.Inputs toRemove = input.getRemoval();
                buttonPressed.remove(toRemove);
            }
        }
    }

    public Boolean RightTile(float rightPlayer, float leftTile){
        if (j2dTile.mapTileNum[coordinaatX+1][coordinaatY] == 1) {
            if (rightPlayer > leftTile) {
                return false;
            }
        }
        return true;
    }
    public boolean LeftTile(float leftPlayer, float rightTile){
        if (j2dTile.mapTileNum[coordinaatX-1][coordinaatY] == 1) {
            if (leftPlayer < rightTile) {
                return false;
            }
        }
        return true;
    }
    public boolean JumpTile(float leftPlayer, float rightPlayer, float rightTile, float leftTile){
        if(j2dTile.mapTileNum[coordinaatX][coordinaatY+1] == 1){
            if((leftPlayer < rightTile || rightPlayer > leftTile) && !player.onGround ){
                player.setOnGround(true);
                canFall = false;

            }
        }

        if (j2dTile.mapTileNum[coordinaatX][coordinaatY - 1] == 1 ) {

            return false;
        }



        return true;
    }
    public void checkTile() {
        if(j2dTile.mapTileNum[coordinaatX][coordinaatY+1 ] != 1){
            player.setOnGround(false);
            canFall = true;
        }
    }
    public void TileEnemy(float leftPlayer,float rightPlayer,float leftTile, float rightTile, float bottomplayer, float toptile){
        if (j2dTile.mapTileNum[coordinaatX+1][coordinaatY] ==3){

            if (teller > 10 && !directionPlayer && rightPlayer >leftTile){
                if (lives != 0) {
                    player.setKilled(true);
                    killedEnemy();

                }
                else
                    reset();
            }
        if( bottomplayer > toptile ){
                    killedEnemy();
                    scoreBool = true;


                }


            }


        }
        public void collisionBulletEnemy(Bullet bullet, Iterator<Bullet> b){
        /**
            float rightbullet = bullet.getMovement().getDx();
                        rightbullet -= 72;

            float beginpositie = (float) ((float) player.getWorldX()-52.5);
            if (beginpositie % 64 ==0){
                xpositie +=1;

            }
            //System.out.println(rightbullet);
            //System.out.println(xpositie);
            if (rightbullet < (xpositie/10) && Enemyarray.size()!= 0){
                System.out.println("test");
                score += 100;
                b.remove();
                killedEnemy();
                enemydead = true;
            }
         */
        }



    public void killedEnemy(){

            if(Enemyarray.contains(coordinaatX+1 )){
                enemy.removeX(getListValue());
                Enemyarray.remove(getListValue());
                lives -= 1;
                if (scoreBool){
                    score += 100;
                }

            }
            if (enemydead){
                enemy.removeX(0);
                Enemyarray.remove(0);
            }



    }
    public int getListValue(){
        int teller = 0;
        for(int x= 0; x < enemylist.size(); x++){
            if(enemylist.contains(coordinaatX + 1)){
                return teller;

                }
            else teller ++;


        }
        return 0;
    }
    private void visualElements() throws IOException {
        F.render();
        for(ProjectileEntity elem: playerBullet) { elem.Vis(); }
        player.Vis();
        enemy.Vis();
        map.Vis();


    }
    public void handleEntities() throws IOException {
        Iterator<Bullet> b = playerBullet.iterator();

        while(b.hasNext()){
            Bullet bullet = b.next();
            if (bullet.getMovement().getDx() > commonGame.gameDimWidth){
                    b.remove();}
                else{
                    bullet.fly();
                    bullet.Vis();
                    collisionBulletEnemy(bullet, b);

                }

                if (bullet.getMovement().getDx() - (64*xcoordinatebullet) >=0 && !directionPlayer){

                    xcoordinatebullet +=1;
                }

        }
    }

        private void reset() {
            input.clearInput();
            movementList.clear();
            lives = CommonGame.lives;
            score = 0;
            isRunning = true;

        }



    }


