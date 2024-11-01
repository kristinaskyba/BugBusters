package heptathlon;

import common.*;
import decathlon.InvalidResultException;

public class HeptLongJump {

	private double A = 0.1888807;
	private double B = 210;
	private double C = 1.41;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	public static InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in meters.
	public int calculateResult(double distance) throws InvalidResultException {
		// Acceptable values.
		if (distance < 210) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (distance > 1000) {
			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}
		int score = calc.calculateField(A, B, C, distance);
		System.out.println("The result is: " + score);
		return score;
	}
}

