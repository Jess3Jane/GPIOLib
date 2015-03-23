import com.cubethree.GPIOLib.*;

public class Turn {
	public static void main( String[] args ){
	try{
		Servo s = GPIOLib.allocateServo( 17 );
		s.setAngle( -90 );
		Thread.sleep( 1000 );
		for( double a = -90; a < 90; ++a ){
			s.setAngle( a );			
			Thread.sleep(50);
		}
	}catch(Exception e){
		e.printStackTrace( System.err );
	}finally{
		GPIOLib.cleanup();
	}
	}
}
