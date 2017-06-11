package srpg;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SRPG {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        LeArquivo leitorArquivo = new LeArquivo();
        Grafo grafo = new Grafo();
//        leitorArquivo.gravaImagem("C:\\Users\\mateu\\Dropbox\\TCC Mateus\\CasosReais\\Perfil001ASegtxt.txt");
        leitorArquivo.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\VariacaoDist\\rotacionadoAtrib.txt");
        leitorArquivo.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\VariacaoDist\\rotacionadotxt.txt");
        leitorArquivo.somaMatriz();
        leitorArquivo.computaCentroide();
//        leitorArquivo.mostraMatriz();
        grafo.criaVetorAtributos(leitorArquivo.getMatriz(), leitorArquivo.getSomaMatriz(), leitorArquivo.getnLinhas(), leitorArquivo.getnColunas(), leitorArquivo.getXc(), leitorArquivo.getYc());
        grafo.removeRuidos();
        grafo.criaGrafo();
        System.out.println("");
        System.out.println("");

//        grafo2
        LeArquivo leitorArquivo2 = new LeArquivo();
        Grafo grafo2 = new Grafo();
        leitorArquivo2.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\VariacaoDist\\variacaoDist2Atrib.txt");
        leitorArquivo2.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\VariacaoDist\\variacaoDist2txt.txt");
        leitorArquivo2.somaMatriz();
        leitorArquivo2.computaCentroide();
        grafo2.criaVetorAtributos(leitorArquivo2.getMatriz(), leitorArquivo2.getSomaMatriz(), leitorArquivo2.getnLinhas(), leitorArquivo2.getnColunas(), leitorArquivo2.getXc(), leitorArquivo2.getYc());
        grafo2.removeRuidos();
        grafo2.criaGrafo();
//        leitorArquivo2.mostraMatriz();
        grafo2.mostraAtributos();
        
        Compare compara = new Compare(grafo, grafo2);
        compara.calcSimilaridade();
        

        
//        grafo.mostraAtributos();

        
        //compara.ordenaPorAngulo();
        //grafo.mostraAtributos();
        //grafo.mostraGrafo();
        //leitorArquivo.mostraMatriz();
        //CODIGO QUE MOSTRA A IMAGEM
        /*JFrame frame = new JFrame();
     frame.getContentPane().setLayout(new FlowLayout());
     frame.getContentPane().add(new JLabel(new ImageIcon(grafo.getImg())));
     frame.pack();
     frame.setVisible(true);*/
    }

}
