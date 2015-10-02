# Gopigo_Java

## Context

This is an academic project, asked us as a group work in the context of our first year of MSc in computer science, in the University of South Brittany.

## Project Aim

The aim is to use a RPi with a GoPiGo card, to make it reachable and to drive it even in an WAN context.

The steps to reach this goal were : 
  - automate calls to the GoPiGo library
  - deploy a HTTP Server and design a REST API
  - realize a web interface to make calls to this API, and thus control and receive video from Pi

## Request Example

- Increase the speed 
GET : piaddress:port/gopigo/speed/increase

- Change video resolution dynamically
GET : piaddress:port/settings/video/resolution/240p

- Tell the Pi to turn right
GET : piaddress:port/gopigo/move/right

## Screenshot

Here is the interface you get in a navigator, which let you change the speed, start and stop the video, drive the robot, etc...

![alt tag](https://github.com/theplatypus/GoPiGo_Java/blob/master/GoPiGo_J/interface.PNG?raw=true)

## Installation

Get the jar file, in the bin directory, and execute it with administrator rights (to listen on the port). Then, your raspberry is listening at your requests on the port #8080, or you can get the interface on the HTTP port (80).

## API Reference

The Javadoc and the REST API doc are both in the doc directory.

## Contributors



## License

The MIT License (MIT)

Copyright (c) 2015 Barrat David, Bloyet Nicolas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
