package daytwo;

import java.util.Arrays;
import java.util.Vector;

import util.FileReader;

public class DayTwo {
	public static void main(String[] args) {
		DayTwo.runPartOne();
		DayTwo.runPartTwo();
	}
	
	private static Vector<Game> getData()
	{
		Vector<String> lines = FileReader.readFile("./src/daytwo/input.txt");
		Vector<Game> games = new Vector<>();

		// e.g. Game 1: 1 green, 2 blue; 13 red, 2 blue, 3 green; 4 green, 14 red
		lines.stream().forEach(line -> {
			line = line.substring(5);
			String[] parts = line.split(":");
			Game game = new Game(Integer.valueOf(parts[0]));
			Arrays.stream(parts[1].split(";")).forEach(session -> {
				Session gameSession = new Session();
				Arrays.stream(session.split(",")).forEach(component -> {
					String[] componentParts = component.trim().split(" ");
					int componentValue = Integer.valueOf(componentParts[0]);
					switch (componentParts[1]) {
						case "green":
							gameSession.setGreen(componentValue);
							break;
						case "red":
							gameSession.setRed(componentValue);
							break;
						case "blue":
							gameSession.setBlue(componentValue);
							break;
					}
				});
				game.addSession(gameSession);
			});			
			
			games.add(game);
		});

		return games;
	}
	
	private static void runPartOne()
	{
		Vector<Game> games = DayTwo.getData();
		int maxRed = 12;
		int maxGreen = 13;
		int maxBlue = 14;
		int sumOfGames = games.stream()
				.filter(g -> g.isValid(maxRed, maxGreen, maxBlue))
				.mapToInt(g -> g.getId())
				.reduce(0, (acc, id) -> acc + id);
		System.out.println("Sum of Games: " + sumOfGames);
	}
	
	private static void runPartTwo()
	{
		Vector<Game> games = DayTwo.getData();
		
		int sum = games.stream()
				.map(game -> game.getMinimumViable().getPower())
				.reduce(0, (acc, power) -> acc + power);
		System.out.println("Sum: " + sum);
	}
}
