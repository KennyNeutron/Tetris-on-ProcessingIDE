public class Boxx {
  void drawBox(int X, int Y, int R, int G, int B) {
    stroke(0);
    strokeWeight(2);
    fill(R, G, B);
    rect(X, Y, 20, 20, 5);
  }
}

void displayScore() {
  fill(0);
  textSize(20);
  textAlign(LEFT, BOTTOM);
  text("SCORE:", 80, 195);
  text("HIGH:",220,195);
  textSize(30);
  text(score,160,200);
  text(highscore,280,200);
  
}
