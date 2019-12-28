package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChessDriver {
	   public static void main(String[]args) 
	   {	   
		   PlayerGameBoard game = new PlayerGameBoard();
		  
	      class BeginScene extends JPanel{
	   	   public void paintComponent(Graphics g)  {
	   	      g.setColor(new Color(204, 204, 204));
	   	      for(int x=0; x<4; x++) {
	   	         for(int y=0; y<4; y++) {
	   	            g.fillRect(40+x*2*50, 40+y*2*50, 50, 50);
	   	            g.fillRect(90+x*100, 90+y*100, 50, 50);
	   	         }
	   	      }
	   	      g.setColor(new Color(209, 254, 248));
	   	   
	   	      for(int x=0; x<4; x++) {
	   	         for(int y=0; y<4; y++) {
	   	            g.fillRect(40+x*100, 90+y*100, 50, 50);
	   	            g.fillRect(90+x*100, 40+y*100, 50, 50);
	   	            
	   	         }
	   	      }
	   	      g.setFont(new Font("Helvetica", Font.BOLD, 20));
	   	      g.setColor(Color.BLACK);
	   	      
	   	      for(int x=0; x<8; x++) {
	   	         g.drawString(""+x, 65+50*x, 25);
	   	         g.drawString(""+(7-x), 20, 70+50*x);
	   	      }
	   	      for(int x=7; x>=0; x--) {
	   	    	  	for (int y=0; y<8; y++) {
	   	    	  		g.drawString(game.getBoard()[y][x].getName(), 55+x*50, 420-y*50);
	   	    	  	}
	   	      }
	   	      g.setFont(new Font("Helvetica", Font.BOLD, 14));
	   	      g.drawString("Welcome to your chess game! Above is the board you will play on.", 17, 480);
	   	      g.drawString("Please note the black pawn piece dissapeared, so P is now used.", 17, 500);
	   	   }
	   	}
	      
	      JFrame begin = new JFrame("Chess Game");
	      begin.setSize(480, 540);
	      begin.setLocation(50, 50);
	      begin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      begin.setContentPane(new BeginScene());
	      begin.setVisible(true);
	      
	      game.move(begin); 
	      game.endScene(begin);
	      if(game.whiteWins) {
	    	  	System.out.println("Congrats White, you have won!");
	      }
	      else {
	    	  	System.out.println("Congrats Black, you have won!");
	      }
	    	  
	      Scanner ask = new Scanner(System.in);
	      System.out.println("Type anything to end the program!");
	      String word = ask.next();
	      System.exit(0);
   }
}