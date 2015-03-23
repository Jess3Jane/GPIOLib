import com.cubethree.GPIOLib.*;
import java.io.Scanner;

class MotorTest {
	public static void main( String args[] ){
	try{
		PWMPin pwm = GPIOLib.allocatePWM( 17 );
		GPIOPin a = GPIOLib.allocateGPIO( 22 );
		GPIOPin b = GPIOLib.allocateGPIO( 27 );

		pwm.setDuty( 0 );
		a.setValue( 1 );
		b.setDirection( GPIOPinDirection.IN );
		System.out.println( "Starting state: 0 pwm, a on, b off" );

		Scanner s = new Scanner( System.in );
		String line;
		while((line = s.nextLine()) != null){
			String parts[] = line.split(" ");
			if( parts[0].equals( "quit" ) ){ return; }
			else if( parts[0].equals( "pwm" ) ){ pwm.setDuty( Double.parseDouble( parts[1] ) ); }
			else if( parts[0].equals( "a" ) ){
				if( parts[1].equals( "on" ) ){ a.setDirection( GPIOPinDirection.out ); a.setValue( true ); }
				else{ a.setDirection( GPIOPinDirection.in ); }
			}else if( parts[0].equals( "b" ) ){
				if( parts[1].equals( "on" ) ){ b.setDirection( GPIOPinDirection.out ); b.setValue( true ); }
				else{ b.setDirection( GPIOPinDirection.in ); }
			}
		}
	}catch( Exception e ){
		e.printStackTrace();
	}finally{
		GPIOLib.cleanup();
	}
	}
}
