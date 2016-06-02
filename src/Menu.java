package tech.tfletch.battleship;

import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class Menu<T>{
  private String pack;
  private List<Class> choices;

  public Menu(String path) throws ClassNotFoundException{
    //Get package name -- TODO: add variability in input
    pack = String.join(".",path.substring(1).split("/"));
    
    //Get classes in said package
    choices = new ArrayList<Class>();
    for(File file : new File(path).listFiles()){
      choices.add( Class.forName( pack + file.getName() ));
    }
  }

  public String[] drawMenu(){
    String[] toRet = {};

    return toRet;
  }
  
  public T makeSelection(int i) throws InstantiationException, IllegalAccessException{
    return (T)(choices.get(i).newInstance());
  }
}
