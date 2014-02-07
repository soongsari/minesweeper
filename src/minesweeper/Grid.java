package minesweeper;

public class Grid {

	private int numberOfMine = 0;

	private Row rows[];

	public Grid(int row, int col) {
		rows = new Row[row];
		for (int i = 0; i < row; i++) {
			rows[i] = new Row(col);
		}
	}

	public int getRow() {
		return rows.length;
	}

	public int getCol() {
		return rows[0].getCol();
	}

	public int getNumberOfMine() {
		return numberOfMine;
	}

	public boolean isGameOver() {
		if (numberOfMine == getRow() * getCol()) {
			return true;
		}

		if (isAllOpen()) {
			return true;
		}
		return false;
	}

	private boolean isAllOpen() {
		for (int i = 0; i < getRow(); i++) {
			Row row = rows[i];
			if (!row.isAllOpen()) {
				return false;
			}
		}
		return true;
	}

	public void putMine(int row, int col) {
		Position pos = new Position(row, col);
		
		if(getSquare(pos).isMine()) return;
		getSquare(pos).setMine();
		for (Position eachPos : pos.getNeighbors(getRow(), getCol())) {
			getSquare(eachPos).setNumOfNearMines();
		}
		
	}

	public void openSquare(int row, int col) throws GameoverException {
		Position pos = new Position(row, col);
		Square square = getSquare(pos);
		
		if (square.isMine()) {
			throw new GameoverException();
		}
		
		square.setOpen();
		
		if(square.getNumOfNearMines() != 0) {
			return ;
		}
	
		for (Position eachPos :  pos.getNeighbors(getRow(), getCol())) {
			Square eachSquare = getSquare(eachPos);
			if(!eachSquare.isOpen()) {
				openSquare(eachPos.getX(), eachPos.getY());
			}
		}	
	}

	public Square getSquare(int row, int col) {
		return rows[row].getSquare(col);
	}
	
	public Square getSquare(Position pos) {
		return rows[pos.getX()].getSquare(pos.getY());
	}
	
	public boolean isFlag(int i, int j) {
		Position pos = new Position(i, j);
		
		return getSquare(pos).isFlag();
	}

	public void setFlag(int i, int j) {
		Position pos = new Position(i, j);
		
		getSquare(pos).setFlag();
	}

	public String printConsole() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getCol(); j++) {
				result.append(getSquare(i, j).printSymbol());
			}
			result.append("\n");
		}
		System.out.println(result.toString());
		return result.toString();
	}

	public void openAll() {
		for (int i = 0; i < getRow(); i++) {
			rows[i].openAll();
		}

	}

}
