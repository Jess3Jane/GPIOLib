package com.cubethree.GPIOLib;
import java.util.ArrayList;

public class GPIOLib {

	public final static int[] VALID_PINS = { 4, 17, 18, 21, 22, 23, 24, 25 };

	private static ArrayList<GPIOContainer> allocatedPins;
	private static boolean initalized = false;

	private static int attemptToAllocate( int pin ) throws GPIOException{
		initalize();
		GPIOContainer obj = null;
		for( int i = 0; i < allocatedPins.size(); ++i ){
			obj = allocatedPins.get( i );
			if( obj.pin == pin ){
				if(obj.gpioObject == null){return i;}else{throw new PinAlreadyExportedException(pin);}			
			}
		}
		throw new InvalidPinException( pin );
	}

	public static GPIOPin allocateGPIO( int pin ) throws GPIOException{
		int i = attemptToAllocate( pin );

		GPIOPin gpio = new GPIOPin();
		gpio.export( pin );
		allocatedPins.get(i).gpioObject = gpio;
		return gpio;
	}

	public static PWMPin allocatePWM( int pin ) throws GPIOException{
		int i = attemptToAllocate( pin );

		PWMPin pwm = new PWMPin();
		pwm.export( pin );
		allocatedPins.get(i).gpioObject = pwm;
		return pwm;
	}

	public static Servo allocateServo( int pin ) throws GPIOException{
		int i = attemptToAllocate( pin );

		Servo servo = new Servo();
		servo.export( pin );
		allocatedPins.get(i).gpioObject = servo;
		return servo;
	}

	public static void unallocatePin( int pin ){
		initalize();
		GPIOContainer obj = null;
		boolean found = false;
		for( int i = 0; i < allocatedPins.size(); ++i ){
			obj = allocatedPins.get( i );
			if( obj.pin == pin ){ found = true; break; }
		}
		if( !found ){ return; }
		obj.gpioObject.cleanup();
		obj.gpioObject = null;
	}

	public static void initalize(){
		//Don't initalize if we've already initalized
		if( initalized ){ return; }

		//Setup the pin table
		allocatedPins = new ArrayList<GPIOContainer>();
		for( int i = 0; i < VALID_PINS.length; ++i ){
			allocatedPins.add( new GPIOContainer( VALID_PINS[i] ) );
		}

		//Make sure that we don't re-setup the table until we clear the table
		initalized = true;
	}

	public static void cleanup(){
		//Call all the individual cleanup methods
		GPIOContainer obj = null;
		for( int i = 0; i < allocatedPins.size(); ++i ){
			obj = allocatedPins.get(i);
			if( obj.gpioObject != null ){
				obj.gpioObject.cleanup();
			}
		}

		//Call the static cleanup methods
		GPIOPin.staticCleanup();
		PWMPin.staticCleanup();

		//Kill our object, the garbage collect will take care of the rest
		allocatedPins = null;

		//We are no longer initalized
		initalized = false;
	}

}

class GPIOContainer {
	int pin;
	GPIO gpioObject;

	public GPIOContainer( int p ){
		pin = p;
		gpioObject = null;
	}
}
