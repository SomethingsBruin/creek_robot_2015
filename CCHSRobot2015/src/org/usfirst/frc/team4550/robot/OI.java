package org.usfirst.frc.team4550.robot;

import edu.wpi.first.wpilibj.Joystick;
import static org.usfirst.frc.team4550.robot.RobotMap.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{

	// // CREATING BUTTONS
	// One type of button is a _joystick button which is any button on a _joystick.
	// You create one by telling it which _joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// The Joystick that the driver is using
	private Joystick _joystick;

	public OI()
	{
		// Sets the _joystick to get input from port 0.
		_joystick = new Joystick( 0 );
	}

	public void printAxis()
	{
		// Prints all of the axises on the controller
		for( int i = 0; i < _joystick.getAxisCount(); i++ )
		{
			System.out.printf( "%10s", i + "   " + _joystick.getRawAxis( i ) + "\t" );
		}
		System.out.println();
	}

	public void printButtons()
	{
		// Prints all of the buttons on the controller
		for( int i = 0; i < _joystick.getButtonCount(); i++ )
		{
			System.out.printf( "%10s", i + "   " + _joystick.getRawButton( i ) + "\t" );
		}
		System.out.println();
	}

	public double getAxis( int axis )
	{
		// Returns the value of the specified axis.
		return _joystick.getRawAxis( axis );
	}

	public double getLJoystickXAxis()
	{
		// Returns the left _joystick's horizontal value.
		return _joystick.getRawAxis( L_JOYSTICK_HORIZONTAL );
	}

	public double getLJoystickYAxis()
	{
		// Returns the left _joystick's vertical value, which is inverted.
		return _joystick.getRawAxis( L_JOYSTICK_VERTICAL ) * -1;
	}

	public double getRJoystickXAxis()
	{
		// Returns the right _joystick's horizontal value.
		return _joystick.getRawAxis( R_JOYSTICK_HORIZONTAL );
	}

	public double getRJoystickYAxis()
	{
		// Returns the right _joystick's vertical value, which is inverted.
		return _joystick.getRawAxis( R_JOYSTICK_VERTICAL ) * -1;
	}

	public double getL2R2()
	{
		// Returns the left / right bumpers.
		return _joystick.getRawAxis( L2_R2 );
	}

	public static double normalize( double value )
	{
		Math.pow( value, 5 );
		// Normalizes the value.
		if( value > 1 )
		{
			return 1;
		}
		else if( value < -1 )
		{
			return -1;
		}

		return value;
	}

	public static double normalizeValue( double value )
	{
		// Normalizes the value.
		if( value > 1 )
		{
			return 1;
		}
		else if( value < -1 )
		{
			return -1;
		}

		return value;
	}

}