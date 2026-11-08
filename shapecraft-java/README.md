# ShapeCraft — Simulation d'usine en Java

**Projet universitaire de Licence 3 Informatique — réalisé en binôme.**

ShapeCraft est une simulation de chaîne de production inspirée des jeux d'automatisation. Le joueur installe des machines sur une grille pour extraire, transporter, transformer et livrer des formes géométriques.

## Fonctionnalités présentes dans le projet
- Placement et orientation de machines sur une grille.
- Extraction de ressources et transfert par tapis roulants.
- Transformations : rotation, découpe, coloration, mélange et empilage.
- Gestion des couches de formes géométriques, de la livraison et du score.
- Interface graphique construite avec **Java Swing**.

## Compétences illustrées
**Java**, programmation orientée objet, architecture modèle/vue-contrôleur, algorithmes, interface graphique et travail en binôme.

## Organisation
- `src/modele/` : logique du jeu, plateau, machines et objets.
- `src/vuecontroleur/` : interface Swing et interactions.
- `src/Main.java` : point d'entrée.
- `data/sprites/` : ressources graphiques utilisées par le code.

## Lancement
Prérequis : **JDK** (Java). Ouvrir le projet dans un IDE Java et lancer `Main.java` depuis la racine du projet (les images sont chargées via des chemins relatifs à `data/`).

En ligne de commande, sur Linux/macOS :
```bash
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out Main
```

> Le projet correspond au travail universitaire remis ; il n'a pas été testé ici dans son interface graphique. Les ressources graphiques proviennent en partie d'un travail inspiré de **shapez.io** ; vérifier leurs conditions de redistribution avant de rendre le dépôt public.

## Crédits
Projet réalisé en binôme dans le cadre de la Licence Informatique de l'Université Claude Bernard Lyon 1. La répartition détaillée des contributions individuelles reste à préciser.
