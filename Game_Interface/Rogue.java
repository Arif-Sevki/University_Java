public class Rogue extends Character {
    private int agility;

    public Rogue(String name, int hp, int agility) {
        super(name, hp);
        this.agility = agility;
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " makes a hidden attack");
        System.out.println("gives " + getAgility() / 6 + " damage");
    }

    public void backstab() {
        System.out.println(this.getName() + " backstabs");
        System.out.println("gives 15 damage");
    }

    public int getAgility() {
        return this.agility;
    }
}
