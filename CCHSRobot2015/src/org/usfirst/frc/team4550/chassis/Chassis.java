package org.usfirst.frc.team4550.chassis;

import org.usfirst.frc.team4550.controls.CCTalon;
import org.usfirst.frc.team4550.robot.OI;
import org.usfirst.frc.team4550.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;

public class Chassis
{

	private CCTalon _left;// The left talons on the robot
	private CCTalon _right;// The right talons on the robot

	private static Chassis _instance;// The instance of the chassis we are using

	private Encoder _leftEncoder;// The encoder for the chassis
	private Encoder _rightEncoder;

	private Gyro _gyro;// The gyro for the chassis

	private double _kP = -0.054;// The kP value for correcting drift in the auto drive; -0.054 before

	private Chassis()
	{
		_left = new CCTalon( RobotMap.LEFT_TALON_PORT, RobotMap.LEFT_TALON_REVERSE );
		_right = new CCTalon( RobotMap.RIGHT_TALON_PORT, RobotMap.RIGHT_TALON_REVERSE );

		_leftEncoder = new Encoder( RobotMap.LEFT_ENCODER_PORT_A, RobotMap.LEFT_ENCODER_PORT_B );
		_leftEncoder.setDistancePerPulse( RobotMap.LEFT_ENCODER_DISTANCE_PER_PULSE );// The distance per tick of the left encoder

		_rightEncoder = new Encoder( RobotMap.RIGHT_ENCODER_PORT_A, RobotMap.RIGHT_ENCODER_PORT_B );
		_rightEncoder.setDistancePerPulse( RobotMap.RIGHT_ENCODER_DISTANCE_PER_PULSE );// The distance per tick of the right encoder

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
		_leftEncoder.reset();
		_rightEncoder.reset();
		_gyro.reset();

		// While we haven't driven our target distance, keep driving
		while( _leftEncoder.getDistance() < distance )
		{
			drive( _gyro. getAngle() * _kP * -1.0, 1.0 );
		}

		stop(); // Stops the robot
	}

	public void stop()
	{
		// Stops the robot
		_left.set( 0 );
		_right.set( 0 );
	}

	public void reset()
	{
		// Resets everything in the chassis
		_leftEncoder.reset();
		_rightEncoder.reset();
		_gyro.reset();
		stop();
	}

	public void turn( double degreesToTurn )
	{
		double timeOut = 2000;
		boolean done = false;

		double kP = 0.623;
		double kI = 0.0347;
		double kD = 0.35;
		double currentTime = System.currentTimeMillis();

		double error = 0.0;
		double prevError = 0.0;
		double errorSum = 0.0;

		while( !done )
		{
			prevError = error;
			error = OI.normalizeValue( ( _gyro.getAngle() - degreesToTurn ) / 100.0 );
			errorSum += error;
			if( errorSum > 5.0 )
			{
				errorSum = 5;
			}
			else if( errorSum < -5.0 )
			{
				errorSum = -5;
			}

			double p = error * kP;
			double i = errorSum * kI;
			double d = ( error - prevError ) * kD;

			double speed = p + i + d;
			if( speed > .35 )
			{
				speed = .35;
			}
			else if( speed < -.35 )
			{
				speed = -.35;
			}

			this.drive( speed, 0.0 );

			if( ( Math.abs( errorSum ) < 0.01 ) || ( System.currentTimeMillis() > currentTime + timeOut ) )
			{
				done = true;
			}

			System.out.println( "Error: " + error + " Error Sum: " + errorSum + " p: " + p + " i: " + i + " d: " + d + " Gyro " + _gyro.getAngle() );
		}

		this.stop();
		done = false;
		_gyro.reset();

	}

	public void drive50()
	{
		_left.set( .5 );
		_right.set( .5 );
	}

	public double getAngle()
	{
		return _gyro.getAngle();
	}

	public void printEncoders()
	{
		System.out.println( "Left " + _leftEncoder.getDistance() + " Right " + _rightEncoder.getDistance() );
	}

	public void testDrive( double speed )
	{
		this.drive( OI.normalize( ( _gyro.getAngle() / 100 ) * _kP * -1.0 ), speed );
	}

}
