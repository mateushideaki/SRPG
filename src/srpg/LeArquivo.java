package srpg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LeArquivo {

    private char matriz[][]; //matriz de atributos
    private int nLinhas;  
    private int nColunas;
    private int somaMatriz; //soma utilizada para normalizar as distancias
    private int nAtributos;


    public void ler(String nomeArq) throws FileNotFoundException {
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
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                int col = 0;
                for(int i = 0; i < linha.length(); i++){
                    if(linha.charAt(i) != ' '){
                        matriz[iter][col++] = linha.charAt(i);
                    }
                }
                iter++;
            }
            //mostraMatriz();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
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

    
    public void somaMatriz(){
        for (int i = 0; i < this.nLinhas; i++) {
            for(int j = 0; j < this.nColunas; j++){
                if(this.matriz[i][j] != '0'){
                    this.somaMatriz++;
                    if(this.matriz[i][j] != '1'){
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
   
    
}
