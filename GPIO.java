public abstract class GPIO {
	protected boolean exported;
	protected int exportedPin; 
	protected abstract void export( int pin ) throws GPIOException;
	protected abstract void unexport() throws GPIOException;
	protected abstract void cleanup();
}
