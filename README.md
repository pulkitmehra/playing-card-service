# playing-card-service

#Deployment
1. Make sure that machine has JDK8 & Maven installed
2. git clone repository
3. Go to root of project  i.e. pom.xml
4. Run (any of the commands below i.e. 5, 6 or 7)
5. For default deployment(randomized shuffling) run <b>mvn clean install spring-boot:run</b> 
6. For explicit randomized shuffling run <b>mvn clean install spring-boot:run -Dspring.profiles.active=randomized-shuffle</b>
7. For explicit splitting-interleaving shuffling run <b>mvn clean install spring-boot:run -Dspring.profiles.active=splitting-interleaving</b>

# Rest API

1. For get all decks : GET - http://localhost:8080/playing-card-service/api/services/1.0/deck/all
2. For get by name   : GET - http://localhost:8080/playing-card-service/api/services/1.0/deck/deckname/{deckname}
3. For add a new deck : PUT - http://localhost:8080/playing-card-service/api/services/1.0/deck/deckname/{deckname}
4. For shuffle : POST - http://localhost:8080/playing-card-service/api/services/1.0/deck/shuffle?deckname={deckname}
5. For removing a deck : DELETE - http://localhost:8080/playing-card-service/api/services/1.0/deck/deckname/{deckname}
