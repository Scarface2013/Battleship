package tech.tfletch.battleship;

import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class Menu<T>{
  private String packageName;
  private List<Class> choices;
  private Class<T> parent;

  public Menu(String path, Class<T> type) throws ClassNotFoundException{
    parent = type;
    //Get package name -- TODO: add variability in input
    packageName = String.join(".",path.split("/"));
    
    //Get classes in said package
    choices = new ArrayList<Class>();
    for(File file : new File(path).listFiles()){
      if(!file.getName().endsWith(".class"))
        continue;
      
      String fileName = file.getName().substring(0,file.getName().length()-6); //get extentionless name
      String className = packageName + "." + fileName;
      Class c = Class.forName(className);
      if(!parent.getSimpleName().equals( c.getSuperclass().getSimpleName() )) 
        continue;
      
      choices.add(c);
    }
  }

  public String[] drawMenu(){
    List<String> toRet = new ArrayList<>();
    toRet.add(parent.getSimpleName()+" class choices:");
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
