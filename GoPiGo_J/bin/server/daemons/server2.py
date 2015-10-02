#!/usr/bin/env python 

# This is a basic example for a socket server for the GoPiGo.
# This allows the client to connects can be used to respond to the commands and run the GoPiGo
# the socket server is running on Port 5005 on localhost

# Send a single byte command to the server from the client:
#
# fwd		#Move forward with PID
# motor_fwd		#Move forward without PID
# bwd	#Move back with PID
# motor_bwd		#Move back without PID
# left		#Turn Left by turning off one motor
# left_rot		#Rotate left by running both motors is opposite direction
# right	#Turn Right by turning off one motor
# right_rot	#Rotate Right by running both motors is opposite direction
# stop		#Stop the GoPiGo
# ispd	#Increase the speed by 10
# dspd		#Decrease the speed by 10
# m1  	#Control motor1
# m2     	#Control motor2
# led		#Turn On/Off the LED's
#set_left_speed	#Set the speed of the right motor
#set_right_speed		#Set the speed of the left motor
#en_com_timeout	#Enable communication timeout
#dis_com_timeout		#Disable communication timeout

import gopigo

print "Python Dameon Started"

while True:
	
	data = raw_input()
	gopigo.right()
