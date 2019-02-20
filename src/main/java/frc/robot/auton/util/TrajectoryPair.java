package frc.robot.auton.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

public class TrajectoryPair {
    private Trajectory.Segment[] left, right;

    public TrajectoryPair(String fileName) throws FileNotFoundException {
        String path = "home/lvuser/deploy/" + fileName + ".csv";
        File file = new File(path);
        Scanner inputStream = new Scanner(file);

        ArrayList<Trajectory.Segment> segmentsLeft = new ArrayList<Trajectory.Segment>();
        ArrayList<Trajectory.Segment> segmentsRight = new ArrayList<Trajectory.Segment>();

        inputStream.nextLine(); // skip header
        // fill segment array lists w/ csv data
        while (inputStream.hasNext()) {
            String line = inputStream.nextLine();
            String[] lineSplit = line.split(",");

            double dt = Double.parseDouble(lineSplit[0]);

            double xLeft = Double.parseDouble(lineSplit[1]);
            double yLeft = Double.parseDouble(lineSplit[2]);
            double positionLeft = Double.parseDouble(lineSplit[3]);
            double velocityLeft = Double.parseDouble(lineSplit[4]);
            double accelerationLeft = Double.parseDouble(lineSplit[5]);
            double jerkLeft = Double.parseDouble(lineSplit[6]);
            double headingLeft = Double.parseDouble(lineSplit[7]);

            double xRight = Double.parseDouble(lineSplit[8]);
            double yRight = Double.parseDouble(lineSplit[9]);
            double positionRight = Double.parseDouble(lineSplit[10]);
            double velocityRight = Double.parseDouble(lineSplit[11]);
            double accelerationRight = Double.parseDouble(lineSplit[12]);
            double jerkRight = Double.parseDouble(lineSplit[13]);
            double headingRight = Double.parseDouble(lineSplit[14]);

            segmentsLeft.add(new Trajectory.Segment(dt, xLeft, yLeft, positionLeft, velocityLeft, accelerationLeft,
                    jerkLeft, headingLeft));
            segmentsRight.add(new Trajectory.Segment(dt, xRight, yRight, positionRight, velocityRight,
                    accelerationRight, jerkRight, headingRight));
        }

        inputStream.close();

        // turn array lists in to arrays
        this.left = new Trajectory.Segment[segmentsLeft.size()];
        this.right = new Trajectory.Segment[segmentsRight.size()];

        for (int i = 0; i < left.length; i++) {
            SmartDashboard.putNumber("i", i);
            left[i] = segmentsLeft.get(i);
            right[i] = segmentsRight.get(i);
        }
    }

    public TrajectoryPair(Trajectory.Segment[] left, Trajectory.Segment[] right) {
        this.left = left;
        this.right = right;
    }

    public int getLength() {
        SmartDashboard.putNumber("Left length", getLeft().length);
        return getLeft().length;
    }

    public Trajectory.Segment[] getLeft() {
        SmartDashboard.putBoolean("getLeft()", true);
        return left;
    }

    public Trajectory.Segment[] getRight() {
        return right;
    }

    public double getAvgAngle(int i) {
        return (left[i].heading + right[i].heading) / 2;
    }
}