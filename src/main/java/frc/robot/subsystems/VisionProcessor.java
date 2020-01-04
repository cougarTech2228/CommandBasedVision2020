
package frc.robot.subsystems;

import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionProcessor extends SubsystemBase {

    public static final String VISION_DATA_TABLE_KEY = "datatable";
    public static final String TABLE_ENTRY_TARGET_STATE = "targState";
    public static final String TABLE_ENTRY_DIST_TO_TARGET = "distTargetIn";
    public static final String TABLE_ENTRY_HORIZ_OFFSET_INCHES = "horzOffToIn";
    public static final int DEFAULT_TABLE_ENTRY_VALUE = 42069666;
    public static final int TARGET_STATE_SEARCHNG = 0;
    public static final int TARGET_STATE_ACQUIRING = 1;
    public static final int TARGET_STATE_LOCKED = 2;
    public static final int MINIMUM_TARGET_DISTANCE = 18;
    public static final int MAXIMUM_TARGET_DISTANCE = 48;
    public static final double FINE_TARGET_TOLERANCE = 1.0;
    public static final double COARSE_TARGET_TOLERANCE = 3.0;

    private NetworkTableInstance visionDataTableInst;
    private NetworkTable visionDataTable;

    private CameraDriveSystem m_cameraDriveSystem;

    // state, 0 for searching, 1 for acquiring, or 2 for locked
    private NetworkTableEntry targStateNTE;
    // distance to target in inches,
    private NetworkTableEntry distTargInNTE;
    // horizontal distance from center of target, positive being to the right
    private NetworkTableEntry horzOffInNTE;
    private boolean inRange = false;

    public VisionProcessor() {

        // You need to register the subsystem to get it's periodic
        // method to be called by the Scheduler
        CommandScheduler.getInstance().registerSubsystem(this);

        m_cameraDriveSystem = RobotContainer.getCameraDriveSystem();

        visionDataTableInst = NetworkTableInstance.getDefault();
        visionDataTable = visionDataTableInst.getTable(VISION_DATA_TABLE_KEY);
        targStateNTE = visionDataTable.getEntry(TABLE_ENTRY_TARGET_STATE);
        distTargInNTE = visionDataTable.getEntry(TABLE_ENTRY_DIST_TO_TARGET);
        horzOffInNTE = visionDataTable.getEntry(TABLE_ENTRY_HORIZ_OFFSET_INCHES);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        if ((getDistanceFromTarget() < MAXIMUM_TARGET_DISTANCE)
                && (getDistanceFromTarget() > MINIMUM_TARGET_DISTANCE)) {
            if (getCameraState() == TARGET_STATE_LOCKED) {
                inRange = true;

                double horizontalOffset = getHorizOffsetFromCenter();
                //System.out.println("horizontalOffset: " + horizontalOffset);

                if (horizontalOffset < ((-1) * (COARSE_TARGET_TOLERANCE))) {
                    if (!m_cameraDriveSystem.getIsMoving()) {
                        m_cameraDriveSystem.setSpeedOffset(CameraDriveSystem.COURSE_SERVO_SPEED_OFFSET);
                        CommandScheduler.getInstance().schedule(RobotContainer.getMoveCameraBackwardCommand());
                    }
                }
                else if (horizontalOffset < ((-1) * (FINE_TARGET_TOLERANCE))) {
                    if (!m_cameraDriveSystem.getIsMoving()) {
                        m_cameraDriveSystem.setSpeedOffset(CameraDriveSystem.FINE_SERVO_SPEED_OFFSET);
                        CommandScheduler.getInstance().schedule(RobotContainer.getMoveCameraBackwardCommand());
                    }
                } else if (horizontalOffset > COARSE_TARGET_TOLERANCE) {
                    if (!m_cameraDriveSystem.getIsMoving()) {
                        m_cameraDriveSystem.setSpeedOffset(CameraDriveSystem.COURSE_SERVO_SPEED_OFFSET);
                        CommandScheduler.getInstance().schedule(RobotContainer.getMoveCameraForwardCommand());
                    }
                } else if (horizontalOffset > FINE_TARGET_TOLERANCE) {
                    if (!m_cameraDriveSystem.getIsMoving()) {
                        m_cameraDriveSystem.setSpeedOffset(CameraDriveSystem.FINE_SERVO_SPEED_OFFSET);
                        CommandScheduler.getInstance().schedule(RobotContainer.getMoveCameraForwardCommand());
                    }
                } else {
                    if (m_cameraDriveSystem.getIsMoving()) {
                        CommandScheduler.getInstance().schedule(RobotContainer.getStopCameraCommand());
                    }
                }
            } else {
                inRange = false;

                if (m_cameraDriveSystem.getIsMoving()) {
                    CommandScheduler.getInstance().schedule(RobotContainer.getStopCameraCommand());
                }
            }
        } else {
            inRange = false;

            if (m_cameraDriveSystem.getIsMoving()) {
                CommandScheduler.getInstance().schedule(RobotContainer.getStopCameraCommand());
            }
        }

        SmartDashboard.putBoolean("In Range", inRange);
        
        SmartDashboard.putNumber("Target State", targStateNTE.getDouble(DEFAULT_TABLE_ENTRY_VALUE));
        
        SmartDashboard.putNumber("Distance to Target (in)", distTargInNTE.getDouble(DEFAULT_TABLE_ENTRY_VALUE));

        SmartDashboard.putNumber("Horiz. Offset in (in)", horzOffInNTE.getDouble(DEFAULT_TABLE_ENTRY_VALUE));
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public double getDistanceFromTarget() {
        return distTargInNTE.getDouble(DEFAULT_TABLE_ENTRY_VALUE);
    }

    public double getHorizOffsetFromCenter() {
        return horzOffInNTE.getDouble(DEFAULT_TABLE_ENTRY_VALUE);
    }

    public int getCameraState() {
        return (int) targStateNTE.getDouble(DEFAULT_TABLE_ENTRY_VALUE);
    }
}
