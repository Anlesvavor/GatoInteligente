package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reja {
    /*
    private EnumEstado noroeste;
    private EnumEstado norte;
    private EnumEstado noreste;
    private EnumEstado oeste;
    private EnumEstado centro;
    private EnumEstado este;
    private EnumEstado suroeste;
    private EnumEstado sur;
    private EnumEstado sureste;
    */
    private EnumEstado[][] estados;

    public Reja() {
        this.estados = null;
        this.estados =  new EnumEstado[3][3];
        this.estados[0][0] = EnumEstado.VACIO;
        this.estados[0][1] = EnumEstado.VACIO;
        this.estados[0][2] = EnumEstado.VACIO;
        this.estados[1][0] = EnumEstado.VACIO;
        this.estados[1][1] = EnumEstado.VACIO;
        this.estados[1][2] = EnumEstado.VACIO;
        this.estados[2][0] = EnumEstado.VACIO;
        this.estados[2][1] = EnumEstado.VACIO;
        this.estados[2][2] = EnumEstado.VACIO;
        /*
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 3; j++) {
                this.estados[i][j] = EnumEstado.VACIO;
            }
        }
        */
    }

    public Reja(EnumEstado[][] estados) {
        this.estados = estados;
    }

    public Reja(Reja otro) {
        this.estados = otro.estados;
    }

    public Boolean lleno(){
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 3; j++) {
                if (estados[i][j].equals(EnumEstado.VACIO)) return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public EnumEstado veredicto(){
        if(!raya(estados[0][0], estados[0][1], estados[0][2]).equals(EnumEstado.VACIO)) return estados[0][0];
        if(!raya(estados[1][0], estados[1][1], estados[1][2]).equals(EnumEstado.VACIO)) return estados[1][0];
        if(!raya(estados[2][0], estados[2][1], estados[2][2]).equals(EnumEstado.VACIO)) return estados[2][0];
        if(!raya(estados[0][0], estados[1][0], estados[2][0]).equals(EnumEstado.VACIO)) return estados[0][0];
        if(!raya(estados[0][1], estados[1][1], estados[2][1]).equals(EnumEstado.VACIO)) return estados[0][1];
        if(!raya(estados[0][2], estados[1][2], estados[2][2]).equals(EnumEstado.VACIO)) return estados[0][2];
        if(!raya(estados[0][0], estados[1][1], estados[2][2]).equals(EnumEstado.VACIO)) return estados[0][0];
        if(!raya(estados[0][2], estados[1][1], estados[2][0]).equals(EnumEstado.VACIO)) return estados[0][2];
        return EnumEstado.VACIO;
    }

    private EnumEstado raya(EnumEstado e1, EnumEstado e2, EnumEstado e3) {
        return (e1.equals(e2) && e1.equals(e3)) ? (e1.equals(EnumEstado.O) ? EnumEstado.O : EnumEstado.X) : EnumEstado.VACIO;
    }

    public Reja ingresar(Integer fila, Integer columna, EnumEstado simbolo){
        if(estados[fila][columna].equals(EnumEstado.VACIO)){
            estados[fila][columna] = simbolo;
        }
        return this;
    }
    /*
        Los espaciones libres son guardados como cadenas con como "00", "12", "02", ... ,etc por que
        ArrayList nos funciona bien con datos enumerados
    */
    public String[] espaciosLibres() {
        String[] libres = new String[9];
        Integer indice = 0;
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 3; j++) {
                if (estados[i][j].equals(EnumEstado.VACIO)) libres[indice++] = String.format("%d%d", i, j);
            }
        }
        return libres;
    }

    public Reja copia(){
        return new Reja(this.estados);
    }

    public void mostrar(){
                      System.out.println(   "    0     1     2      ");
                      System.out.println(   "                       ");
        System.out.println(String.format("0   %s  |  %s  |  %s    ", v(estados[0][0]), v(estados[0][1]), v(estados[0][2])));
                      System.out.println(   "   -----------------   ");
        System.out.println(String.format("1   %s  |  %s  |  %s    ", v(estados[1][0]), v(estados[1][1]), v(estados[1][2])));
                      System.out.println(   "   -----------------   ");
        System.out.println(String.format("2   %s  |  %s  |  %s    ", v(estados[2][0]), v(estados[2][1]), v(estados[2][2])));
    }

    private String v(EnumEstado es){
        return es.equals(EnumEstado.VACIO) ? "_" : es.toString();
    }
    private String vv(EnumEstado es){
        return es.equals(EnumEstado.VACIO) ? "V" : es.toString();
    }

    @Override
    public String toString() {
        return "Reja{" +
                "estados=[" + String.format("%s,%s,%s; %s,%s,%s; %s,%s,%s",
                v(estados[0][0]),v(estados[0][1]),v(estados[0][2]),
                v(estados[1][0]),v(estados[1][1]),v(estados[1][2]),
                v(estados[2][0]),v(estados[2][1]),v(estados[2][2])) +
                "]}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reja reja = (Reja) o;
        return this.estados.equals(reja);
        //return Arrays.equals(estados, reja.estados);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(estados);
    }

    public EnumEstado[][] getEstados() {
        return estados;
    }

    public void setEstados(EnumEstado[][] estados) {
        this.estados = estados;
    }

}
