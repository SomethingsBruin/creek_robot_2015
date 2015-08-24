package org.usfirst.frc.team4550.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4550.chassis.Chassis;
import org.usfirst.frc.team4550.mechanism.Mechanism;
import org.usfirst.frc.team4550.robot.commands.ExampleCommand;
import org.usfirst.frc.team4550.robot.subsystems.ExampleSubsystem;

import autonomous.AutoCommand;

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

	private static OI _oi;// The operator interface( driver ) of the robot
	private Chassis _chassis;// The robot chassis
	private Mechanism _mechanism;// The robot mechanism
	
	private double _maxUpSpeed = 1;
	private double _maxDownSpeed = 1;
	
	private boolean done = false;
	
	private SendableChooser _autoChooser;
	
	int autoChoice = 1;
	
	AutoCommand _autoCommand;
	
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
		
		SmartDashboard.putNumber( "Max Arm Up Speed: ", _maxUpSpeed );
		SmartDashboard.putNumber( "Max Arm Down Speed: ", _maxDownSpeed );
		
		_autoChooser = new SendableChooser( );
		_autoChooser.addObject( "Normal Auto", 0 );
		_autoChooser.addDefault( "Do nothing", 1 );
		_autoChooser.addObject( "Pick up tote" , 2 );
		_autoChooser.addObject( "Container Auto", 3 );
		_autoChooser.addObject(  "Drive Straight" , 4 );
		_autoChooser.addObject( "Turn", 5 );
		
		SmartDashboard.putData( "Auto Chooser", _autoChooser );
		
		
		// instantiate the command used for the autonomous period
		autonomousCommand = new ExampleCommand();
	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	public void autonomousInit()
	{
		// Schedule the autonomous command (example)
		if( autonomousCommand != null )
		{
			autonomousCommand.start();
		}
		_chassis.reset();
		autoChoice = (int) _autoChooser.getSelected( );
		_autoCommand = new AutoCommand( );		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		switch( autoChoice )
		{
			case 0:
				_autoCommand.controlAuto( );
				break;
			case 2:
				_autoCommand.liftArm( );
				break;
			case 3:
				_autoCommand.containerAuto();
				break;
			case 4:
				_autoCommand.goStraight();
				break;
			case 5:
				_autoCommand.turn( );
				break;
			default :
				break;
		}
	}

	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		_maxUpSpeed = SmartDashboard.getNumber( "Max Arm Up Speed: ", _maxUpSpeed );
		_maxDownSpeed = SmartDashboard.getNumber( "Max Arm Down Speed: ", _maxDownSpeed );
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
		done = false;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();

		// Limits the turn speed to .25
		double turnAxis = _oi.getLJoystickXAxis();
		turnAxis /= 4;

		// Drives the chassis by getting the position of the axis of the joystick
		_chassis.drive( _oi.getLJoystickXAxis(), _oi.getLJoystickYAxis() );

		_mechanism.move( OI.normalizeToMaxAndMin( _maxDownSpeed, _maxUpSpeed, _oi.getL2R2() ) );// Moves the mechanism up and down based on the L2R2 axis
		
		/*for( int i = 0; i < 15; i++ )
		{
			System.out.print( i + ": " + _panel.getCurrent( i ) + " " );
		}
		System.out.println( );
		System.out.println( _panel.getTotalCurrent() );*/
		System.out.println( _chassis.getAngle() );
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		System.out.println( _chassis.getAngle() );
		Timer timer = new Timer();
		timer.start();
		_chassis.reset();
		
		while( timer.get( ) < 2 && !done )
		{
			_mechanism.move( -.5 );
		}
		
		_mechanism.move( 0.0 );
		_chassis.reset();
		if( !done )
		{
			_chassis.turn( -90 );
			System.out.println( _chassis.getAngle() );
			done = true;
		}
		_chassis.stop( );
	}

}
