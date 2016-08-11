package tech.tfletch.battleship;

import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class Menu<T>{
  private String packageName;
  private List<Class> choices;
  private Class<T> parent;

  public Menu(String path, Class<T> type){
    parent = type;
    //Get package name -- TODO: add variability in input
    packageName = String.join(".",path.split("/"));
    
    //Get classes in said package
    choices = new ArrayList<Class>();
    File classDirectory = new File("build/classes/main/"+path);

    if(classDirectory == null){
      System.err.println("Menu instantiated with unknown directory: " + path);
      System.exit(0);
    }
    for(File file : classDirectory.listFiles()){
      if(!file.getName().endsWith(".class"))
        continue;
      
      String fileName = file.getName().substring(0,file.getName().length()-6); //get extentionless name
      String className = packageName + "." + fileName;
      try{
        Class c = Class.forName(className);
        if(!parent.getSimpleName().equals( c.getSuperclass().getSimpleName() )) 
          continue;
        choices.add(c);
      }catch(ClassNotFoundException e){
        System.err.print("A problem has occured while importing ");
        System.err.println(className + ". See trace below for details");
        e.printStackTrace();
      }
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
  
  public boolean isValid(String s){
    try{
      int i = Integer.parseInt(s);
      return isValid(i);
    }catch(NumberFormatException e){
      System.err.println("User supplied non-number in Menu selection.");
      return false;
    }
 
  }
  public boolean isValid(int i){
    return choices.size() > i;
  }
  
  public String getSelectionName(int i){
    return choices.get(i).getSimpleName();
  }
  
  public T makeSelection(String s){
    try{
      int i = Integer.parseInt(s);
      return makeSelection(i);
    }catch(NumberFormatException e){
      System.err.println("User supplied non-number in Menu selection.");
      return null;
    }
  }
  public T makeSelection(int i){
    try{
      return (T)(choices.get(i).newInstance());
    }catch(Exception e){
      System.err.println("Error instantiating class:");
      e.printStackTrace();
      return null;
    }
  }
}
