public class Init {
  void screen() {
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

  void drawArea() {
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


  void tileADs() {
    int arow=0;
    int acol=0;
    for (arow=0; arow<20; arow++) {
      for (acol=0; acol<10; acol++) {
        tileAD[acol][arow]=0;
      }
    }
  }
}
