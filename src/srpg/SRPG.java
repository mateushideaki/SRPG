package srpg;

import java.io.FileNotFoundException;

public class SRPG {

    public static void main(String[] args) throws FileNotFoundException {
        LeArquivo leitor = new LeArquivo();
        Grafo leitorImg = new Grafo();

        leitor.ler("entrada1.txt");
        //leitor.mostraMatriz();
        leitor.somaMatriz();

        leitorImg.ler();
        leitorImg.computaCentroide();
        leitorImg.criaVetorAtributos(leitor.getMatriz(), leitor.getSomaMatriz());
        leitorImg.criaGrafo();

        Compare compara = new Compare(leitorImg, leitorImg);
        //compara.ordenaPorAngulo();
        compara.calcSimilaridade();
        //leitorImg.mostraAtributos();
        //leitorImg.mostraGrafo();


        //leitor.mostraMatriz();
    //CODIGO QUE MOSTRA A IMAGEM
        /*JFrame frame = new JFrame();
     frame.getContentPane().setLayout(new FlowLayout());
     frame.getContentPane().add(new JLabel(new ImageIcon(leitorImg.getImg())));
     frame.pack();
     frame.setVisible(true);*/
}

}
