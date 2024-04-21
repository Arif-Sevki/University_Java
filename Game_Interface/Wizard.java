public class Wizard extends Character {
    private int mp;
    private int maxMp;

    public Wizard(String name, int hp, int mp) {
        super(name, hp);
        this.mp = mp;
        maxMp = mp;

    }

    @Override
    public void attack() {
        System.out.println(this.getName() + " makes a magic attack");
        System.out.println("gives " + getMp() / 5 + " damage");
        setMp(getMp() - 10);
        System.out.println("MP: " + getMp() + " / " + getMaxMp());
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void fireball() {
        System.out.println(this.getName() + " casts fireball");
        System.out.println("gives 20 damage");
        setMp(getMp() - 20);
    }

    public int getMp() {
        return this.mp;
    }

    public int getMaxMp() {
        return this.maxMp;
    }

}
