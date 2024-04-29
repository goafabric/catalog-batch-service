# docker compose
go to /src/deploy/docker and do "./stack up" or "./stack up -native"

# run jvm multi image
docker run --pull always --name catalog-batch-service --rm -p50600:50600 goafabric/catalog-batch-service:$(grep '^version=' gradle.properties | cut -d'=' -f2)

# run native image
docker run --pull always --name catalog-batch-service-native --rm -p50600:50600 goafabric/catalog-batch-service-native:$(grep '^version=' gradle.properties | cut -d'=' -f2) -Xmx32m

# run native image arm
docker run --pull always --name catalog-batch-service-native --rm -p50600:50600 goafabric/catalog-batch-service-native-arm64v8:$(grep '^version=' gradle.properties | cut -d'=' -f2) -Xmx32m