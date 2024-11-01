package heptathlon;

import common.*;
import decathlon.InvalidResultException;

public class HeptJavelinThrow {


	private double A = 15.9803;
	private double B = 3.8;
	private double C = 1.04;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in metres.
	public int calculateResult(double distance) throws InvalidResultException {
		// Acceptable values.
		if (distance < 3.8) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (distance > 110) {

			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}
		int score = calc.calculateField(A, B, C, distance);
		System.out.println("The result is: " + score);
		return score;
	}

}

