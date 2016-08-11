package tech.tfletch.battleship;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import GUI.*;

public class Game{
  
  // config is a hash of "config keys" => "int" s that are fed into Menu.
  // It was chosen to seed Game because making a copy of an object sucks,
  // and it's much simpler to build it anew, but manually typing your options
  // in every time is not okay (especially since there are AI runners that
  // create 1000+ games per run).
  private Map<String,String> config = new HashMap<>();
  
  // Set during initialize()
  private Player player1;
  private Player player2;
  private GUI gui;

  // Set after game ends
  private Player winner;
  private Player loser;

  public Game(){
    this.initialize();
  }
  
  public Game(Map config){
    this.config = config;
    this.initialize();
  }
  
  public Game(GUI gui){
    this.gui = gui;
    this.initialize();
  }
  
  public Game(Map config, GUI gui){
    this.config = config;
    this.gui = gui;
    this.initialize();
  }

  public void nextTurn(){
    // STEP 1:
    // Asks player what they want their next target to be
    String p1Cord = player1.nextTurn();
    String p2Cord = player2.nextTurn();
    
    // STEP 2:
    // Check validity of returned co-ordinates
    String regex = "([a-jA-j])([0-9])";
    if(p1Cord == null || p2Cord == null 
    || !p1Cord.matches(regex) || !p2Cord.matches(regex)){
      gui.draw("One of the AI is returning invalid coordinates. Exiting");
      System.exit(70); //EX_SOFTWARE
    }
    // STEP 3:
    // Fires at enemy board, based on target. Hit|Miss is returned, so that
    // the AI can (theoretically) refine their search for next call to nextTurn
    String p1Resp = player2.fireAt(p1Cord);
    String p2Resp = player1.fireAt(p2Cord);
    
    // STEP 4:
    // Passes in the cords they picked in step 1, along with the other player's
    // response to said cords
    player1.setResponse(p1Cord, p2Resp);
    player2.setResponse(p2Cord, p1Resp);
    
  }
  
  public Map<String,String> getConfig(){
    return config;
  }
  
  public GUI getGui(){
    return gui;
  }
  
  private void configure(Menu menu, String key){
    gui.draw(menu.drawMenu());
    config.put(key, gui.promptUser("Select a User Interface"));
    while(true){
      if(menu.isValid(config.get(key))){
        break;
      }else{
        config.put(key, gui.promptUser("Select a User Interface"));
      }
    }
  }

  private void initialize(){
    player1 = new Player();
    player2 = new Player();
    
    if(gui == null){
      //The two default-ish GUI choices
      try{
        gui = new Basic();
      }catch(Exception e){
        gui = new CommandLine();
      }
  
      // Allow user to change GUI from default
      Menu<GUI> GUIMenu = new Menu<>("GUI/", GUI.class);
      String key = "GUI";
      if(!(config.containsKey(key) && GUIMenu.isValid(config.get(key)))){
        this.configure(GUIMenu, key);
      }
      //If we already have the correct GUI, don't remake it
      if(!gui.getClass().getSimpleName().equals(
        GUIMenu.getSelectionName(Integer.parseInt(config.get(key))))
      ){
        
        gui.DESTROY();
        gui = GUIMenu.makeSelection(config.get(key));
      }
    }
    
    // Select an AI for each player
    Menu<AI> AIMenu = new Menu<>("AI/", AI.class);
    if(!(config.containsKey("P1AI") && AIMenu.isValid(config.get("P1AI")))){
      this.configure(AIMenu, "P1AI");
    }
    if(!(config.containsKey("P2AI") && AIMenu.isValid(config.get("P2AI")))){ 
      this.configure(AIMenu, "P2AI");
    }
    player1.setAI(AIMenu.makeSelection(config.get("P1AI")));
    player2.setAI(AIMenu.makeSelection(config.get("P2AI")));
    
    // Select BoardBuilder style
    Menu<BoardBuilder> BBMenu = 
      new Menu<>("BoardBuilder/", BoardBuilder.class);
    
    if(!(config.containsKey("BoBu") && BBMenu.isValid(config.get("BoBu")))){ 
      this.configure(BBMenu, "BoBu");
    }
    BoardBuilder boardBuilder = 
      BBMenu.makeSelection(config.get("BoBu"));

    player1.setBoard(boardBuilder.buildBoard());
    player2.setBoard(boardBuilder.buildBoard());
  }
  
  public void listAI(){
    gui.cls();
    
    // Prints AI names in correct positions

    gui.draw(
      String.format(
        "%1$-"+(33-player1.getAI().getName().length())+"s",
        player1.getAI().getName()
      ),
      player2.getAI().getName()
    );
    gui.draw(player1.getBoard(), player2.getBoard());
    
    if(winner != null){
      gui.draw("\nWINNER: " + winner.getAI().getName());
      gui.draw("LOSER: " + loser.getAI().getName());
    }
  }
  public boolean isRunning(){
    if(player1.hasLost()){
      loser = player1;
      winner = player2;
      return false;
    }
    else if(player2.hasLost()){
      loser = player2;
      winner = player1;
      return false;
    }
    return true;
  }
  
  public static void main(String[] args){
    //Create a master copy of game (we need the config and GUI)
    Game master = new Game();
    Map<String, String> cfg = master.getConfig();
    GUI gui = master.getGui();
    
    int cnt = Integer.parseInt(gui.promptUser("How many times would you like to run?"));
  
    ArrayList<Game> history = new ArrayList<>();
    while(cnt-->0){
      Game game = new Game(cfg, gui);
      game.listAI();
      while(game.isRunning()){
        game.nextTurn();
      }
      game.listAI();
      history.add(game);
    }
    
    while(true){
      String response = gui.promptUser("Which game would you like to see? (0-"
        + (history.size()-1) + ")");
      if(response.equals("quit") || response.equals("exit")){
        gui.DESTROY();
	System.exit(0);
      }
      try{
        history.get(Integer.parseInt(response)).listAI();
      }catch(IndexOutOfBoundsException e){
        gui.draw("No game found with specified ID");
      }catch(NumberFormatException e){
        gui.draw("Not a number");
      }
    }
  }
}
