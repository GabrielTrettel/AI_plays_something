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
        this.label = p.getLabel();
    }

    public void resetOwnership() {
        this.id = 0;
        this.label = ' ';
    }

    public boolean isEmpty() {
        if (this.id == 0)
            return true;
        else
            return false;
    }

}
