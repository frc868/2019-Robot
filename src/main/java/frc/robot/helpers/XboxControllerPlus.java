package frc.robot.helpers;

import edu.wpi.first.wpilibj.XboxController;

public class XboxControllerPlus extends XboxController {
    private static class Bindings {
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

    public ButtonPlus a, b, x, y, rb, lb, rstk, lstk, start, menu;

    public XboxControllerPlus(int port) {
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
    }

    private double cube(double input) {
        return Math.pow(input, 3);
    }

    public double getLX() {
        return cube(getRawAxis(Bindings.LX));
    }

    public double getLY() {
        return cube(getRawAxis(Bindings.LY));
    }

    public double getRX() {
        return cube(getRawAxis(Bindings.RX));
    }

    public double getRY() {
        return cube(getRawAxis(Bindings.RY));
    }

    public double getLT() {
        return getRawAxis(Bindings.LT);
    }

    public double getRT() {
        return getRawAxis(Bindings.RT);
    }

    public boolean getRSTK() {
        return getStickButtonPressed(Hand.kRight);
    }

    public boolean getLSTK() {
        return getStickButtonPressed(Hand.kLeft);
    }
}