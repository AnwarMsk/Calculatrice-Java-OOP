# Calculatrice Java - Programmation Orientée Objet

Ce projet est une calculatrice simple développée en Java en respectant les principes fondamentaux de la **programmation orientée objet (POO)**. Il prend en charge les opérations de base : addition, soustraction, multiplication et division.

## ✨ Fonctionnalités

- Interface graphique avec JavaFX
- Structure orientée objet (classes, encapsulation, héritage)
- Gestion des erreurs (division par zéro, entrées non valides, etc.)

## 🔧 Technologies utilisées

- Java
- JavaFX
- IDE : VS Code

## 📁 Structure du projet
```
Calculatrice-Java-OOP/
└── src/                              # Code source
    ├── Calculatrice/
    │   ├── 61122.png
    │   ├── Calculatrice.fxml
    │   ├── CalculatriceController.java
    │   ├── DivisionException.java
    │   ├── ExpressionException.java
    │   ├── Historique.java
    │   ├── Historiqueayy.txt
    │   ├── Main.java
    │   ├── OperationComplexe.java
    │   └── OperationException.java
    │
    └── Login/
        ├── Login.fxml
        ├── Login.txt
        └── LoginController.java
```
## 🚀 Exécution a l'aide du IDE

1. Assurez-vous d’avoir JavaFX installé et configurer
2. Via IntelliJ ou Visual Studio :
  - **Configurez JavaFX** dans `Project Structure > Libraries` :
    1. Allez dans **File > Project Structure**.
    2. Dans la section **Libraries**, cliquez sur **+** pour ajouter la bibliothèque JavaFX en sélectionnant le dossier `lib` de JavaFX (par exemple : `javafx-sdk-23.0.1/lib`).
  - **Lancez `Main.java`** pour exécuter l'application.

## 🚀 Exécution a l'aide du fichier ".jar" (recommandé)

 Ouvrez un terminal et compilez le projet avec la commande suivante :
  ```bash
  java --enable-preview --module-path ".\javaFX\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar .\Calculatrice.jar
