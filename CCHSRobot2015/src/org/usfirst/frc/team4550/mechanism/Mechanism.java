package org.usfirst.frc.team4550.mechanism;

import org.usfirst.frc.team4550.controls.CCTalon;
import org.usfirst.frc.team4550.robot.OI;
import org.usfirst.frc.team4550.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;

public class Mechanism
{

	private static Mechanism _instance;
	private CCTalon _mechanismTalon;
	private DigitalInput _upperLimitSwitch;
	private DigitalInput _lowerLimitSwitch;

	private Mechanism()
	{
		_mechanismTalon = new CCTalon( RobotMap.TALON_MECHANISM_PORT, false );
		_upperLimitSwitch = new DigitalInput( RobotMap.UPPER_LIMIT_SWITCH_PORT );
		_lowerLimitSwitch = new DigitalInput( RobotMap.LOWER_LIMIT_SWITCH_PORT );
	}

	public static Mechanism getInstance()
	{
		if( _instance == null )
		{
			_instance = new Mechanism();
		}
		return _instance;
	}

	public void move( double controlInput )
	{
		System.out.println(_upperLimitSwitch.get() + " " + _lowerLimitSwitch.get());
		if( controlInput > 0 && _upperLimitSwitch.get() )// If we need to move down, then set the talon to move down
		{
			_mechanismTalon.set( OI.normalizeValue( controlInput ) );
		}
		else if( controlInput < 0 && _lowerLimitSwitch.get() )// Otherwise, if we need to move up, then set the talon to move up
		{
			_mechanismTalon.set( OI.normalizeValue( controlInput ) );
		}
		else
		// Otherwise, don't let the robot move
		{
			_mechanismTalon.set( -.05 );
		}
	}
}
