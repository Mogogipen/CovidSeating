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
		return (seats[row][col] == State.OPEN);
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
		State[] pos = new State[4];
		pos[0] = seats[row-1][col];
		pos[1] = seats[row+1][col];
		pos[2] = seats[row][col-1];
		pos[3] = seats[row][col+1];
		for (int i = 0; i < 4; i++) {
			if (pos[0] != )
		}
	}
	
	public static void main(String args[]) {
		int rows = getUserInteger("How many rows are there in the theater?");
		int cols = getUserInteger("How many columns are there in the theater?");
		SeatingChart s1 = new SeatingChart(rows, cols);
	}
	
	public static int getUserInteger(String request) {
		int x = 0;
		Scanner in = new Scanner(System.in);
		boolean proceed = false;
		while (!proceed) {
			System.out.println(request);
			x = in.nextInt();
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
