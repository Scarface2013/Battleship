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

  public void draw(Object[] sa){
    for(Object s : sa){
      draw(s);
    }
  }
  public void draw(Object s){
    System.out.println(s);
  }
}
