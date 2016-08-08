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
  
  public String nextTurn(){
    return ai.nextTarget();
  }
  
  public void setResponse(String cord, String resp){
    ai.setResponse(cord,resp);
  }
  
  public String fireAt(String cord){
    return board.fireAt(cord); //returns (hit|miss)
  }

  public boolean hasLost(){
    for(int[] b : board.getBoard()){
      for(int a : b){
        if(a == 1) return false;
      }
    }
    return true;
  }
}
