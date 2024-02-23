![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

# docker compose
go to /src/deploy/docker and do "./stack up" or "./stack up -native"

# run jvm multi image
docker run --pull always --name catalog-batch-service --rm -p50600:50600 goafabric/catalog-batch-service:1.2.3-SNAPSHOT

# run native image
docker run --pull always --name catalog-batch-service-native --rm -p50600:50600 goafabric/catalog-batch-service-native:1.2.3-SNAPSHOT -Xmx32m

# run native image arm
docker run --pull always --name catalog-batch-service-native --rm -p50600:50600 goafabric/catalog-batch-service-native-arm64v8:1.2.3-SNAPSHOT -Xmx32m

# loki logger
docker run --pull always --name catalog-batch-service --rm -p50600:50600 --log-driver=loki --log-opt loki-url="http://host.docker.internal:3100/loki/api/v1/push" goafabric/catalog-batch-service:1.2.3-SNAPSHOT