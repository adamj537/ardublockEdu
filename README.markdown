Note:  I (Adam) have been notified that there is a bug which prevents ArduBlockEdu from working with the Arduino software v.1.6.12 or later.  I've not yet fixed this, but it's on my to-do list.

ArduBlock Education Edition
======
ArduBlock is a Block Programming Language for Arduino. The language and functions model closely to [Arduino Language Reference](http://arduino.cc/en/Reference/HomePage).
This version of ArduBlock is tweaked to more closely resemble the Arduino language, and is meant to teach kids programming basics before transitioning to C or C++.
Some of the changes:
* Block names match the [Arduino Language Reference](http://arduino.cc/en/Reference/HomePage) as much as possible.  For example, the original ArduBlock had a block called "repeat"; this version has a block named "for" - this way kids will get to know the concept of a for loop.
* Block drawers are organized to match the [Arduino Libraries](http://www.arduino.cc/en/Reference/Libraries) as much as possible.
* Code produced is as human-readable as possible.  Variable or function names aren't changed unless absolutely necessary.  All code is indented four spaces.
* Third-party libraries are removed, so kids don't feel as overwhelmed.

Installation
----
After building this, copy the target/ardublock-all.jar to Arduino\tools\ArduBlockTool\tool.  Then open the Arduino IDE, and go to Tools --> ArduBlock Education Edition.

Building The Project
----
This project is written in Java, so you can use [Netbeans](http://www.oracle.com/technetwork/articles/javase/jdk-netbeans-jsp-142931.html) or [Eclipse](https://eclipse.org/) to modify and compile it.  I (Adam) am using Netbeans.  This project also uses a build manager called [Maven](https://maven.apache.org/), which nicely ensures that most dependancies are downloaded to your computer when you compile for the first time.
If you want to modify ArduBlock, and are also using Netbeans, there are a few things you need to do:
* Clone this repository.  Hopefully that's an obvious step :)
* Clone the [openblocks](https://github.com/taweili/openblocks) repository.  Openblocks is the code that makes all the pretty blocky shapes, and must be on your machine.
* Open both projects in Netbeans.  With Netbeans, there's not a central project file you can click to open the project.  Instead, you must first open Netbeans, and use Netbeans to open the project.
* The first time you build, make sure you have an internet connection, as Maven will need to download dependancies.
* Build openblocks.
* Build arduBlock.
** Netbeans will complain about [not knowing what the "main" class is](http://stackoverflow.com/questions/20601845/no-main-class-found-in-netbeans).  To fix that, right-click on your Project in the project explorer, click on Properties --> Run --> Set "Main Class" to "com.ardublock.Main".
** Netbeans may complain about not being able to download the pde-1.0.jar file.  To fix that, go to the Netbeans Project Explorer and navigate to ArduBlock --> Dependancies --> Right-click on pde-1.0.jar and click "Manually install artifact".  Browse to the pde.jar file included in the lib folder of the ardublock repository.

To Do List
-----
Most of the features I'd like to implement have to do with functions with arguments.  There's currently a block that allows you to create your own function with a single argument, and it must be an integer.
* Make it work with the latest version of Arduino (see note above).
* There's a bug in the function-with-argument block that causes a compiler error if you use the default argument.  Fixing this is my top priority.
* Figure out how to support multiple function arguments.  Maybe with a "glue" operator?
* Make variable type change based on what you add to function
* Add help documentation.
* Translate comments into code.
* Fix the "save as picture" feature.

Authors
----
* Adam Johnson adamj537@gmail.com

Authors of the original version:
----
* David Li taweili@gmail.com
* HE Qichen heqichen@gmail.com

License
----
This file is part of ArduBlock.

ArduBlock is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

ArduBlock is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with ArduBlock.  If not, see <http://www.gnu.org/licenses/>.
