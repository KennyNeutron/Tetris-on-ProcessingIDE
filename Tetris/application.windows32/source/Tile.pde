public class Tile {
  void drawStored() {
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

  void drawNext() {
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
  void checkClear() {
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

  void shiftContent(int urow) {
    int ucol=0;
    int b=0;

    for (b=urow-1; b>=0; b--) {
      for (ucol=0; ucol<10; ucol++) {
        tileAD[ucol][b+1]=tileAD[ucol][b];
      }
    }
  }

  void tileEnd() {
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

  void nextTile() {
    float randd;
    randd=random(0, 8);
    nxTILE=int(randd);
    if (nxTILE==0) {
      nxTILE=7;
    } else if (nxTILE==8) {
      nxTILE=1;
    }
  }

  void areaAffected() {
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
  void getloc() {
    thiscol=(tileX-100)/20;
    thisrow=(tileY-220)/20;
  }


  void tileContent() {
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

  void goDown() {
    tileY+=20;
  }

  void drawTile(int tX, int tY, int tN, int tD) {   //tX= tile Coord X || tY= tile Coord Y || tN= tile TYPE || tD=tile DIRECTION
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

  void drawTile1(int X, int Y, int tD) {
    int d=tD;
    b.drawBox(X, Y, 255, 255, 0);
    b.drawBox(X+20, Y, 255, 255, 0);
    b.drawBox(X, Y+20, 255, 255, 0);
    b.drawBox(X+20, Y+20, 255, 255, 0);
  }

  void drawTile2(int X, int Y, int D) {
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

  void drawTile3(int X, int Y, int D) {
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

  void drawTile4(int X, int Y, int D) {
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

  void drawTile5(int X, int Y, int D) {
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

  void drawTile6(int X, int Y, int D) {
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

  void drawTile7(int X, int Y, int D) {
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


  void checkloc() {
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
