package chess;

public class ChessPiece {
 	public String name;
 	public String pieceType;
 	public String pieceColor;
	   
 	public ChessPiece(String type, String nam, String color) 
 	{
 		pieceType = type;
 		name = nam; 
 		pieceColor = color;
 	}
 	public ChessPiece()
 	{
 		pieceType = "empty";
 		name = "   ";
 		pieceColor = "none";
 	}
 	public String getName() {
 		return name;
   	}   
 	public String getType() {
 		return pieceType;
 	}
 	public String getColor()
 	{
 		return pieceColor;
 	}
}
