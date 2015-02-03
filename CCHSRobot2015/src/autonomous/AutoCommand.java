package autonomous;

import org.usfirst.frc.team4550.chassis.Chassis;
import org.usfirst.frc.team4550.mechanism.Mechanism;

import edu.wpi.first.wpilibj.Timer;

public class AutoCommand
{

	private final double FIRST_DISTANCE = 0;
	private final double THIRD_DISTANCE = 0;
	
	private final double POSITION_ONE_LEG_TWO = 0;
	private final double POSITION_TWO_LEG_TWO = 0;
	private final double POSITION_THREE_LEG_TWO = 0;

	public void controlAuto( int position )
	{
		Timer autoTimer = new Timer( );
		
		autoTimer.start();
		while( autoTimer.get() < 1 )
		{
			Mechanism.getInstance().move( .5 );
		}
		autoTimer.stop();
		
		Chassis.getInstance().turn(90);
		
		Chassis.getInstance().driveDistance( FIRST_DISTANCE );
		
		Chassis.getInstance().turn(-90);
		
		if( position == 0 )
		{
			Chassis.getInstance().driveDistance( POSITION_ONE_LEG_TWO );
		}
		else if( position == 1 )
		{
			Chassis.getInstance().driveDistance( POSITION_TWO_LEG_TWO );
		}
		else if( position == 2 )
		{
			Chassis.getInstance().driveDistance( POSITION_THREE_LEG_TWO );
		}
		
		Chassis.getInstance().turn(90);
		
		Chassis.getInstance().driveDistance( THIRD_DISTANCE );
	}

}
