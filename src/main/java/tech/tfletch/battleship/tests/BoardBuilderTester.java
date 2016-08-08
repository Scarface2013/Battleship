package tech.tfletch.battleship.tests;

import GUI.*;
import BoardBuilder.*;
import tech.tfletch.battleship.*;
import java.text.DecimalFormat;

public class BoardBuilderTester{

  public static String toString(int[][] gameBoard, int cnt){
    DecimalFormat d = new DecimalFormat("00.0");
    String toRet = "";
    toRet+="    a     b     c     d     e     f     g     h     i     j   \n";
    toRet+=" +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n";
    int count = 0;
    for(int[] a : gameBoard){
      toRet+=(count++ + "|");
      for(int i : a){
        toRet+=d.format((i*1.0/cnt));
	toRet+="%";
	toRet+="|";
      }
      toRet+="\n";
    }
    toRet+=" +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+";
    return toRet;
  }

  public static void main(String[] args){
    try{
      GUI gui = new CommandLine();
      BoardBuilder bb = new RandomBoard();
      int c = 10000000;
      int[][] sum = new int[10][10];
      while(c-->0){
        int[][] b = bb.buildBoard().getBoard();
        
	for(int i = 0; i < 10; i++){
	  for(int j = 0; j < 10; j++){
	    sum[i][j] += b[i][j];
	  }
	}
      }
      gui.draw(toString(sum,100000));
      
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
