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

import socket
import gopigo

import camera_streamer

# Listen on localhost at port 5005
TCP_IP = '127.0.0.1' 
TCP_PORT = 5005
BUFFER_SIZE = 20

# Create a TCP socket and bind the server to the port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))

cameraStreamer = camera_streamer.CameraStreamer()

print "Python Dameon Started : 127.0.0.1:5005"

while True:
	#Wait for incomming connections
	s.listen(1)
	
	#Accept an incoming connection
	conn, addr = s.accept()
	
	print '\nConnection address:', addr
	while 1:
		#Check the data
		data = conn.recv(BUFFER_SIZE)
		if not data: break	
		print "received data:", data
		if len(data) < 1:
			print ("Invalid command")
			conn.send("Invalid command")

		elif data=='fwd':
			gopigo.fwd()
			conn.send("Moving forward")

		elif data=='stop':
			gopigo.stop()
			conn.send("Stopping")

		elif data=='bwd':
			gopigo.bwd()
			conn.send("Moving back")

		elif data=='left':
			gopigo.left()
			conn.send("Turning left")

		elif data=='left_rot':
			gopigo.left_rot()
			conn.send("Turning left_rot")

		elif data=='right':
			gopigo.right()
			conn.send("Turning right")

		elif data=='right_rot':
			gopigo.right_rot()
			conn.send("Turning right")

		elif data=='i':
			gopigo.increase_speed()
			conn.send("Increase speed")

		elif data=='d':
			gopigo.decrease_speed()
			conn.send("Decrease speed")

		elif data=='ledOn':
			gopigo.led_on()
			conn.send("Led ON")

		elif data=='ledOff':
			gopigo.led_off()
			conn.send("Led OFF")

		elif data=='set_right_speed':
			gopigo.set_right_speed()
			conn.send("Set Right Speed")

		elif data=='set_left_speed':
			gopigo.set_left_speed()
			conn.send("Set Left Speed")

		else:
			print ("Invalid command")
			conn.send("Invalid command")
	conn.close()
