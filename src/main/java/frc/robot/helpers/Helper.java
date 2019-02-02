package frc.robot.helpers;

public class Helper {

    public static double boundValue(double value, double lowerLimit, double upperLimit) {
        if (value > upperLimit) {
            return upperLimit;
        } else if (value < lowerLimit) {
            return lowerLimit;
        } else {
            return value;
        }
    }

    public static double boundValue(double value) {
        return boundValue(value, -1, 1);
    }
}