public class Potion extends Item {
    private int heal;

    public Potion(String name, String description, int heal) {
        super(name, description);
        this.heal = heal;
    }

    public int getHeal() {
        return this.heal;
    }

    @Override
    public void use() {
        System.out.println("Using " + this.getName());
        System.out.println("Healing " + this.getHeal() + " HP");
    }

}
