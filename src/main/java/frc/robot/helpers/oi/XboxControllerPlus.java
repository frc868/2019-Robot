package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.XboxController;

public class XboxControllerPlus extends XboxController{
    
    public ButtonPlus a, b, x, y, rb, lb, rstk, lstk, start, menu;
    public POVButtonPlus povN, povNE, povE, povSE, povS, povSW, povW, povNW;
    /**
     * extension of an xbox controller but using actual names instead of numbers for the buttons
     *
     * @param port the usb port the controller is plugged into (0 for driver, 1 for operator)
     */
    public XboxControllerPlus(int port){
        super(port);
        a = new ButtonPlus(this, Bindings.A);
        b = new ButtonPlus(this, Bindings.B);
        x = new ButtonPlus(this, Bindings.X);
        y = new ButtonPlus(this, Bindings.Y);
        rb = new ButtonPlus(this, Bindings.RB);
        lb = new ButtonPlus(this, Bindings.LB);
        rstk = new ButtonPlus(this, Bindings.RSTK);
        lstk = new ButtonPlus(this, Bindings.LSTK);
        start = new ButtonPlus(this, Bindings.START);
        menu = new ButtonPlus(this, Bindings.MENU);
    
        povN = new POVButtonPlus(this, 0);
        povNE = new POVButtonPlus(this, 45);
        povE = new POVButtonPlus(this, 90);
        povSE = new POVButtonPlus(this, 135);
        povS = new POVButtonPlus(this, 180);
        povSW = new POVButtonPlus(this, 225);
        povW = new POVButtonPlus(this, 270);
        povNW = new POVButtonPlus(this, 315);
    }
    
    /**
     * cubes joystick input
     *
     * @param input the value of the input
     */
    public static double cube(double input){
        return Math.pow(input, 3);
    }
    
    /**
     * @return value of left x axis
     */
    public double getLX(){
        return getRawAxis(Bindings.LX);
    }
    
    /**
     * @return the value of the left y axis of the controller
     */
    public double getLY(){
        return getRawAxis(Bindings.LY);
    }
    
    /**
     * @return the value of the right x axis of the controller
     */
    public double getRX(){
        return getRawAxis(Bindings.RX);
    }
    
    /**
     * @return the value of the right y axis of the controller
     */
    public double getRY(){
        return getRawAxis(Bindings.RY);
    }
    
    /**
     * @return the value of the left trigger axis of the controller
     */
    public double getLT(){
        return getRawAxis(Bindings.LT);
    }
    
    /**
     * @return the value of the right trigger of controller
     */
    public double getRT(){
        return getRawAxis(Bindings.RT);
    }
    
    /**
     * @return the state of the right stick button
     */
    public boolean getRSTK(){
        return getStickButtonPressed(Hand.kRight);
    }
    
    /**
     * @return the state of the left stick button
     */
    public boolean getLSTK(){
        return getStickButtonPressed(Hand.kLeft);
    }
    
    /**
     * does the shaking thing on the controller
     *
     * @param state whether you want your hand to vibrate
     */
    public void setRumble(boolean state){
        int value = state ? 1 : 0;
        setRumble(RumbleType.kLeftRumble, value);
        setRumble(RumbleType.kRightRumble, value);
    }
    
    /**
     * class is dedicated to having all the static final ints needed for your xbox controller mapping
     */
    private static class Bindings{
        
        private static final int A = 1;
        private static final int B = 2;
        private static final int X = 3;
        private static final int Y = 4;
        private static final int RB = 6;
        private static final int LB = 5;
        private static final int RSTK = 10;
        private static final int LSTK = 9;
        private static final int START = 8;
        private static final int MENU = 7;
        
        private static final int LX = 0;
        private static final int LY = 1;
        private static final int RX = 4;
        private static final int RY = 5;
        private static final int LT = 2;
        private static final int RT = 3;
    }
}