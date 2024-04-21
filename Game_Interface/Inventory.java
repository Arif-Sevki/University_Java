public class Inventory {
    private Item[] items;
    private int count;

    public Inventory(int size) {
        this.items = new Item[size];
        this.count = 0;
    }

    public void addItem(Item item) {
        if (this.count < this.items.length) {
            this.items[this.count] = item;
            this.count++;
        } else {
            System.out.println("Inventory is full");
        }
    }

    public void showItems() {
        for (int i = 0; i < this.count; i++) {
            System.out.println(this.items[i].getName());
        }
    }

    public void useItem(int index) {
        if (index < this.count) {
            this.items[index].use();
            for (int i = index; i < this.count - 1; i++) {
                this.items[i] = this.items[i + 1];
            }
            this.count--;
        } else {
            System.out.println("Invalid index");
        }
    }

}
