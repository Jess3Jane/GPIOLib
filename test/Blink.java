import com.cubethree.GPIOLib.*;

public class Blink {
	public static void main( String[] args ){
	try{
		GPIOPin p = GPIOLib.allocateGPIO( 22 );
		for( int i = 0; i < 10; ++i ){
			p.setValue(true);
			Thread.sleep(1000);
			p.setValue(false);
			Thread.sleep(1000);
		}
	}catch( Exception e ){
		e.printStackTrace( System.err );
	}finally{
		GPIOLib.cleanup();
	}
	}
}
