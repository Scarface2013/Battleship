package GUI;

import java.util.*;
import tech.tfletch.battleship.*;

public class CommandLine extends GUI{
  private Scanner s = new Scanner(System.in);
  
  public String promptUser(String question){
    System.out.println(question+": ");
    if(!s.hasNextLine()){
      System.err.println("Something is wrong with STDIN. I'm out");
      System.exit(66); //EX_NOINPUT as defined by sysexits.h
    }
    return s.nextLine();    
  }
  
  public void draw(Object s){
    System.out.println(s);
  }
  
  public void cls(){
    // Default console size is 80x24, so 24 newlines will clear it.
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }
}
