/* <Sistema de Reconhecimento de Padrões utilizando Grafos.>
    Copyright (C) <2017>  <Mateus Hideaki Taroda> <mateustaroda@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package srpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Compare {

    private double beta; //DIFERENCA DE DISTANCIA DE DOIS ATRIBUTOS EM RELACAO AO CENTROIDE, QUE AINDA SEJA CONSIDERADO SIMILAR (VALOR POSITIVO)
    private double alpha; //DIFERENCA DE ANGULOS
    private double delta; //DIFERENCA DE DISTANCIA ENTRE OS ATRIBUTOS VIZINHOS
    private double similaridade;
    private Grafo g1;
    private Grafo g2;

    public Compare(Grafo g1, Grafo g2) {
        this.beta = 0.04;
        this.alpha = 5;
        this.delta = 0.006;
        this.similaridade = 0;
        this.g1 = g1;
        this.g2 = g2;   
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(g1.getListaAtributos(), new Random(seed));
        Collections.shuffle(g2.getListaAtributos(), new Random(seed));
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
        double maiorSimilaridade = 0, media = 0, pctg = 0;
        int matchVizinhos = 0;
        Atributo maiorSimAtt;
        for (int i = 0; i < g1.getListaAtributos().size(); i++) {
            List<Atributo> vizinhanca1;
            List<Atributo> vizinhanca2;
            Atributo a1 = g1.getListaAtributos().get(i);
            maiorSimilaridade = 0;
            maiorSimAtt = null;
            for (int j = 0; j < g2.getListaAtributos().size(); j++) {
                Atributo a2 = g2.getListaAtributos().get(j);
                if (!a2.isMatched() && a1.getId() == a2.getId()) {
                    if (Math.abs(a1.getDistCentroide() - a2.getDistCentroide()) <= beta) { //valor absoluto de a1.dist - a2.dist
                        vizinhanca1 = new ArrayList<>();
                        vizinhanca2 = new ArrayList<>();
                        for (int l = 0; l < g1.getListaAtributos().size(); l++) {
                            if (g1.getGrafo()[a1.getPosInicial()][l] != 0) {
                                Atributo vizinho = g1.getListaAtributos().get(l);
                                vizinhanca1.add(vizinho);
                            }
                        }

                        for (int l = 0; l < g2.getListaAtributos().size(); l++) {
                            if (g2.getGrafo()[a2.getPosInicial()][l] != 0) {
                                Atributo vizinho = g2.getListaAtributos().get(l);
                                vizinhanca2.add(vizinho);
                            }
                        }

                        matchVizinhos = 0;
                        for (int v1 = 0; v1 < vizinhanca1.size(); v1++) {
                            Atributo vizinho1 = vizinhanca1.get(v1);
                            for (int v2 = 0; v2 < vizinhanca2.size(); v2++) {
                                Atributo vizinho2 = vizinhanca2.get(v2);
                                if (vizinho1.getId() == vizinho2.getId()) {
                                    if (Math.abs(g1.getGrafo()[a1.getPosInicial()][vizinho1.getPosInicial()] - g2.getGrafo()[a2.getPosInicial()][vizinho2.getPosInicial()]) <= delta) {
                                        if (Math.abs(vizinho1.getDistCentroide() - vizinho2.getDistCentroide()) <= beta) {
                                            int distAngular1 = Math.abs(a1.getAng() - vizinho1.getAng());
                                            int distAngular2 = Math.abs(a2.getAng() - vizinho2.getAng());
                                            if (distAngular1 > 180) {
                                                distAngular1 = 360 - distAngular1;
                                            }
                                            if (distAngular2 > 180) {
                                                distAngular2 = 360 - distAngular2;
                                            }
                                            if (Math.abs(distAngular1 - distAngular2) <= alpha) {
                                                matchVizinhos++;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        media = (double) (vizinhanca1.size() + vizinhanca2.size()) / 2;
                        pctg = (double) (matchVizinhos * 100) / media;
                        if (maiorSimilaridade < pctg) {
                            maiorSimilaridade = pctg;
                            maiorSimAtt = a2;
                        }
                    }
                }
            }
            if (maiorSimAtt != null) {
                maiorSimAtt.setMatched(true);
                this.similaridade += maiorSimilaridade;
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
