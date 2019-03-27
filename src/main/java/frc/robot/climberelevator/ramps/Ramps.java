package frc.robot.climberelevator.ramps;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.helpers.subsystems.SubsystemManagerChild;

/**
 * ramps subsystem for climbing
 */
public class Ramps extends SubsystemManagerChild{
    
    private final boolean DEPLOYED_STATE = true;
    private Solenoid actuator;
    
    public Ramps(){
        super("Ramps");
        actuator = new Solenoid(RobotMap.PCM, RobotMap.ClimberElevator.Ramps.ACTUATOR);
    }
    
    /**
     * opens ramp
     */
    public void open(){
        setState(DEPLOYED_STATE);
    }
    
    @Override
    public void initEnabled(){
        close();
    }
    
    /**
     * closes ramp
     */
    public void close(){
        setState(!DEPLOYED_STATE);
    }
    
    @Override
    public void updateSD(){
        SmartDashboard.putBoolean("Ramps: Open?", isOpen());
    }
    
    /**
     * @return true if ramps are open
     */
    public boolean isOpen(){
        return getState() == DEPLOYED_STATE;
    }
    
    /**
     * @return state of ramps
     */
    public boolean getState(){
        return actuator.get();
    }
    
    /**
     * @param state state to set actuator to
     */
    public void setState(boolean state){
        actuator.set(state);
    }
}
