package tech.tfletch.battleship;

public class Board{
  private int[][] gameBoard;
  private final String xMap = "abcdefghij";
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

  public String fireAt(String cord){
    // get # equiv of x
    int x = xMap.indexOf(cord.toLowerCase().charAt(0));

    // Subtract 48 because casting a char to an int converts it to its
    // respective character code (and 0 is 48, 1 is 49, etc.)
    int y = cord.charAt(1) - 48;

    int result = gameBoard[y][x];

    if(result == 1){
      gameBoard[y][x] = 2;
      return "hit";
    }

    return "miss";
  }
  
  public int[][] getBoard(){
    return gameBoard;
  }
  
  public String toString(){
    String toRet = "";
    toRet+="  a b c d e f g h i j \n";
    toRet+=" +-+-+-+-+-+-+-+-+-+-+\n";
    int count = 0;
    for(int[] a : gameBoard){
      toRet+=(count++ + "|");
      for(int i : a){
        toRet+=sMap[i]+"|";
      }
      toRet+="\n";
    }
    toRet+=" +-+-+-+-+-+-+-+-+-+-+";
  return toRet;
  }
}
