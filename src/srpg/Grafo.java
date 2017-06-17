package srpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.imageio.ImageIO;

public class Grafo {

    private int tl; //total linhas
    private int tc; //total colunas
    private List<Atributo> listaAtributos;
    private double grafo[][]; //matriz de adjacencias
    private char matriz[][]; //matriz dos atributos
    private int somaMatriz; //usa para normalizar as distancias
    private int totalVizinhos;
    private double somaDistViz;
    private double somaDistCent;

    public Grafo() {
        this.tl = 0;
        this.tc = 0;
        this.listaAtributos = new ArrayList<>();
        this.grafo = null;
        this.matriz = null;
        this.somaMatriz = 0;
        this.totalVizinhos = 0;
        this.somaDistViz = 0;
        this.somaDistCent = 0;
    }
    
    public void mostraMatrizAtributos(){
        for(int i = 0; i < tl; i++){
            for(int j = 0; j < tc; j++){
                System.out.print(this.matriz[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void somaDistVizinhos(){
        for(int i = 0; i < this.getListaAtributos().size(); i++){
            for(int j = 0; j < this.getListaAtributos().size(); j++){
                if(this.grafo[i][j] > 0) {
                    this.somaDistViz += this.grafo[i][j];
                    this.totalVizinhos++;
                }
            }
        }
        this.totalVizinhos = totalVizinhos / 2;
        this.somaDistViz = somaDistViz / 2;
    }

    public void gravaImagem(String nome, int x, int y) {
        try {
            BufferedImage img = new BufferedImage(tc, tl, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < tl; i++) {
                for (int j = 0; j < tc; j++) {
                    if (this.matriz[i][j] != ' ') {
                        if (this.matriz[i][j] == '1') {
                            img.setRGB(j, i, 13158600);
                        } else if (this.matriz[i][j] == '0') {
                            img.setRGB(j, i, 16777215);
                        } else {
                            img.setRGB(j, i, 16711680);
                        }
                    }
                }
            }
            img.setRGB(x, y, 16711680);
            File f = new File("C:\\Users\\mateu\\Desktop\\" + nome);
            ImageIO.write(img, "JPEG", f);
            //mostraMatriz();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
        }
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

    public void criaVetorAtributos(char m[][], int somaMatriz, int nLinhas, int nColunas, int xc, int yc) {
        this.tl = nLinhas;
        this.tc = nColunas;
        this.matriz = m;
        this.somaMatriz = somaMatriz;
        this.listaAtributos = new ArrayList<>();
        int k = 0;

        for (int i = 0; i < this.tl; i++) {
            for (int j = 0; j < this.tc; j++) {
                if ((m[i][j] != '0') && (m[i][j] != '1' && (m[i][j] != ' '))) {
//                    System.out.println(xc + " " + yc + "      " + i + " " + j);
                    this.listaAtributos.add(new Atributo(m[i][j], i, j, yc, xc, somaMatriz, k++, nLinhas));
                }
            }
        }
//        this.listaAtributos.add(new Atributo('2', 0, 0, xc, yc, somaMatriz, k++));
//        this.listaAtributos.add(new Atributo('2', tl - 1, 0, xc, yc, somaMatriz, k++));
//        this.listaAtributos.add(new Atributo('2', 0, tc - 1, xc, yc, somaMatriz, k++));
//        this.listaAtributos.add(new Atributo('2', tl - 1, tc - 1, xc, yc, somaMatriz, k++));
//        this.matriz[0][0] = '2';
//        this.matriz[tl-1][0] = '2';
//        this.matriz[0][tc-1] = '2';
//        this.matriz[tl-1][tc-1] = '2';

    }

//    public void copiaMatriz(char m[][]) {
//        this.matriz = new char[tl][tc];
//        for (int i = 0; i < tl; i++) {
//            for (int j = 0; j < tc; j++) {
//                this.matriz[i][j] = m[i][j];
//            }
//        }
//    }
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

//    public void removeRuidos() {
//        //para cada atributo, verifica na matriz se possui tudo 0 em volta, se for, remove da lista.
//        Iterator it = this.listaAtributos.iterator();
//        while (it.hasNext()) {
//            Atributo att = (Atributo) it.next();
//            if (zeroEmVolta(att.getXn(), att.getYn())) {
//                it.remove();
//            }
//        }
//    }
    public void somaDist() {
        for (Atributo att : this.listaAtributos) {
            this.somaDistCent += att.getDistCentroide();
        }
        this.somaDistCent = this.somaDistCent / 2;
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
//                System.out.println(this.grafo[posFilho][posPai]);
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

    public int getTotalVizinhos() {
        return totalVizinhos;
    }

    public void setTotalVizinhos(int totalVizinhos) {
        this.totalVizinhos = totalVizinhos;
    }

    public double getSomaDistViz() {
        return somaDistViz;
    }

    public void setSomaDistViz(double somaDistViz) {
        this.somaDistViz = somaDistViz;
    }

    public double getSomaDistCent() {
        return somaDistCent;
    }

    public void setSomaDistCent(double somaDistCent) {
        this.somaDistCent = somaDistCent;
    }

    

}
