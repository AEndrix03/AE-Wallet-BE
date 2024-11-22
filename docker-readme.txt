Inizializzare il cluster così:

docker swarm init
docker stack deploy -c docker-compose.yml ae-wallet-be


Buildare l'immagine
docker build -t ae-wallet-be .

Chiudi il cluster:
docker swarm leave --force