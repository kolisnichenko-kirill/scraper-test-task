# ScraperTestTask
Test task on writing a scraper for the [leon.bet](https://leon.bet/) website for the Aspira Ltd.

### Built With

* [Java](https://www.java.com/en/) - 3 Billion Devices "Run" Java
* [Maven](https://maven.apache.org/) - Dependency Management
* [jackson](https://github.com/FasterXML/jackson) - The best JSON parser for Java
* [lombok](https://projectlombok.org/) - The Project Lombok


### Run it
mvn clean package exec:java

### Details

The result of the scraper's work is the output to the console of data and all markets on the first event of each top league in all sports from the [leon.bet](https://leon.bet/) website:

Футбол, Англия Премьер-лига<br />
&emsp;Брентфорд - Тоттенхэм Хотспур, 2022-12-26 14:30:00.0, 1970324840997915<br />
&emsp;&emsp;Победитель<br />
&emsp;&emsp;&emsp;1, 3.75, 1970325541416472<br />
&emsp;&emsp;&emsp;X, 3.67, 1970325541416471<br />
&emsp;&emsp;&emsp;2, 1.96, 1970325541416470<br />
&emsp;&emsp;Двойной исход<br />
&emsp;&emsp;&emsp;1X, 1.83, 1970325541416437<br />
&emsp;&emsp;&emsp;12, 1.28, 1970325541416436<br />
&emsp;&emsp;&emsp;Х2, 1.26, 1970325541416435<br />
....
