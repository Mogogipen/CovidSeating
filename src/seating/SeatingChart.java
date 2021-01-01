package seating;

import java.util.Scanner;

public class SeatingChart {
	
	enum State {
		OPEN,
		TAKEN,
		PADDING
	}
	
	private State[][] seats;
	
	SeatingChart (int rows, int cols) {
		seats = new State[rows][cols];
		makeEmpty();
	}
	
	public void makeEmpty() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[0].length; j++) {
				seats[i][j] = State.OPEN;
			}
		}
	}
	
	public boolean seatAvailable(int row, int col) {
		if (row > 0 && row < seats.length && col > 0 && col < seats[0].length)
			return (seats[row][col] == State.OPEN);
		return false;
	}
	
	public boolean takeSeat(int row, int col) {
		if (seatAvailable(row, col)) {
			seats[row][col] = State.TAKEN;
			addPadding(row, col);
			return true;
		}
		return false;
	}
	
	private void addPadding(int row, int col) {
		pad(row-1, col);
		pad(row+1, col);
		pad(row, col+1);
		pad(row, col-1);
	}
	private void pad(int r, int c) {
		try {
			if (seats[r][c] == State.OPEN)
				seats[r][c] = State.PADDING;
		} catch (Exception e) {
			
		}
	}
	
	public String toString() {
		String result = "";
		
		// Column numbers
		result += "   ";
		for (int i = 0; i < seats[0].length; i++) {
			result += " " + (i+1);
		}
		result += "\n";
		
		// Rows with a number at the start
		for (int i = 0; i < seats.length; i++) {
			result += ((i+1) + " |");
			for (int j = 0; j < seats[0].length; j++) {
				result += " ";
				State s = seats[i][j];
				if (s == State.OPEN)
					result += "O";
				else if (s == State.TAKEN)
					result += "X";
				else
					result += ".";
			}
			result += " |\n";
		}
		return result;
	}
	
	public static void main(String args[]) {
		int rows = getUserInteger("How many rows are there in the theater?");
		int cols = getUserInteger("How many columns are there in the theater?");
		SeatingChart s1 = new SeatingChart(rows, cols);
		System.out.println(s1);
		
		while(true) {
			boolean seatTaken = false;
			while (!seatTaken) {
				int row = getUserInteger("Which row do you want to sit at?")-1;
				int col = getUserInteger("Which seat do you want in that row? (Column)")-1;
				seatTaken = s1.takeSeat(row, col);
				if (!seatTaken)
					System.out.println("Sorry, that seat is either invalid, or unavailable");
			}
			System.out.println(s1);
		}
	}
	
	public static int getUserInteger(String request) {
		int x = 0;
		Scanner in = new Scanner(System.in);
		boolean proceed = false;
		while (!proceed) {
			System.out.println(request);
			try {
				x = in.nextInt();
			} catch (Exception e) {
				break;
			}
			boolean valid = false;
			boolean confirmed = false;
			while (!valid) {
				System.out.printf("Is %d correct? (y/n)\n", x);
				String s = in.next();
				s.toLowerCase();
				if (s.equals("y")) {
					valid = true;
					confirmed = true;
				}
				else if (s.equals("n"))
					valid = true;
			}
			if (x > 0 && confirmed)
				proceed = true;
			else if (x <= 0) {
				System.out.print("Sorry, that is invalid. ");
			}
		}
		return x;
	}

}
