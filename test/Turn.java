import com.cubethree.GPIOLib.*;

public class Turn {
	public static void main( String[] args ){
	try{
		PWMPin s = GPIOLib.allocatePWM( 17 );
		s.setDuty( 0.015 );
		Thread.sleep( 1000 );
	}catch(Exception e){
		e.printStackTrace( System.err );
	}finally{
		GPIOLib.cleanup();
	}
	}
}
