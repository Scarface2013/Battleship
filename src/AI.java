package tech.tfletch.battleship;
public abstract class AI{
  // cord ~~ /[a-j][0-9]/
  // resp ~~ /(hit|miss)/
  public abstract String getName();
  public abstract String nextTarget(); //returns cord
  public abstract void setResponse(String cord, String resp); 
}
