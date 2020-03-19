// Name: Eric Kokmanian
// ID#: 40028608
// COMP249
// Assignment #1
// Due Date : February 11th 2017
// This program simulates a battleship game between a user and the computer. The winner is the one who destroys all enemy ships.

import java.util.Scanner; 

public class Battleship {
	private Player cpu = new Player(); // Instance variables
	private Player user = new Player("User");
	private Position[][] board;
	private int totalShips = 6;
	private int totalGrenades = 4;
	
	
	Scanner myKey = new Scanner(System.in);
	
	public Battleship() // Constructor to create board
	{
		board = new Position[8][8];
	}
	
	public boolean available(String entry) // To see if that String entry is available
	{		
		int r = ((int) entry.charAt(0)-65); 
		int c = entry.charAt(1)-49; 
		
		return(board[c][r]==null);
	}
	
	public boolean available(int x, int y) // Overloading method available() to see if that position is available
	{
		return(board[x][y]==null);
	}
	
	public boolean fitIn(String entry) // To see if that String fits in the board's dimensions 
	{
		int m = ((int) entry.charAt(0)-65); 
		int n = entry.charAt(1)-49; 
		
		return(m>=0 && m<=7 && n>=0 && n<=7);
	}
	
	public boolean fitIn(int x, int y) // Overloading method fitIn() to see if that position fits in the board's dimensions
	{
		return(x>=0 && x<=7 && y>=0 && y<=7);
	}
	
	public Position[][] getBoard() // To print board --> USEFUL?
	{
		return(board);
	}
	
	public void setUserShips(Player p, Scanner sc) // For user to set his ships
	{
		for (int i = 0; i< totalShips ; i++)
			{
				System.out.print("Enter the coordinates of your ship #" + (i+1) + ": ");
				String place = sc.nextLine();
				
				if(fitIn(place)) 
				{
					if(available(place))
					{
						Position po = new Position(p.getName(), place);
						
						po.setShip();
						board[po.getNbr()][po.getLtr()] = po;
					}
					else
					{
						System.out.println("Sorry, but the position you entered is already taken. Try again!");
						i--;
						continue;
					}
				}
				else
				{
					System.out.println("Sorry, but the position you entered does not fit in the board. Try again!");
					i--; // to replace the same ship
					continue; // to restart loop
				}
			}
		}
	
	public void setCpuShips(Player p) // For cpu to set his ships
	{
		for (int i = 0; i< totalShips ; i++)
			{
				
				
				Position pos = new Position(p.getName(), (int) (Math.random()*8) , (int) (Math.random()*8));
				
				
				if(available(pos.getNbr(), pos.getLtr())) //bug 
				{
					pos.setShip();
					board[pos.getNbr()][pos.getLtr()] = pos;
				}
				else
				{
					i--; 
					continue; 
				}
				
			}
		
	}
	
	public void setUserGrenades(Player p, Scanner sc) // For user to set his grenades
	{		
		for (int i = 0; i< totalGrenades ; i++)
			{
				System.out.print("Enter the coordinates of your grenade #" + (i+1) + ": ");
				String place = sc.nextLine();
				
				if(fitIn(place))
				{
					if(available(place))
					{
						Position po = new Position(p.getName(), place);
						
						po.setGrenade();
						board[po.getNbr()][po.getLtr()] = po;
						// board[po.getNbr()][po.getLtr()].setUsed();
					}
					else
					{
						System.out.println("Sorry, but the position you entered is already taken. Try again!");
						i--;
						continue;
					}
				}
				else
				{
					System.out.println("Sorry, but the position you entered does not fit in the board. Try again!");
					i--;
					continue;
				}
			}
		}
	
	public void setCpuGrenades(Player p) // For cpu to set his grenades
	{
		for (int i = 0; i< totalGrenades ; i++)
			{
				Position pos = new Position(p.getName(), (int) (Math.random()*8) , (int) (Math.random()*8));
				
				if(available(pos.getNbr(), pos.getLtr()))
				{
					pos.setGrenade();
					board[pos.getNbr()][pos.getLtr()] = pos;
					
				}
				else
				{
					i--;
					continue;
				}
			
			}
	}
		
	public void displayBoard() // To display board
	{
		for (int i = 0; i< 8 ; i++)
		{
			for (int j = 0; j<8 ; j++)
			{
				
				if (board[i][j].belongsTo(cpu)) //problem
				{
					if (board[i][j].isCalled())
					{
						if (board[i][j].getShip())
						{
							System.out.print('S');
						}
						else if (board[i][j].getGrenade())
						{
							System.out.print('G');
						}
						else
							System.out.print(" ");
					}
					else
						System.out.print("_");
				}
				else // if the element at that position belongs to the user
				{
					if (board[i][j].getShip())
					{
						System.out.print('s');
					}
					else if (board[i][j].getGrenade())
						{
							System.out.print('g');
						}
					else
						System.out.print(" ");	
				}
			}
		}	
	}
	
	
	public void shootRocket(Player p, Scanner sc) // To shoot a rocket (to play)
	{
		if (p.belongsTo(cpu))
		{
			int x,y;
			
			do
			{
				x = (int) (Math.random()*8);
				y = (int) (Math.random()*8);
			}
			while(board[x][y].belongsTo(cpu));
			
			System.out.print("Position of my rocket: " + (char)(x+65) + y);
			
			if (board[x][y].getGrenade())
			{
				System.out.println("Boom! Grenade!");
				p.grenadeHit();
				p.addMiss();
				board[x][y].setCalled();
				shootRocket(user, myKey);	// prompts user to have an extra turn			
			}
			else if (board[x][y].getShip())
			{
				System.out.println("Ship hit!");
				p.shipHit();
				board[x][y].setCalled();
				
			}
			else
			{
				System.out.println("Nothing");
				board[x][y].setCalled();
			}
			
		}
		else
		{
			System.out.print("Position of your rocket : ");
			String input = sc.nextLine();
			int o = ((int)input.charAt(0) - 65);
			int q = input.charAt(1)-49;
			System.out.println("o=" + o + " q = " + q);
			
			if (fitIn(input))
			{
				if(board[o][q].belongsTo(cpu)) // problem
				{
					if (board[o][q].getShip())
					{
						System.out.println("Ship hit!");
						p.shipHit();
						board[o][q].setCalled();
					}
					else if (board[o][q].getGrenade()) //recognizes it 
					{
						System.out.println("Boom! Grenade!");
						p.grenadeHit();
						p.addMiss();
						board[o][q].setCalled();
						shootRocket(cpu, myKey); // prompts cpu to get an extra turn
					}
				}
				else
				{
					System.out.println("Nothing");
					board[o][q].setCalled();
				}
				
			}
		}
	}
	
	public void runGame() // To run game
	{
		System.out.println("  ------------------------------");
		System.out.println("    Hi, let’s play Battleship!");
		System.out.println("  ------------------------------");
		System.out.println("");
		
		setUserShips(user, myKey); // user sets his ships
		setUserGrenades(user, myKey); //user sets his grenades
		System.out.println("Computer's turn to choose positions of ships and grenades!");
		setCpuShips(cpu); // cpu sets his ships
		setCpuGrenades(cpu); // cpu sets his grenades
		
		System.out.println("The computer has placed its ships and grenades at random. Let's play!"); // doesn't print!
		while (user.getShips() != 0 && cpu.getShips() != 0)
		{
			shootRocket(user, myKey); // problem
			displayBoard();
			shootRocket(cpu, myKey);
			displayBoard();
		}
		
		if(user.getShips() == 0) // if user has no ships left
			System.out.println("I win! Better luck next time.");
		else // if cpu has no ships let
			System.out.println("You win! I am no match to you.");
		
		System.out.println("You missed " + user.getMiss() + " times. I missed " + cpu.getMiss() + " times.");
		displayBoard(); // display final board of every ship
	}
}

// program end