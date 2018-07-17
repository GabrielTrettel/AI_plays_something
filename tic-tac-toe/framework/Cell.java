public class Cell {
    private char label;
    private int id;

    public Cell() {
        this.id = 0;
        this.label = ' ';
    }

    public char getLabel() {
        return this.label;
    }
    public int getID() {
        return this.id;
    }
    public void setOwnership(Player p) {
        this.id = p.getID();
        this.label = (this.id % 2 != 0) ? 'X' : 'O';

    }
    public void resetID() {
        this.id = 0;
    }
    public void resetLabel() {
        this.label = ' ';
    }
}
