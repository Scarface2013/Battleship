package tech.tfletch.battleship;

import GUI.*;

public class Game{
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
  
  private void initialize(){
    player1 = new Player();
    player2 = new Player();

    //The two default GUI choices
    try{
      gui = new Basic();
    }catch(Exception e){
      gui = new CommandLine();
    }

    // Allow user to change GUI from default command line.
    Menu<GUI> GUIMenu = new Menu<>("GUI/",GUI.class);
    gui.draw(GUIMenu.drawMenu());
    GUI tGui = null;
    while(tGui == null){
      tGui = GUIMenu.makeSelection(
        gui.promptUser("Select a User Interface")
      );
    }
    gui = tGui;

    // Select an AI for each player
    Menu<AI> AIMenu = new Menu<>("AI/", AI.class);
    gui.draw(AIMenu.drawMenu());
    AI p1AI, p2AI;
    p1AI = p2AI = null;
    while(p1AI == null)
      p1AI = AIMenu.makeSelection(gui.promptUser("Select AI for player 1"));
    while(p2AI == null)
      p2AI = AIMenu.makeSelection(gui.promptUser("Select AI for player 2"));
    player1.setAI(p1AI);
    player2.setAI(p2AI);
    
    Menu<BoardBuilder> boardBuilderMenu = new Menu<>("BoardBuilder/", BoardBuilder.class);
    gui.draw(boardBuilderMenu.drawMenu());
    BoardBuilder boardBuilder = null;
    while(boardBuilder == null){
      boardBuilder = boardBuilderMenu.makeSelection(
        gui.promptUser("Select a Board Generation style")
      );
    }
    player1.setBoard(boardBuilder.buildBoard());
    player2.setBoard(boardBuilder.buildBoard());
  }
  
  private void listAI(){
    gui.cls();
    
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
