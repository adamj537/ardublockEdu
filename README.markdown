ArduBlock Education Edition
======

ArduBlock is a Block Programming Language for Arduino. The language and functions model closely to [Arduino Language Reference](http://arduino.cc/en/Reference/HomePage).
This version of ArduBlock is tweaked to more closely resemble the Arduino language, and is meant to teach kids the programming basics before transitioning to C or C++.
Some of the changes:
* TODO:  Block names match the [Arduino Language Reference](http://arduino.cc/en/Reference/HomePage) as much as possible.  For example, the original ArduBlock had a block called "repeat"; this version has a block named "for" - this way kids will get to know the concept of a for loop.
* TODO:  Code produced is as human-readable as possible.  Variable or function names aren't changed unless absolutely necessary.  All code is indented four spaces.
* TODO:  Block drawers are organized to match the [Arduino Libraries](http://www.arduino.cc/en/Reference/Libraries) as much as possible.
* TODO:  Third-party libraries are removed, so kids don't feel as overwhelmed.

Installation
----
The project is managed by Maven. After checking out the source for the first time, one should run the following to install Arduino's pde.jar into the local repository. 

	$ mvn validate

Usage
----

	$ mvn exec:java -Dexec.mainClass="com.ardublock.Main"

Development
----
Change the /src/main/resources/com/ardublock/block/ardublock_def.xml to add new blocks to ArduBlock

	$ mvn clean package

	$ mvn compile exec:java -Dexec.mainClass="com.ardublock.Main"

The Visual Block environment should show up. Happy Hacking! ;) 

Deploy
----
Edit code of ardublock

	$ mvn clean package

copy the target/ardublock-all.jar to Arduino\tools\ArduBlockTool\tool

Authors
----
* David Li taweili@gmail.com
* HE Qichen heqichen@gmail.com


ToDo
----
* Integrate the [scripting engine](http://java.sun.com/developer/technicalArticles/J2SE/Desktop/scripting/) into the language blocks for code generation

License
----

Copyright (C) 2011 David Li and He Qichen

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
