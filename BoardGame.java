package chess;
//Finished game: interactive
//classes: BoardGame, ChessPiece
//WORK ON: 
//---begin moving everything into JOptionPane style windows -- or connecting to window ("window');

import java.util.*; //to work on: making into resource class more, can move other color
import javax.swing.*;
import java.awt.*;

public class BoardGame extends JPanel {
   public static void main(String[]args) {
      boolean gameOver = false;
      
      /****************************************creates chess board****************************************/      
      ChessPiece [][] chessBoard = new ChessPiece [8][8];
      
      ChessPiece rook1White = new ChessPiece("rook", "♖", "white");
      ChessPiece rook2White = new ChessPiece("rook", "♖", "white");
      ChessPiece knight1White = new ChessPiece("knight", "♘", "white");
      ChessPiece bishop1White = new ChessPiece("bishop", "♗", "white");
      ChessPiece queenWhite = new ChessPiece("queen", "♕", "white");
      ChessPiece kingWhite = new ChessPiece("king", "♔", "white");
      ChessPiece bishop2White = new ChessPiece ("bishop", "♗", "white");
      ChessPiece knight2White = new ChessPiece ("knight", "♘", "white");
      
      ChessPiece rook1Black = new ChessPiece("rook", "♜", "black");
      ChessPiece rook2Black = new ChessPiece("rook", "♜", "black");
      ChessPiece knight1Black = new ChessPiece("knight", "♞", "black");
      ChessPiece bishop1Black = new ChessPiece("bishop", "♝", "black");
      ChessPiece queenBlack = new ChessPiece("queen", "♛", "black");
      ChessPiece kingBlack = new ChessPiece("king", "♚", "black");
      ChessPiece bishop2Black = new ChessPiece ("bishop", "♝", "black");
      ChessPiece knight2Black = new ChessPiece ("knight", "♞", "black");
      
      chessBoard[0][0] = rook1White;
      chessBoard[0][1] = knight1White;
      chessBoard[0][2] = bishop1White;
      chessBoard[0][3] = queenWhite;
      chessBoard[0][4] = kingWhite;
      chessBoard[0][5] = bishop2White;
      chessBoard[0][6] = knight2White;
      chessBoard[0][7] = rook2White;
      
      chessBoard[7][0] = rook1Black;
      chessBoard[7][1] = knight1Black;
      chessBoard[7][2] = bishop1Black;
      chessBoard[7][3] = queenBlack;
      chessBoard[7][4] = kingBlack;
      chessBoard[7][5] = bishop2Black;
      chessBoard[7][6] = knight2Black;
      chessBoard[7][7] = rook2Black;
      
      ChessPiece [] row2 = new ChessPiece[8];
      for (int x=0; x<8; x++)
      {
         ChessPiece pawnWhite = new ChessPiece("pawn", "♙", "white");
         chessBoard[1][x] = pawnWhite;
         
         ChessPiece pawnBlack = new ChessPiece("pawn", "B♙", "black");
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
      
      /**** END CREATING BOARD SET UP ****/
      
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
                  g.drawString(chessBoard[y][x].getName(), 55+x*50, 420-y*50);
               }
            }
            g.setFont(new Font("Helvetica", Font.BOLD, 14));
            g.drawString("Welcome to your chess game! Above is the board you will play on.", 17, 480);
         }
      }
      
      JFrame window = new JFrame("Chess Game");
      window.setSize(480, 540);
      window.setLocation(100, 50);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setContentPane(new BeginScene());
      window.setVisible(true);
      
      int turns = 0;   
      boolean whitesTurn = true;
      Scanner ask = new Scanner(System.in);
           
      
      /****************************************begins turns loop****************************************/
      
      while (gameOver==false)
      {
         /*if(turns%2==0)
         {
            System.out.println("It's white's turn!");
            turns++;
            whitesTurn=true;
         }
         else 
         {
            System.out.println("It's black's turn!");
            turns++;
            whitesTurn=false;
         }*/
         
      /****************************************asks where wants to go****************************************/
         
         int column, row, newRow, newColumn;
      
         String loca = JOptionPane.showInputDialog("Please write the location the piece you want to move is in \n"+
                                                   "and where you want it to go. Write the column then row.\n"+
                                                   "(eg. 0103 moves the piece in col. 0, row 1 to col. 0 row 3)");
         boolean wrong = true;
         int all=0;
         
         while(wrong){
            try {
               all = Integer.parseInt(loca);
               wrong = false;
            }
            catch(NumberFormatException e) {
               loca = JOptionPane.showInputDialog("Whoops! That was in the wrong format, please try again!\n"+
                                                "Please write the location the piece you want to move is in \n"+
                                                "and where you want it to go. Write the column then row.\n"+
                                                "(eg. 0103 moves the piece in col. 0, row 1 to col. 0 row 3)");
               all = Integer.parseInt(loca);
            }   
         }
          
         newRow = all%10;
         all /= 10;
         newColumn = all%10;
         all /= 10;
         row = all%10;
         all /= 10;
         column = all;
                  
      /*****************************checks legality of move*****************************/
      
         boolean legal = false;
         int absDeltaX = Math.abs(newColumn-column);
         int absDeltaY = Math.abs(newRow-row);
         boolean pawnToQueen = false;
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
      /****************************ACTUALLY MOVES TO NEW LOCATION*****************************/
               
         if (legal && inBounds)
         {
            String color = chessBoard[row][column].getColor();
            if(pawnToQueen==true)
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
            }
            else
            {
               chessBoard[newRow][newColumn] = chessBoard[row][column];
               chessBoard[row][column] = new ChessPiece();
            }
         }
         else 
         {
            System.out.println("Whoops, that was an illegal move! Try again.");
            turns--;
         }
         
      /*****************************prints out new board*****************************/
             
         class Panel_White extends JPanel{
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
                     g.drawString(chessBoard[y][x].getName(), 55+x*50, 420-y*50);
                  }
               }
               g.setFont(new Font("Helvetica", Font.BOLD, 14));
               g.drawString("It's white's turn!", 17, 480);
            }
         }
      
         class Panel_Black extends JPanel{
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
                     g.drawString(chessBoard[y][x].getName(), 55+x*50, 420-y*50);
                  }
               }
               g.setFont(new Font("Helvetica", Font.BOLD, 14));
               g.drawString("It's black's turn!", 17, 480);
            }
         }
      
         if(turns%2==1){
            window.setContentPane(new Panel_White());
            turns++;
            whitesTurn = true;
         }
         else{
            window.setContentPane(new Panel_Black());
            turns++;
            whitesTurn = false;
         }
         window.setVisible(true);
      
         
      /*****************************checks if someone won*****************************/
         boolean whiteWins = true;
         boolean blackWins = true;
         
         for (int x=0; x<8; x++)
         {
            for (int y =0; y<8; y++)
            {
               if(chessBoard[x][y].equals(kingWhite))
                  blackWins = false;
               else if(chessBoard[x][y].equals(kingBlack))
                  whiteWins=false;
            }
         }
         
         if ((blackWins||whiteWins)==true)
         {
            gameOver=true;
            if(blackWins=true)
               System.out.println("Congratulations to Black! You have won!");
            else if(whiteWins = true)
               System.out.println("Congratulations to White! You have won!");
         }
      }
   }
}
