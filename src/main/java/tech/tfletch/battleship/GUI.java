package tech.tfletch.battleship;

public abstract class GUI{
  
  public abstract String promptUser(String question);
  public abstract void draw(Object s);
  public abstract void cls();
  public abstract void DESTROY();
  
  public void draw(Object[] sa){
    for(Object s : sa){
      draw(s);
    }
  }
  
  public void draw(Object[] a, Object[] b){
    int max = a.length > b.length ? a.length : b.length;
    
    for(int i = 0; i < max; i++){
      draw(
        (i < a.length ? (a[i].toString() + "     ") : ""),
        (i < b.length ? b[i].toString() : "")
      );
    }
  }
  
  public void draw(Object a, Object b){
    // if the stringified object contains newlines, we want to split-screen on them
    if(a.toString().contains("\n") || b.toString().contains("\n")){
      draw(a.toString().split("\n"), b.toString().split("\n"));
    }else{
      draw(a.toString() + b.toString());
    }
  }
}
