package tech.tfletch.battleship;

import java.util.Map;
import java.util.HashMap;

import GUI.*;

public class Game{
  
  private Map<String,String> config = new HashMap<>();
  
  // Set during initialize()
  private Player player1;
  private Player player2;
  private GUI gui;

  // Set after game ends
  private Player winner;
  private Player loser;

  public Game(){
    
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
  
  private void configure(Menu menu, String key){
    gui.draw(menu.drawMenu());
    while(menu.isValid(
        config.put(key, gui.promptUser("Select a User Interface"))
    ));
  }

  private void initialize(){
    player1 = new Player();
    player2 = new Player();

    //The two default-ish GUI choices
    try{
      gui = new Basic();
    }catch(Exception e){
      gui = new CommandLine();
    }

    // Allow user to change GUI from default
    Menu<GUI> GUIMenu = new Menu<>("GUI/",GUI.class);
    String key = "GUI";
    if(!(config.containsKey(key) && GUIMenu.isValid(config.get(key)))){ 
      this.configure(GUIMenu, key);
    }
    gui = GUIMenu.makeSelection(config.get(key));

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
  
  private void listAI(){
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
    Game game = new Game();
    game.initialize();
    game.listAI();
    while(game.isRunning()){
      game.nextTurn();
    }
    game.listAI();
  }
}
