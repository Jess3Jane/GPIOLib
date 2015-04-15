import com.cubethree.GPIOLib.*;
import java.util.Scanner;

public class Turn2 {
	public static double high = 2.5;
	public static double mid = 2;
	public static double low = 1.5;

	public static double bindToRange( double min, double value, double max ){ return Math.max( min, Math.min( max, value ) ); }

	public static void main( String[] args ){
	try{
		PWMPin pwm = GPIOLib.allocatePWM( 17 );
		
		double duty = mid;
		pwm.setDuty( duty );

		Scanner s = new Scanner( System.in );
		String line;
		while((line = s.nextLine()) != null){
			String parts[] = line.split(" ");
			if( parts[0].equals( "quit" ) ){ break; }
			else if( parts[0].equals( "pwm" ) ){
				pwm.setDuty( bindToRange( low, (duty = Double.parseDouble( parts[1] )), high ));
			}
			System.out.format( "Current Value: PWM=%f\n", duty );
		}
	}catch(Exception e){
		e.printStackTrace( System.err );
	}finally{
		GPIOLib.cleanup();
	}
	}
}
