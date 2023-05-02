#remove all containers
docker ps -a | grep messenger | awk '{print $1}' | xargs docker stop
docker ps -a | grep messenger | awk '{print $1}' | xargs docker rm

#remove all images
docker images | grep messenger | awk '{print $3}' | xargs docker rmi
