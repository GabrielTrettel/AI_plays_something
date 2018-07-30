/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, ddom
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

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
