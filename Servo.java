class Servo extends PWMPin {
	public double minMs, maxMs, minAngle, maxAngle;

	public Servo(){
		minMs = 0.75;
		maxMs = 2.25;
		minAngle = -90;
		maxAngle = 90;
	}

	private double bindToRange( double min, double value, double max ){ return Math.max( min, Math.min( max, value ) ); }

	public void setAngle( double angle ){
		setPercent( (angle - minAngle) / (maxAngle - minAngle) );
	}

	public void setPercent( double percent ){
		setDuty( bindToRange( 0, (percent * (maxMs - minMs) + minMs) / TIME_PER_CYCLE, 1 ) );
	}
}
