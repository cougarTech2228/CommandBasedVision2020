package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class CameraDriveSystem extends SubsystemBase {

    public static final int MIN_SERVO_ANGLE = 0;
    public static final int MAX_SERVO_ANGLE = 180;
    public static final int STOP_SERVO_ANGLE = 90;
    public static final int COURSE_SERVO_SPEED_OFFSET = 0;
    public static final int FINE_SERVO_SPEED_OFFSET = 80;

    private Servo leftServo;
    private Servo rightServo;

    private boolean isMoving;
    private int speedOffset = COURSE_SERVO_SPEED_OFFSET;

    public CameraDriveSystem() {

        // You need to register the subsystem to get it's periodic
        // method to be called by the Scheduler
        CommandScheduler.getInstance().registerSubsystem(this);

        leftServo = new Servo(frc.robot.Constants.PWM_PIN_0);
        rightServo = new Servo(frc.robot.Constants.PWM_PIN_1);

        isMoving = false;
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void moveForward() {
        // System.out.println("moveForward");
        isMoving = true;

        int angle = MIN_SERVO_ANGLE + speedOffset;
        leftServo.setAngle(angle);

        int invertedAngle = (int) map(angle, MIN_SERVO_ANGLE, MAX_SERVO_ANGLE, MAX_SERVO_ANGLE, MIN_SERVO_ANGLE);
        rightServo.setAngle(invertedAngle);
    }

    public void moveBackward() {
        // System.out.println("moveBackward");
        isMoving = true;

        int angle = MAX_SERVO_ANGLE - speedOffset;
        leftServo.setAngle(angle);

        int invertedAngle = (int) map(angle, MIN_SERVO_ANGLE, MAX_SERVO_ANGLE, MAX_SERVO_ANGLE, MIN_SERVO_ANGLE);
        rightServo.setAngle(invertedAngle);
    }

    public void stop() {
        isMoving = false;

        leftServo.setAngle(STOP_SERVO_ANGLE);
        rightServo.setAngle(STOP_SERVO_ANGLE);
    }

    private double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public void setSpeedOffset(int newSpeedOffset) {
        speedOffset = newSpeedOffset;
    }
}
