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
    private int nLinhas;

    public Atributo(char i, int x, int y, int xc, int yc, int somaMatriz, int pos, int nLinhas) {
        this.id = i;
        this.xn = x;
        this.yn = y;
        this.distCentroide = this.calculaDist(xc, yc);
        this.ang = this.calculaAng(xc, yc);
        this.posInicial = pos;
        this.nLinhas = nLinhas;
        this.matched = false;
    }

    public double calculaDist(int xc, int yc) {
//        System.out.println(Math.sqrt(((this.xn - xc) * (this.xn - xc)) + ((this.yn - yc) * (this.yn - yc))));
//        System.out.println(Math.sqrt(((this.xn - xc) * (this.xn - xc)) + ((this.yn - yc) * (this.yn - yc))) / somaMatriz);
//        System.out.println("");
        return (double) Math.sqrt(((this.xn - xc) * (this.xn - xc)) + ((this.yn - yc) * (this.yn - yc)));
    }

    public int calculaAng(int xc, int yc) {
//        double ang = Math.atan2((xc - this.xn), (yc - this.yn)); //o segundo e invertido devido ao eixo y da imagem ser invertido
        int x = this.xn - xc;
        int y = (nLinhas - this.yn) - (nLinhas - yc);
//        int somaQuadrante = 0;
//        if (x < 0 && y < 0) {
//            somaQuadrante = 180;
//        } else if (x < 0) {
//            somaQuadrante = 90;
//        } else if (y < 0) {
//            somaQuadrante = 270;
//        }

        double ang = Math.atan2(/*Math.abs*/(y), /*Math.abs*/(x)); //o segundo e invertido devido ao eixo y da imagem ser invertido
        ang = Math.toDegrees(ang);
        if (ang < 0) {
            ang += 360;
        }

//        System.out.println((int) (Math.round(ang)));
//        System.out.println(((int)(Math.round(ang))) + somaQuadrante);
        return (((int) (Math.round(ang)))/* + somaQuadrante*/);
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
