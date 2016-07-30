package tech.tfletch.battleship;

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
    
  }
  
  private void initialize(){
    player1 = new Player();
    player2 = new Player();
    gui = new GUI();
    
    // Allow user to change GUI from default command line.
    Menu<GUI> GUIMenu = new Menu<>("GUI/",GUI.class);
    gui.draw(GUIMenu.drawMenu());
    GUI tGui = GUIMenu.makeSelection(gui.promptUser("Select a User Interface"));
    if(tGui != null){
      gui = tGui;
    }

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
    BoardBuilder boardBuilder = boardBuilderMenu.makeSelection(gui.promptUser("Select a Board Generation style"));
    player1.setBoard(boardBuilder.buildBoard());
    player2.setBoard(boardBuilder.buildBoard());
  }
  
  private void listAI(){
    gui.draw(player1.getAI().getName());
    gui.draw(player1.getBoard());
    
    gui.draw("\n\n");
    
    gui.draw(player2.getAI().getName());
    gui.draw(player2.getBoard());
    
  }
  public boolean isRunning(){
    if(player1.hasWon()){
      winner = player1;
      loser = player2;
      return false;
    }
    else if(player2.hasWon()){
      winner = player2;
      loser = player1;
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
