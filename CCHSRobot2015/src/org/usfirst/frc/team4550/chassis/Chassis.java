package org.usfirst.frc.team4550.chassis;

import org.usfirst.frc.team4550.controls.CCTalon;
import org.usfirst.frc.team4550.robot.OI;
import org.usfirst.frc.team4550.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;

public class Chassis
{

	private CCTalon _left;
	// private CCTalon _leftBottom;
	private CCTalon _right;
	// private CCTalon _rightBottom;
	private static Chassis _instance;

	private Encoder _encoder;// The encoder for the chassis

	private Gyro _gyro;// The gyro for the chassis

	private double _kP = 0.0;// The kP value for correcting drift in the auto drive

	private Chassis()
	{
		_left = new CCTalon( RobotMap.LEFT_TALON_PORT, RobotMap.LEFT_TALON_REVERSE );
		_right = new CCTalon( RobotMap.RIGHT_TALON_PORT, RobotMap.RIGHT_TALON_REVERSE );

		_encoder = new Encoder( RobotMap.ENCODER_PORT_A, RobotMap.ENCODER_PORT_B );
		// The distance per tick of the encoder
		_encoder.setDistancePerPulse( RobotMap.ENCODER_DISTANCE_PER_PULSE );

		_gyro = new Gyro( RobotMap.GYRO_PORT );
	}

	public static Chassis getInstance()
	{
		// Makes sure we only have one chassis and returns this chassis when asked
		if( _instance == null )
		{
			_instance = new Chassis();
		}
		return _instance;
	}

	public void drive( double xVal, double yVal )
	{
		double tmpLeft = yVal; // For left motors

		tmpLeft += xVal;
		tmpLeft = OI.normalize( tmpLeft );

		_left.set( tmpLeft );

		double tmpRight = yVal; // For right motors

		tmpRight += xVal * -1.0;// Makes sure that we reverse one of the wheel sets when turning
		tmpRight = OI.normalize( tmpRight );

		_right.set( tmpRight );
	}

	public void driveDistance( double distance )
	{
		// Resets the encoder and gyro
		_encoder.reset();
		_gyro.reset();

		// While we haven't driven our target distance, keep driving
		while( _encoder.getDistance() < distance )
		{
			drive( _gyro.getAngle() * _kP * -1.0, 1.0 );
		}
	}
	
	public void stop( )
	{
		//	Stops the robot
		_left.set( 0 );
		_right.set( 0);
	}

	public void reset()
	{
		// Resets everything in the chassis
		_encoder.reset();
		_gyro.reset();
		stop( );
	}

}
