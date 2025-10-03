package rpg;

import rpg.command.AttackCommand;
import rpg.command.BasicAttackStrategy;
import rpg.command.CommandManager;
import rpg.command.DefendCommand;
import rpg.composite.CharacterLeaf;
import rpg.composite.GroupComposite;
import rpg.dao.CharacterDAO;
import rpg.dao.CharacterData;
import rpg.model.*;
import rpg.observer.CombatLog;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=== RPG Project Demo ===");

        // Utilisation du Builder
        ConcreteCharacter personnage1 = new CharacterBuilder()
                .setName("harley")
                .setStrength(10)
                .setAgility(8)
                .setIntelligence(6)
                .build();

        ConcreteCharacter personnage2 = new CharacterBuilder()
                .setName("robin")
                .setStrength(7)
                .setAgility(5)
                .setIntelligence(9)
                .build();

        // Ajout de décorateurs
        ICharacter invisibleHero = new InvisibilityDecorator(personnage1);
        ICharacter fireVillain = new FireResistDecorator(new TelepathyDecorator(personnage2));

        // DAO
        CharacterDAO dao = new CharacterDAO();
        dao.save(new CharacterData(personnage1));
        dao.save(new CharacterData(personnage2));
        System.out.println("DAO findAll: " + dao.findAll().size() + " personnages sauvegardés.");

        // Composite: Groupe
        GroupComposite army = new GroupComposite("Army");
        army.add(new CharacterLeaf(invisibleHero));
        army.add(new CharacterLeaf(fireVillain));
        System.out.println("Army power: " + army.getPowerLevel());

        // Commandes + Observers
        CommandManager manager = new CommandManager();
        CombatLog log = new CombatLog();
        personnage1.addObserver(log);
        personnage2.addObserver(log);

        manager.execute(new AttackCommand(personnage1, personnage2, new BasicAttackStrategy()));
        manager.execute(new DefendCommand(personnage2));

        manager.saveHistory("history.log");
        System.out.println("History saved. Replay:");
        manager.replay();
    }
}
