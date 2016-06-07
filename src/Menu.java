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
    pack = String.join(".",path.split("/"))+".";
    
    //Get classes in said package
    choices = new ArrayList<Class>();
    for(File file : new File(path).listFiles()){
      choices.add( Class.forName( 
        pack + file.getName().substring(0,file.getName().length()-6) // remove .class extention
      ));
    }
  }

  public String[] drawMenu(){
    List<String> toRet = new ArrayList<>();
    toRet.add(choices.get(0).getSuperclass().getSimpleName()+" class choices:");
    int count = 0;
    for(Class choice : choices){
      toRet.add("  " + (count++) + " - " + choice.getSimpleName());
    }
    String[] a = {};
    return toRet.toArray(a);
  }
  
  public T makeSelection(int i) throws InstantiationException, IllegalAccessException{
    return (T)(choices.get(i).newInstance());
  }
}
