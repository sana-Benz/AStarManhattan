# Labyrinthe A* et Propagation du Feu

## Description
Ce projet implémente une solution pour déterminer si un prisonnier peut s'échapper d'un labyrinthe avant d'être rattrapé par un feu en propagation. Le problème est résolu en combinant deux algorithmes principaux :

- **Propagation du feu** : Utilisation de l'algorithme de recherche en largeur (BFS) pour modéliser la propagation synchronisée du feu.
- **Recherche du chemin** : Application de l'algorithme A* avec l'heuristique de Manhattan pour trouver le chemin optimal du prisonnier vers la sortie tout en évitant le feu.

## Fonctionnalités
- Détecte si le prisonnier peut atteindre la sortie avant que le feu ne bloque ou envahisse la case.
- Supporte plusieurs labyrinthes définis dans un fichier d'entrée.
- Implémente une gestion synchrone entre la propagation du feu et les mouvements du prisonnier.

## Structure du Projet
Le projet est organisé comme suit :

```
Labyrinthe/
├── src/
│   ├── Labyrinthe.java        # Classe représentant la grille du labyrinthe
│   ├── LabyrintheReader.java  # Lecture des labyrinthes depuis un fichier ou la console
│   ├── LabyrintheSolver.java  # Algorithmes BFS et A*
│   └── Main.java              # Point d'entrée principal du programme
├── bin/                       # Dossier de compilation des fichiers .class
├── maze.txt                   # Exemple de fichier d'entrée
└── README.md                  # Ce fichier
```

## Prérequis
- Java 11 ou version supérieure installé sur votre machine.

## Compilation et Exécution
1. **Compilation** :
   Exécutez la commande suivante pour compiler tous les fichiers Java :
   ```bash
   javac -d bin src/*.java
   ```
   Cette commande crée des fichiers `.class` dans le dossier `bin`.

2. **Exécution** :
   Une fois la compilation terminée, exécutez le programme avec la commande suivante :
   ```bash
   java -cp bin Main <chemin_vers_maze.txt>
   ```
   Exemple :
   ```bash
   java -cp bin Main maze.txt
   ```

   Si vous fournissez un chemin vers un fichier (par exemple `maze.txt`), le programme lira les labyrinthes depuis ce fichier. Sinon, vous pouvez entrer les labyrinthes directement dans le terminal après le lancement du programme.

## Format du Fichier d'Entrée
- La première ligne contient un entier `T`, le nombre de labyrinthes.
- Pour chaque labyrinthe :
  - Une ligne contenant deux entiers `N` et `M` (dimensions de la grille).
  - Les `N` lignes suivantes représentent la grille, où :
    - `.` : Cellule libre
    - `#` : Mur
    - `D` : Position initiale du prisonnier
    - `S` : Sortie
    - `F` : Position initiale du feu

### Exemple
Fichier `maze.txt` :
```
3
4 5
....D
.....
.....
F...S
4 4
...D
....
....
F..S
3 4
###D
####
S..F
```

### Sortie attendue
Pour chaque labyrinthe, le programme imprime une ligne contenant :
- `Y` : Si le prisonnier peut atteindre la sortie à temps.
- `N` : Sinon.

### Exemple de sortie pour l'entrée ci-dessus :
```
Y
N
N
```

## Auteur
**BEN SLAMA Sana**
