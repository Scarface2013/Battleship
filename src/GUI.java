package tech.tfletch.battleship;

import java.util.*;

public class GUI{
  private Scanner s = new Scanner(System.in);
  
  public GUI(){
    
  }
  
  public String promptUser(String question){
    System.out.println(question+": ");
    return s.nextLine();    
  }

  public void draw(String[] sa){
    //Clear screen
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    //Prints array
    for(String s : sa){
      System.out.println(s);
    }
  }
}
