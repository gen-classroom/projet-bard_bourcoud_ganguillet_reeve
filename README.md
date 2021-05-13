# Créateur de site statique

## Résumé
Ce projet a été créé dans le cadre du cours génie logiciel dispensé par la haute école d'ingénieurie du canton de Vaud (HEIG-VD)

## Installation release 0.0.2
### Créer votre dossier de travail
1. Télécharger la release statique-0.0.2.jar
2. Déplacer le .jar dans le dossier ou vous voulez créer votre dossier de travail qui contiendra les pages de votre site
3. ouvrir un terminal de commande et accéder au dossier ou se trouve le .jar
4. Exécuter la commande : ```java -jar statique-0.0.2.jar init /nomDeVotreDossier```,
 Si la commande ne marche pas, essayez de mettre ```/nomDeVotreDossier``` entre guillements double.
5. Votre projet a été créé dans votre dossier de travail portant le nom précisé lors de la commande précédente

### Configurer votre environnement de travail
La commande init effectué au point précédent vous a notamment créé un dossier templates`dans le dossier racine de votre site. 
Ce dernier permet de configurer la structure générale de chacune des pages de votre site au moyen de 2 documents html:
- Menu.html
    - Fichier que vous pouvez modifier à souhait représentant le menu des pages de votre site.
- Layout.html
    - Squelette de base pour chacune de vos pages html. Vous avez accès à diverses expressions que vous pouvez insérer dans ce fichier:
        - L'expression ```{{ content }}``` sert à indiquer où le contenu spécifique de chacune de vos pages sera inséré dans ce squelette.
        - L'expression ```{{> menu.html }}``` sert à indiquer où le contenu de menu.html sera inséré dans le squelette
        - L'expression ```{{ site.title }}``` sert à indiquer où le nom de votre site renseigné dans le config.json sera inséré dans le squelette.
        - L'expression ```{{ page.title }}``` sert à indiquer où le nom de la page renseigné dans le fichier markdown de cette dernière sera inséré dans le squelette
### Compiler votre site statique
1. Exécuter la commande : ```java -jar statique-0.0.2.jar build <Dossier racine de votre site> ```
2. Les pages html de votre site statique ont été créés dans un dossier build contenu dans le dossier racine de votre site

### Mettre votre site sur un serveur http
1. Déplacer le .jar dans le dossier racine de votre site
2. Ouvrir un terminal de commande et accéder au dossier racine de votre site statique
3. Exécuter la commande : ```java -jar statique-0.0.2.jar serve```
4. Vous pouvez accéder aux pages de votre site sur ```localhost:8080```

### Supprimer le dossier build
1. Déplacer le .jar dans le dossier crée par l'opération précédente (cette étape sera modifié lors du prochain sprint)
2. Ouvrir un terminal de commande et accéder au dossier racine de votre site statique (dossier contenant le index.md et le config.js)
3. Exécuter la commande : ```java -jar statique-0.0.2.jar clean```
4. Votre dossier build s'il existe a été supprimé

## Pour modifier le programme
Si vous voulez modifiez/améliorez notre code, sentez-vous libre! Il vous suffit de forker notre repo.
### Instructions pour la compilation
Notre repo est un projet maven. Voici les commandes maven à exécuter pour créer un .jar contenant toutes les dépendances du programme
```
mvn clean install
mvn assembly:single
```