package tech.tfletch.battleship;

public class Game{
  private Player player1;
  private Player player2;
  private GUI gui;

  public Game(){
    
  } 

  private void nextTurn(){
  
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
   
    player1.setBoard(boardBuilder.buildBoard(1));
    player2.setBoard(boardBuilder.buildBoard(2));
    
  }
  
  private void listAI(){
    gui.draw(player1.getAI().toString());
    gui.draw(player2.getAI().toString());
  }
  
  public static void main(String[] args){
    Game game = new Game();
    game.initialize();
    game.listAI();
  }
  
}
