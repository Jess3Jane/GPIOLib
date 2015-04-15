import com.cubethree.GPIOLib.*;
import java.util.Scanner;

class MotorTest {
	public static void main( String args[] ){
	try{
		PWMPin pwm = GPIOLib.allocatePWM( 17 );
		GPIOPin a = GPIOLib.allocateGPIO( 22 );
		GPIOPin b = GPIOLib.allocateGPIO( 27 );

		boolean aValue = false;
		boolean bValue = false;
		double pwmValue = 0;

		pwm.setDuty( 0 );
		a.setDirection( GPIOPinDirection.OUT );
		b.setDirection( GPIOPinDirection.OUT );
		a.setValue( false );
		b.setValue( false );
		System.out.format( "Current Value: A=%b B=%b PWM=%f\n", aValue, bValue, pwmValue );

		Scanner s = new Scanner( System.in );
		String line;
		while((line = s.nextLine()) != null){
			String parts[] = line.split(" ");
			if( parts[0].equals( "quit" ) ){ break; }
			else if( parts[0].equals( "pwm" ) ){ pwm.setDuty( (pwmValue = Double.parseDouble( parts[1] )) ); }
			else if( parts[0].equals( "a" ) ){
				if( parts[1].equals( "on" ) ){ a.setValue( true ); aValue = true; }
				else{ a.setValue( false ); aValue = false; }
			}else if( parts[0].equals( "b" ) ){
				if( parts[1].equals( "on" ) ){b.setValue( true ); bValue = true; }
				else{ b.setValue( false ); bValue = false; }
			}
			System.out.format( "Current Value: A=%b B=%b PWM=%f\n", aValue, bValue, pwmValue );
		}
	}catch( Exception e ){
		e.printStackTrace();
	}finally{
		GPIOLib.cleanup();
	}
	}
}
