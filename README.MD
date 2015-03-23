This is just a simple library to access the Raspberry PI's GPIO pins from java
PWM is enabled through the pi-blaster project(https://github.com/sarfata/pi-blaster)

Programs written with GPIOLib must be run with sudo
To use simply include com.cubethree.GPIOLib.*;
To compile the examples:
	javac -cp .:lib/* [file]

To run the examples:
	sudo java -cp .:lib/* [class]
