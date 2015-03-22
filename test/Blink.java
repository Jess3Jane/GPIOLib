import com.cubethree.GPIOLib.*;

public class Blink {
	public static void main( String[] args ){
	try{
		GPIOPin p = GPIOLib.allocateGPIO( 22 );
		p.setValue(true);
		Thread.sleep(1000);
		p.setValue(false);
	}catch( Exception e ){
		GPIOLib.cleanup();
	}
	}
}
