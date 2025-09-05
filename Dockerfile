# Utiliser une image Java comme base
FROM openjdk:17-jdk-slim

# Créer un dossier de travail
WORKDIR /app

# Copier le JAR compilé dans le conteneur
COPY target/raquettelover-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel l’application tourne
EXPOSE 8080

# Commande pour lancer l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
