class Main {

  void Draw() {  
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

  void transfer_content() {
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
