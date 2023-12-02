package daytwo;

import java.util.Vector;

public class Game {
	private int id;
	private Vector<Session> sessions;
	
	public Game(int id) {
		this.id = id;
		this.sessions = new Vector<>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void addSession(Session session) {
		this.sessions.add(session);
	}
	
	public boolean isValid(int maxRed, int maxGreen, int maxBlue) {
		return !this.sessions.stream()
			.filter(session -> !session.isValid(maxRed, maxGreen, maxBlue))
			.findFirst()
			.isPresent();
	}
	
	public Session getMinimumViable() {
		return this.sessions.stream()
			.reduce(new Session(), (current, s) -> current.getMinimumViableSession(s));
	}
}
