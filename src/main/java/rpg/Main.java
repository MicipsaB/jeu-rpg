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
        ConcreteCharacter spiderMan = new CharacterBuilder()
                .setName("Spider-Man")
                .setStrength(10)
                .setAgility(8)
                .setIntelligence(6)
                .build();

        ConcreteCharacter greenGoblin = new CharacterBuilder()
                .setName("Green-Goblin")
                .setStrength(7)
                .setAgility(5)
                .setIntelligence(9)
                .build();

        // Ajout de décorateurs
        ICharacter invisibleHero = new InvisibilityDecorator(spiderMan);
        ICharacter fireVillain = new FireResistDecorator(new TelepathyDecorator(greenGoblin));

        // DAO
        CharacterDAO dao = new CharacterDAO();
        dao.save(new CharacterData(spiderMan));
        dao.save(new CharacterData(greenGoblin));
        System.out.println("DAO findAll: " + dao.findAll().size() + " personnages sauvegardés.");

        // Composite: Groupe
        GroupComposite army = new GroupComposite("Army");
        army.add(new CharacterLeaf(invisibleHero));
        army.add(new CharacterLeaf(fireVillain));
        System.out.println("Army power: " + army.getPowerLevel());

        // Commandes + Observers
        CommandManager manager = new CommandManager();
        CombatLog log = new CombatLog();
        spiderMan.addObserver(log);
        greenGoblin.addObserver(log);

        manager.execute(new AttackCommand(spiderMan, greenGoblin, new BasicAttackStrategy()));
        manager.execute(new DefendCommand(greenGoblin));

        manager.saveHistory("history.log");
        System.out.println("History saved. Replay:");
        manager.replay();
    }
}
