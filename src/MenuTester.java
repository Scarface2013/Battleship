package tech.tfletch.battleship.tests;

import tech.tfletch.battleship.*;
import java.util.Scanner;

public class MenuTester{
  public static void main(String[] args){
    try{
      Scanner in = new Scanner(System.in);
      Menu<AI> menu = new Menu<AI>("AI/");

      for(String s : menu.drawMenu()){
        System.out.println(s);
      }
      System.out.print("Please make a selction: ");
      AI o = menu.makeSelection(in.nextInt());

      System.out.println(o.toString());
      System.out.println(o.Test());
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
