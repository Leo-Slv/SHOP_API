# Use imagens mais estáveis
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Instalar Maven
RUN apk add --no-cache maven

# Copiar pom.xml primeiro (cache layer)
COPY pom.xml .

# Baixar TODAS as dependências (incluindo plugins)
RUN mvn dependency:go-offline -B && \
    mvn dependency:resolve-sources -B && \
    mvn dependency:resolve -B

# Copiar código fonte
COPY src ./src

# Compilar em modo online (permite baixar dependências se necessário)
RUN mvn clean package -DskipTests

# Stage final - runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Instalar netcat para aguardar o postgres
RUN apk add --no-cache netcat-openbsd

# Copiar JAR
COPY --from=build /app/target/SHOP-API-0.0.1-SNAPSHOT.jar app.jar

# Script para aguardar postgres na porta 5432
RUN echo '#!/bin/sh' > wait-for-postgres.sh && \
    echo 'echo "Aguardando PostgreSQL..."' >> wait-for-postgres.sh && \
    echo 'while ! nc -z postgres 5433; do' >> wait-for-postgres.sh && \
    echo '  echo "PostgreSQL ainda não está pronto - aguardando..."' >> wait-for-postgres.sh && \
    echo '  sleep 2' >> wait-for-postgres.sh && \
    echo 'done' >> wait-for-postgres.sh && \
    echo 'echo "PostgreSQL está pronto!"' >> wait-for-postgres.sh && \
    echo 'exec java -jar app.jar' >> wait-for-postgres.sh && \
    chmod +x wait-for-postgres.sh

EXPOSE 5400

CMD ["./wait-for-postgres.sh"]
