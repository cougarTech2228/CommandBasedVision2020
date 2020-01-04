package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.CameraDriveSystem;

/**
 *
 */
public class MoveTrolleyForwardCommand extends InstantCommand {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final CameraDriveSystem m_cameraDriveSystem;

    public MoveTrolleyForwardCommand(CameraDriveSystem cameraDriveSystem) {

        m_cameraDriveSystem = cameraDriveSystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(cameraDriveSystem);
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        if (!m_cameraDriveSystem.getIsMoving()) {
            m_cameraDriveSystem.moveForward();
        }
    }

}
