package org.usfirst.frc.team4550.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4550.chassis.Chassis;
import org.usfirst.frc.team4550.mechanism.Mechanism;
import org.usfirst.frc.team4550.robot.commands.ExampleCommand;
import org.usfirst.frc.team4550.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();

	public static OI _oi;// The operator interface( driver ) of the robot
	public Chassis _chassis;// The robot chassis
	public Mechanism _mechanism;// The robot mechanism

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		// Creates the OI and the Chassis of the robot
		_oi = new OI();
		_chassis = Chassis.getInstance();
		_mechanism = Mechanism.getInstance();

		// instantiate the command used for the autonomous period
		autonomousCommand = new ExampleCommand();
	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
		// chedule the autonomous command (example)
		if( autonomousCommand != null )
		{
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if( autonomousCommand != null )
		{
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit()
	{
		_chassis.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();

		//Limits the turn speed to .25
		double turnAxis = _oi.getLJoystickXAxis();
		if( turnAxis > .25 )
		{
			turnAxis = .25;
		}
		else if( turnAxis < -.25 )
		{
			turnAxis = -.25;
		}
		
		// Drives the chassis by getting the position of the axis of the joystick
		_chassis.drive( _oi.getLJoystickXAxis(), _oi.getLJoystickYAxis() );

		_mechanism.move( _oi.getL2R2() );//Moves the mechanism up and down based on the L2R2 axis
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}

}
