Inizializzare il cluster così:

docker swarm init
echo "ctLACFNbtdI+4q9Qzb7Hxc8lPpfd46OKZFEw6+/O2RE=" | docker secret create encryption_key - (o un altro secret)
docker stack deploy -c docker-compose.yml aewallet

Buildare l'immagine
docker build -t aewallet .

Chiudi il cluster:
docker swarm leave --force