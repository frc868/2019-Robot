package frc.robot.helpers.motionprofiling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import jaci.pathfinder.Trajectory;

public class TrajectoryPair {
    private ArrayList<Trajectory.Segment> left, right;

    public TrajectoryPair(String path) {
        try {
            String leftpath = "home/lvuser/deploy/output" + path + ".left.pf1.csv";
            String rightpath = "home/lvuser/deploy/output" + path + ".right.pf1.csv";

            File left = new File(leftpath), right = new File(rightpath);
            Scanner leftStream = new Scanner(left), rightStream = new Scanner(right);

            ArrayList<Trajectory.Segment> segmentsLeft = new ArrayList<Trajectory.Segment>();
            ArrayList<Trajectory.Segment> segmentsRight = new ArrayList<Trajectory.Segment>();

            // skip header
            leftStream.nextLine(); 
            rightStream.nextLine();

            // fill segment array lists w/ csv data
            while (leftStream.hasNext()) {
                String leftLine = leftStream.nextLine();
                String rightLine = rightStream.nextLine();
                String[] leftLineSplit = leftLine.split(",");
                String[] rightLineSplit = rightLine.split(",");

                double dt = Double.parseDouble(leftLineSplit[0]);

                double xLeft = Double.parseDouble(leftLineSplit[1]);
                double yLeft = Double.parseDouble(leftLineSplit[2]);
                double positionLeft = Double.parseDouble(leftLineSplit[3]);
                double velocityLeft = Double.parseDouble(leftLineSplit[4]);
                double accelerationLeft = Double.parseDouble(leftLineSplit[5]);
                double jerkLeft = Double.parseDouble(leftLineSplit[6]);
                double headingLeft = Double.parseDouble(leftLineSplit[7]);

                double xRight = Double.parseDouble(rightLineSplit[1]);
                double yRight = Double.parseDouble(rightLineSplit[2]);
                double positionRight = Double.parseDouble(rightLineSplit[3]);
                double velocityRight = Double.parseDouble(rightLineSplit[4]);
                double accelerationRight = Double.parseDouble(rightLineSplit[5]);
                double jerkRight = Double.parseDouble(rightLineSplit[6]);
                double headingRight = Double.parseDouble(rightLineSplit[7]);

                segmentsLeft.add(new Trajectory.Segment(dt, xLeft, yLeft, positionLeft, velocityLeft, accelerationLeft, jerkLeft, headingLeft));
                segmentsRight.add(new Trajectory.Segment(dt, xRight, yRight, positionRight, velocityRight, accelerationRight, jerkRight, headingRight));
            }

            leftStream.close();
            rightStream.close();
        } catch (FileNotFoundException e) {}
    }

    public TrajectoryPair(ArrayList<Trajectory.Segment> left, ArrayList<Trajectory.Segment> right) {
        this.left = left;
        this.right = right;
    }

    public int getLength() {
        return getLeft().size();
    }

    public ArrayList<Trajectory.Segment> getLeft() {
        return left;
    }

    public ArrayList<Trajectory.Segment> getRight() {
        return right;
    }

    public double getAvgAngle(int i) {
        return (left.get(i).heading + right.get(i).heading) / 2;
    }
}