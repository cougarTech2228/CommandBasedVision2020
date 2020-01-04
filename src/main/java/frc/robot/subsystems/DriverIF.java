package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;
import frc.robot.OI;

/**
 *
 */
public class DriverIF extends SubsystemBase {

    public DriverIF() {

        // You need to register the subsystem to get it's periodic
        // method to be called by the Scheduler
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

        RobotContainer.getOI();
        if (OI.getXboxDpadLeft()) {
            RobotContainer.getCameraDriveSystem().setSpeedOffset(0);
            CommandScheduler.getInstance().schedule(RobotContainer.getMoveTrolleyBackwardCommand());
        } else {
            RobotContainer.getOI();
            if (OI.getXboxDpadRight()) {
                RobotContainer.getCameraDriveSystem().setSpeedOffset(0);
                CommandScheduler.getInstance().schedule(RobotContainer.getMoveTrolleyForwardCommand());
            }
        }
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
