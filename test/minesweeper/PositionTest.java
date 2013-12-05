package minesweeper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {
	private Position position;
	
	@Before
	public void setup() {
		position = new Position(2, 3);
	}
	
	@Test
	public void init() {
		assertThat(position, is(new Position(2, 3)));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void initWhenIndexOutOfBoundRow() throws Exception {
		new Position(0, 3);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void initWhenIndexOutOfBoundColumn() throws Exception {
		new GridSize(3, 0);
	}
}
