# GoPiGo_Java

## Context

This is an academic project, asked us as a group work in the context of our first year of MSc in computer science, in the University of South Brittany

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



## Installation

Provide code examples and explanations of how to get the project.

## API Reference

Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

## Tests

Describe and show how to run the tests with code examples.

## Contributors

Let people know how they can dive into the project, include important links to things like issue trackers, irc, twitter accounts if applicable.

## License

A short snippet describing the license (MIT, Apache, etc.)
