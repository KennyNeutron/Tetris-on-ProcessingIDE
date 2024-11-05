import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Tetris extends PApplet {

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

public void setup() {
  
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

public void draw() {
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


public void keyPressed() {
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
public class Boxx {
  public void drawBox(int X, int Y, int R, int G, int B) {
    stroke(0);
    strokeWeight(2);
    fill(R, G, B);
    rect(X, Y, 20, 20, 5);
  }
}

public void displayScore() {
  fill(0);
  textSize(20);
  textAlign(LEFT, BOTTOM);
  text("SCORE:", 80, 195);
  text("HIGH:",220,195);
  textSize(30);
  text(score,160,200);
  text(highscore,280,200);
  
}
public class Init {
  public void screen() {
    background(200);
    textFont(arial_black, 48);
    fill(0);
    textSize(40);
    textAlign(CENTER, TOP);
    text("TETRIS", width/2, 10);
    textSize(20);
    text("by: Kenny Neutron", width/2, 55);
    fill(255);
    stroke(0);
    rect(85, 205, 230, 430);
  }

  public void drawArea() {
    int a;
    int areaX=80;
    int areaY=200;

    for (a=0; a<=10; a++) {
      b.drawBox(areaX, 200, 100, 100, 100);
      b.drawBox(areaX, 620, 100, 100, 100);
      areaX+=20;
    }
   
    for (a=0; a<=21; a++) {
      b.drawBox(80, areaY, 100, 100, 100);
      b.drawBox(300, areaY, 100, 100, 100);
      areaY+=20;
    }
    //DEBUGGING COORDINATE
    /*
    fill(0);
    textSize(10);
    textAlign(CENTER, TOP);
    int txtY=220;
    for (a=0; a<20; a++) {
      text(a, 90, txtY+5);
      txtY+=20;
    }

    int txtX=110;
    for (a=0; a<10; a++) {
      text(a, txtX, 200);
      txtX+=20;
    }*/
  }


  public void tileADs() {
    int arow=0;
    int acol=0;
    for (arow=0; arow<20; arow++) {
      for (acol=0; acol<10; acol++) {
        tileAD[acol][arow]=0;
      }
    }
  }
}
class Main {

  public void Draw() {  
    /*
  tileAD[0][19]=2;
     tileAD[1][19]=2;
     tileAD[2][19]=2;
     tileAD[3][19]=2;
     tileAD[4][19]=2;
     tileAD[5][19]=2;
     tileAD[6][19]=2;
     tileAD[7][19]=2;
     tileAD[8][19]=2;*/

    init.screen();
    init.drawArea();
    tile.drawNext();
    tile.drawStored();
    tile.tileContent();
    if (gameover==false) {  
      tile.checkloc();
      tile.tileEnd();
    }


    if (keypress=='s'&&tscroll==0&&gameover==false) {
      while (tscroll==0) {
        tile.goDown();
        tile.checkloc();
        tile.tileContent();
        tile.tileEnd();
      }
      keypress=' ';
    }
    if (tscroll==0&&gameover==false) {
      tile.drawTile(tileX, tileY, tileN, tileDIR);
    } else if (tscroll==1&&gameover==false) {
      transfer_content();
      tileX=180;
      tileY=220;
      tileDIR=1;
      tscroll=0;
      tileN=nxTILE;
      tile.nextTile();
      count=0;
    }
    tile.checkClear();
    delay(speed);

    count++;
    if (count%6==0&&gameover==false) {
      tile.goDown();
    }

    if (gameover==true) {
      textSize(30);
      textAlign(CENTER, BOTTOM);
      fill(0);
      text("GAME OVER!", 200, 160);
      tile.drawTile(tileX, tileY, tileN, tileDIR);
    }

    if (score>highscore) {
      highscore=score;
    }

    displayScore();

    //Debugging
    //b.drawBox(280,600,0,255,255);
    /*
  fill(0);
     textSize(15);
     text("rC0:", 30, 30);
     text(rowC[0], 50, 30);
     text("rC1:", 30, 45);
     text(rowC[1], 50, 45);
     text("rC2:", 30, 60);
     text(rowC[2], 50, 60);
     text("rC3:", 30, 75);
     text(rowC[3], 50, 75);
     text("rcN:", 30, 90);
     text(rowclear, 50, 90);*/
  }

  public void transfer_content() {
    if (afrow1-1<0||afrow2-1<0||afrow3-1<0||afrow4-1<0) {
      gameover=true;
    }
    if (transfer==0&&gameover==false) {
      tileAD[afcol1][afrow1]=tileN;
      tileAD[afcol2][afrow2]=tileN;
      tileAD[afcol3][afrow3]=tileN;
      tileAD[afcol4][afrow4]=tileN;
    } else if (transfer==1&&gameover==false) {
      tileAD[afcol1][afrow1-1]=tileN;
      tileAD[afcol2][afrow2-1]=tileN;
      tileAD[afcol3][afrow3-1]=tileN;
      tileAD[afcol4][afrow4-1]=tileN;
    }
  }
}
public class Tile {
  public void drawStored() {
    fill(0);
    textSize(20);
    textAlign(LEFT, BOTTOM);
    text("STORED:", 340, 540);
    fill(255);
    stroke(0);
    strokeWeight(4);
    rect(340, 540, 100, 100, 10);
    switch(stTILE) {
    case 1:
      drawTile(370, 570, 1, 1);
      break;
    case 2:
      drawTile(380, 550, 2, 1);
      break;    
    case 3:
      drawTile(360, 570, 3, 1);
      break;    
    case 4:
      drawTile(360, 570, 4, 1);
      break;    
    case 5:
      drawTile(370, 560, 5, 1);
      break;    
    case 6:
      drawTile(370, 560, 6, 1);
      break;    
    case 7:
      drawTile(360, 570, 7, 1);
      break;
    }
  }

  public void drawNext() {
    fill(0);
    textSize(20);
    textAlign(LEFT, BOTTOM);
    text("NEXT:", 340, 240);
    fill(255);
    stroke(0);
    strokeWeight(4);
    rect(340, 240, 100, 100, 10);
    switch(nxTILE) {
    case 1:
      drawTile(370, 270, 1, 1);
      break;
    case 2:
      drawTile(380, 250, 2, 1);
      break;    
    case 3:
      drawTile(360, 270, 3, 1);
      break;    
    case 4:
      drawTile(360, 270, 4, 1);
      break;    
    case 5:
      drawTile(370, 260, 5, 1);
      break;    
    case 6:
      drawTile(370, 260, 6, 1);
      break;    
    case 7:
      drawTile(360, 270, 7, 1);
      break;
    }
  }
  public void checkClear() {
    int crow=0;
    int a=0;
    for (crow=4; crow<20; crow++) {
      if (tileAD[0][crow]!=0&&tileAD[1][crow]!=0&&tileAD[2][crow]!=0&&tileAD[3][crow]!=0&&tileAD[4][crow]!=0&&tileAD[5][crow]!=0&&tileAD[6][crow]!=0&&tileAD[7][crow]!=0&&tileAD[8][crow]!=0&&tileAD[9][crow]!=0) {
        rowC[a]=crow;
        a++;
        rowclear=a;
      }
    }
    if (rowC[0]!=0) {
      shiftContent(rowC[0]);
      rowC[0]=0;
      score++;
    }/* else if (rowC[1]!=0) {
      shiftContent(rowC[1]);
      rowC[1]=0;
      score++;
    } else if (rowC[2]!=0) {
      shiftContent(rowC[2]);
      rowC[2]=0;
      score++;
    } else if (rowC[3]!=0) {
      shiftContent(rowC[3]);
      rowC[3]=0;
      score++;
    }*/
  }

  public void shiftContent(int urow) {
    int ucol=0;
    int b=0;

    for (b=urow-1; b>=0; b--) {
      for (ucol=0; ucol<10; ucol++) {
        tileAD[ucol][b+1]=tileAD[ucol][b];
      }
    }
  }

  public void tileEnd() {
    getloc();
    areaAffected();

    if (tileAD[afcol1][afrow1]!=0||tileAD[afcol2][afrow2]!=0||tileAD[afcol3][afrow3]!=0||tileAD[afcol4][afrow4]!=0) {
      transfer=1;
      tscroll=1;
    } else {
      if (afrow1==19||afrow2==19||afrow3==19||afrow4==19) {
        transfer=0;
        tscroll=1;
      }
    }
  }

  public void nextTile() {
    float randd;
    randd=random(0, 8);
    nxTILE=PApplet.parseInt(randd);
    if (nxTILE==0) {
      nxTILE=7;
    } else if (nxTILE==8) {
      nxTILE=1;
    }
  }

  public void areaAffected() {
    switch(tileN) {
    case 1:
      afcol1=thiscol;
      afrow1=thisrow;

      afcol2=thiscol+1;
      afrow2=thisrow;

      afcol3=thiscol;
      afrow3=thisrow+1;

      afcol4=thiscol+1;
      afrow4=thisrow+1;
      break;
    case 2:
      if (tileDIR==1||tileDIR==3) {
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol;
        afrow3=thisrow+2;

        afcol4=thiscol;
        afrow4=thisrow+3;
      } else if (tileDIR==2||tileDIR==4) {
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow;

        afcol3=thiscol+2;
        afrow3=thisrow;

        afcol4=thiscol+3;
        afrow4=thisrow;
      }
      break;
    case 3:
      if (tileDIR==1||tileDIR==3) {
        afcol1=thiscol+1;
        afrow1=thisrow;

        afcol2=thiscol+2;
        afrow2=thisrow;

        afcol3=thiscol;
        afrow3=thisrow+1;

        afcol4=thiscol+1;
        afrow4=thisrow+1;
      } else if (tileDIR==2||tileDIR==4) {
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol+1;
        afrow3=thisrow+1;

        afcol4=thiscol+1;
        afrow4=thisrow+2;
      }
      break;
    case 4:
      if (tileDIR==1||tileDIR==3) {
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow;

        afcol3=thiscol+1;
        afrow3=thisrow+1;

        afcol4=thiscol+2;
        afrow4=thisrow+1;
      } else if (tileDIR==2||tileDIR==4) {
        afcol1=thiscol+1;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol+1;
        afrow3=thisrow+1;

        afcol4=thiscol;
        afrow4=thisrow+2;
      }
      break;
    case 5:
      switch(tileDIR) {
      case 1:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol;
        afrow3=thisrow+2;

        afcol4=thiscol+1;
        afrow4=thisrow+2;
        break;    
      case 2:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow;

        afcol3=thiscol+2;
        afrow3=thisrow;

        afcol4=thiscol;
        afrow4=thisrow+1;
        break;      
      case 3:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow;

        afcol3=thiscol+1;
        afrow3=thisrow+1;

        afcol4=thiscol+1;
        afrow4=thisrow+2;
        break;
      case 4:
        afcol1=thiscol;
        afrow1=thisrow+1;

        afcol2=thiscol+1;
        afrow2=thisrow+1;

        afcol3=thiscol+2;
        afrow3=thisrow+1;

        afcol4=thiscol+2;
        afrow4=thisrow;
        break;
      }
      break;
    case 6:
      switch (tileDIR) {
      case 1:
        afcol1=thiscol+1;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow+1;

        afcol3=thiscol+1;
        afrow3=thisrow+2;

        afcol4=thiscol;
        afrow4=thisrow+2;
        break;
      case 2:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol+1;
        afrow3=thisrow+1;

        afcol4=thiscol+2;
        afrow4=thisrow+1;
        break;
      case 3:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol;
        afrow3=thisrow+2;

        afcol4=thiscol+1;
        afrow4=thisrow;
        break;
      case 4:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow;

        afcol3=thiscol+2;
        afrow3=thisrow;

        afcol4=thiscol+2;
        afrow4=thisrow+1;
        break;
      }

      break;
    case 7:
      switch (tileDIR) {
      case 1:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow;

        afcol3=thiscol+2;
        afrow3=thisrow;

        afcol4=thiscol+1;
        afrow4=thisrow+1;
        break;
      case 2:
        afcol1=thiscol+1;
        afrow1=thisrow;

        afcol2=thiscol+1;
        afrow2=thisrow+1;

        afcol3=thiscol+1;
        afrow3=thisrow+2;

        afcol4=thiscol;
        afrow4=thisrow+1;
        break;
      case 3:
        afcol1=thiscol;
        afrow1=thisrow+1;

        afcol2=thiscol+1;
        afrow2=thisrow+1;

        afcol3=thiscol+2;
        afrow3=thisrow+1;

        afcol4=thiscol+1;
        afrow4=thisrow;
        break;
      case 4:
        afcol1=thiscol;
        afrow1=thisrow;

        afcol2=thiscol;
        afrow2=thisrow+1;

        afcol3=thiscol;
        afrow3=thisrow+2;

        afcol4=thiscol+1;
        afrow4=thisrow+1;
        break;
      }

      break;
    }
  }
  public void getloc() {
    thiscol=(tileX-100)/20;
    thisrow=(tileY-220)/20;
  }


  public void tileContent() {
    int trow=0;
    int tcol=0;
    int tX=100;
    int tY=220;
    for (trow=0; trow<20; trow++) {
      tX=100;
      tcol=0;
      for (tcol=0; tcol<10; tcol++) {
        switch(tileAD[tcol][trow]) {
        case 0:
          break;
        case 1:
          b.drawBox(tX, tY, 255, 255, 0);
          break;
        case 2:
          b.drawBox(tX, tY, 0, 185, 255);
          break;
        case 3:
          b.drawBox(tX, tY, 250, 0, 0);
          break;
        case 4:
          b.drawBox(tX, tY, 0, 255, 0);
          break;
        case 5:
          b.drawBox(tX, tY, 255, 160, 0);
          break;
        case 6:
          b.drawBox(tX, tY, 255, 75, 165);
          break;
        case 7:
          b.drawBox(tX, tY, 135, 0, 255);
          break;
        }
        tX+=20;
      }
      tY+=20;
    }
  }

  public void goDown() {
    tileY+=20;
  }

  public void drawTile(int tX, int tY, int tN, int tD) {   //tX= tile Coord X || tY= tile Coord Y || tN= tile TYPE || tD=tile DIRECTION
    switch(tN) {
    case 1:
      drawTile1(tX, tY, tD);
      break;
    case 2:
      drawTile2(tX, tY, tD);
      break;
    case 3:
      drawTile3(tX, tY, tD);
      break;
    case 4:
      drawTile4(tX, tY, tD);
      break;
    case 5:
      drawTile5(tX, tY, tD);
      break;
    case 6:
      drawTile6(tX, tY, tD);
      break;
    case 7:
      drawTile7(tX, tY, tD);
      break;
    }
  }

  public void drawTile1(int X, int Y, int tD) {
    int d=tD;
    b.drawBox(X, Y, 255, 255, 0);
    b.drawBox(X+20, Y, 255, 255, 0);
    b.drawBox(X, Y+20, 255, 255, 0);
    b.drawBox(X+20, Y+20, 255, 255, 0);
  }

  public void drawTile2(int X, int Y, int D) {
    if (D==1||D==3) {
      b.drawBox(X, Y, 0, 185, 255);
      b.drawBox(X, Y+20, 0, 185, 255);
      b.drawBox(X, Y+40, 0, 185, 255);
      b.drawBox(X, Y+60, 0, 185, 255);
    } else if (D==2||D==4) {
      b.drawBox(X, Y, 0, 185, 255);
      b.drawBox(X+20, Y, 0, 185, 255);
      b.drawBox(X+40, Y, 0, 185, 255);
      b.drawBox(X+60, Y, 0, 185, 255);
    }
  }

  public void drawTile3(int X, int Y, int D) {
    if (D==1||D==3) {
      b.drawBox(X+20, Y, 250, 0, 0);
      b.drawBox(X+40, Y, 250, 0, 0);
      b.drawBox(X, Y+20, 250, 0, 0);
      b.drawBox(X+20, Y+20, 250, 0, 0);
    } else if (D==2||D==4) {
      b.drawBox(X, Y, 250, 0, 0);
      b.drawBox(X, Y+20, 250, 0, 0);
      b.drawBox(X+20, Y+20, 250, 0, 0);
      b.drawBox(X+20, Y+40, 250, 0, 0);
    }
  }

  public void drawTile4(int X, int Y, int D) {
    if (D==1||D==3) {
      b.drawBox(X, Y, 0, 255, 0);
      b.drawBox(X+20, Y, 0, 255, 0);
      b.drawBox(X+20, Y+20, 0, 255, 0);
      b.drawBox(X+40, Y+20, 0, 255, 0);
    } else if (D==2||D==4) {
      b.drawBox(X+20, Y, 0, 255, 0);
      b.drawBox(X, Y+20, 0, 255, 0);
      b.drawBox(X+20, Y+20, 0, 255, 0);
      b.drawBox(X, Y+40, 0, 255, 0);
    }
  }

  public void drawTile5(int X, int Y, int D) {
    switch(D) {
    case 1:
      b.drawBox(X, Y, 255, 160, 0);
      b.drawBox(X, Y+20, 255, 160, 0);
      b.drawBox(X, Y+40, 255, 160, 0);
      b.drawBox(X+20, Y+40, 255, 160, 0);
      break;
    case 2:
      b.drawBox(X, Y, 255, 160, 0);
      b.drawBox(X+20, Y, 255, 160, 0);
      b.drawBox(X+40, Y, 255, 160, 0);
      b.drawBox(X, Y+20, 255, 160, 0);
      break;
    case 3:
      b.drawBox(X, Y, 255, 160, 0);
      b.drawBox(X+20, Y, 255, 160, 0);
      b.drawBox(X+20, Y+20, 255, 160, 0);
      b.drawBox(X+20, Y+40, 255, 160, 0);
      break;
    case 4:
      b.drawBox(X+40, Y, 255, 160, 0);
      b.drawBox(X, Y+20, 255, 160, 0);
      b.drawBox(X+20, Y+20, 255, 160, 0);
      b.drawBox(X+40, Y+20, 255, 160, 0);
      break;
    }
  }

  public void drawTile6(int X, int Y, int D) {
    switch(D) {
    case 1:
      b.drawBox(X+20, Y, 255, 75, 165);
      b.drawBox(X+20, Y+20, 255, 75, 165);
      b.drawBox(X, Y+40, 255, 75, 165);
      b.drawBox(X+20, Y+40, 255, 75, 165);
      break;
    case 2:
      b.drawBox(X, Y, 255, 75, 165);
      b.drawBox(X, Y+20, 255, 75, 165);
      b.drawBox(X+20, Y+20, 255, 75, 165);
      b.drawBox(X+40, Y+20, 255, 75, 175);
      break;
    case 3:
      b.drawBox(X, Y, 255, 75, 165);
      b.drawBox(X+20, Y, 255, 75, 165);
      b.drawBox(X, Y+20, 255, 75, 165);
      b.drawBox(X, Y+40, 255, 75, 165);
      break;
    case 4:
      b.drawBox(X, Y, 255, 75, 165);
      b.drawBox(X+20, Y, 255, 75, 165);
      b.drawBox(X+40, Y, 255, 75, 165);
      b.drawBox(X+40, Y+20, 255, 75, 165);
      break;
    }
  }

  public void drawTile7(int X, int Y, int D) {
    switch(D) {
    case 1:
      b.drawBox(X, Y, 135, 0, 255);
      b.drawBox(X+20, Y, 135, 0, 255);
      b.drawBox(X+40, Y, 135, 0, 255);
      b.drawBox(X+20, Y+20, 135, 0, 255);
      break;
    case 2:
      b.drawBox(X+20, Y, 135, 0, 255);
      b.drawBox(X+20, Y+20, 135, 0, 255);
      b.drawBox(X+20, Y+40, 135, 0, 255);
      b.drawBox(X, Y+20, 135, 0, 255);
      break;
    case 3:
      b.drawBox(X, Y+20, 135, 0, 255);
      b.drawBox(X+20, Y+20, 135, 0, 255);
      b.drawBox(X+40, Y+20, 135, 0, 255);
      b.drawBox(X+20, Y, 135, 0, 255);
      break;
    case 4:
      b.drawBox(X, Y, 135, 0, 255);
      b.drawBox(X, Y+20, 135, 0, 255);
      b.drawBox(X, Y+40, 135, 0, 255);
      b.drawBox(X+20, Y+20, 135, 0, 255);
      break;
    }
  }


  public void checkloc() {
    switch(tileN) {
    case 1:
      if (tileX>260) {
        tileX=260;
      }
      break;
    case 2:
      if (tileDIR==1||tileDIR==3) {
        if (tileX>280) {
          tileX=280;
        }
      } else if (tileDIR==2||tileDIR==4) {
        if (tileX>220) {
          tileX=220;
        }
      }
      break;
    case 3:
      if (tileDIR==1||tileDIR==3) {
        if (tileX>240) {
          tileX=240;
        }
      } else if (tileDIR==2||tileDIR==4) {
        if (tileX>260) {
          tileX=260;
        }
      }
      break;
    case 4:
      if (tileDIR==1||tileDIR==3) {
        if (tileX>240) {
          tileX=240;
        }
      } else if (tileDIR==2||tileDIR==4) {
        if (tileX>260) {
          tileX=260;
        }
      }
      break;
    case 5:
      if (tileDIR==1||tileDIR==3) {
        if (tileX>260) {
          tileX=260;
        }
      } else if (tileDIR==2||tileDIR==4) {
        if (tileX>240) {
          tileX=240;
        }
      }
      break;
    case 6:
      if (tileDIR==1||tileDIR==3) {
        if (tileX>260) {
          tileX=260;
        }
      } else if (tileDIR==2||tileDIR==4) {
        if (tileX>240) {
          tileX=240;
        }
      }
      break;
    case 7:
      if (tileDIR==1||tileDIR==3) {
        if (tileX>240) {
          tileX=240;
        }
      } else if (tileDIR==2||tileDIR==4) {
        if (tileX>260) {
          tileX=260;
        }
      }
      break;
    }
  }
}
  public void settings() {  size(480, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Tetris" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
