//Matthew McMillian Galaxian Program  
  
import ddf.minim.*;

class Enemy {  
  int x;  
  int y;
  int hp;
  int type;
  boolean isAlive;  
  PImage enemy_avatar;
  
  Enemy() {    
    if(random(0,1) < .4)
    {
      enemy_avatar = loadImage("AI_Bee.gif");
      type = 1;
      hp = 1;
    }
    else if(random(0,1) < .8)
    {
      enemy_avatar = loadImage("AI_Bee_2.png");
      type = 1;
      hp = 1;
    }
    else
    {
      enemy_avatar = loadImage("AI_3_Full.png");
      type = 2;
      hp = 2;
    }
    isAlive = true;
  }
  
  void setPos(int getX, int getY)
  {
    x = getX;     
    y = getY;    
  }
  
  void damage() {
   hp--;   
   if(type == 2)
   {
     enemy_avatar = loadImage("AI_3.png");
   }
  }
  
  void Imaging() {
    if(isAlive == true)
      image(enemy_avatar,x,y,40,40);
  }
  
  void kill()  {
    if(hp <= 0)
    {
      isAlive = false;
      x = -1000;
      y = -1000;
    }
  }
  
  void setX(int p) {
    x=p;
  }
  
  int getX(){
   return x; 
  }
  
  void setY(int p) {
    x=p;
  }
  
  int getY(){
   return y; 
  }
  
}

public PImage player_ship;
public PImage Engine_Background;
public PImage Engine_MainMenu;
public PImage player_projectile;

public boolean isStart = false;
public boolean isShoot = false;
public boolean isGoing = false;
public boolean AI_isShoot = false;
public boolean AI_isGoing = false;
public boolean movement = false;
public boolean complete = false;

public AudioSnippet explosion;
public AudioSnippet laser;
public AudioSnippet gameOver;
public AudioSnippet gameStart;
public AudioSnippet lv1Theme;

public Minim ex;
public Minim la;
public Minim go;
public Minim gs;
public Minim lv1;

public long lastTime = 0;

public int player_xPos = 240;
public static int player_yPos = 640;
public int projectile_xPos;
public int projectile_yPos;

public int Score = 0;

public Enemy[] arr = new Enemy[12]; //115 Pixels Apart

public int[] xCoor = {103,229,356};
public static int ySpot = 470;


void setup() {
  
  size(500,700);
  frameRate(60);  
  player_ship = loadImage("Player_Ship_Sprite.png");
  Engine_Background = loadImage("Engine_Background_500x700.png");
  Engine_MainMenu = loadImage("MenuScreen.png");
  player_projectile = loadImage("Lazer.png");
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
  
  gameStart.rewind();
  gameStart.play();
  
  lastTime = millis();
  createEnemies();
  setEnemies(); 
  
  background(Engine_MainMenu);
}

void draw() {
  
  textSize(20);
  text("Score::" + Score,20,30);
  fill(255,255,255);
  
  if(isShoot == true && isGoing == true)
  {
    projectile_yPos -= 10; 
  }
    
  if(isStart == false)
  {    
    Menu();
  }
  else if(complete == false)
  {
    if(lv1Theme.isPlaying() == false)
    {
        lv1Theme.rewind();
        lv1Theme.play();
    }    
    hitReg(projectile_xPos,projectile_yPos);
    startGame();
    checkConditions();
    textSize(20);
    text("Score::" + Score,20,30);
    fill(255,255,255);
  }
  else
  {
    lv1Theme.pause();
  }
  
  
}

void Menu()
{  
  if(keyCode == ENTER)
  {
    isStart = true;
  }
}

void startGame()
{  
  background(Engine_Background);
  image(player_ship,player_xPos,player_yPos,40,40);
  
  if(millis()-lastTime > 2000 && movement == false)
  {
     for(int i = 0; i < 12; i++)
     {     
         if(i < 4 || i > 7)
         {
           arr[i].setX(arr[i].getX()+30);
         }
         else
         {
           arr[i].setX(arr[i].getX()-30);
         }
         arr[i].Imaging();
     }
     lastTime = millis();
     movement = true;
  }
  else if(millis()-lastTime > 2000 && movement == true)
  {
     for(int i = 0; i < 12; i++)
     {     
         if(i < 4 || i > 7)
         {
           arr[i].setX(arr[i].getX()-30);
         }
         else
         {
           arr[i].setX(arr[i].getX()+30);
         }
         arr[i].Imaging();
     }
     lastTime = millis();
     movement = false;
  }
  else
  {
    for(int i = 0; i < 12; i++)
    {
     arr[i].Imaging(); 
    }
  }
   
  
  if(isShoot == true && isGoing == true)
  {
     image(player_projectile,projectile_xPos,projectile_yPos,4,20); 
  }  
}

void keyPressed(){
 
  switch(keyCode){
    
    case RIGHT:    
      println(player_xPos);
      if(player_xPos != 440)
      {
        player_xPos += 10; 
        break;
      }  
    
    case LEFT:  
      println(player_xPos);
      if(player_xPos != 10)
      {
        player_xPos -= 10;
        break;
      }
     
    case ' ':
      println("Shoot");
      if(isShoot == false && isGoing == false)
      {
        laser.rewind();
        laser.play(); 
      }
      Shoot();
      
     
    default:
      break;
  }  
}

void Shoot()
{
  if(isShoot == false)
  {
    isShoot = true;
    projectile_yPos = player_yPos;
    projectile_xPos = player_xPos+20;    
    image(player_projectile,projectile_xPos,projectile_yPos,4,20);
    isGoing = true;
  }
}

void checkConditions()
{  
  if(projectile_yPos == 0)
  {
     isShoot = false;
     isGoing = false;
  }
  if(millis()%3000 == 0)
  {
    movement = false;
  }  
  for(int i = 0; i < 12; i++)
  {
    if(arr[i].isAlive == false)
    {
      complete = true;
    }
    else
    {
     complete = false;
     break;
    }
  }
}

void createEnemies()
{
  for(int i = 0; i < 12; i++)
  {
     arr[i] = new Enemy();  
  }
}

void setEnemies()
{  
  arr[0].setPos(40,40);
  arr[1].setPos(166,40);
  arr[2].setPos(292,40);
  arr[3].setPos(420,40);
  arr[4].setPos(40,150);
  arr[5].setPos(166,150);
  arr[6].setPos(292,150);
  arr[7].setPos(420,150);
  arr[8].setPos(40,260);
  arr[9].setPos(166,260);
  arr[10].setPos(292,260);
  arr[11].setPos(420,260);  
}

void hitReg(int x, int y) //Projectile is 4x20
{
  for(int i = 0; i < 12; i++)
  {
    if((arr[i].getX() <= x && x <= (arr[i].getX()+40)) && (arr[i].getY() <= y && y <= (arr[i].getY()+40)))
    {  
        Score += 100;
        arr[i].damage();
        projectile_xPos = 1000000;
        projectile_yPos = 1000000;
        arr[i].kill();
        explosion.rewind();
        explosion.play();          
           
      isGoing = false;
      isShoot = false;  
    }
  }
}