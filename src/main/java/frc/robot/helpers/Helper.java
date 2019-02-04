package frc.robot.helpers;

public class Helper {

    /**
     * 
     * @param value value to bound
     * @param lowerLimit lower limit of output
     * @param upperLimit upper limit of ouput
     * @return a bounded version of the input value. EX: boundValue(2, 0, 1) will restrict 2 to between 0 and 1, and thus output 1
     */
    public static double boundValue(double value, double lowerLimit, double upperLimit) {
        if (value > upperLimit) {
            return upperLimit;
        } else if (value < lowerLimit) {
            return lowerLimit;
        } else {
            return value;
        }
    }

    /**
     * 
     * @param value value to bound
     * @return input value, bounded between -1 and 1. For example, if -1.2 is inputted, -1 will be outputted
     */
    public static double boundValue(double value) {
        return boundValue(value, -1, 1);
    }

    /**
     * Creates a virtual deadzone. 
     * If a value is between the lower max and upper min, it will output zero. 
     * If a value is outside this range, the original value will be outputted.
     * @param value value to deadzone
     * @param lowerMinimum the lower maximum value
     * @param upperMinimum the uppper minimum value
     * @return the "deadzoned" version of the input value
     */
    public static double deadzone(double value, double lowerMaximum, double upperMinimum) {
        if (value > lowerMaximum && value < upperMinimum) {
            return 0;
        } else {
            return value;
        }
    }
}