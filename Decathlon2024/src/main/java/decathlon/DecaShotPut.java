package decathlon;

import common.CalcTrackAndField;
import common.InputResult;

public class DecaShotPut {

	private int score;
	private double A = 51.39;
	private double B = 1.5;
	private double C = 1.05;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in meters.
	public int calculateResult(double distance) {

		while (active) {

			try {
				// Fixed lower value - Oskar
				if (distance < 1.5) {
					System.out.println("Value too low");
					distance = inputResult.enterResult();
				} else if (distance > 30) {
					System.out.println("Value too high");
					distance = inputResult.enterResult();

					// Fixed higher value
					if (distance > 30) {
						System.out.println("Error: Input must be 30 or less. Setting to maximum allowed value.");
						distance = 30;
					}
					// Don't set active = false here to allow the while loop to continue
				}else {

					score = calc.calculateField(A, B, C, distance);
					active = false;
				}
			} catch (Exception e) {

				System.out.println("Please enter numbers");
			}
		}
		System.out.println("The result is: " + score);
		return score;
	}

}
