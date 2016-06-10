package tech.tfletch.battleship;

public class Player{
  private AI ai;
  private Board board;
  
  public Player(){
    
  }
  
  public void setBoard(Board board){
    this.board = board;
  }
  public Board getBoard(){
    return board;
  }
  
  public void setAI(AI ai){
    this.ai = ai;
  }
  public AI getAI(){
    return ai;
  }
}
