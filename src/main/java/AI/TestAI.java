package AI;

import tech.tfletch.battleship.*;

// This AI will fire at every square, in order.

public class TestAI extends AI{
  
  private boolean isFirst = true;
  private final char[] xMap = {'a','b','c','d','e','f','g','h','i','j'};
  private String[] targets = {};
  private int targetNum = 0;
  
  public String getName(){
    return "TestAI";
  }
  
  public void setResponse(String cord, String resp){
    //This AI is stupid, so it just throws away response info 
  }
  
  public String nextTarget(){
    if(isFirst){
      targets = constructTargets();
      isFirst = false;
    }
    return targets[targetNum++];
  }
  
  private String[] constructTargets(){
    String[] toRet = new String[100];
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        toRet[(i*10)+j] = "" + xMap[i] + j;
      }
    }
    return toRet;
  }
}
