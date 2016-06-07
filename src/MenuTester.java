package tech.tfletch.battleship.tests;

import tech.tfletch.battleship.*;

public class MenuTester{
  public static void main(String[] args){
    try{
      Menu<AI> menu = new Menu<AI>("AI/");
      AI o = menu.makeSelection(0);
      System.out.println(o.toString());
      System.out.println(o.Test());
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
