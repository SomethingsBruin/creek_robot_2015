package org.usfirst.frc.team4550.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import static org.usfirst.frc.team4550.robot.RobotMap.*;

import org.usfirst.frc.team4550.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{

	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
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

	Joystick joystick;

	public OI()
	{
		// Sets the joystick to get input from port 0.
		joystick = new Joystick( 0 );
	}

	public void printAxis()
	{
		for( int i = 0; i < joystick.getAxisCount(); i++ )
		{
			System.out.printf( "%10s", i + "   " + joystick.getRawAxis( i ) + "\t" );
		}
		System.out.println();
	}

	public void printButtons()
	{
		for( int i = 0; i < joystick.getButtonCount(); i++ )
		{
			System.out.printf( "%10s", i + "   " + joystick.getRawButton( i ) + "\t" );
		}
		System.out.println();
	}

	public double getAxis( int axis )
	{
		// Returns the value of the specified axis.
		return joystick.getRawAxis( axis );
	}

	public double getLJoystickXAxis()
	{
		// Returns the left joystick's horizontal value.
		return joystick.getRawAxis( L_JOYSTICK_HORIZONTAL );
	}

	public double getLJoystickYAxis()
	{
		// Returns the left joystick's vertical value, which is inverted.
		return joystick.getRawAxis( L_JOYSTICK_VERTICAL ) * -1;
	}

	public double getRJoystickXAxis()
	{
		// Returns the right joystick's horizontal value.
		return joystick.getRawAxis( R_JOYSTICK_HORIZONTAL );
	}

	public double getRJoystickYAxis()
	{
		// Returns the right joystick's vertical value, which is inverted.
		return joystick.getRawAxis( R_JOYSTICK_VERTICAL ) * -1;
	}

	public double getL2R2()
	{
		// Returns the left / right bumpers.
		return joystick.getRawAxis( L2_R2 );
	}

	public static double normalize( double value )
	{
		//Nomalizes the
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