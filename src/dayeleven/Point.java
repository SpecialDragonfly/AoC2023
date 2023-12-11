package dayeleven;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String toString() {
		return x + "," + y;
	}

	public long distanceTo(Point point) {
		long xDiff = Math.abs(point.getX() - this.getX());
		long yDiff = Math.abs(point.getY() - this.getY());
		
		return xDiff + yDiff;
	}
}
