#!/bin/sh

docker stop $(docker ps -q) 2> /dev/null
docker container rm $(docker container ls -aq) 2> /dev/null
docker volume rm $(docker volume ls -q)$ 2> /dev/null
docker volume prune -f &> /dev/null

docker run -it --privileged -p 16147:16147 -p 6147:6147 -v "$PWD":/player --rm "battlecode/battlecode-2018"
