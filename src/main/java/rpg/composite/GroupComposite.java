package rpg.composite;

import java.util.*;

/**
 * Composite représentant un groupe hiérarchique de personnages ou sous-groupes.
 */
public class GroupComposite implements PartyComponent {
    private String name;
    private List<PartyComponent> members = new ArrayList<>();

    public GroupComposite(String name) { this.name = name; }

    public void add(PartyComponent pc) { members.add(pc); }
    public void remove(PartyComponent pc) { members.remove(pc); }

    @Override
    public double getPowerLevel() {
        return members.stream().mapToDouble(PartyComponent::getPowerLevel).sum();
    }

    @Override
    public String getName() { return name; }
}
