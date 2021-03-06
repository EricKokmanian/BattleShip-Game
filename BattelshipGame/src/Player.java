// Name: Eric Kokmanian
// ID#: 40028608
// COMP249
// Assignment #1
// Due Date : February 11th 2017
// This is a class for the battleship game. It creates players and has methods that are used to see if ships or grenades were hit, etc.

public class Player {
	private String name; // Instance variables
	private int ships;
	private int grenades;
	private int miss;
	
	public Player() // Default constructor for cpu
	{
		ships = 6;
		grenades = 4;
		name = "Computer";	
	}
	
	public Player(String name) // Constructor for user
	{
		ships = 6;
		grenades = 4;
		this.name = name;
	}
	
	public void addMiss() // To add a missed turn
	{
		miss++;
	}
	
	public int getMiss() // To return the nbrs of misses
	{
		return(miss);
	}
	
	public String getName() // To get name of player
	{
		return(name);
	}
	
	public void shipHit() // To decrement the nbr of ships
	{
		ships--;
	}
	
	public void grenadeHit() // Tp decrement the nbr of grenades
	{
		grenades--;
	}
	
	public int getShips() // To return ships
	{
		return(ships);
	}
	
	public int getGrenades() // To return grenades
	{
		return(grenades);
	}
	
	public boolean belongsTo(Player n) // To check if the coordinates entered belong to the player
	{
		return(name == n.getName());
	}	
	
	public String toString() 
	{
		return(name);
	}

}