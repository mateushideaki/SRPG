package srpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

public class LeArquivo {

    private char matriz[][]; //matriz de atributos da imagem
    private int nLinhas;
    private int nColunas; 
    private int somaMatriz; //soma utilizada para normalizar as distancias
    private int nAtributos; //numero de atributos
    private int b[][]; //matriz BINARIA da imagem 
    private BufferedImage img;
    private int xc; //x do centroide
    private int yc; //y do centroide

    public void gravaImagem(String nomeArq) {
        File arq = new File(nomeArq);
        Scanner scanner;
        String linha;
        StringTokenizer tok;
        int iter = 0;
        try {
            scanner = new Scanner(new FileReader(arq));
            linha = scanner.nextLine();
            tok = new StringTokenizer(linha);
            this.nLinhas = Integer.parseInt(tok.nextToken());
            this.nColunas = Integer.parseInt(tok.nextToken());
            this.img = new BufferedImage(nLinhas, nColunas, BufferedImage.TYPE_INT_RGB);
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                int col = 0;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) != ' ') {
                        if (linha.charAt(i) == '1') {
                            this.img.setRGB(iter, col, 13158600);
                        } else if (linha.charAt(i) == '0') {
                            this.img.setRGB(iter, col, 16777215);
                        } else {
                            this.img.setRGB(iter, col, 16711680);
                        }
                        col++;
                        //this.b[col++][iter] = Integer.valueOf(String.valueOf(linha.charAt(i)));
                    }
                }
                iter++;
            }
            File f = new File("C:\\Users\\mateu\\Desktop\\imgTeste.jpg");
            ImageIO.write(img, "JPEG", f);
            scanner.close();
            //mostraMatriz();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
        }
    }

    public void leMatrizBinaria(String nomeArq) {
        File arq = new File(nomeArq);
        Scanner scanner;
        String linha;
        StringTokenizer tok;
        int iter = 0;
        try {
            scanner = new Scanner(new FileReader(arq));
            linha = scanner.nextLine();
            tok = new StringTokenizer(linha);
            this.nLinhas = Integer.parseInt(tok.nextToken());
            this.nColunas = Integer.parseInt(tok.nextToken());
            this.b = new int[nLinhas][nColunas];
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                int col = 0;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) != ' ') {
                        if (linha.charAt(i) == '1') {
                            this.b[iter][col] = 1;
                        } else {
                            this.b[iter][col] = 0;
                        }
                        col++;
                    }
                }
                iter++;
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
        }
    }

    public void computaCentroide() throws IOException {
        int sl, sc;
        sl = 0;
        sc = 0;
        this.somaMatriz = 0;
        for (int i = 0; i < this.nLinhas; i++) {
            for (int j = 0; j < this.nColunas; j++) {
                if (this.b[i][j] != 0) {
                    sl += j;
                    sc += i;
                    this.somaMatriz++;
                }
            }
        }

        this.xc = sl / this.somaMatriz;
        this.yc = sc / this.somaMatriz;
//        System.out.println("xc " + xc + " yc " + yc);
//        File f = new File("C:\\Users\\mateu\\Desktop\\Result\\PMV-258-TemplateBWSkel.jpg");
//        this.img = ImageIO.read(f);
//        this.img.setRGB(xc, yc, 255);
//        ImageIO.write(img, "JPEG", f);
    }

    public void leMatrizAtributos(String nomeArq) throws FileNotFoundException {
        File arq = new File(nomeArq);
        Scanner scanner;
        String linha;
        StringTokenizer tok;
        int iter = 0;
        try {
            scanner = new Scanner(new FileReader(arq));
            linha = scanner.nextLine();
            tok = new StringTokenizer(linha);
            this.nLinhas = Integer.parseInt(tok.nextToken());
            this.nColunas = Integer.parseInt(tok.nextToken());
            this.matriz = new char[this.nLinhas][this.nColunas];
            this.nAtributos = 0;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                int col = 0;
                for (int i = 0; i < linha.length(); i++) {
                    if (linha.charAt(i) != ' ') {
                        matriz[iter][col++] = linha.charAt(i);
                        if(linha.charAt(i) != '0' && linha.charAt(i) != '1'){
                            this.nAtributos++;
                        }
                    }
                }
                iter++;
            }
            scanner.close();
            //mostraMatriz();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
        }
    }
    
    public void somaMatriz() {
        for (int i = 0; i < this.nLinhas; i++) {
            for (int j = 0; j < this.nColunas; j++) {
                if (this.matriz[i][j] != '0') {
                    this.somaMatriz++;
                    if (this.matriz[i][j] != '1') {
                        this.nAtributos++;
                    }
                }
            }
        }
    }

    public void mostraMatriz() {
        for (int i = 0; i < this.nLinhas; i++) {
            System.out.println(this.matriz[i]);
        }
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public int getnLinhas() {
        return nLinhas;
    }

    public void setnLinhas(int nLinhas) {
        this.nLinhas = nLinhas;
    }

    public int getnColunas() {
        return nColunas;
    }

    public void setnColunas(int nColunas) {
        this.nColunas = nColunas;
    }

    public int getSomaMatriz() {
        return somaMatriz;
    }

    public void setSomaMatriz(int somaMatriz) {
        this.somaMatriz = somaMatriz;
    }

    public int getnAtributos() {
        return nAtributos;
    }

    public void setnAtributos(int nAtributos) {
        this.nAtributos = nAtributos;
    }

    public int[][] getB() {
        return b;
    }

    public void setB(int[][] b) {
        this.b = b;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
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
    
    

    

}
