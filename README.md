# Data Crawler
RSS feed crawler storing data in an embedded Derby DB from more than 50 sources.

## Usage
* git clone
* mvn clean install

Run code with Parameters: <database path> <crawle interval in min:double> <randomize interval:true/false>

Example: 

```
> sudo java -cp run-data-crawler-jar-with-dependencies.jar fschmidt.data.crawler.Run /home/pi/data/ 20.0 true
```

## Exemplary Sources
* BBC
* Google News
* Yahoo News
* Reddit
* NY Times
* Reuters
* ACM
* Springer
* Science Magazine
* Google Blog
* IBM Research News Blog
* Zeit
* Stern
* Heise
* FAZ
* Spiegel Online
* Sueddeutsche

## Developer
tba

## Next steps
* merge crawling results from differen databases
* JDBC connection to a variety of different databases
* twitter,instagram,facebook crawler
* Intelligent crawling component, which analyzes the frequency of new created texts per feed and manages the crawling threads
* spider crawler
