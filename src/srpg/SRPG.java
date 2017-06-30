package srpg;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SRPG {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("Grafo 1");
        System.out.println("");
        LeArquivo leitorArquivo = new LeArquivo();
        Grafo grafo = new Grafo();
        leitorArquivo.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\Perf02AAtribThin.txt");
        leitorArquivo.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\Perf02ASegtxt.txt");
//        leitorArquivo.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\perfil001\\Perfil001AAtribThin.txt");
//        leitorArquivo.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\perfil001\\Perfil001ASegtxt.txt");
//        leitorArquivo.inverteMatrizAtributos();
        leitorArquivo.computaCentroide();
//        leitorArquivo.gravaImagem("C:\\Users\\mateu\\Desktop\\Result\\quadradotxt.txt");
//        System.out.println(leitorArquivo.getSomaMatriz());
        grafo.criaVetorAtributos(leitorArquivo.getMatriz(), leitorArquivo.getSomaMatriz(), leitorArquivo.getnLinhas(), leitorArquivo.getnColunas(), leitorArquivo.getXc(), leitorArquivo.getYc());
//        grafo.removeRuidos();
//        grafo.mostraMatrizAtributos();
//        grafo.gravaImagem("testando3.jpg", leitorArquivo.getXc(), leitorArquivo.getYc());
        grafo.criaGrafo();
        System.out.println("N Atributos " + grafo.getListaAtributos().size());

        System.out.println("");
        System.out.println("Grafo 2");
        System.out.println("");

//        grafo2
        LeArquivo leitorArquivo2 = new LeArquivo();
        Grafo grafo2 = new Grafo();
        leitorArquivo2.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\Perf02BAtribThin.txt");
        leitorArquivo2.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\Perf02BSegtxt.txt");
//        leitorArquivo2.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\perfil001\\Perfil001BAtribThin.txt");
//        leitorArquivo2.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\perfil001\\Perfil001BSegtxt.txt");
//        leitorArquivo2.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\rotacionado2Atrib.txt");
//        leitorArquivo2.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\rotacionado2txt.txt");
//        leitorArquivo2.inverteMatrizAtributos();
        leitorArquivo2.computaCentroide();
        grafo2.criaVetorAtributos(leitorArquivo2.getMatriz(), leitorArquivo2.getSomaMatriz(), leitorArquivo2.getnLinhas(), leitorArquivo2.getnColunas(), leitorArquivo2.getXc(), leitorArquivo2.getYc());
//        grafo2.removeRuidos();
//        grafo2.mostraMatrizAtributos();
//        grafo2.gravaImagem("testando2.jpg", leitorArquivo2.getXc(), leitorArquivo2.getYc());

        grafo2.criaGrafo();
        System.out.println("N Atributos " + grafo2.getListaAtributos().size());

//        leitorArquivo2.mostraMatriz();
//        grafo2.mostraAtributos();
        Compare compara = new Compare(grafo, grafo2);
//        compara.ordenaPorAngulo();
//        compara.shuffle();
        compara.calcSimilaridade();

//        grafo.mostraAtributos();
//        grafo2.mostraAtributos();
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
