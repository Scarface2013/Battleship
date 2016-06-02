public class Menu<T>{
  private String pack;
  private List<Class> choices;

  public Menu(String path){
    //Get package name -- TODO: add variability in input
    pack = path.substring(1).split('/').join('.');
    
    //Get classes in said package
    choices = new ArrayList<Class>();
    for(File file : new File(path).listFiles()){
      choices.add( Class.forName( pack + file.getName() ));
    }
  }

  public String[] drawMenu(){
    String[] toRet = "";

    return toRet;
  }
  
  public T makeSelection(int i){
    return (T)(new choices[i].newInstance());
  }
}
