public class Weapon extends Item {
    private int power;

    public Weapon(String name, String description, int power) {
        super(name, description);
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        System.out.println("Attacking with " + this.getName());
        System.out.println("Dealing " + this.getPower() + " damage");

    }

}
