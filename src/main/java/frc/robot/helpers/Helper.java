package frc.robot.helpers;

public class Helper{
    
    /**
     * @param value value to bound
     * @return input value, bounded between -1 and 1. For example, if -1.2 is inputted, -1 will be outputted
     */
    public static double boundValue(double value){
        return boundValue(value, -1, 1);
    }
    
    /**
     * @param value      value to bound
     * @param lowerLimit lower limit of output
     * @param upperLimit upper limit of ouput
     * @return a bounded version of the input value. EX: boundValue(2, 0, 1) will restrict 2 to between 0 and 1, and thus output 1
     */
    public static double boundValue(double value, double lowerLimit, double upperLimit){
        if(value > upperLimit){
            return upperLimit;
        }else if(value < lowerLimit){
            return lowerLimit;
        }else{
            return value;
        }
    }
    
    /**
     * Creates a virtual deadzone.
     * If a value is between the lower max and upper min, it will output zero.
     * If a value is outside this range, the original value will be outputted.
     *
     * @param value value to deadzone
     * @param size  the size of the deadzone
     * @return the "deadzoned" version of the input value
     */
    public static double deadzone(double value, double size){
        if(Math.abs(value) < Math.abs(size)){
            return 0;
        }else{
            return value;
        }
    }
    
    /**
     * @param angle0 in radians
     * @param angle1 in radians
     * @return the difference between the two angles (between -pi and pi)
     */
    public static double angleDiff(double angle0, double angle1){
        return Math.atan2(Math.sin(angle0 - angle1), Math.cos(angle0 - angle1));
    }
}