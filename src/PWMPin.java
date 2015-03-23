package com.cubethree.GPIOLib;
import java.io.File;
import java.io.FileOutputStream;

public class PWMPin extends GPIO {
	public static final double TIME_PER_CYCLE = 10;
	private static FileOutputStream PWMFile;

	public PWMPin(){
		exported = false;
		exportedPin = -1;
	}

	protected void export( int pin ) throws GPIOException{
		//Don't export the pin if the object is already exported to something
		if( exported ){ throw new ObjectAlreadyExportedException( pin, exportedPin ); }

		//Make sure the PWM file is open
		if( PWMFile == null ){
			try{
				PWMFile = new FileOutputStream( new File( "/dev/pi-blaster" ) );
			}catch( Exception e ){
				throw new PinWriteException( pin );
			}
		}

		//No real exporting needed here, we just write it to zero to ensure that it's allocated to pi-blaster
		try{
			PWMFile.write( (pin + "=0").getBytes() );
		}catch( Exception e ){
			throw new PinWriteException( pin );
		}

		exported = true;
		exportedPin = pin;
	}

	protected void unexport() throws GPIOException{
		//If it's not exported we don't have to release it
		if( !exported ){ return; }

		//Make sure the file hasn't been closed for whatever reason
		if( PWMFile == null ){
			try{
				PWMFile = new FileOutputStream( new File( "/dev/pi-blaster" ) );
			}catch( Exception e ){
				throw new PinWriteException( exportedPin );
			}
		}

		//Release the pin
		try{
			PWMFile.write( ("release " + exportedPin).getBytes() );
		}catch( Exception e ){
			throw new PinWriteException( exportedPin );
		}

		exported = false;
		exportedPin = -1;
	}

	protected void cleanup(){ try{ unexport(); }catch( Exception e ){;} }

	protected static void staticCleanup(){ try{ PWMFile.close(); }catch( Exception e ){;} }

	public void setDuty( double duty ){
		//Can't set the duty if we aren't exported (well, we can, but shouldn't)
		if( !exported ){ return; }

		//Make sure the file is open
		if( PWMFile == null ){
			try{ PWMFile = new FileOutputStream( new File( "/dev/pi-blaster" ) ); }catch( Exception e ){return;}
		}

		//Write to the file
		try{ PWMFile.write( (exportedPin + "=" + duty).getBytes() ); }catch( Exception e ){;}
	}
}
