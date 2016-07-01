package tech.tfletch.battleship;

public class Board{
  private int[][] gameBoard;
  private String xMap = "abcdefghij";
  // 0 - water
  // 1 - ship
  // 2 - dead ship
  
  public Board(int[][] gameBoard) throws Exception{
    //check board validity
    boolean valid = true;
    if(gameBoard.length != 10)
      valid = false;
    for(int[] row : gameBoard){
      if(row.length != 10) 
        valid = false;
      for(int entry : row){
        if(entry != 0 || entry != 1) 
          valid = false;
      } 
    }

    if(valid)
      this.gameBoard = gameBoard;
    else
      throw new Exception();
  }

  public String fireAt(String x, int y){
    // get # equiv of x
    return "";
  }
  
}
