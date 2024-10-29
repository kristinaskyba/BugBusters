package heptathlon;

import common.*;
import decathlon.InvalidResultException;

public class HeptShotPut {


	private double A = 56.0211;
	private double B = 1.5;
	private double C = 1.05;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	//public static InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in meters.
	public int calculateResult(double distance) throws InvalidResultException {

		// Acceptable values.
		if (distance < 1.5) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (distance > 30) {

			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}
		int score = calc.calculateField(A, B, C, distance);
		System.out.println("The result is: " + score);
		return score;
	}
}
