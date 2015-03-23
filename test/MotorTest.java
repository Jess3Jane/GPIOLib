import com.cubethree.GPIOLib.*;
import java.util.Scanner;

class MotorTest {
	public static void main( String args[] ){
	try{
		PWMPin pwm = GPIOLib.allocatePWM( 17 );
		GPIOPin a = GPIOLib.allocateGPIO( 22 );
		GPIOPin b = GPIOLib.allocateGPIO( 21 );

		pwm.setDuty( 0 );
		a.setValue( true );
		b.setDirection( GPIOPinDirection.IN );
		System.out.println( "Starting state: 0 pwm, a on, b off" );

		Scanner s = new Scanner( System.in );
		String line;
		while((line = s.nextLine()) != null){
			String parts[] = line.split(" ");
			if( parts[0].equals( "quit" ) ){ return; }
			else if( parts[0].equals( "pwm" ) ){ pwm.setDuty( Double.parseDouble( parts[1] ) ); }
			else if( parts[0].equals( "a" ) ){
				if( parts[1].equals( "on" ) ){ a.setDirection( GPIOPinDirection.OUT ); a.setValue( true ); }
				else{ a.setDirection( GPIOPinDirection.IN ); }
			}else if( parts[0].equals( "b" ) ){
				if( parts[1].equals( "on" ) ){ b.setDirection( GPIOPinDirection.OUT ); b.setValue( true ); }
				else{ b.setDirection( GPIOPinDirection.IN ); }
			}
		}
	}catch( Exception e ){
		e.printStackTrace();
	}finally{
		GPIOLib.cleanup();
	}
	}
}
