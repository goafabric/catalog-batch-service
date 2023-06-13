# docker compose
go to /src/deploy/docker and do "./stack up" or "./stack up -native"

# run jvm multi image
docker run --pull always --name catalog-service --rm -p50900:50900 goafabric/catalog-service:1.0.0

# run native image
docker run --pull always --name catalog-service-native --rm -p50900:50900 goafabric/catalog-service-native:1.0.0 -Xmx32m

# run native image arm
docker run --pull always --name catalog-service-native --rm -p50900:50900 goafabric/catalog-service-native-arm64v8:1.0.0 -Xmx32m

# loki logger
docker run --pull always --name catalog-service --rm -p50900:50900 --log-driver=loki --log-opt loki-url="http://host.docker.internal:3100/loki/api/v1/push" goafabric/catalog-service:1.0.0