package srpg;

import java.io.FileNotFoundException;

public class SRPG {

    public static void main(String[] args) throws FileNotFoundException {
        LeArquivo leitorArquivo = new LeArquivo();
        Grafo grafo = new Grafo();

        leitorArquivo.ler("entrada1.txt");
        //leitorArquivo.mostraMatriz();
        leitorArquivo.somaMatriz();

        grafo.lerImagem();
        grafo.computaCentroide();
        grafo.criaVetorAtributos(leitorArquivo.getMatriz(), leitorArquivo.getSomaMatriz());
        grafo.removeRuidos();
        grafo.criaGrafo();

        Compare compara = new Compare(grafo, grafo);
        //compara.ordenaPorAngulo();
        compara.calcSimilaridade();
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
