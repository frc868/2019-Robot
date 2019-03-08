package frc.robot.sensors.camera;

public class VisionData {
    private String rawData;
    private double distance, position, angle;
    private final int WIDTH  = 320, HEIGHT = 240;

    public VisionData(String newData) {
        try {
            if (newData != null && !newData.equals("")) {  
                rawData = newData;      
                if (hasTarget()) {
                    String[] dataArray = rawData.split(",");

                    distance = Double.parseDouble(dataArray[0]);
                    position = Double.parseDouble(dataArray[1]) - WIDTH/2;
                    angle = Double.parseDouble(dataArray[2]);
                }
            }
        } catch (Exception e) {}
    }

    public String getRawData() {
        return rawData != null ? rawData : ",,";
    }

    public boolean hasTarget() {
        return !getRawData().contains(",,");
    }

    public double getDistance() {
        return distance;
    }

    public double getPosition() {
        return position;
    }

    public double getAngle() {
        return angle;
    }
    

}