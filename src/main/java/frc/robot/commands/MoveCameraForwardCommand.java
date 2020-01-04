package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CameraDriveSystem;
import frc.robot.RobotContainer;
import frc.robot.Constants;

/**
 *
 */
public class MoveCameraForwardCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final CameraDriveSystem m_cameraDriveSystem;

    public MoveCameraForwardCommand(CameraDriveSystem cameraDriveSystem) {

        m_cameraDriveSystem = cameraDriveSystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_cameraDriveSystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!m_cameraDriveSystem.getIsMoving()) {
            m_cameraDriveSystem.moveForward();
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        double horizOffset = RobotContainer.getVisionProcessor().getHorizOffsetFromCenter();
        if (horizOffset < Constants.VISION_TARGET_TOLERANCE_IN_INCHES) {
            return true;
        }

        return false;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_cameraDriveSystem.stop();
    }
}
