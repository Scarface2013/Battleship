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
    for(String s : sa){
      draw(s);
    }
  }
  public void draw(String s){
    System.out.println(s);
  }
}
