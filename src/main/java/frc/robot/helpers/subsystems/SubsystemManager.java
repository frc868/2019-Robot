/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.helpers.subsystems;

import java.util.ArrayList;

/**
 * Add your docs here.
 */
public abstract class SubsystemManager {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static ArrayList<SubsystemManagerChild> subsystems = new ArrayList<SubsystemManagerChild>();

  public static void init() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.init();
    }
  }

  public static void initSD() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.initSD();
    }
  }

  public static void initDisabled() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.initDisabled();
    }
  }

  public static void initEnabled() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.initEnabled();
    }
  }

  public static void update() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.update();
    }
  }

  public static void updateDisabled() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.updateDisabled();
    }
  }

  public static void updateEnabled() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.updateEnabled();
    }
  }

  public static void updateSD() {
    for (SubsystemManagerChild sys : subsystems) {
      sys.updateSD();
    }
  }
}