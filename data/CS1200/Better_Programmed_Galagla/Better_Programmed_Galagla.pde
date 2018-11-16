//Matthew McMillian Galaxian Program 
//750x1334
  
import ddf.minim.*;

//Notes On 11-2-16
//Need to redefine midpoint / common jump function
//Need to change the AI Movement to better allocate to project specs
//Need to make AI Attack
//Need to make Player Shoot, Possibly its own seperate obejct inside the player class

////////////////////////////////////////////////////////////////////////////////
class Enemy { // Enemy Class, call functs from here  
  
  float x = -100;  
  float y = -100;
  int w = 40;
  int h = 40;
  
  int hp;
  int type = 1;
  
  float slope;
  float b;
  
  boolean isAlive; 
  boolean isAttacking = false;
  
  PImage[] images = new PImage[2];
  int currentFrame = 0;
  int time = millis(); 
  
  Enemy() {    
    if(random(0,1) < .4){
      
      for (int i = 0; i < 2; i++) {
      String imageName = "Red_Bee" + nf(i, 4) + ".gif";
      images[i] = loadImage(imageName);
      }
      
      type = -1;
      hp = 1;
     }
    else if(random(0,1) < .8){
      
      for (int i = 0; i < 2; i++) {
      String imageName = "Yellow_Bee" + nf(i, 4) + ".gif";
      images[i] = loadImage(imageName);
      }      
      type = 0;
      hp = 1;
    }
    else{
      
      for (int i = 0; i < 2; i++) {
      String imageName = "Purple_Bee" + nf(i, 4) + ".gif";
      images[i] = loadImage(imageName);
      }    
      
      type = 2;
      hp = 2;
    }
    isAlive = true;
  }  
  void setPos(float getX, int getY) {
    x = getX;     
    y = getY;    
  }  
  void damage() {
     hp--;   
     if(type == 2 && hp == 1) {
     
     for (int i = 0; i < 2; i++) {
      String imageName = "Green_Bee" + nf(i, 4) + ".gif";
      images[i] = loadImage(imageName);
      } 
       
     }   
     if(hp == 0) {        
        isAlive = false;
        score += 10;
     }
  }
 
  void Imaging() {    
        //FRAME ANIMATION
        if(millis() > time + 400)
        {
         currentFrame = (currentFrame+1) % 2;
         time = millis();
        }
        image(images[(currentFrame) % 2],x,y,40,40);       
  }  
  void kill()  {
    hp--;
    if(isAttacking)
      score+=20;
    else
      score+=10;
      
    if(type == 2 && hp < 2)
    {
      for (int i = 0; i < 2; i++) {
      String imageName = "Green_Bee" + nf(i, 4) + ".gif";
      images[i] = loadImage(imageName);
      } 
    }
    
    //RIOT SPAGHETTI DEATH CODE
    if(hp == 0)
    {
      explosion.rewind();
      explosion.play();
      isAlive = false;
      x = -1000000;
      y = -1000000;
      hp = 0;
      isAttacking = false;
      isAlive = false;
      lastAttackTime = 0;
    }
  }   
  void setX(float p) {
    x=p;
  }  
  float getX(){
   return x; 
  }  
  void setY(float p) {
    y=p;
  }  
  float getY(){
   return y; 
  }
  
}
////////////////////////////////////////////////////////////////////////////////
class Player{ // Player Class, call functs from here
  
  int hp = 1;
  
  float x = 360;
  float deadX;
  float y = 780;
  
  float MovementVar = 0;
  float Speed = 5.0; 
  
  int w = 40;
  int h = 40;
  
  Lazer l = null;
  
  boolean isAlive = true;
  
  PImage player_ship;  
  
  Player() {           
  }
  
  void damage() {
    hp--;   
     if(hp == 0)
     {
       deadX=x;
       explosion.rewind();
       explosion.play();
       x = -10000;
       isAlive = false;
       inLevelOne = false;
       playerDead = true;
       lv1Theme.pause();
     }   
  }
  
  void Shoot()
  {
   l = new Lazer(this);
   println("Fired New Lazer");
  }
  
  void Imaging() {
    if(isAlive == true) {
      if(l != null)
      {
        if(l.Alive() == true)
           l.Imaging(this);        
      }
      
      if(this.x <= 0)
      {
        Left = 0;
      }
      
      if(this.x >= 710)
      {
        Right = 0;
     
      }
      
      MovementVar += (Right - Left) * Speed;
      x = MovementVar;   
      image(player_ship,x,y,40,40);
    }    
 }  
}
////////////////////////////////////////////////////////////////////////////////

class Lazer
{
  
  float x;
  float y;
  
  PImage Charge = loadImage("Lazer.png");
  
  boolean isAlive = false;
  
  Lazer(Player p)
  {
    x = p.x + 20;
    y = p.y;
    isAlive = true;
  }
  
  boolean Alive()
  {
    return isAlive;
  }
  
  void Imaging(Player pla)
  {
    if(isAlive == true)
    {
      if(y >= 0)
      {
        image(Charge,x,y);
        y-=10;
      }
      else if ( y < 0)
      {
        isAlive = false;
        pla.l = null;
      } 
    }
    
  }
}

////////////////////////////////////////////////////////////////////////////////

//Images for the Background and Main Menu Screens
public PImage Engine_Background;
public PImage Engine_MainMenu;
public PImage Engine_Background_GameOver;
public PImage exp;

//Audio Players for Sound Effects
public AudioSnippet explosion;
public AudioSnippet laser;
public AudioSnippet gameOver;
public AudioSnippet gameStart;
public AudioSnippet lv1Theme;

//Minim's for the Audio Players
public Minim ex;
public Minim la;
public Minim go;
public Minim gs;
public Minim lv1;

//ArrayList of the AI, Unknown Amount
public ArrayList<Enemy> LV1_ARR = new ArrayList<Enemy>();
public ArrayList<Enemy> LV2_ARR = new ArrayList<Enemy>();
public ArrayList<Enemy> LV3_ARR = new ArrayList<Enemy>();
public Player pla = new Player();
  
//Booleans to Determine the Level
public boolean inMenu = true;
public boolean inLevelOne = false;
public boolean inLevelTwo = false;
public boolean inLevelThree = false;
public boolean playerDead = false;
public boolean trans12 = false;

//Boolean to determine if the minions have moved or not
public boolean movement = false;

//Vars to provide the amount of AI on the Level
public int row;
public int col;

//Var used to determine time passed
public long lastTime = 0;
public long transTime = 0;
public int frameCounter = 0;
public int attackCounter = 0;
public long lastAttackTime = 0;

//Keeps Score per Monster Killed
public int score = 0;

//Keeps track of monster moving
public int moveCounter = 0;
public boolean move = false;

//For Smooth Movement Input
float Left = 0;
float Right = 0;

void setup() {
  size(750,830); //750x1334 Screen Size
  frameRate(60); //FPS = 60
  
  //Loads the Images Needed
  Engine_Background = loadImage("Engine_Background_750x1334.png");
  Engine_MainMenu = loadImage("MenuScreen.png");
  Engine_Background_GameOver = loadImage("Engine_Background_GameOver.png");
  pla.player_ship = loadImage("Player_Ship_Sprite.png");
  exp = loadImage("ExplosionPNG.png");
  
  //Loads the Sounds Needed
  ex = new Minim(this);
  explosion = ex.loadSnippet("explosion.mp3");
  la = new Minim(this);
  laser = la.loadSnippet("laser.mp3");
  go = new Minim(this);
  gameOver = go.loadSnippet("game_over.mp3");
  gs = new Minim(this);
  gameStart = gs.loadSnippet("game_start.mp3");
  lv1 = new Minim(this);
  lv1Theme = lv1.loadSnippet("lv1_theme.mp3");
  
  lastTime = millis();
  
  //Creates AI for the Arrays
  initalizeAI();
  createAI();
}

void draw() {  
  if(inMenu == true)
  {
      mainMenu();
  }
  else if(inLevelOne == true)
  {
      LevelOne();
  }
  else if(inLevelTwo == true)
  {
      LevelTwo();
  }
  else if(trans12 == true)
  {
    playerDead();
  }
  else if(inLevelThree == true)
  {
      LevelThree();
  }
  else if(playerDead == true)
  {
      playerDead();
      
  }  
}

void playerDead()
{
  background(Engine_Background_GameOver);
  image(exp,pla.deadX,pla.y,40,40);
  textSize(50);  
  text("Score::" + score,width/2 - 125,height/2 + 200);
  fill(255,255,255);
}

void mainMenu() {
   background(Engine_MainMenu);   
   if(keyCode == ENTER)
    {
      inMenu = false;
      inLevelOne = true;
    }   
}

void LevelOne() {
  
  background(Engine_Background);
  
  if(lv1Theme.isPlaying() == false) {
      createAI();
      lv1Theme.rewind();
      lv1Theme.play();
  }  
  
  //Functions that must be called every frame, regardless of Level
  imageAI();
  pla.Imaging();
  moveAI();
  checkConditions();
  
  
  
  //Score Initilization
  textSize(20);  
  text("Score::" + score,20,25);
  fill(255,255,255);
  
  LV1_ARR.get(attackCounter).isAttacking = true;
  
  if(LV1_ARR.get(attackCounter).isAlive == false)
  {
    if(attackCounter+1 < LV1_ARR.size())
    {
     attackCounter++;  
    }
  }
  
  boolean allAlive = false;
  
  for(int i = 0; i < LV1_ARR.size(); i++)
  {
     if(LV1_ARR.get(i).isAlive == true)
     {
      allAlive = true; 
     }
  }
  
  if(allAlive == false)
  {     
      LV1_ARR = null;
      LV1_ARR = new ArrayList<Enemy>();
      initalizeAI();
      createAI();
      attackCounter = 0;
  }
  
  
}

void LevelTwo() {
  
}

void LevelThree() {
  
}

void checkConditions() { //Collision
  
  //Lazer Hit Detection
  for(int i = 0; i < LV1_ARR.size(); i++)
  {
     if(pla.l != null)
     {      
         if(pla.l.x >= LV1_ARR.get(i).x && pla.l.x <= LV1_ARR.get(i).x + 40 && pla.l.y >= LV1_ARR.get(i).y && pla.l.y <= LV1_ARR.get(i).y+40)
         {
           pla.l.isAlive = false;
           pla.l = null;
           LV1_ARR.get(i).kill();
         }       
     }
  }
  
  //AI Going Below The Screen
  for(int i = 0; i < LV1_ARR.size(); i++)
  {
     if(LV1_ARR.get(i).y+40 > height)
     {
       LV1_ARR.get(i).kill();
       LV1_ARR.get(i).kill();
       score-=20;
     }
  }  
  
  //Player Hit Detection
  for(int i = 0; i < LV1_ARR.size(); i++)
  {
     if(LV1_ARR.get(i).y+40 >= pla.y && LV1_ARR.get(i).y+40 <= pla.y+40 && LV1_ARR.get(i).x >= pla.x && LV1_ARR.get(i).x <= pla.x+40)
     {
        //println("Hit_Player @" + millis());
        pla.damage();
     }
  }
}

//
void keyReleased()
{
  if (key == CODED)
  {
    if (keyCode == LEFT)
    {
      Left = 0;
    }
    if (keyCode == RIGHT)  
    {
      Right = 0; 
    }
  }
}

void keyPressed() { 
  if (key == CODED)
  {
    if (keyCode == LEFT)
    {
      Left = 1;
    }
    if (keyCode == RIGHT)
    {
      Right = 1;
    }
    if(keyCode == UP)
    {
      if(pla.l == null) {     
         laser.rewind();
         laser.play();
         pla.Shoot(); 
      } 
    }
  }
}

void initalizeAI() {     
       for(int i = 0; i < 3*5; i++) {
           Enemy x = new Enemy();
           LV1_ARR.add(x); 
       }       
       for(int i = 0; i < 6*10; i++) {
           Enemy x = new Enemy();
           LV2_ARR.add(x); 
       }    
      for(int i = 0; i < 9*13; i++) {
           Enemy x = new Enemy();
           LV3_ARR.add(x); 
       }
}

void createAI() {
  if(inLevelOne == true) {    
      row = 3;
      col = 5;    
      for(int i = 0; i < row*col; i++)
      {          
          if(i < 5) {
             LV1_ARR.get(i).setPos((float)(40 + (157.5 * (i%5))),40); //Last @ 670, 3rd @ 512.5, Mid @ 355, 2nd @ 197.5, First @ 40 
          }
          else if(i < 10) {
             LV1_ARR.get(i).setPos((float)(40 + (157.5 * (i%5))),150); //Last @ 670, 3rd @ 512.5, Mid @ 355, 2nd @ 197.5, First @ 40 
          }
          else {
             LV1_ARR.get(i).setPos((float)(40 + (157.5 * (i%5))),260); //Last @ 670, 3rd @ 512.5, Mid @ 355, 2nd @ 197.5, First @ 40 
          }
      }
  }
  else if(inLevelTwo == true) {
    row = 6;
    col = 10;
    for(int i = 0; i < row*col; i++) {          
        if(i < 6) {
           LV2_ARR.get(i).setPos((float)(40 + (78.75 * (i%10))),40); // First @ 40, 2nd @ 118.75, Mid of First @ 197.5, Mid @ 355, Last @ 670
        }
        else if(i < 12) {
           LV2_ARR.get(i).setPos((float)(40 + (78.75 * (i%10))),150); // First @ 40, 2nd @ 118.75, Mid of First @ 197.5, Mid @ 355, Last @ 670
        }
        else if(i < 18){
           LV2_ARR.get(i).setPos((float)(40 + (78.75 * (i%10))),260); // First @ 40, 2nd @ 118.75, Mid of First @ 197.5, Mid @ 355, Last @ 670
        }
        else if(i < 24) {
           LV2_ARR.get(i).setPos((float)(40 + (78.75 * (i%10))),370); // First @ 40, 2nd @ 118.75, Mid of First @ 197.5, Mid @ 355, Last @ 670
        }
        else if(i < 30) {
           LV2_ARR.get(i).setPos((float)(40 + (78.75 * (i%10))),480); // First @ 40, 2nd @ 118.75, Mid of First @ 197.5, Mid @ 355, Last @ 670
        }
        else {
           LV2_ARR.get(i).setPos((float)(40 + (78.75 * (i%10))),590); // First @ 40, 2nd @ 118.75, Mid of First @ 197.5, Mid @ 355, Last @ 670
        }
    }
  }
  else if(inLevelThree == true) {
    row = 9;
    col = 13;
  }  
}

void imageAI()
{
  for(int i = 0; i < 3*5; i++) {
          LV1_ARR.get(i).Imaging();
       }       
       for(int i = 0; i < 6*10; i++) {
           LV2_ARR.get(i).Imaging();
       }    
      for(int i = 0; i < 9*13; i++) {
           LV3_ARR.get(i).Imaging();
       }
}

void moveAI()
{
 if(inLevelOne == true)
 {
   if(moveCounter < 40 && move == false)
   {
     for(int i = 0; i < LV1_ARR.size(); i++)
     {
         /*
          LV1_ARR.get(i).setX(LV1_ARR.get(i).x-1);
          if(LV1_ARR.get(i).isAttacking)
          {
            LV1_ARR.get(i).setY(LV1_ARR.get(i).y+1);
          }
         */ 
         
         if(!LV1_ARR.get(i).isAttacking)
         {
            LV1_ARR.get(i).setX(LV1_ARR.get(i).x-1); 
         }
         else
         {
             if(LV1_ARR.get(i).getX() <= pla.x)
             {
               LV1_ARR.get(i).setX(LV1_ARR.get(i).x+1.5);
               LV1_ARR.get(i).setY(LV1_ARR.get(i).y+1.5); 
             }
             else if(LV1_ARR.get(i).getX() >= pla.x)
             {
               LV1_ARR.get(i).setX(LV1_ARR.get(i).x-1.5);
               LV1_ARR.get(i).setY(LV1_ARR.get(i).y+1.5);
             }
         }
     }     
     moveCounter++;
     
     if(moveCounter == 40)
     {
      move = true; 
     }
   }
   
   
   if(moveCounter > -40 && move == true)
   {
     for(int i = 0; i < LV1_ARR.size(); i++)
     {
         /*
          LV1_ARR.get(i).setX(LV1_ARR.get(i).x+1);
          if( LV1_ARR.get(i).isAttacking)
          {
            LV1_ARR.get(i).setY(LV1_ARR.get(i).y+1);
          }
        */
        
        if(!LV1_ARR.get(i).isAttacking)
         {
            LV1_ARR.get(i).setX(LV1_ARR.get(i).x+1); 
         }
         else
         {
             if(LV1_ARR.get(i).getX() <= pla.x)
             {
               LV1_ARR.get(i).setX(LV1_ARR.get(i).x+1.5);
               LV1_ARR.get(i).setY(LV1_ARR.get(i).y+1.5); 
             }
             else if(LV1_ARR.get(i).getX() >= pla.x)
             {
               LV1_ARR.get(i).setX(LV1_ARR.get(i).x-1.5);
               LV1_ARR.get(i).setY(LV1_ARR.get(i).y+1.5);
             }
         }
     }
     
     moveCounter--;
     
     if(moveCounter == -40)
     {
      move = false; 
     }
     
   }
  }
}
/*
void moveAI()
{  
  if(millis()-lastTime > 2000 && movement == false)
  {
     if(inLevelOne == true) // Movement should be constant according to the time needed to move from side xMin to xMax... Redo *****
                            // Move all rows to the left until the guy @ pos 0 cant move anymore within boundary. Same for Right until guy @ pos max-1 cant move anymore within boundary
     {
       for(int i = 0; i < row*col; i++)
       {     
           if(i < 5 || i > 10)
           {
              LV1_ARR.get(i).x = LV1_ARR.get(i).x + 30;
           }
           else
           {
              LV1_ARR.get(i).x = LV1_ARR.get(i).x - 30;
           }         
       } 
     }    
     lastTime = millis();
     movement = true;
  }
  else if(millis()-lastTime > 2000 && movement == true)
  {
    if(inLevelOne == true)
     {
       for(int i = 0; i < row*col; i++)
       {     
           if(i < 5 || i > 10)
           {
              LV1_ARR.get(i).x = LV1_ARR.get(i).x - 30;
           }
           else
           {
              LV1_ARR.get(i).x = LV1_ARR.get(i).x + 30;
           }         
       } 
     }
     lastTime = millis();
     movement = false;
  }  
  
}
*/