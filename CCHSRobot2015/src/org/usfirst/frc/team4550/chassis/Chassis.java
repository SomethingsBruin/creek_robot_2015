package org.usfirst.frc.team4550.chassis;

import org.usfirst.frc.team4550.controls.CCTalon;
import org.usfirst.frc.team4550.robot.OI;
import org.usfirst.frc.team4550.robot.RobotMap;

public class Chassis
{

	private CCTalon _leftTop;
	private CCTalon _leftBottom;
	private CCTalon _rightTop;
	private CCTalon _rightBottom;
	private Chassis _instance;

	private Chassis()
	{
		_leftTop = new CCTalon( RobotMap.LEFT_TOP_TALON_PORT, RobotMap.LEFT_TOP_TALON_REVERSE );
		_leftBottom = new CCTalon( RobotMap.LEFT_BOTTOM_TALON_PORT, RobotMap.LEFT_BOTTOM_TALON_REVERSE );
		_rightTop = new CCTalon( RobotMap.RIGHT_TOP_TALON_PORT, RobotMap.RIGHT_TOP_TALON_REVERSE );
		_rightBottom = new CCTalon( RobotMap.RIGHT_BOTTOM_TALON_PORT, RobotMap.RIGHT_BOTTOM_TALON_REVERSE );
	}

	public Chassis getInstance()
	{
		if( _instance == null )
		{
			_instance = new Chassis();
		}
		return _instance;
	}

	public void drive( double rightHorizontal, double rightVertical, double leftHorizontal )
	{
		double leftTopSpeed = rightVertical;
		double leftBottomSpeed = rightVertical;
		double rightBottomSpeed = rightVertical;
		double rightTopSpeed = rightVertical;

		if( leftHorizontal < 0 )
		{
			leftTopSpeed *= -1 * leftHorizontal;
			leftBottomSpeed *= -1 * leftHorizontal;
			rightTopSpeed *= leftHorizontal;
			rightBottomSpeed *= leftHorizontal;
		}
		else if( leftHorizontal > 0 )
		{
			leftTopSpeed *= leftHorizontal;
			leftBottomSpeed *= leftHorizontal;
			rightTopSpeed *= -1 * leftHorizontal;
			rightBottomSpeed *= -1 * leftHorizontal;
		}
		else if( rightHorizontal < 0 )
		{
			leftTopSpeed *= rightHorizontal * -0.2;
			leftBottomSpeed *= rightHorizontal * -0.2;
			rightTopSpeed *= rightHorizontal;
			rightBottomSpeed *= rightHorizontal;
		}
		else if( rightHorizontal > 0 )
		{
			leftTopSpeed *= rightHorizontal;
			leftBottomSpeed *= rightHorizontal;
			rightTopSpeed *= rightHorizontal * -0.20;
			rightBottomSpeed *= rightHorizontal * -0.20;
		}
		leftTopSpeed = OI.normalize( leftTopSpeed );
		leftBottomSpeed = OI.normalize( leftBottomSpeed );
		rightTopSpeed = OI.normalize( rightTopSpeed );
		rightBottomSpeed = OI.normalize( rightBottomSpeed );

		_leftTop.set( leftTopSpeed );
		_leftBottom.set( leftBottomSpeed );
		_rightTop.set( rightTopSpeed );
		_rightBottom.set( rightBottomSpeed );
	}
}
