# HKEX Data Grabber

This data grabber is a software that collecting free data from Hong Kong Stock Exchange and store it on both locally and server database for future analysistic purpose.

  - Able to choose to collect Morning Close / Evening Close data
  - Configuration file allows user easily change the database location, username, password and data URLs

### Tech

HKEX Data Grabber uses a number of open source projects to work properly:

* [JAVA] - Very common for everyone
* [Gradle] - Awesome and Convience building tool
* [Eclipse] - A standard IDE for Java
* [MySQL] - For database

### Installation

HKEX Data Grabber requires [Java SDK 8 ](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Gradle] and [MySQL] to run.

### Set up Database Table

```sh 
CREATE TABLE `records` (
    `code` varchar(16) NOT NULL,
    `name` varchar(128) NOT NULL,
    `date` date NOT NULL,
    `market` varchar(16) NOT NULL,
    `sh` int(11) DEFAULT NULL,
    `dollar` int(11) DEFAULT NULL,
    PRIMARY KEY (`code`,`date`,`market`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

### Running
After everthing is installed correctly, please first create and connect to the database. Customise the *config.properties* file in *resources/main/config*.

To build the program please run the following...

```sh
$ cd HKEX_Data_Grabber
$ gradle build
```

Since this program require an argument
For grabbing data for the **Morning Close**...

```sh
$ gradle run -PappArgs="['AM']"
```

For grabbing data for the **Evening Close**...

```sh
$ gradle run -PappArgs="['PM']"
```

### Addition
You can put this into a program schedular so that it can run it automatically for Morning and Evening.

License
----
**Free Software!**

[//]: # 


   [Java]: <http://www.oracle.com/technetwork/java/index.html>
   [Gradle]: <https://gradle.org/>
   [Eclipse]: <http://www.eclipse.org/>
   [MySQL]: <https://www.mysql.com/>