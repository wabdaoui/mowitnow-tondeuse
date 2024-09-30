Instructions pour générer et exécuter le fichier JAR
Étape 1 : Nettoyer et générer le projet
Assurez-vous que vous êtes dans le répertoire contenant le fichier pom.xml. Utilisez la commande suivante pour nettoyer le projet et générer le fichier JAR :
mvn clean package
Étape 2 : Exécuter le fichier JAR
Ouvrez une fenêtre de commande et exécutez le fichier JAR généré en fournissant le chemin vers le fichier d'entrée comme argument. Voici un exemple de commande :
java -jar tendeuse-0.0.1-SNAPSHOT.jar "C:\DATA\input.txt"
Étape 3 : Vérifier le fichier de sortie
L'application va générer un fichier de sortie nommé output.txt dans un répertoire data au meme emplacement que le jar.