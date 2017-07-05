/* <Sistema de Reconhecimento de Padrões utilizando Grafos.>
    Copyright (C) <2017>  <Mateus Hideaki Taroda> <mateustaroda@gmail.com>

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

public class ElementoPilha {
    private int posPai;
    private int x;
    private int y;
    private double dist;

    public ElementoPilha(int posPai, int x, int y, double dist) {
        this.posPai = posPai;
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public int getPosPai() {
        return posPai;
    }

    public void setPosPai(int posPai) {
        this.posPai = posPai;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

}
