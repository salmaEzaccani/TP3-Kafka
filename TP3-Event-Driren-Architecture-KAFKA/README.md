## Salima EL HOU SDIA 2

# 1- Utilisation de kafka 

<img src="captures/1-kafka.png">


# 2- Utilisation de Docker avec Kafka

## Étapes d'utilisation

### 1. Créer le fichier `docker-compose.yml`

<img src="captures/1-dockerCompose.png">

### 2. Démarrer les conteneurs Docker

Dans le répertoire où se trouve votre fichier `docker-compose.yml`, exécutez la commande suivante pour démarrer les conteneurs Docker :

```bash
docker-compose up -d
```
<img src="captures/2-docker up.png">

### 3. Tester avec Kafka-console-producer et Kafka-console-consumer


#### Tester avec kafka-console-producer

Pour envoyer des messages à un topic Kafka, utilisez la commande suivante dans le conteneur :

```bash
kafka-console-producer --broker-list localhost:9092 --topic test
```
<img src="captures/producer.png">

#### Tester avec kafka-console-consumer

Pour lire les messages depuis un topic Kafka, exécutez la commande suivante :

```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
```
<img src="captures/cunsumer.png">

# 3- Projet avec Kafka et Spring Cloud Streams

### PageEvent :

<img src="captures/PageEvent.png">


### PageEventRestController :

<img src="captures/PageEventRestController.png">


### PageEventRestController :

<img src="captures/PageEventRestController.png">

# Result :

<img src="captures/result.png">

Console : 
<img src="captures/pageEventConsole.png">

<img src="captures/consumerR2.png">

Configure bindings pour pageEventFunction -> lire a partir de R1  &  Ecrire en topic R3 :

<img src="captures/FunctionInOut.png">