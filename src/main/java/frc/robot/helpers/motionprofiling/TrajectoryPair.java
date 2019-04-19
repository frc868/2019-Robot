package frc.robot.helpers.motionprofiling;

import jaci.pathfinder.Trajectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import frc.robot.drivetrain.Drivetrain;

public class TrajectoryPair {
    private ArrayList<Trajectory.Segment> left, right;
    
    public TrajectoryPair(String filename, boolean backwards){
        try {
            String leftpath = "home/lvuser/deploy/output/" + filename + ".left.pf1.csv";
            String rightpath = "home/lvuser/deploy/output/" + filename + ".right.pf1.csv";
            System.out.println("made strings for files");
    
            if(backwards){
                String temp = leftpath;
                leftpath = rightpath;
                rightpath = leftpath;
            }
            
            File left = new File(leftpath), right = new File(rightpath);
            System.out.println("made file objects");
            Scanner leftStream = new Scanner(left), rightStream = new Scanner(right);

            ArrayList<Trajectory.Segment> segmentsLeft = new ArrayList<Trajectory.Segment>();
            ArrayList<Trajectory.Segment> segmentsRight = new ArrayList<Trajectory.Segment>();

            // skip header
            leftStream.nextLine(); 
            rightStream.nextLine();

            // fill segment array lists w/ csv data
            System.out.println("reaading file");
            while (leftStream.hasNext()) {
                System.out.println("reading line ");
                String leftLine = leftStream.nextLine();
                String rightLine = rightStream.nextLine();
                String[] leftLineSplit = leftLine.split(",");
                String[] rightLineSplit = rightLine.split(",");

                double dt = Double.parseDouble(leftLineSplit[0]);

                double xLeft = Double.parseDouble(leftLineSplit[1]);
                double yLeft = Double.parseDouble(leftLineSplit[2]);
                double positionLeft = Double.parseDouble(leftLineSplit[3]);
                double velocityLeft = backwards ? -Double.parseDouble((leftLineSplit[4])) : Double.parseDouble(leftLineSplit[4]);
                double accelerationLeft = Double.parseDouble(leftLineSplit[5]);
                double jerkLeft = Double.parseDouble(leftLineSplit[6]);
                double headingLeft = Double.parseDouble(leftLineSplit[7]);

                double xRight = Double.parseDouble(rightLineSplit[1]);
                double yRight = Double.parseDouble(rightLineSplit[2]);
                double positionRight = Double.parseDouble(rightLineSplit[3]);
                double velocityRight = backwards ? -Double.parseDouble(rightLineSplit[4]) : Double.parseDouble(rightLineSplit[4]);
                double accelerationRight = Double.parseDouble(rightLineSplit[5]);
                double jerkRight = Double.parseDouble(rightLineSplit[6]);
                double headingRight = Double.parseDouble(rightLineSplit[7]);

                segmentsLeft.add(new Trajectory.Segment(dt, xLeft, yLeft, positionLeft, velocityLeft, accelerationLeft, jerkLeft, headingLeft));
                segmentsRight.add(new Trajectory.Segment(dt, xRight, yRight, positionRight, velocityRight, accelerationRight, jerkRight, headingRight));
            }
            System.out.println("finished reading file");

            leftStream.close();
            rightStream.close();

            this.left = segmentsLeft;
            this.right = segmentsRight;
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
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