package GUI;

import tech.tfletch.battleship.GUI;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Basic extends GUI{
  JFrame frame;
  JPanel inPanel;
  JTextArea output;
  JTextField input;
  JScrollPane scroll;
  String userResponse;
  
  public Basic(){
    frame = new JFrame();
    frame.setSize(660,416);
    
    output = new JTextArea();
    //output.setEditable(false);
    output.setFont(new Font("monospaced",Font.PLAIN, 11));
    output.setLineWrap(true);
    output.append("Welcome to Battleship!\n");
    
    input = new JTextField();
    input.setPreferredSize(new Dimension(660,16));
    input.addActionListener(e -> {
      userResponse = input.getText();
      output.append("> "+userResponse+"\n");
      input.setText("");
    }); //userResponse is set on enter press
    
    scroll = new JScrollPane(output);
    scroll.setPreferredSize(new Dimension(660,400));
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

  
    inPanel = new JPanel();
    inPanel.setPreferredSize(new Dimension(660,16));
    inPanel.add(input);
    
    frame.getContentPane().add(scroll, BorderLayout.NORTH);
    frame.getContentPane().add(inPanel);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
  }
  
  public boolean test(){
    return true; // lol probably
  }
  
  public void draw(Object o){
    String s = o.toString();
    output.append(s + "\n");
  }
  
  public String promptUser(String s){
    draw(s);
    while(userResponse == null){
      try{
        Thread.sleep(100);
      }catch(InterruptedException e){
        
      }
    }
    String toRet = userResponse;
    userResponse = null;
    return toRet;
  }
  
  public void cls(){
    output.setText("");
  }
}
