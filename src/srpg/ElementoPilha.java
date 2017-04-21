/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srpg;

/**
 *
 * @author Mateus
 */
public class ElementoPilha {
    private int posPai;
    private int x;
    private int y;
    private double dist;

    public ElementoPilha(int posPai, int x, int y, double dist) {
        this.posPai = posPai;
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public int getPosPai() {
        return posPai;
    }

    public void setPosPai(int posPai) {
        this.posPai = posPai;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

}
