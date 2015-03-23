import com.cubethree.GPIOLib.*;
import java.util.Scanner;

class MotorTest {
	public static void main( String args[] ){
	try{
		PWMPin pwm = GPIOLib.allocatePWM( 17 );
		GPIOPin a = GPIOLib.allocateGPIO( 22 );
		GPIOPin b = GPIOLib.allocateGPIO( 21 );

		boolean aValue = true;
		boolean bValue = false;
		double pwmValue = 0;

		pwm.setDuty( 0 );
		a.setValue( true );
		b.setDirection( GPIOPinDirection.IN );

		Scanner s = new Scanner( System.in );
		String line;
		while((line = s.nextLine()) != null){
			String parts[] = line.split(" ");
			if( parts[0].equals( "quit" ) ){ return; }
			else if( parts[0].equals( "pwm" ) ){ pwm.setDuty( (pwmValue = Double.parseDouble( parts[1] )) ); }
			else if( parts[0].equals( "a" ) ){
				if( parts[1].equals( "on" ) ){ a.setDirection( GPIOPinDirection.OUT ); a.setValue( true ); aValue = true; }
				else{ a.setDirection( GPIOPinDirection.IN ); aValue = false; }
			}else if( parts[0].equals( "b" ) ){
				if( parts[1].equals( "on" ) ){ b.setDirection( GPIOPinDirection.OUT ); b.setValue( true ); bValue = true; }
				else{ b.setDirection( GPIOPinDirection.IN ); bValue = false; }
			}
			System.out.format( "Current Value: A=%b B=%b PWM=%f", aValue, bValue, pwmValue );
		}
	}catch( Exception e ){
		e.printStackTrace();
	}finally{
		GPIOLib.cleanup();
	}
	}
}
