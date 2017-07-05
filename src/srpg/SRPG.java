/* <Sistema de Reconhecimento de Padrões utilizando Grafos.>
    Copyright (C) <2017>  <Mateus Hideaki Taroda> <mateustaroda@gmail.com>
    
    This file is part of SRPG.

    SRPG is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SRPG is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package srpg;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SRPG {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("Grafo 1");
        System.out.println("");
        LeArquivo leitorArquivo = new LeArquivo();
        Grafo grafo = new Grafo();
        leitorArquivo.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\certos\\casoreal\\Perf02AAtribThin.txt");
        leitorArquivo.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\certos\\casoreal\\Perf02ASegtxt.txt");
        leitorArquivo.computaCentroide();
        grafo.criaVetorAtributos(leitorArquivo.getMatriz(), leitorArquivo.getSomaMatriz(), leitorArquivo.getnLinhas(), leitorArquivo.getnColunas(), leitorArquivo.getXc(), leitorArquivo.getYc());
        grafo.criaGrafo();
        System.out.println("N Atributos " + grafo.getListaAtributos().size());

        System.out.println("");
        System.out.println("Grafo 2");
        System.out.println("");

//        grafo2
        LeArquivo leitorArquivo2 = new LeArquivo();
        Grafo grafo2 = new Grafo();
        leitorArquivo2.leMatrizAtributos("C:\\Users\\mateu\\Desktop\\Result\\certos\\casoreal\\Perf02BAtribThin.txt");
        leitorArquivo2.leMatrizBinaria("C:\\Users\\mateu\\Desktop\\Result\\certos\\casoreal\\Perf02BSegtxt.txt");
        leitorArquivo2.computaCentroide();
        grafo2.criaVetorAtributos(leitorArquivo2.getMatriz(), leitorArquivo2.getSomaMatriz(), leitorArquivo2.getnLinhas(), leitorArquivo2.getnColunas(), leitorArquivo2.getXc(), leitorArquivo2.getYc());

        grafo2.criaGrafo();
        System.out.println("N Atributos " + grafo2.getListaAtributos().size());

        Compare compara = new Compare(grafo, grafo2);
        compara.calcSimilaridade();


    }

}
