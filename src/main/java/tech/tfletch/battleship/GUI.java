package tech.tfletch.battleship;

import java.util.*;

// An EXTREMELY basic GUI system for Battleship

public class GUI{
  private Scanner s = new Scanner(System.in);
  
  public GUI(){
    
  }
  
  public String promptUser(String question){
    System.out.println(question+": ");
    return s.nextLine();    
  }
  
  public void draw(Object[] sa){
    for(Object s : sa){
      draw(s);
    }
  }

  public void draw(Object s){
    System.out.println(s);
  }

}
