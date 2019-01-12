package frc.robot;

public class Helper {

    public static double boundValue(double value, double upperLimit, double lowerLimit) {
        if (value > upperLimit) {
            return upperLimit;
        } else if (value < lowerLimit) {
            return lowerLimit;
        } else {
            return value;
        }
    }

    public static double boundValue(double value) {
        return boundValue(value, 1, -1);
    }
}