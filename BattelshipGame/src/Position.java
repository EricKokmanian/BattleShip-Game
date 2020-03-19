// Name: Eric Kokmanian
// ID#: 40028608
// COMP249
// Assignment #1
// Due Date : February 11th 2017
// This is another class for the battleship game. It creates positions on the board and has methods to place ships, grenades, etc.

public class Position {
	private boolean ship; // Instance variables
	private boolean grenade;
	private boolean called;
	private int ltr; // column of entry
	private int nbr; // row of entry
	private String player;
	
	public Position(String player, String entry) // Constructor
	{
		called = false;
		this.player = player;
		
		ltr = ((int) entry.charAt(0)-65);
		nbr = entry.charAt(1)-49;
		
	}
	
	public Position(String player, int ltr , int nbr) // Constructor
	{
		this.player = player;
		this.ltr = ltr;
		this.nbr = nbr;
		called = false;
	}
	
	public int getNbr() // To return the row
	{
		return(nbr);
	}
	
	public int getLtr() // To return the column
	{
		return(ltr);
	}
	
	public void setShip() // To set a ship at that position
	{
		ship = true;
	}
	
	public boolean getShip() // To return if there is a ship or not
	{
		return(ship);
	}
	
	public void setGrenade() // To set a grenade at that position
	{
		grenade = true;
	}
	
	public boolean getGrenade() // To return if there is a grenade or not
	{
		return(grenade);
	}

	public String getPlayer() // To return the player
	{
		return(player);
	}
	
	public boolean isCalled() // To return if that position was called before or not
	{
		return(called);
	}
	
	public void setCalled() // To set a position that was called before
	{
		called = true;
	}

	public String toString() // for testing
	{
		return("position created... row:" + nbr + " column:" + ltr);
		
	}
	
	public boolean belongsTo(Player n) // To check if the coordinates entered belong to the player
	{
		return(player == n.getName());
	}
}