# 🐾 CalédoMon

**CalédoMon** est un projet de jeu vidéo développé en **Java** dans le cadre du cours de **Génie Logiciel** du **Master MIAGE**.  
Le jeu s’inspire des mécaniques de combats au tour par tour (type Pokémon), tout en mettant en valeur la **faune de Nouvelle-Calédonie** à travers des créatures appelées *Calédomons*.

---

## 🎯 Objectifs pédagogiques

Ce projet a pour but de mettre en pratique les concepts clés du Génie Logiciel :

- Architecture logicielle claire (MVC)
- Programmation orientée objet avancée
- Utilisation de **design patterns**
- Séparation des responsabilités (modèle, vue, contrôleur)
- Gestion de projet avec **Maven**
- Interface graphique avec **JavaFX**
- Extensibilité et maintenabilité du code

---

## 🧩 Présentation du jeu

- Chaque **Calédomon** représente un animal emblématique de Nouvelle-Calédonie  
- Chaque Calédomon possède :
  - Des statistiques (PV, Attaque, Défense, Vitesse)
  - Un type (Aérien, Terrestre, Aquatique, etc.)
  - **4 capacités uniques** (attaques, buffs, debuffs, capacités spéciales)
- Le joueur choisit son Calédomon et affronte une IA lors de combats au tour par tour
- Interface graphique animée (sélection, combats, effets visuels et sonores)

---

## 🏗️ Architecture du projet

Le projet suit une architecture **MVC (Model – View – Controller)** :

- src/main/java
- ├── model # Logique métier (Animal, Actions, Types, Stats)
- ├── view # Interfaces graphiques JavaFX
- ├── controller # Gestion des interactions et du déroulement du jeu
- └── main # Point d’entrée de l’application


Les ressources graphiques et sonores sont stockées dans :

- src/main/resources
- ├── images
- ├── sounds
- └── styles


---

## 🛠️ Technologies utilisées

- **Java 17+**
- **JavaFX**
- **Maven**
- CSS JavaFX pour le style
- Git pour le versionnement

---

## ▶️ Compilation et exécution (Maven)

### 📌 Prérequis

- Java JDK **17 ou supérieur**
- Maven **3.8+**
- JavaFX (si non inclus dans le JDK)

Vérifier les versions :
```bash
java -version
mvn -version
```

---

## 🔧 Compilation du projet

À la racine du projet :

```bash
mvn clean package -Dskiptests
```

Cela :

- Télécharge les dépendances

- Compile le code

- Génère un .jar dans le dossier target/

---

## ▶️ Exécution du projet
Méthode 1 : via Maven (recommandée)
```bash
mvn javafx:run
```

Méthode 2 : via le JAR généré
```bash
java -jar target/caledomon-1.0-SNAPSHOT.jar
```

---

## 🎨 Fonctionnalités principales

- Sélection interactive des Calédomons

- Panneaux d’informations animés (hover)

- Combats au tour par tour

- Capacités variées (attaques, buffs, debuffs)

- Animations et effets sonores

- Interface responsive et stylisée

## 📚 Contexte académique

Projet réalisé dans le cadre du Master MIAGE – Génie Logiciel
L’objectif principal est de démontrer la maîtrise des bonnes pratiques de conception logicielle, ainsi que la capacité à développer une application complète et structurée.

---

## Générer et consulter la javadoc

À la racine du projet :

```bash
mvn javadoc:javadoc
```

Ouvrir index.html dans :

```bash
target/reports/apidocs/index.html
```

---

## 👥 Auteurs

Quoc-Kim B. et Florian JBDD.

Année : 2025
