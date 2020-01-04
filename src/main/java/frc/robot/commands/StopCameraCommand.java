package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.CameraDriveSystem;

/**
 *
 */
public class StopCameraCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final CameraDriveSystem m_CameraDriveSystem;

    public StopCameraCommand(CameraDriveSystem cameraDriveSystem) {

        m_CameraDriveSystem = cameraDriveSystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(cameraDriveSystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_CameraDriveSystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
}
