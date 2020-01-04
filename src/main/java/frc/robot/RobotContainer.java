/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final static OI m_oi = new OI();

  // Robot Subsystems
  private final static CameraDriveSystem m_cameraDriveSystem = new CameraDriveSystem();
  private final static DriverIF m_driverIF = new DriverIF();
  private final static VisionProcessor m_visionProcessor = new VisionProcessor();

  // Robot Commands
  private final static MoveCameraBackwardCommand m_moveCameraBackwardCommand = new MoveCameraBackwardCommand(
      m_cameraDriveSystem);
  private final static MoveCameraForwardCommand m_moveCameraForwardCommand = new MoveCameraForwardCommand(m_cameraDriveSystem);
  private final static MoveTrolleyBackwardCommand m_moveTrolleyBackwardCommand = new MoveTrolleyBackwardCommand(
      m_cameraDriveSystem);
  private final static MoveTrolleyForwardCommand m_moveTrolleyForwardCommand = new MoveTrolleyForwardCommand(
      m_cameraDriveSystem);
  private final static StopCameraCommand m_stopCameraCommand = new StopCameraCommand(m_cameraDriveSystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  public static OI getOI()
  {
    return m_oi;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

  public static MoveCameraBackwardCommand getMoveCameraBackwardCommand() {
    return m_moveCameraBackwardCommand;
  }

  public static MoveCameraForwardCommand getMoveCameraForwardCommand() {
    return m_moveCameraForwardCommand;
  }

  public static MoveTrolleyBackwardCommand getMoveTrolleyBackwardCommand() {
    return m_moveTrolleyBackwardCommand;
  }

  public static MoveTrolleyForwardCommand getMoveTrolleyForwardCommand() {
    return m_moveTrolleyForwardCommand;
  }

  public static StopCameraCommand getStopCameraCommand() {
    return m_stopCameraCommand;
  }

  public static CameraDriveSystem getCameraDriveSystem() {
    return m_cameraDriveSystem;
  }

  public static DriverIF getDriverIF() {
    return m_driverIF;
  }

  public static VisionProcessor getVisionProcessor() {
    return m_visionProcessor;
  }
}
