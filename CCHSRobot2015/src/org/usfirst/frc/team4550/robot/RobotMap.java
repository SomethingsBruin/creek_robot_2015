package org.usfirst.frc.team4550.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static final int L_JOYSTICK_HORIZONTAL = 0;
	public static final int L_JOYSTICK_VERTICAL = 1;
	public static final int L2_R2 = 2;
	public static final int R_JOYSTICK_HORIZONTAL = 3;
	public static final int R_JOYSTICK_VERTICAL = 4;

	public static final int X_BUTTON = 1;
	public static final int O_BUTTON = 2;
	public static final int SQUARE_BUTTON = 3;
	public static final int TRIANGLE_BUTTON = 4;
	public static final int L1_BUTTON = 5;
	public static final int R1_BUTTON = 6;
	public static final int DOWN_BUTTON = 7;
	public static final int RIGHT_BUTTON = 8;
	public static final int LEFT_BUTTON = 9;
	public static final int UP_BUTTON = 10;
	public static final int L3_BUTTON = 11;
	public static final int R3_BUTTON = 12;

	public static final int LEFT_TALON_PORT = 0;
	public static final boolean LEFT_TALON_REVERSE = false;
	public static final int RIGHT_TALON_PORT = 1;
	public static final boolean RIGHT_TALON_REVERSE = true;

	public static final int ENCODER_PORT_A = -1;
	public static final int ENCODER_PORT_B = -2;
	public static final int ENCODER_DISTANCE_PER_PULSE = 0;

	public static final int GYRO_PORT = -1;
	
	public static final int UPPER_LIMIT_SWITCH_PORT = 0;
	public static final int LOWER_LIMIT_SWITCH_PORT = 1;
	public static final int TALON_MECHANISM_PORT = 2;

}
