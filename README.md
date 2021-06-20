# Gapstars Showcase

### How to run the sample project?

There are a couple of ways you can jump start:

* Run from Dockerized container
* Build the source and run from your favourite IDE

## Prerequisites

* just create an empty database on your MySQL server with name 'star' (or any name if you're willing to mod the properties)
* What about tables and data? It's a magic :D Just create empty database..

## Run from Dockerized container

* You must have docker installed and configured
* just run below command (remember to check the database name, username, password etc)

```shell
docker run -e spring.datasource.username=root -e spring.datasource.password=root -e spring.datasource.url=jdbc:mysql://localhost:3306/star --net host ejkpac/showcase:gapstar-0.0.1-SNAPSHOT
```

## Build the source and run from your favourite IDE

* This is self-explanatory just as opening a regular Maven based JAVA project.. No magic :)

```diff
- If your Maven build fails, you probably do not have docker installed in your machine. 
- To fix this you have to set two properties to false in POM file line 32,33!   
```

### Hugs & Bugs

You may reach out to me at : ejkpac@gmail.com
