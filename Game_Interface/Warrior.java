public class Warrior extends Character {
    private int strength;

    public Warrior(String name, int hp, int strength) {
        super(name, hp);
        this.strength = strength;
    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " makes a physical attack");
        System.out.println("gives " + getStrength() / 6 + " damage");
    }

    public void swordSlash() {
        System.out.println(this.getName() + " slashes with a sword");
        System.out.println("gives 15 damage");
    }

    public int getStrength() {
        return this.strength;
    }
}
