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
public class Atributo {

    private int posInicial;
    private char id; //identificacao (atributo a, b, c, 1, 2, etc)
    private int xn; //xn e yn sao as coordenadas do atributo
    private int yn;
    private double distCentroide; //distancia do atributo ao centroide
    private int ang; //angulo do atributo em relacao ao centroide
    private boolean matched;

    public Atributo(char i, int x, int y, int xc, int yc, int somaMatriz, int pos) {
        this.id = i;
        this.xn = x;
        this.yn = y;
        this.distCentroide = this.calculaDist(xc, yc, somaMatriz) * 1000;
        this.ang = this.calculaAng(xc, yc);
        this.posInicial = pos;
    }

    public double calculaDist(int xc, int yc, int somaMatriz) {
        return Math.sqrt(((this.xn - xc) * (this.xn - xc)) + ((this.yn - yc) * (this.yn - yc))) / somaMatriz;
    }

    public int calculaAng(int xc, int yc) {
        double ang = Math.atan2((xc - this.xn), (this.yn - yc)); //o segundo e invertido devido ao eixo y da imagem ser invertido
        ang = Math.toDegrees(ang);
        return (int) Math.round(ang);
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public int getXn() {
        return xn;
    }

    public void setXn(int xn) {
        this.xn = xn;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public double getDistCentroide() {
        return distCentroide;
    }

    public void setDistCentroide(double distCentroide) {
        this.distCentroide = distCentroide;
    }

    public int getAng() {
        return ang;
    }

    public void setAng(int ang) {
        this.ang = ang;
    }

    public int getPosInicial() {
        return posInicial;
    }

    public void setPosInicial(int posInicial) {
        this.posInicial = posInicial;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

}
