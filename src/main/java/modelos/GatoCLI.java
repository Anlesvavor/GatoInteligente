package modelos;

import arboleos.SearchRoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GatoCLI {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Reja reja = new Reja();
        EnumGanador ganador = EnumGanador.POR_DEFINIR;
        EnumTurno turno = Math.random() * 2 > 1 ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;
        NodeGato siguienteNodo = new NodeGato(new Jugada(turno, ganador, simbolo, reja));
        NodeGato tree = siguienteNodo;


        for (int i = 0; i < 5; i++) {
            while(!reja.lleno() && ganador.equals(EnumGanador.POR_DEFINIR)){
                reja.mostrar();
                if (turno.equals(EnumTurno.JUGADOR)){
                    reja = entradaJugador(reja, simbolo);
                } else {
                    SearchRouteGato ruta = siguienteNodo.depthGanadorSearch(simbolo);
                    if(siguienteNodo.depthGanadorSearch(simbolo).getRoute().isEmpty()){
                        Boolean flag = Boolean.FALSE;
                        do {
                            Integer n1 = (int) Math.floor(Math.random() * 3);
                            Integer n2 = (int) Math.floor(Math.random() * 3);
                            for(String s : reja.espaciosLibres()){
                                if (String.format("%s%s",n1, n2).equals(s)){
                                    flag = Boolean.TRUE;
                                    reja.ingresar(n1, n2, simbolo);
                                    break;
                                }
                            }
                        }while(flag.equals(Boolean.FALSE));
                        siguienteNodo = siguienteNodo.addChild(new NodeGato(new Jugada(turno, ganador, simbolo, reja)));
                    } else {
                        for (Integer j = 0; j < ruta.getRoute().size() - 1; j++) {
                            if(ruta.getRoute().get(j).getData().getReja().equals(reja)){
                                reja = ruta.getRoute().get(j + 1).getData().getReja();
                                siguienteNodo = siguienteNodo.depthSearch(ruta.getRoute().get(j + 1).getData()).getNode();
                            }
                        }
                    }
                }
                if (!reja.veredicto().equals(EnumEstado.VACIO)){
                    System.out.println(String.format("El ganador es %s jugando con %s !", turno, reja.veredicto()));
                    ganador = turno.equals(EnumTurno.JUGADOR) ? EnumGanador.JUGADOR : EnumGanador.COMPUTADORA;
                }else if(reja.lleno()){
                    System.out.println("Empate");
                    ganador = EnumGanador.EMPATE;
                }
                turno = turno.equals(EnumTurno.COMPUTADORA) ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
                simbolo = simbolo.equals(EnumEstado.O) ? EnumEstado.X : EnumEstado.O;
            }
            reja.mostrar();
            System.out.println(tree.toStringNice());
            reja = new Reja();
            ganador = EnumGanador.POR_DEFINIR;
            turno = Math.random() * 2 > 1 ? EnumTurno.JUGADOR : EnumTurno.COMPUTADORA;
            simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;
            siguienteNodo = tree;
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

    public static void popularArbol(){
        NodeGato raiz = new NodeGato(new Jugada());
        NodeGato puntero = raiz;
        Reja r;
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;

        for (int i = 0; i < 1000; i++) {
            r = new Reja();
            while (!r.lleno()) {
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
                simbolo = simbolo.equals(EnumEstado.O) ? EnumEstado.X : EnumEstado.O;
                puntero = puntero.addChild(new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.EMPATE, simbolo, r)));
            }
            puntero = raiz;
        }
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
