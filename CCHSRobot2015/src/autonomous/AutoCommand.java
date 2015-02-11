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
	
	public void AutoCommand( )
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
		while( timer.get( ) < 3 && !done )
		{
			_mechanism.move( -.8 );
		}
		_mechanism.move( 0.0 );
		if( !done )
		{
			_chassis.turn( -90 );
		}
		timer.stop( );
		timer.reset( );
		timer.start( );
		while( timer.get() < 6.5 && !done )
		{
			_chassis.testDrive( .30 );
		}
		done = true;
		_chassis.stop();
	}

}
