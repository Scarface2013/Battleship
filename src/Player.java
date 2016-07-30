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
  
  public String fireAt(String s){
    return board.fireAt(s);
  }

  public void setResponse(){
  
  } 
  
  public boolean hasWon(){
    for(int[] b : board.getBoard()){
      for(int a : b){
        if(a == 1) return false;
      }
    }
    return true;
  }
}
