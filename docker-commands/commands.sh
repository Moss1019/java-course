
# pull an image
docker pull <image name>

# list images
docker images

# delete an image
docker rmi <image name>

# run an image in a container
docker run <image name>
# remember to add flags to clean up the container, and to allow terminal interactivity
docker run --rm -ti <image name>

# map ports
# -p <host port>:<container port>
docker run --rm -ti -p 8080:8080 -p 8081:80 <image name>
# will map port 8080 on the host to 8080 on the container
# and port 8081 on the host to port 80 on the container
# so we can call the container logic with localhost address e.b. http://localhost:8081
# will serve the web page running on port 80 in the container and
# http://localhost:8080/api/health will call the health endpoint in the container
# mapping ports reserves them, so nothing else on the host will be able to run on these ports
# while the container is running

# map volumes
# -v <host path>:<container path>
docker run --rm -ti -p 8080:8080 -v /var/containers/logs:/var/logs/
# this will map the host directory (/var/containers/logs) to the container directory (/var/logs)
# so that anything that the container is doing in that directory, will be mirrored on the host,
# e.g. wirting log files, these will then be written to the host instead, or reading from the directory
# will read host files instead. This also makes the data persist after the container is destroyed
