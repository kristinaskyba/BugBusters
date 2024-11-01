package decathlon;

import common.*;

public class DecaHighJump {

	private double A = 0.8465;
	private double B = 75;
	private double C = 1.42;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	public static InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in centimeters.
	public int calculateResult(double distance) throws InvalidResultException {


		// Acceptable values.
		if (distance < 75) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (distance > 300) {
			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}
		int score = calc.calculateField(A, B, C, distance);
		System.out.println("The result is: " + score);
		return score;
	}
}

