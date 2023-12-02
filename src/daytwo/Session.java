package daytwo;

public class Session {
	private int red;
	private int green;
	private int blue;
	
	public Session() {
		this.setRed(0);
		this.setGreen(0);
		this.setBlue(0);
	}

	public Session(int red, int green, int blue) {
		this.setRed(red);
		this.setGreen(green);
		this.setBlue(blue);
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public boolean isValid(int maxRed, int maxGreen, int maxBlue) {
		return this.red <= maxRed && this.green <= maxGreen && this.blue <= maxBlue;
	}
	
	public String toString() {
		return "R:" + this.red + " G:" + this.green + " B:" + this.blue;
	}
	
	public int getPower() {
		return this.red * this.green * this.blue;
	}
	
	public Session getMinimumViableSession(Session otherSession) {
		return new Session(
			Math.max(this.red, otherSession.getRed()),
			Math.max(this.green, otherSession.getGreen()),
			Math.max(this.blue, otherSession.getBlue())
		);
	}
}
