//Tetris Using Processing
//by: Kenny Neutron
//May 19,2021


PFont arial_black;

Init init;
Boxx b;
Tile tile;
Main main;


int col;
int row;
int[][] tileAD= new int[20][40];
int thiscol;
int thisrow;

int afcol1=0;
int afcol2=0;
int afcol3=0;
int afcol4=0;

int afrow1=0;
int afrow2=0;
int afrow3=0;
int afrow4=0;

int tileX=180;
int tileY=220;
int tileN=6;
int tileDIR=1;
int nxTILE;
int stTILE;

int count=0;
int scrolling=0;
int tscroll=0;
int transfer=0;
int speed=50;

int rowclear=0;
int[] rowC=new int[4];

int score=0;
int highscore=0;

boolean gameover=false;
boolean pause=false;

char keypress;

void setup() {
  size(480, 720);
  frameRate(60);
  arial_black=createFont("Arial-Black-48.vlw", 48);
  init=new Init();
  b=new Boxx();
  tile=new Tile();
  main=new Main();
  init.screen();
  init.tileADs();
  tile.nextTile();
  tileN=nxTILE;
  tile.nextTile();
  stTILE=0;
}

void draw() {
  if (pause==false) {
    main.Draw();
  } else if (pause==true) {
    init.screen();
    init.drawArea();
    tile.drawNext();
    tile.drawStored();
    tile.tileContent();
    displayScore();
    textSize(30);
    textAlign(CENTER, BOTTOM);
    fill(0);
    text("PAUSED", 200, 160);
    tile.drawTile(tileX, tileY, tileN, tileDIR);
    strokeWeight(2);
  }
}


void keyPressed() {
  keypress=key;
  if (key=='a'&&gameover==false&&pause==false) {
    tileX-=20;
    if (tileX<100) {
      tileX=100;
    }
    tile.getloc();
    tile.areaAffected();
    if (tileAD[afcol1][afrow1]!=0||tileAD[afcol1][afrow2]!=0||tileAD[afcol3][afrow3]!=0||tileAD[afcol4][afrow4]!=0) {
      tileX+=20;
    }
  }

  if (key=='d'&&gameover==false&&pause==false) {
    tileX+=20;
    tile.getloc();
    tile.areaAffected();
    if (tileAD[afcol1][afrow1]!=0||tileAD[afcol2][afrow2]!=0||tileAD[afcol3][afrow3]!=0||tileAD[afcol4][afrow4]!=0) {
      tileX-=20;
    }
  }

  if (key=='['&&gameover==false&&pause==false) {
    tileDIR--;
    if (tileAD[afcol1][afrow1]!=0||tileAD[afcol2][afrow2]!=0||tileAD[afcol3][afrow3]!=0||tileAD[afcol4][afrow4]!=0) {
      tileDIR++;
    }
    if (tileDIR<=0) {
      tileDIR=4;
    }
  }
  if (key==']'&&gameover==false&&pause==false) {
    tileDIR++;
    if (tileAD[afcol1][afrow1]!=0||tileAD[afcol2][afrow2]!=0||tileAD[afcol3][afrow3]!=0||tileAD[afcol4][afrow4]!=0) {
      tileDIR--;
    }
    if (tileDIR>=5) {
      tileDIR=1;
    }
  }

  if (key=='p'&&gameover==false&&pause==false) {
    int temp_stTILE;
    if (stTILE==0) {
      stTILE=tileN;
      tileX=180;
      tileY=220;
      tileDIR=1;
      tscroll=0;
      tileN=nxTILE;
      tile.nextTile();
    } else {
      temp_stTILE=stTILE;
      stTILE=tileN;
      tileN=temp_stTILE;
    }
  }

  if (key=='P'&&gameover==false) {
    if (pause==false) {
      pause=true;
    } else if (pause==true) {
      pause=false;
    }
  }

  if (key=='n'&&gameover==true&&pause==false) {
    tileX=180;
    tileY=220;
    init.tileADs();
    tile.nextTile();
    tileN=nxTILE;
    tile.nextTile();
    stTILE=0;
    gameover=false;
    score=0;
    count=0;
  }
}
