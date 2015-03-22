package com.cubethree.GPIOLib;

public class GPIOException extends Exception {
	public GPIOException( String exception ){
		super( exception );	
	}
}

class PinAlreadyExportedException extends GPIOException {
	public PinAlreadyExportedException(){
		super( "Pin is already exported" );
	}

	public PinAlreadyExportedException( int pin ){
		super( "Pin " + pin + " is already exported" );
	}
}

class InvalidPinException extends GPIOException {
	public InvalidPinException(){
		super( "Invalid Pin." );
	}

	public InvalidPinException( int pin ){
		super( "Pin " + pin + " is not valid." );	
	}
}

class PinWriteException extends GPIOException {
	public PinWriteException(){
		super( "Could not write to pin" );
	}

	public PinWriteException( int pin ){
		super( "Could not write to pin " + pin );
	}
}

class ObjectAlreadyExportedException extends GPIOException {
	public ObjectAlreadyExportedException(){
		super( "Object already exported" );
	}

	public ObjectAlreadyExportedException( int attempt, int real ){
		super( "Object already exported on pin " + real + ". Can't Export pin " + attempt );
	}
};
