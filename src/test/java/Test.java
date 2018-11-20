import arboleos.Node;
import arboleos.SearchRoute;
import modelos.*;
import org.junit.jupiter.api.Assertions;

import static modelos.EnumEstado.O;
import static modelos.EnumEstado.X;

public class Test {

    @org.junit.jupiter.api.Test
    public void prueba1() {
        NodeGato raiz = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : X;

        for (int i = 0; i < 50000; i++) {
            NodeGato anterior = raiz;
            NodeGato tmp;
            Reja r = new Reja();
            while (!r.lleno()) {
                Boolean flag = Boolean.FALSE;
                GatoCLI.movimientoAleatorioComputadora(simbolo, r);
                simbolo = simbolo.equals(EnumEstado.O) ? X : EnumEstado.O;

                Reja r2 = (Reja) DeepCopy.copy(r);

                tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r2));
                SearchRouteGato ruta = anterior.breadthRejaSearch(r2);
                if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
                    anterior.addChild(tmp);
                    anterior = tmp;
                } else if (ruta.getNode() != null) {
                    anterior = ruta.getNode();
                } else {
                    //anterior = tmp;
                }
            }
        }
        System.out.println(raiz.toStringNice());
    }

    @org.junit.jupiter.api.Test
    public void prueba2() {
        NodeGato raiz = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));

    }

    @org.junit.jupiter.api.Test
    public void prueba3() {
        Reja r1 = new Reja();
        r1.ingresar(0, 0, X);
        EnumEstado[][] e2 = r1.getEstados();
        Reja r2 = new Reja(e2);
        r2.ingresar(1, 1, EnumEstado.O);
        r1.mostrar();
        r2.mostrar();
    }

    @org.junit.jupiter.api.Test
    public void prueba4() {
        Reja r1 = new Reja();
        r1.ingresar(0, 0, X);
        Reja r2 = (Reja) DeepCopy.copy(r1);
        r2.ingresar(1, 1, EnumEstado.O);
        r1.mostrar();
        r2.mostrar();
        NodeGato raiz = new NodeGato(new Jugada());
        raiz.setData(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));
        raiz.addChild(new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, r1)));
        raiz.addChild(new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja().ingresar(2, 2, EnumEstado.O))));
        System.out.println(raiz.toStringNice());

        System.out.println(raiz.breadthRejaSearch(new Reja().ingresar(0, 0, X)).getNode().toStringNice());
        System.out.println(new Reja().ingresar(0, 0, X));
        raiz.breadthRejaSearch(r1).getNode().addChild(new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, r2)));
        System.out.println(raiz.toStringNice());
        raiz.breadthRejaSearch(r1).getNode().addChild(new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja().ingresar(1, 2, EnumEstado.X))));
        System.out.println(raiz.toStringNice());
        raiz.breadthRejaSearch(new Reja().ingresar(0, 0, EnumEstado.X).ingresar(1, 1, EnumEstado.O))
                .getNode().addChild(new NodeGato(
                new Jugada(EnumTurno.JUGADOR, EnumGanador.EMPATE, EnumEstado.X, new Reja().ingresar(0, 0, X).ingresar(1, 1, O).ingresar(2, 2, X))));
        Reja tmp = new Reja();
        tmp.ingresar(0, 0, X);
        //tmp.ingresar(1,1,EnumEstado.O);
        tmp.mostrar();
        raiz.breadthRejaSearch(tmp).getNode().getData().getReja().mostrar();
        System.out.println(raiz.toStringNice());
        //.addChild(new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, r2)));

    }

    @org.junit.jupiter.api.Test
    public void prueba5() {
        Reja r1 = new Reja().ingresar(0, 0, EnumEstado.X);
        Reja test = new Reja().ingresar(0, 0, EnumEstado.X);
        //Assertions.assertEquals(r1, test);
        Assertions.assertTrue(r1.equals(test));
    }

    @org.junit.jupiter.api.Test
    public void prueba6() {
        NodeGato raiz = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));
        EnumEstado simbolo = EnumEstado.O;

        NodeGato anterior = raiz;
        NodeGato tmp;
        Reja r = new Reja().ingresar(0, 0, O);

        Reja r2 = new Reja().ingresar(0, 0, O).ingresar(1, 1, X);

        Reja r3 = new Reja().ingresar(2, 2, X);

        Reja r4 = new Reja().ingresar(1, 2, X).ingresar(2, 2, X);

        Reja r5 = new Reja().ingresar(1, 2, X).ingresar(2, 2, X).ingresar(0, 2, O);

        Reja test = new Reja().ingresar(1, 2, X).ingresar(2, 2, X).ingresar(1, 0, O);

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r));
        SearchRouteGato ruta = anterior.breadthRejaSearch(r);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r2));
        ruta = anterior.breadthRejaSearch(r2);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;

        anterior = raiz;
        tmp = null;

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r3));
        ruta = anterior.breadthRejaSearch(r3);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r4));
        ruta = anterior.breadthRejaSearch(r4);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.COMPUTADORA, EnumEstado.X, r5));
        ruta = anterior.breadthRejaSearch(r5);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;

        anterior = raiz;

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, test));
        ruta = anterior.breadthRejaSearch(test);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;

        anterior = raiz;
        tmp = null;

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r3));
        ruta = anterior.breadthRejaSearch(r3);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        } else if (ruta.getNode() != null) {
            anterior = ruta.getNode();
        } else {
            anterior = tmp;
        }


        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r4));
        ruta = anterior.breadthRejaSearch(r4);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.
            anterior.addChild(tmp);
        } else if (ruta.getNode() != null) {
            anterior = ruta.getNode();
        } else {
            anterior = tmp;
        }

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, test));
        ruta = anterior.breadthRejaSearch(test);
        if (ruta.getNode().getLevel().equals(-1)) { //Se cumple la condición, OK.+
            anterior.addChild(tmp);
        } else if (ruta.getNode() != null) {
            anterior = ruta.getNode();
        } else {
            anterior = tmp;
        }

        System.out.println(raiz.toStringNice());
        System.out.println();

        System.out.println("===============================================================================");

        //raiz.breadthRejaSearch(r3).getRoute().forEach(nodeGato -> System.out.println(String.format("%s\n==================", nodeGato)));
        raiz.breadthRejaSearch(new Reja()).getNode().breadthGanadorSearch(EnumEstado.X).getRoute().forEach(nodeGato -> System.out.println(String.format("\n%s%s",nodeGato.getLevel() ,nodeGato.getData())));
        System.out.println(String.format(">>>%s",r5));
        //System.out.println(raiz.breadthRejaSearch(new Reja()).getNode().breadthGanadorSearch(EnumEstado.X).getNode().toStringNice());
        //System.out.println(raiz.breadthGanadorSearch(EnumEstado.X).getNode().toStringNice());
    }

    @org.junit.jupiter.api.Test
    public void popularArbolTest() {
        NodeGato raiz = GatoCLI.popularArbol(1000000);
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : X;

        Reja r = new Reja();

        //System.out.println(raiz.toStringNice());
        System.out.println();

        System.out.println("================================================================================");
        if (raiz.breadthRejaSearch(r).getNode().depthGanadorSearch(simbolo).getNode() != null) {
            //System.out.println(raiz.breadthRejaSearch(raiz.breadthRejaSearch(r).getNode().breadthGanadorSearch(simbolo).getNode().getData().getReja()).getRoute().size());
            //raiz.breadthRejaSearch(raiz.breadthRejaSearch(new Reja()).getNode().depthGanadorSearch(simbolo).getNode().getData().getReja()).getRoute().forEach(nodeGato -> System.out.println(nodeGato.getData().getReja()));
            //raiz.breadthRejaSearch(r).getNode().breadthGanadorSearch(simbolo).getRoute().forEach(nodeGato -> System.out.println(nodeGato.getData().getReja()));
              //raiz.breadthRejaSearch(new Reja()).getNode().breadthGanadorSearch(simbolo).getRoute().forEach(nodeGato -> System.out.println(String.format("%s\n==================",nodeGato)));
            //raiz.depthRejaSearch(raiz.depthRejaSearch(new Reja()).getNode().breadthGanadorSearch(EnumEstado.X).getNode().getData().getReja()).getRoute().forEach(nodeGato -> System.out.println(nodeGato.getData()));
            //raiz.depthGanadorSearch(EnumEstado.X).getRoute().forEach(nodeGato -> System.out.println(String.format("\n%s%s",nodeGato.getLevel() ,nodeGato.getData())));
            //raiz.depthSearch(raiz.breadthGanadorSearch(EnumEstado.X).getNode().getData()).getRoute().forEach(nodeGato -> System.out.println(String.format("\n%s%s",nodeGato.getLevel() ,nodeGato.getData())));
            /*
            raiz.depthSearch(raiz.breadthGanadorSearch(EnumEstado.X).getNode().getData()).rutaARaiz().getRoute().forEach(nodeGato -> System.out.println(String.format("\n%s%s",nodeGato.getLevel() ,nodeGato.getData())));
            /*
            */

            while (!r.lleno()) {
                Reja r2 = (Reja) DeepCopy.copy(r);
                r = raiz.breadthRejaSearch(r2).getNode().breadthGanadorSearch(simbolo).getRoute().get(1).getData().getReja();
                r.mostrar();
                System.out.println();
                simbolo = simbolo.equals(EnumEstado.O) ? X : EnumEstado.O;
            }

        }
    }
}