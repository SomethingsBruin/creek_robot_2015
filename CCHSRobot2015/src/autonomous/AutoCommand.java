package autonomous;

import org.usfirst.frc.team4550.chassis.Chassis;
import org.usfirst.frc.team4550.mechanism.Mechanism;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class AutoCommand
{
	
	private Chassis _chassis;
	private Mechanism _mechanism;
	private boolean done = false;
	
	public AutoCommand()
	{
		_chassis = Chassis.getInstance( );
		_mechanism = Mechanism.getInstance( );
		done = false;
	}

	public void controlAuto( )
	{
		LiveWindow.run();
		Timer timer = new Timer();
		timer.start();
		_chassis.reset();
		
		while( timer.get( ) < 2 && !done )
		{
			_mechanism.move( -.5 );
		}
		
		_mechanism.move( 0.0 );
		if( !done )
		{
			_chassis.turn( -90 );
		}
		_chassis.stop( );
		timer.stop( );
		timer.reset( );
		timer.start( );
		while( timer.get() < 3.20 && !done )//3.5
		{
			_chassis.testDrive( .30 );
		}
		done = true;
		_chassis.stop();
	}
	
	public void liftArm( )
	{
		LiveWindow.run();
		Timer timer = new Timer();
		timer.start();
		_chassis.reset();
		while( timer.get( ) < 2 && !done )
		{
			_mechanism.move( -.5 );
		}
		done = true;
	}
	
	public void containerAuto( )
	{
		LiveWindow.run();
		Timer timer = new Timer();
		timer.start();
		_chassis.reset();
		
		while( timer.get( ) < 4.85 && !done )
		{
			_mechanism.move( -.5 );
		}
		
		_mechanism.move( 0.0 );
		if( !done )
		{
			_chassis.turn( 90 );
		}
		_chassis.stop( );
		timer.stop( );
		timer.reset( );
		timer.start( );
		while( timer.get() < 3.90 && !done )//4.25
		{
			_chassis.testDrive( .30 );
		}
		done = true;
		_chassis.stop();
	}
	
	public void goStraight( )
	{
		LiveWindow.run();
		Timer timer = new Timer();
		timer.start( );
		while( timer.get() < 2.25 && !done )
		{
			_chassis.testDrive( .30 );
		}
		done = true;
		_chassis.stop();
	}
	
	public void turn( )
	{
		LiveWindow.run();
		_chassis.reset();
		if( !done )
		{
			_chassis.turn( 90 );
		}
		done = true;
		_chassis.stop();
	}

}
