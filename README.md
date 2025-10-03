# jeu-rpg (Java)

## Description

Petit projet RPG en Java illustrant plusieurs **Design Patterns (GoF)** :

- Builder
- Decorator
- Singleton
- DAO
- Composite
- Command
- Strategy
- Observer
- MVC (console)

Ce projet est conçu comme un bac à sable pédagogique pour montrer comment combiner différents patterns dans une application de type jeu de rôle.

---

## Arborescence

```
jeu-rpg/
 └── src/
     └── rpg/
         ├── Main.java
         ├── model/        # Personnages, builder, décorateurs
         ├── dao/          # DAO et persistance
         ├── rules/        # Règles globales (Singleton)
         ├── composite/    # Groupes hiérarchiques (Composite)
         ├── command/      # Commandes, stratégie, gestionnaire
         ├── observer/     # Observateurs, journal de combat
         └── mvc/          # MVC console
```

---

## ▶️ Compilation et exécution

1. Se placer dans le dossier `src` :

```bash
cd src
```

2. Compiler :

```bash
javac rpg/**/*.java
```

3. Exécuter :

```bash
java rpg.Main
```

---

## Fonctionnalités démontrées

- Création de personnages avec **Builder** et validation via **Singleton** `GameSettings`.
- Ajout dynamique de pouvoirs avec **Decorator** (Invisibilité, Résistance au feu, Télépathie).
- Sauvegarde/chargement avec **DAO** (`CharacterDAO`).
- Organisation d’équipes/armées avec **Composite** (`GroupComposite`).
- Exécution d’actions avec **Command** (`AttackCommand`, `DefendCommand`), possibilité de replay.
- Différentes stratégies d’attaque via **Strategy**.
- Notifications automatiques via **Observer** (`CombatLog`).
- Interface simple en **MVC** (console).
