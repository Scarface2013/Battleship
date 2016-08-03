package tech.tfletch.battleship.tests;

import GUI.*;
import tech.tfletch.battleship.*;
import java.util.Scanner;

public class MenuTester{
  public static void main(String[] args){
    try{
      Scanner in = new Scanner(System.in);
      Menu<AI> menu = new Menu<AI>("AI/", AI.class);
      GUI gui = new Basic();
      gui.draw(menu.drawMenu());
      String response = gui.promptUser("Please make a selction: ");
      AI o = menu.makeSelection(Integer.parseInt(response));

      gui.draw(o.getName());
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
