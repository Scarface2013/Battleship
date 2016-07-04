package tech.tfletch.battleship;

public class Board{
  private int[][] gameBoard;
  private final char[] xMap = {'a','b','c','d','e','f','g','h','i','j'};
  private final char[] sMap = {' ','O','X'}; // see toString for proper usage
  // 0 - water
  // 1 - ship
  // 2 - dead ship
  
  public Board(int[][] gameBoard) throws Exception{
    //check board validity
    if(gameBoard.length != 10)
      throw new Exception("Board height is "+ gameBoard.length);
    for(int[] row : gameBoard){
      if(row.length != 10) 
        throw new Exception("Board width is " + row.length);
      for(int entry : row){
        if(entry != 0 && entry != 1) 
          throw new Exception("Board entry is " + entry);
      } 
    }
    this.gameBoard = gameBoard;
  }

  public String fireAt(String x, int y){
    // get # equiv of x
    return "";
  }
  
  public String toString(){
    String toRet = "";
    toRet+="+-+-+-+-+-+-+-+-+-+-+\n";
    for(int[] a : gameBoard){
      toRet+="|";
      for(int i : a){
        toRet+=sMap[i]+"|";
      }
      toRet+="\n";
    }
    toRet+="+-+-+-+-+-+-+-+-+-+-+";
  return toRet;
  }
}
