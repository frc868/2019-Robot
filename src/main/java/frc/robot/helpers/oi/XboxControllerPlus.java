package frc.robot.helpers.oi;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.POVButton;

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
    public POVButtonPlus povN, povNE, povE, povSE, povS, povSW, povW, povNW;

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

        povN = new POVButtonPlus(this, 0);
        povNE = new POVButtonPlus(this, 45);
        povE = new POVButtonPlus(this, 90);
        povSE = new POVButtonPlus(this, 135);
        povS = new POVButtonPlus(this, 180);
        povSW = new POVButtonPlus(this, 225);
        povW = new POVButtonPlus(this, 270);
        povNW = new POVButtonPlus(this, 315);
    }

    public static double cube(double input) {
        return Math.pow(input, 3);
    }

    public double getLX() {
        return getRawAxis(Bindings.LX);
    }

    public double getLY() {
        return getRawAxis(Bindings.LY);
    }

    public double getRX() {
        return getRawAxis(Bindings.RX);
    }

    public double getRY() {
        return getRawAxis(Bindings.RY);
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