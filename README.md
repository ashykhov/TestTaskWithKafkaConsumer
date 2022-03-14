###Installation
You can use 'docker-compose -f docker-compose.yaml up -d' command in root directory to up the db, kafka and zookeeper, and then run the producer and consumer apps.

###Usage
**call-events-producer** app produces event every 2 seconds by default

**call-events-service** consumes events and saves them to db.
    Every 20 seconds it takes unprocessed events and process them by making calls to test endpoint.
    You can watch unprocessed events (that are already in db but still not processed) by visiting http://localhost:8080/info/unprocessed
    
    