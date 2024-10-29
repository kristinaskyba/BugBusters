package heptathlon;

import common.*;
import decathlon.InvalidResultException;

public class HeptHightJump {


	private double A = 1.84523;
	private double B = 75;
	private double C = 1.348;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in cenimeters.
	public int calculateResult(double distance) throws InvalidResultException {
		// Acceptable values in cm
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
