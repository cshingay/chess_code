package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayerGameBoard {
	public boolean whitesTurn;
	public ChessPiece[][] chessBoard;
    boolean pawnToQueen = false;
	boolean blackWins = true;
	boolean whiteWins = true;
	public ChessPiece blackKing = new ChessPiece ("king", "♚", "black");
	public ChessPiece whiteKing = new ChessPiece ("king", "♔", "white");
	public int column;
	public int row;
	public int newColumn;
	public int newRow;
	//public Computer comp;
	
    public ChessPiece[][] getBoard() {
    		return chessBoard;
    }
    
	public PlayerGameBoard() {
	      chessBoard = new ChessPiece [8][8];
	     // comp = new Computer();
	      
	      ChessPiece rook1White = new ChessPiece("rook", "♖", "white");
	      ChessPiece rook2White = new ChessPiece("rook", "♖", "white");
	      ChessPiece knight1White = new ChessPiece("knight", "♘", "white");
	      ChessPiece bishop1White = new ChessPiece("bishop", "♗", "white");
	      ChessPiece queenWhite = new ChessPiece("queen", "♕", "white");
	      ChessPiece bishop2White = new ChessPiece ("bishop", "♗", "white");
	      ChessPiece knight2White = new ChessPiece ("knight", "♘", "white");
	      
	      ChessPiece rook1Black = new ChessPiece("rook", "♜", "black");
	      ChessPiece rook2Black = new ChessPiece("rook", "♜", "black");
	      ChessPiece knight1Black = new ChessPiece("knight", "♞", "black");
	      ChessPiece bishop1Black = new ChessPiece("bishop", "♝", "black");
	      ChessPiece queenBlack = new ChessPiece("queen", "♛", "black");
	      ChessPiece bishop2Black = new ChessPiece ("bishop", "♝", "black");
	      ChessPiece knight2Black = new ChessPiece ("knight", "♞", "black");
	      
	      chessBoard[0][0] = rook1White;
	      chessBoard[0][1] = knight1White;
	      chessBoard[0][2] = bishop1White;
	      chessBoard[0][3] = queenWhite;
	      chessBoard[0][4] = whiteKing;
	      chessBoard[0][5] = bishop2White;
	      chessBoard[0][6] = knight2White;
	      chessBoard[0][7] = rook2White;
	      
	      chessBoard[7][0] = rook1Black;
	      chessBoard[7][1] = knight1Black;
	      chessBoard[7][2] = bishop1Black;
	      chessBoard[7][3] = queenBlack;
	      chessBoard[7][4] = blackKing;
	      chessBoard[7][5] = bishop2Black;
	      chessBoard[7][6] = knight2Black;
	      chessBoard[7][7] = rook2Black;
	      
	      for (int x=0; x<8; x++)
	      {
	         ChessPiece pawnWhite = new ChessPiece("pawn", "♙", "white");
	         chessBoard[1][x] = pawnWhite;
	         
	         ChessPiece pawnBlack = new ChessPiece("pawn", "P", "black");
	         chessBoard[6][x] = pawnBlack;
	      }
	      
	      for (int x=7; x>=0; x--)
	      {
	         for(int y=0; y<8; y++)
	         {
	            if(chessBoard[y][x]==null)
	               chessBoard[y][x] = new ChessPiece();
	         }
	      }
	      
	      whitesTurn = true;
	}
	
	public void move(JFrame f) {
        futureMove();
		if (isLegal())
        {
           String color = chessBoard[row][column].getColor();
           
           if(pawnToQueen)
           {
              if(color == "black")
              {
                 chessBoard[newRow][newColumn] = new ChessPiece("queen", "♛", "black");
                 chessBoard[row][column] = new ChessPiece();
              }               
              else if (color == "white")
              {
                 chessBoard[newRow][newColumn] = new ChessPiece("queen", "♕", "white");
                 chessBoard[row][column] = new ChessPiece();
              }
      	   	pawnToQueen = false;

           }
           else
           {
              chessBoard[newRow][newColumn] = chessBoard[row][column];
              chessBoard[row][column] = new ChessPiece();
           }
           whitesTurn = !whitesTurn;

        }
        else 
        {
           System.out.println("Whoops, that was an illegal move! Try again."); //FIX NOT PRINTING
        }
        
        
        printBoard(f);
        
       // comp.move(f);
        
        gameOver(f);
        if(!whiteWins && !blackWins)
        		move(f);
	}
	
	public void printBoard(JFrame f) {
		class Panel00 extends JPanel{
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
     	    	  		g.drawString(((getBoard()[y][x])).getName(), 55+x*50, 420-y*50);
     	    	  	}
     	      }
     	   }
     	}

      f.setContentPane(new Panel00());
      f.setVisible(true);
	}
	
	public boolean isLegal() {
		boolean legal = false;
        int absDeltaX = Math.abs(newColumn-column);
        int absDeltaY = Math.abs(newRow-row);
        boolean inBounds = true;
        
        // checks if in bounds
        if(column<=7&&row<=7&&newColumn<=7&&newRow<=7)
           inBounds = true;
        else 
           inBounds = false;
        
        if(inBounds)
        {
        //pawn
           if (chessBoard[row][column].getType().equals("pawn"))
           {
              if(chessBoard[row][column].getColor().equals("white"))
              {
                 if(newColumn==column && row==newRow-1&&chessBoard[newRow][newColumn].getType().equals("empty"))
                    legal = true;
                 else if(row==1)
                 {
                    if(newColumn==column && row==newRow-2&&chessBoard[newRow][newColumn].getType().equals("empty"))
                       legal = true;
                 }
                 if(absDeltaX==1&&absDeltaY==1&&chessBoard[newRow][newColumn].getColor().equals("black"))
                    legal = true;
                 if(newRow==7)
                    pawnToQueen=true; //turns into queen
              }    
              else if(chessBoard[row][column].getColor().equals("black"))
              {
                 if(newColumn==column && row==newRow+1&&chessBoard[newRow][newColumn].getType().equals("empty"))
                    legal = true;
                 else if(row==6)
                 {
                    if(newColumn==column && row==newRow+2&&chessBoard[newRow][newColumn].getType().equals("empty"))
                       legal = true;
                 }
                 if(absDeltaX==1&&absDeltaY==1&&chessBoard[newRow][newColumn].getColor().equals("white"))
                    legal = true;
              
                 if(newRow==0)
                    pawnToQueen=true; // turns into queen
              }  
           }
           // rook
           else if(chessBoard[row][column].getType().equals("rook"))
           {
              if(newColumn==column)
                 legal = true;
              else if (row==newRow)
                 legal = true;
           }
           //bishop
           else if (chessBoard[row][column].getType().equals("bishop"))
           {     
              if(absDeltaX == absDeltaY)
                 legal = true;
           }
           // knight
           else if(chessBoard[row][column].getType().equals("knight"))
           {
              if(absDeltaX==1&&absDeltaY==2)
                 legal = true;
              else if(absDeltaY==1 && absDeltaX==2)
                 legal = true;
           }
           //queen
           else if(chessBoard[row][column].getType().equals("queen"))
           {
              if(absDeltaX == absDeltaY)
                 legal = true;
              else if(newColumn==column)
                 legal = true;
              else if (row==newRow)
                 legal = true;
           }
           //king
           else if(chessBoard[row][column].getType().equals("king"))
           {
              if(absDeltaX==1||absDeltaY==1)
                 legal=true;
           }
           // not a piece
           else
           {
           		System.out.println("Uh oh, not a piece!");
           }
        
        /*****************************checks jumping over things*****************************/
           int x1 = column;
           int y1 = row;
           int xf = newColumn;
           int yf = newRow;
        
           if(!chessBoard[row][column].getType().equals("knight") && (absDeltaX>1||absDeltaY>1))
           {
              if(x1<xf)
              {
                 xf--;
                 if(y1<yf)
                 {
                    yf--;
                    while (y1<yf && x1<xf)
                    {
                       y1++;
                       x1++;
                       if(!chessBoard[y1][x1].getType().equals("empty"))
                          legal=false;
                    }}
                 if(y1>yf)
                 {
                    yf++;
                    while (y1>yf && x1<xf)
                    {
                       y1--;
                       x1++;
                       if(!chessBoard[y1][x1].getType().equals("empty"))
                          legal=false;
                    }}
                 while (x1<xf)
                 {
                    x1++;
                    if(!chessBoard[y1][x1].getType().equals("empty"))
                       legal=false;
                 }
              }
              else if(x1>xf)
              {
                 xf++;
                 if(y1<yf)
                 {
                    yf--;
                    while (y1<yf && x1>xf)
                    {
                       y1++;
                       x1--;
                       if(!chessBoard[y1][x1].getType().equals("empty"))
                          legal=false;
                    }}
                 else if(y1>yf)
                 {
                    yf--;
                    while (y1>yf && x1> xf)
                    {
                       y1--;
                       x1--;
                       if(!chessBoard[y1][x1].getType().equals("empty"))
                          legal=false;
                    }}
                 while (x1>xf)
                 {
                    x1--;
                    if(!chessBoard[y1][x1].getType().equals("empty"))
                       legal=false;
                 }
              }
              else 
              {  
                 if(y1<yf)
                 {
                    yf--;
                    while (y1<yf)
                    {
                       y1++;
                       if(!chessBoard[y1][x1].getType().equals("empty"))
                          legal=false;
                    }}
                 else if(y1>yf)
                 {
                    yf++;
                    while (y1>yf)
                    {
                       y1--;
                       if(!chessBoard[y1][x1].getType().equals("empty"))
                          legal=false;
                    } }
              }
           }
        
        /*****************************CHECKS FOR KILLING*****************************/
           String color = chessBoard[row][column].getColor();
           if(chessBoard[newRow][newColumn].getColor().equals(color))
           {
              legal = false;
           }
        
        /*****************************CHECKS WHAT IS MOVING****************************/
        
           if(whitesTurn){
              if(!chessBoard[row][column].getColor().equals("white"))
                 legal=false;
           }
           else {
              if(!chessBoard[row][column].getColor().equals("black"))
                 legal=false;
           }
        }
        
		return legal;
	}
	
	public boolean gameOver(JFrame f) {
		whiteWins = true;
		blackWins = true;
		for(int x = 0; x<8; x++) {
			for(int y = 0; y<8; y++) {
				if(chessBoard[x][y].equals(whiteKing))
					blackWins = false;
				if(chessBoard[x][y].equals(blackKing))
					whiteWins = false;
			}
		}
		
		if(blackWins||whiteWins) {
		  	  class End extends JPanel{
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
		  	   	    	  		g.drawString(getBoard()[y][x].getName(), 55+x*50, 420-y*50);
		  	   	    	  	}
		  	   	      }
		  	   	      g.setFont(new Font("Helvetica", Font.BOLD, 14));
		  	   	      g.drawString((whiteWins? "Congrats White, you have won!": "Congrats Black, you have won!"), 17, 480);
		  	   	   }
		  	   	}
		  	      
	  	  	f.setContentPane(new End());
			return true;
		}
		return false;
	}

	public void futureMove() {
		Scanner ask = new Scanner(System.in);
        try{
    			System.out.println("Which column is the piece you want to move in? (eg. 0, 1, 2, etc.)");
            column = ask.nextInt();
            
            System.out.println("Which row is it in? (eg. 0, 1, 2 etc.");
            row = ask.nextInt();
            
            System.out.println("Which column do you want to move it to?");
            newColumn = ask.nextInt();
            
            System.out.println("Which row do you want to move it to?");
            newRow = ask.nextInt();        
        }
        catch(Exception e) {
        		System.out.println("Whoops, that type was not recognized, please try again!");
        		futureMove();
        }
	}
	
	public void endScene(JFrame frame) {
  	  class End extends JPanel{
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
  	   	    	  		g.drawString(getBoard()[y][x].getName(), 55+x*50, 420-y*50);
  	   	    	  	}
  	   	      }
  	   	      g.setFont(new Font("Helvetica", Font.BOLD, 14));
  	   	      g.drawString((whiteWins? "Congrats White, you have won!": "Congrats Black, you have won!"), 17, 480);
  	   	   }
  	   	}
  	      
  	  	frame.setContentPane(new End());
	}
	
	public void setCol(int col) {
		column = col;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setNewCol(int nCol) {
		newColumn = nCol;
	}
	public void setNewRow(int nRow) {
		newRow = nRow;
	}
}
	
