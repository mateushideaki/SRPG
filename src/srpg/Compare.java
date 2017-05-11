/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class Compare {

    private double beta; //DIFERENCA DE DISTANCIA DE DOIS ATRIBUTOS EM RELACAO AO CENTROIDE, QUE AINDA SEJA CONSIDERADO SIMILAR (VALOR POSITIVO)
    private double alpha; //DIFERENCA DE ANGULOS
    private double delta; //DIFERENCA DE DISTANCIA ENTRE OS ATRIBUTOS VIZINHOS
    private double similaridade;
    private Grafo g1;
    private Grafo g2;

    public Compare(Grafo g1, Grafo g2) {
        this.beta = 0.00000;
        this.alpha = 0;
        this.delta = 0;
        this.similaridade = 0;
        this.g1 = g1;
        this.g2 = g2;
    }

    public void ordenaPorAngulo() {
        Collections.sort(this.g1.getListaAtributos(), new Comparator() {
            public int compare(Object o1, Object o2) {
                Atributo a1 = (Atributo) o1;
                Atributo a2 = (Atributo) o2;
                if (a1.getAng() < a2.getAng()) {
                    return -1;
                } else if (a1.getAng() > a2.getAng()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        Collections.sort(this.g2.getListaAtributos(), new Comparator() {
            public int compare(Object o1, Object o2) {
                Atributo a1 = (Atributo) o1;
                Atributo a2 = (Atributo) o2;
                if (a1.getAng() < a2.getAng()) {
                    return -1;
                } else if (a1.getAng() > a2.getAng()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public void calcSimilaridade() {
        for (int i = 0; i < g1.getListaAtributos().size(); i++) {
            Atributo a1 = g1.getListaAtributos().get(i);
            for (int j = 0; j < g2.getListaAtributos().size(); j++) {
                Atributo a2 = g2.getListaAtributos().get(j);
                if (a1.getId() == a2.getId()) {
                    if (Math.abs(a1.getDistCentroide() - a2.getDistCentroide()) <= beta/* && Math.abs(a1.getAng() - a2.getAng()) <= alpha*/) { //valor absoluto de a1.dist - a2.dist
                        //se entrou aqui deu match "parcial" (apenas atributo com atributo sem contar similaridade da vizinhanca)

                        List<Atributo> vizinhanca1 = new ArrayList<>();
                        List<Atributo> vizinhanca2 = new ArrayList<>();

                        for (int l = 0; l < g1.getListaAtributos().size(); l++) {
                            if (g1.getGrafo()[a1.getPosInicial()][l] != 0) {
                                Atributo vizinho = g1.getListaAtributos().get(l);
                                vizinhanca1.add(vizinho);
                            }
                        }

                        //System.out.println(a1.getPosInicial() + " " + a1.getId() + " " + vizinhanca1.size());
                        for (int l = 0; l < g2.getListaAtributos().size(); l++) {
                            if (g2.getGrafo()[a2.getPosInicial()][l] != 0) {
                                Atributo vizinho = g2.getListaAtributos().get(l);
                                vizinhanca2.add(vizinho);
                            }
                        }

                        int matchVizinhos = 0;
                        for (int v1 = 0; v1 < vizinhanca1.size(); v1++) {
                            Atributo vizinho1 = vizinhanca1.get(v1);
                            for (int v2 = 0; v2 < vizinhanca2.size(); v2++) {
                                Atributo vizinho2 = vizinhanca2.get(v2);
                                //Se algum atributo for escondido, outros podem ser afetados, entao pode nao possuir o mesmo ID
                                //if (vizinho1.getId() == vizinho2.getId()) {
                                    if (Math.abs(g1.getGrafo()[a1.getPosInicial()][vizinho1.getPosInicial()] - g2.getGrafo()[a2.getPosInicial()][vizinho2.getPosInicial()]) <= delta) {
                                        if (Math.abs(vizinho1.getDistCentroide() - vizinho2.getDistCentroide()) <= beta) {
                                            if (Math.abs((a1.getAng() - vizinho1.getAng()) - (a2.getAng() - vizinho2.getAng())) <= alpha) {
                                                matchVizinhos++;
                                            }
                                        }
                                    }
                                //}
                            }
                        }
                        //System.out.println("v1 " + vizinhanca1.size() + "  v2 "+ vizinhanca2.size() + "  matchs " + matchVizinhos);
                        double media = (double) (vizinhanca1.size() + vizinhanca2.size()) / 2;
                        double pctg = (double) (matchVizinhos * 100) / media;
                        this.similaridade += pctg;
                    }
                }
            }
        }
        double mediaAtt = (double) (g1.getListaAtributos().size() + g2.getListaAtributos().size()) * 100 / 2;
        this.similaridade = (double) (this.similaridade * 100) / mediaAtt;
        System.out.println("similaridade entre os grafos: " + this.similaridade + "%");
    }

    public Atributo encontraAtributo() {
        Atributo att = null;
        return att;
    }

    public double getSimilaridade() {
        return similaridade;
    }

    public void setSimilaridade(double similaridade) {
        this.similaridade = similaridade;
    }

}
