package frc.robot.helpers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxControllerPlus extends XboxController {
    private static class Bindings {
        private static final int A = 0;
        private static final int B = 1;
        private static final int X = 2;
        private static final int Y = 3;
        private static final int RB = 6;
        private static final int LB = 7;
        private static final int RSTK = 4;
        private static final int LSTK = 5;
        private static final int START = 8;
        private static final int MENU = 9;

        private static final int LX = 0;
        private static final int LY = 1;
        private static final int RX = 2;
        private static final int RY = 3;
        private static final int LT = 4;
        private static final int RT = 5;
    }

    public Button a, b, x, y, rb, lb, rstk, lstk, start, menu;

    public XboxControllerPlus(int port) {
        super(port);
        a = new JoystickButton(this, Bindings.A);
        b = new JoystickButton(this, Bindings.B);
        x = new JoystickButton(this, Bindings.X);
        y = new JoystickButton(this, Bindings.Y);
        rb = new JoystickButton(this, Bindings.RB);
        lb = new JoystickButton(this, Bindings.LB);
        rstk = new JoystickButton(this, Bindings.RSTK);
        lstk = new JoystickButton(this, Bindings.LSTK);
        start = new JoystickButton(this, Bindings.START);
        menu = new JoystickButton(this, Bindings.MENU);
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
}