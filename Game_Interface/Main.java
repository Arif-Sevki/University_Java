public class Main {
    public static void main(String[] args) {
        Wizard wizard = new Wizard("Merlin", 100, 50);
        Warrior warrior = new Warrior("Aragorn", 150, 20);
        Rogue rogue = new Rogue("Legolas", 120, 25);

        // Create items
        Weapon sword = new Weapon("Sword", "A sharp sword", 25);
        Weapon staff = new Weapon("Staff", "A magical staff", 15);
        Potion healingPotion = new Potion("Healing Potion", "Restores HP", 30);

        // Add items to characters' inventories
        Inventory wizardInventory = new Inventory(2);
        wizardInventory.addItem(staff);
        wizardInventory.addItem(healingPotion);

        Inventory warriorInventory = new Inventory(1);
        warriorInventory.addItem(sword);

        Inventory rogueInventory = new Inventory(1);
        rogueInventory.addItem(healingPotion);

        // Battle simulation
        System.out.println("Battle begins!");
        wizard.attack();
        warrior.attack();
        warrior.swordSlash();
        rogue.attack();
        rogue.backstab();

        System.out.println("\nUsing items from inventory:");
        wizardInventory.useItem(0); // Use staff
        warriorInventory.useItem(0); // Use sword
        rogueInventory.useItem(0); // Use healing potion

        System.out.println("\nBattle ends!");
    }
}
