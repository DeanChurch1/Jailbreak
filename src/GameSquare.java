////////////////////////////////////////////////////////////////
// Copyright, CompuScholar, Inc.
//
// This source code is for use by the students and teachers who 
// have purchased the corresponding TeenCoder or KidCoder product.
// It may not be transmitted to other parties for any reason
// without the written consent of CompuScholar, Inc.
// This source is provided as-is for educational purposes only.
// CompuScholar, Inc. makes no warranty and assumes
// no liability regarding the functionality of this program.
//
////////////////////////////////////////////////////////////////

// This class is provided complete as part of the activity starter.
// No student modification is necessary!

/** Java Programming
 * Chapter 16 - Jail Break Project
 * This class represents the one square on the game board in the 
 * Jail Break game.
 * @author CompuScholar, Inc.
 * @version 1.0
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

// This class represents one square on the game board
public class GameSquare
{
	// declare some constants representing the 3 types of game squares
	static public final int TYPE_NEUTRAL = 0;
	static public final int TYPE_JAIL = 1;
	static public final int TYPE_CAMP = 2;

	// These member variables will hold the square type, row and column
	private int myType;
	private int myRow;
	private int myCol;
	
	// Each square has a JButton object that forms the GUI
	private JButton myButton;
	
	// Each square MAY have a game piece on it, or this will be null otherwise
	private AbstractGamePiece myPiece;
	
	// This member holds the square's color
	Color myColor;
	
	// These static borders can be used on any square (selected or unselected)
	static Border selectedBorder = BorderFactory.createLineBorder(Color.yellow,3);
	static Border unselectedBorder = BorderFactory.createLineBorder(Color.black,1);

	// The GameSquare constructor will initialize the member variables
	public GameSquare(int row, int col, JPanel panel, ActionListener listener)
	{
		myType = TYPE_NEUTRAL;	// assume neutral square
		myRow = row;
		myCol = col;
		
		// create JButton for the GUI.  Set it's size, default color, and unselected border 
		myButton = new JButton();
		myButton.setPreferredSize(new Dimension(50,50));
		myColor = Color.white;
		myButton.setBackground(myColor);
		myButton.setBorder(unselectedBorder);
		myButton.setOpaque(true);
		
		// add the action listener (which is really the JailBreak main class!
		myButton.addActionListener(listener);
		
		// add this JButton to the panel
		panel.add(myButton);
	}

	// set the type of square as neutral, Jail, or Camp
	public void setType(int type)
	{
		myType = type;
		if (myType == TYPE_NEUTRAL)
		{
			myColor = Color.white;
		}
		else if (myType == TYPE_JAIL)
		{
			myColor = Color.cyan;
		}
		else
		{
			myColor = new Color(220,220,220); // a lighter gray color
		}
		
		// update the background color to show the type of square
		myButton.setBackground(myColor);
	}
	
	// this method returns the square's type
	public int getType() 
	{
		return myType;
	}
	
	/// this method returns the game piece on the square (or null if none!)
	public AbstractGamePiece getPiece()
	{
		return myPiece;
	}
	
	// these two methods return the square's row and column
	public int getRow()
	{
		return myRow;
	}
	public int getCol() 
	{
		return myCol;
	}
	
	// this method will add the specified piece to the game square
	public void setPiece(AbstractGamePiece piece)
	{
		myPiece = piece;	// store the piece
		
		// update the piece's position to match this square's row and column
		piece.setPosition(myRow, myCol);
		
		// choose the font for the abbreviation we are going to display
		if (piece.getPlayerType() == AbstractGamePiece.PLAYER_OUTLAWS)
			myButton.setFont(new Font(null,Font.BOLD,16));
		else
			myButton.setFont(new Font(null,Font.ITALIC,18));
		
		// display the abbreviation representing the game piece on the JButton
		myButton.setText(myPiece.getAbbreviation());
	}
	
	// remove any game piece from the square
	public void clearSquare()
	{
		myPiece = null;
		myButton.setText("");
		
	}
	
	// show the yellow border around this square
	public void select()
	{
		myButton.setBorder(selectedBorder);
	}
	
	// show the thin black around this square
	public void deselect()
	{
		myButton.setBorder(unselectedBorder);
	}
	
	// returns true if the source object equals the internal JButton
	public boolean isClicked(Object source)
	{
		if (source == myButton)
			return true;
		else
			return false;
		
	}
	
}
