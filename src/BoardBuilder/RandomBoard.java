package BoardBuilder;

import tech.tfletch.battleship.*;
import java.util.Random;


public class RandomBoard extends BoardBuilder{
  int[][] boardArray = new int[10][10];
  int[] sizeMap = {5,4,3,3,2}; //ship sizes
  
  public Board buildBoard(int seed){
    // seed number generator
    Random rand = new Random(seed);
    
    for(int i = 0; i<5;i++){
      boolean valid = false;
      int num = 0;
      int x = 0;
      int y = 0;
      int dir = 0;
      while(!valid){
        num = rand.nextInt(9999);
        
	// Massage numbers into usable format
        x = num%10;
        num/=10;
        y = num%10;
        num/=10;
        dir = num%4; // 0/1 = up/down and 2/3 = left/right
         
        valid = validate(x,y,dir,sizeMap[i]);
      }
      for(int j = 0; j < sizeMap[i]; j++){
        if(dir == 0)
          boardArray[x][y+j] = 1;
        else if(dir == 1)
          boardArray[x][y-j] = 1;
        else if(dir == 2)
          boardArray[x-j][y] = 1;
        else if(dir == 3)
          boardArray[x+j][y] = 1;
      }
    }
    try{
      return new Board(boardArray);
    }catch(Exception e){
      e.printStackTrace();
      return null;

    }
  }

  public boolean validate(int xPos, int yPos, int dir, int size){
    // Check bounds
    if(dir == 0 && (yPos+size) > 9)
      return false;
    else if(dir == 1 && (yPos-size) < 0)
      return false;
    else if(dir == 2 && (xPos-size) < 0)
      return false;
    else if(dir == 3 && (xPos+size) > 9)
      return false;
    
    // Check ship overlap
    for(int j = 0; j < size; j++){
      if(dir == 0 && boardArray[xPos][yPos+j] == 1)
        return false;
      else if(dir == 1 && boardArray[xPos][yPos-j] == 1)
        return false;
      else if(dir == 2 && boardArray[xPos-j][yPos] == 1)
        return false;
      else if(dir == 3 && boardArray[xPos+j][yPos] == 1)
        return false;
    }
    return true;
  }
}
