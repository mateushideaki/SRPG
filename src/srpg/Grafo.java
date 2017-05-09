/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.imageio.ImageIO;

/**
 *
 * @author Mateus
 */
public class Grafo {

    private BufferedImage img;

    private int tl; //total linhas
    private int tc; //total colunas
    private int b[][]; //matriz BINARIA da imagem 
    private int xc; //ponto x do centroide
    private int yc; //ponto y do centroide
    private List<Atributo> listaAtributos;
    private double grafo[][]; //matriz de adjacencias
    private char matriz[][]; //matriz dos atributos
    private int somaMatriz; //usa para normalizar as distancias

    public void lerImagem() {
        this.img = null;
        try {
            this.img = ImageIO.read(new File("Perf01Bbbin.png"));
            this.tl = this.img.getHeight();
            this.tc = this.img.getWidth();
        } catch (IOException e) {
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public int[][] getB() {
        return b;
    }

    public void setB(int[][] b) {
        this.b = b;
    }

    public int getXc() {
        return xc;
    }

    public void setXc(int xc) {
        this.xc = xc;
    }

    public int getYc() {
        return yc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public List<Atributo> getListaAtributos() {
        return listaAtributos;
    }

    public void setListaAtributos(List<Atributo> listaAtributos) {
        this.listaAtributos = listaAtributos;
    }

    public double[][] getGrafo() {
        return grafo;
    }

    public void setGrafo(double[][] grafo) {
        this.grafo = grafo;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public int getSomaMatriz() {
        return somaMatriz;
    }

    public void setSomaMatriz(int somaMatriz) {
        this.somaMatriz = somaMatriz;
    }

    public void mostraAtributos() {
        for (int i = 0; i < this.listaAtributos.size(); i++) {
            int soma = 0;
            System.out.println("id: " + i + " Tipo: " + this.listaAtributos.get(i).getId()
                    + " Dist Centroide: " + this.listaAtributos.get(i).getDistCentroide()
                    + " Ang Centroide: " + this.listaAtributos.get(i).getAng()
                    + " x: " + this.listaAtributos.get(i).getXn()
                    + " y: " + this.listaAtributos.get(i).getYn());
            for (int j = 0; j < this.listaAtributos.size(); j++) {
                if (this.grafo[i][j] > 0) {
                    soma++;
                    System.out.print(j + "  ");
                }
            }
            System.out.println("num vizinhos " + soma);
            System.out.println("");

        }
    }

    public void mostraMatriz() {
        for (int i = 0; i < this.tl; i++) {
            for (int j = 0; j < this.tc; j++) {
                System.out.print(b[i][j]);
            }
            System.out.println("");
        }
    }

    public void computaCentroide() {
        int sl, sc, n;
        sl = 0;
        sc = 0;
        n = 0;
        this.b = new int[this.tl][this.tc];
        for (int i = 0; i < this.tl; i++) {
            for (int j = 0; j < this.tc; j++) {
                if (this.img.getRGB(j, i) != -1) { //sistema de coordenadas de imagens ao contrario 
                    this.b[i][j] = 1;
                    sl += i;
                    sc += j;
                    n++;
                }
            }
        }
        this.xc = sl / n; //INVERTIDO NA IMAGEM!!!! (yc, xc)
        this.yc = sc / n;
        //this.img.setRGB(yc, xc, 13414);
        //System.out.println("sl " + sl/n + " sc " + sc/n);
    }

    public void criaVetorAtributos(char m[][], int somaMatriz) {
        copiaMatriz(m);
        this.somaMatriz = somaMatriz;
        this.listaAtributos = new ArrayList<>();
        int k = 0;

        for (int i = 0; i < this.tl; i++) {
            for (int j = 0; j < this.tc; j++) {
                if ((m[i][j] != '0') && (m[i][j] != '1' && (m[i][j] != ' '))) {
                    this.listaAtributos.add(new Atributo(m[i][j], i, j, xc, yc, somaMatriz, k++));
                }
            }
        }
        this.listaAtributos.add(new Atributo('2', 0, 0, xc, yc, somaMatriz, k++));
        this.listaAtributos.add(new Atributo('2', tl - 1, 0, xc, yc, somaMatriz, k++));
        this.listaAtributos.add(new Atributo('2', 0, tc - 1, xc, yc, somaMatriz, k++));
        this.listaAtributos.add(new Atributo('2', tl - 1, tc - 1, xc, yc, somaMatriz, k++));
        this.matriz[0][0] = '2';
        this.matriz[tl-1][0] = '2';
        this.matriz[0][tc-1] = '2';
        this.matriz[tl-1][tc-1] = '2';

    }

    public void copiaMatriz(char m[][]) {
        this.matriz = new char[tl][tc];
        for (int i = 0; i < tl; i++) {
            for (int j = 0; j < tc; j++) {
                this.matriz[i][j] = m[i][j];
            }
        }
    }

    public int buscaPosAtributo(int x, int y) {
        int pos = -1;
        for (int i = 0; i < this.listaAtributos.size(); i++) {
            if (this.listaAtributos.get(i).getXn() == x && this.listaAtributos.get(i).getYn() == y) {
                pos = i;
            }
        }
        return pos;
    }

    public void mostraGrafo() {
        DecimalFormat decimal = new DecimalFormat("00.0000");
        for (int i = 0; i < this.listaAtributos.size(); i++) {
            for (int j = 0; j < this.listaAtributos.size(); j++) {
                System.out.print(decimal.format(this.grafo[i][j]) + " ");
            }
            System.out.println("");
        }
    }

    public int temAtributoEmVolta(int x, int y) {
        int iniX = -1, iniY = -1, fimX = 1, fimY = 1;
        if (x == 0) {
            iniX = 0;
        }
        if (x >= this.tl - 1) {
            fimX = 0;
        }
        if (y == 0) {
            iniY = 0;
        }
        if (y >= this.tc - 1) {
            fimY = 0;
        }
        for (int i = iniX; i <= fimX; i++) {
            for (int j = iniY; j <= fimY; j++) {
                if ((i != 0 || j != 0) && this.matriz[x + i][y + j] != '0' && this.matriz[x + i][y + j] != '1') {
                    return buscaPosAtributo(x + i, y + j);
                }
            }
        }
        return -1;
    }

    public boolean zeroEmVolta(int x, int y) {
        int iniX = -1, iniY = -1, fimX = 1, fimY = 1;
        if (x == 0) {
            iniX = 0;
        }
        if (x >= this.tl - 1) {
            fimX = 0;
        }
        if (y == 0) {
            iniY = 0;
        }
        if (y >= this.tc - 1) {
            fimY = 0;
        }

        for (int i = iniX; i <= fimX; i++) {
            for (int j = iniY; j <= fimY; j++) {
                if ((i != 0 || j != 0) && this.matriz[x + i][y + j] != '0') {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeRuidos() {
        //para cada atributo, verifica na matriz se possui tudo 0 em volta, se for, remove da lista.
        Iterator it = this.listaAtributos.iterator();
        while (it.hasNext()) {
            Atributo att = (Atributo) it.next();
            if (zeroEmVolta(att.getXn(), att.getYn())) {
                it.remove();
            }
        }
    }

    public void criaGrafo() {
        List zeroEmVolta = new ArrayList<>();
        this.grafo = new double[this.listaAtributos.size()][this.listaAtributos.size()];
        Queue pilha = new LinkedList();

        int x = this.listaAtributos.get(0).getXn();
        int y = this.listaAtributos.get(0).getYn();
        int posPai = 0;
        int posFilho;
        ElementoPilha elem = new ElementoPilha(posPai, x, y, 0);
        pilha.add(elem);
        this.matriz[x][y] = '0';

        while (!pilha.isEmpty()) {
            elem = (ElementoPilha) pilha.poll();
            posPai = elem.getPosPai();
            x = elem.getX();
            y = elem.getY();

            posFilho = temAtributoEmVolta(x, y);
            if (posFilho != -1) {
                Atributo filho = this.listaAtributos.get(posFilho);
                this.grafo[posPai][posFilho] = (double) ((elem.getDist() + 1) / somaMatriz);
                this.grafo[posFilho][posPai] = (double) ((elem.getDist() + 1) / somaMatriz);
                ElementoPilha elemPush = new ElementoPilha(posFilho, filho.getXn(), filho.getYn(), 0);
                pilha.add(elemPush);
                this.matriz[elemPush.getX()][elemPush.getY()] = '0';
            } else {
                boolean soTemZero = true;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (this.matriz[x + i][y + j] == '1') {
                            soTemZero = false;
                            ElementoPilha elemPush = new ElementoPilha(posPai, x + i, y + j, (elem.getDist() + 1));
                            pilha.add(elemPush);
                            this.matriz[elemPush.getX()][elemPush.getY()] = '0';
                        }
                    }
                }
                if (soTemZero) {
                    zeroEmVolta.add(elem);
                }
            }
        }//FIM WHILE (PILHA VAZIA)

        //TRATAMENTO PARA O FINAL, QUANDO DUAS LINHAS SE ENCONTRAM A PARTIR DE DOIS ATRIBUTOS DIFERENTES
        for (int i = 0; i < zeroEmVolta.size() - 1; i++) {
            ElementoPilha elem1 = (ElementoPilha) zeroEmVolta.get(i);
            for (int j = i; j < zeroEmVolta.size(); j++) {
                ElementoPilha elem2 = (ElementoPilha) zeroEmVolta.get(j);
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k != 0 || l != 0) {
                            if (elem1.getX() == elem2.getX() + k && elem1.getY() == elem2.getY() + l) {
//                                System.out.println(elem1.getX() + " " + elem1.getY());
//                                System.out.println(elem2.getX() + " " + elem2.getY());
//                                System.out.println("");
//                                System.out.println(elem1.getPosPai() + " " + elem2.getPosPai());
//                                System.out.println("");
//                                System.out.println(elem1.getDist() + " " + elem2.getDist());
                                this.grafo[elem1.getPosPai()][elem2.getPosPai()] = (double) ((elem1.getDist() + elem2.getDist()) / somaMatriz);
                                this.grafo[elem2.getPosPai()][elem1.getPosPai()] = (double) ((elem1.getDist() + elem2.getDist()) / somaMatriz);
                            }
                        }
                    }
                }
            }
        }
    }
}
