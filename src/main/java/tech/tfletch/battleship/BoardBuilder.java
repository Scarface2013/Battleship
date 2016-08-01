package tech.tfletch.battleship;

public abstract class BoardBuilder{
  public abstract Board buildBoard(int seed);
  public Board buildBoard(){
    return buildBoard((int)(Math.random()*2147483647));
  }
}
