package srpg;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SRPG {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        LeArquivo leitorArquivo = new LeArquivo();
        Grafo grafo = new Grafo();
//        leitorArquivo.gravaImagem("C:\\Users\\mateu\\Dropbox\\TCC Mateus\\CasosReais\\Perfil001ASegtxt.txt");
        leitorArquivo.leMatrizAtributos("C:\\Users\\mateu\\Dropbox\\TCC Mateus\\CasosReais\\Perfil001AAtribThin.txt");
        leitorArquivo.leMatrizBinaria("C:\\Users\\mateu\\Dropbox\\TCC Mateus\\CasosReais\\Perfil001ASegtxt.txt");
        leitorArquivo.somaMatriz();
        leitorArquivo.computaCentroide();
//        leitorArquivo.mostraMatriz();
        grafo.criaVetorAtributos(leitorArquivo.getMatriz(), leitorArquivo.getSomaMatriz(), leitorArquivo.getnLinhas(), leitorArquivo.getnColunas(), leitorArquivo.getXc(), leitorArquivo.getYc());
        grafo.removeRuidos();
        grafo.criaGrafo();

//        grafo2
        LeArquivo leitorArquivo2 = new LeArquivo();
        Grafo grafo2 = new Grafo();
        leitorArquivo2.leMatrizAtributos("C:\\Users\\mateu\\Dropbox\\TCC Mateus\\CasosReais\\PBT-020-TemplateAtrib.txt");
        leitorArquivo2.leMatrizBinaria("C:\\Users\\mateu\\Dropbox\\TCC Mateus\\CasosReais\\PBT-020-Templatetxt.txt");
        leitorArquivo2.somaMatriz();
        leitorArquivo2.computaCentroide();
        grafo2.criaVetorAtributos(leitorArquivo2.getMatriz(), leitorArquivo2.getSomaMatriz(), leitorArquivo2.getnLinhas(), leitorArquivo2.getnColunas(), leitorArquivo2.getXc(), leitorArquivo2.getYc());
        grafo2.removeRuidos();
        grafo2.criaGrafo();
//
//        grafo.mostraAtributos();
//
        Compare compara = new Compare(grafo, grafo2);
        compara.calcSimilaridade();

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
