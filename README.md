# Créateur de site statique

## Résumé
Ce projet a été créé dans le cadre du cours génie logiciel dispensé par la haute école d'ingénieurie du canton de Vaud (HEIG-VD)

## Installation release 0.0.1
### Créer votre dossier de travail
1. Télécharger la release statique-0.0.1.jar
2. Déplacer le .jar dans le dossier ou vous voulez créer votre dossier de travail qui contiendra les pages de votre site
3. ouvrir un terminal de commande et accéder au dossier ou se trouve le .jar
4. Exécuter la commande : "java -jar statique-0.0.1.jar init /nomDeVotreDossier"
5. Votre projet a été créé dans votre dossier de travail portant le nom précisé lors de la commande précédente

### Compiler votre site statique
1. Déplacer le .jar dans le dossier crée par l'opération précédente (cette étape sera modifié lors du prochain sprint)
2. Ouvrir un terminal de commande et accéder au dossier racine de votre site statique (dossier contenant le index.md et le config.js)
3. Exécuter la commande : "java -jar statique-0.0.1.jar build"
4. Les pages html de votre site statique ont été créés dans le dossier build

### supprimer le dossier build
1. Ouvrir un terminal de commande et accéder au dossier racine de votre site statique (dossier contenant le index.md et le config.js)
2. Exécuter la commande : "java -jar statique-0.0.1.jar clean"
3. Votre dossier build s'il existe a été supprimé