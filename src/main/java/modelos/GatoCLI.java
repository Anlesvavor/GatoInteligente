package modelos;

import arboleos.SearchRoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modelos.EnumEstado.X;

public class GatoCLI {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EnumGanador ganador = EnumGanador.POR_DEFINIR;
        EnumTurno turno = Math.random() * 2 > 1 ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;
        NodeGato raiz = popularArbol(2000);


        for (int i = 0; i < 5; i++) {
            NodeGato anterior = raiz;
            NodeGato tmp;
            Reja rej = new Reja();
            while(!rej.lleno() && ganador.equals(EnumGanador.POR_DEFINIR)){
                rej.mostrar();
                Reja rej2 = (Reja) DeepCopy.copy(rej);
                if (turno.equals(EnumTurno.JUGADOR)){
                    entradaJugador(rej, simbolo);
                } else if (raiz.breadthRejaSearch(rej2).getNode().depthGanadorSearch(simbolo).getNode() != null
                        && raiz.breadthRejaSearch(rej2).getNode().depthGanadorSearch(simbolo).getRoute().size() > 2) {
                    rej = raiz.breadthRejaSearch(rej2).getNode().depthGanadorSearch(simbolo).getRoute().get(1).getData().getReja();

                } else {
                    movimientoAleatorioComputadora(simbolo, rej);
                    /*
                    Reja r2 = (Reja) DeepCopy.copy(r);

                    tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r2));
                    SearchRouteGato ruta = anterior.breadthRejaSearch(r2);

                    if(ruta.getNode().getLevel().equals(-1)){ //Se cumple la condición, OK.
                        anterior.addChild(tmp);
                        anterior = tmp;
                    }else if(ruta.getNode() != null){
                        anterior = ruta.getNode();
                    } else {
                        //anterior = tmp;
                    }
                    */
                }
                if (!rej.veredicto().equals(EnumEstado.VACIO)){
                    System.out.println(String.format("El ganador es %s jugando con %s !", turno, rej.veredicto()));
                    ganador = turno.equals(EnumTurno.JUGADOR) ? EnumGanador.JUGADOR : EnumGanador.COMPUTADORA;
                }else if(rej.lleno()){
                    System.out.println("Empate");
                    ganador = EnumGanador.EMPATE;
                }
                turno = turno.equals(EnumTurno.COMPUTADORA) ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
                simbolo = simbolo.equals(EnumEstado.O) ? EnumEstado.X : EnumEstado.O;
            }
            //r.mostrar();

            ganador = EnumGanador.POR_DEFINIR;
            turno = Math.random() * 2 > 1 ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
            simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;
            //siguienteNodo = raiz;
        }


    }
    //
    //
    //
    //
    //
    // AQUI TE QUEDASTE JESUS JOSE DE LAS 1:05AM DEL MARTES
    // AHORA QUIERES AGREGAR LAS JUGADAS AL ARBOL
    // rECUEDA QUE EN UN ARBOL CORRESPONDIENTE A UNA SOLA PARTIDA
    // NO HAY DOS JUGADAS EN EL MISMO NIVEL!!!
    // CADA PARTIDA CREA UNA RAMA DESDE PRINCIPIO A FIN
    //
    //
    //
    //
/*
    public void jugar(Reja nuevaReja){
        this.reja = nuevaReja;
        turno = turno.equals(EnumTurno.JUGADOR) ? EnumTurno.COMPUTADORA : EnumTurno.JUGADOR;
    }
*/

    public static NodeGato popularArbol(Integer iteraciones){

        NodeGato raiz = new NodeGato(new Jugada(null, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));

        for (int i = 0; i < Math.abs(iteraciones); i++) {
            EnumGanador ganador = EnumGanador.POR_DEFINIR;
            EnumTurno turno = Math.random() * 2 > 1 ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
            EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;

            NodeGato anterior = raiz;
            NodeGato tmp;
            Reja r = new Reja();
            while (!r.lleno() && ganador.equals(EnumGanador.POR_DEFINIR)) {
                movimientoAleatorioComputadora(simbolo, r);

                Reja r2 = (Reja) DeepCopy.copy(r);

                if (!r2.veredicto().equals(EnumEstado.VACIO)){
                    //System.out.println(String.format("El ganador es %s jugando con %s !", turno, r2.veredicto()));
                    ganador = turno.equals(EnumTurno.JUGADOR) ? EnumGanador.JUGADOR : EnumGanador.COMPUTADORA;

                }else if(r2.lleno()){
                   // System.out.println("Empate");
                    ganador = EnumGanador.EMPATE;
                }

                tmp = new NodeGato(new Jugada(turno, ganador, simbolo, r2));
                SearchRouteGato ruta = anterior.breadthRejaSearch(r2);

                if(ruta.getNode().getLevel().equals(-1)){ //Se cumple la condición, OK.
                    anterior.addChild(tmp);
                    anterior = tmp;
                }else if(ruta.getNode() != null){
                    anterior = ruta.getNode();
                } else {
                    //anterior = tmp;
                }

                turno = turno.equals(EnumTurno.COMPUTADORA) ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
                simbolo = simbolo.equals(EnumEstado.O) ? EnumEstado.X : EnumEstado.O;
            }
        }
        return raiz;
    }

    public static void movimientoAleatorioComputadora(EnumEstado simbolo, Reja r) {
        Boolean flag = Boolean.FALSE;
        do {
            Integer n1 = (int) Math.floor(Math.random() * 3);
            Integer n2 = (int) Math.floor(Math.random() * 3);
            for (String s : r.espaciosLibres()) {
                if (String.format("%s%s", n1, n2).equals(s)) {
                    flag = Boolean.TRUE;
                    r.ingresar(n1, n2, simbolo);
                    break;
                }
            }
        } while (flag.equals(Boolean.FALSE));
    }


    public static Reja entradaJugador(Reja reja, EnumEstado simb) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String temp;
        String espaciosLibres = "";
        Boolean flag = Boolean.FALSE;

        System.out.println(String.format("Ingresa una coordenada a jugar (Juegas %s)", simb));
        for(String s : reja.espaciosLibres())
            espaciosLibres = espaciosLibres.length() == 0 ? s : String.format("%s, %s", espaciosLibres, s);
        System.out.println(String.format("Opciones disponibles: %s", espaciosLibres));
        do {
            temp = reader.readLine();
            for(String s : reja.espaciosLibres()){
                if (temp.equals(s)){
                    flag = Boolean.TRUE;
                    break;
                }
            }
        }while(flag.equals(Boolean.FALSE));
        reja.ingresar(Integer.parseInt(temp.substring(0,1)), Integer.parseInt(temp.substring(1,2)), simb);

        return reja;
    }

}
