package GUI;

import java.util.*;
import tech.tfletch.battleship.*;

public class CommandLine extends GUI{
  private Scanner s = new Scanner(System.in);
  
  public CommandLine(){
    
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
