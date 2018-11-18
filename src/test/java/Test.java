import arboleos.SearchRoute;
import modelos.*;

public class Test {

    @org.junit.jupiter.api.Test
    public void prueba1(){
        NodeGato raiz = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;

        for (int i = 0; i < 10; i++) {
            NodeGato anterior = raiz;
            NodeGato tmp;
            Reja r = new Reja();
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

                //Reja r2 = new Reja(r);

                tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, new Reja(r)));
                SearchRouteGato ruta = anterior.breadthRejaSearch(new Reja(r));
                if(ruta.getNode().getLevel().equals(-1)){ //Se cumple la condición, OK.
                    anterior.addChild(tmp);
                }
                anterior = tmp;
            }
        }
        System.out.println(raiz.toStringNice());
    }

    @org.junit.jupiter.api.Test
    public void prueba2(){
        NodeGato raiz = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, EnumEstado.VACIO, new Reja()));
        NodeGato anterior = raiz;
        NodeGato tmp;
        //NodeGato puntero = raiz;
        EnumEstado simbolo = Math.random() * 2 > 1 ? EnumEstado.O : EnumEstado.X;
        EnumEstado[][] estados = new EnumEstado[3][3];      //Declara un arreglo de un valor Enumerado, este parametro
        estados[0][0] = EnumEstado.X;                       //lo toma el new Reja() para crear una rendija del juego
        estados[0][1] = EnumEstado.VACIO;
        estados[0][2] = EnumEstado.VACIO;
        estados[1][0] = EnumEstado.VACIO;
        estados[1][1] = EnumEstado.VACIO;
        estados[1][2] = EnumEstado.VACIO;
        estados[2][0] = EnumEstado.VACIO;
        estados[2][1] = EnumEstado.VACIO;
        estados[2][2] = EnumEstado.VACIO;
                                                            // Aqui el valor es el siguiente [X,_,_; _,_,_ ; _,_,_ ]
        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, new Reja(estados)));
        SearchRouteGato ruta = anterior.breadthRejaSearch(new Reja(estados));
        if(ruta.getNode().getLevel().equals(-1)){ //Se cumple la condición, OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;
        //System.out.println(raiz.toStringNice());
        // ^^^ Este comportamiento es desado, debe de agregar el nodo con la reja

        estados = new EnumEstado[3][3];
        estados[0][0] = EnumEstado.X;
        estados[0][1] = EnumEstado.O;
        estados[0][2] = EnumEstado.VACIO;
        estados[1][0] = EnumEstado.VACIO;
        estados[1][1] = EnumEstado.VACIO;
        estados[1][2] = EnumEstado.VACIO;
        estados[2][0] = EnumEstado.VACIO;
        estados[2][1] = EnumEstado.VACIO;
        estados[2][2] = EnumEstado.VACIO;

        Reja r2 = new Reja(estados);
        // Aqui el arreglo cambia para que la reja devuelva algo tal que: [X,O,_; _,_,_ ; _,_,_ ]

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r2));
        ruta = anterior.breadthRejaSearch(new Reja(estados));
        if(ruta.getNode().getLevel().equals(-1)){ //Se cunple la condición, por que la reja no esta en el arbol OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;
        //System.out.println(raiz.toStringNice());
        /// ^^^ MAL MAL MAL POR QUE CAMBIA EL VALOR DE LA PRIMER ENTRADA!!!! NOTIENE NADA QUE VER!!!!!
        anterior = raiz;
        estados = new EnumEstado[3][3];
        estados[0][0] = EnumEstado.VACIO;
        estados[0][1] = EnumEstado.VACIO;
        estados[0][2] = EnumEstado.O;
        estados[1][0] = EnumEstado.VACIO;
        estados[1][1] = EnumEstado.VACIO;
        estados[1][2] = EnumEstado.VACIO;
        estados[2][0] = EnumEstado.VACIO;
        estados[2][1] = EnumEstado.VACIO;
        estados[2][2] = EnumEstado.VACIO;

        Reja r3 = new Reja(estados);
        // Aqui el arreglo cambia para que la reja devuelva algo tal que: [X,O,_; _,_,_ ; _,_,_ ]

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, r3));
        ruta = anterior.breadthRejaSearch(r3);
        if(ruta.getNode().getLevel().equals(-1)){ //Se cunple la condición, por que la reja no esta en el arbol OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;
        //System.out.println(raiz.toStringNice());
        /// ^^^ MAL MAL MAL POR QUE CAMBIA EL VALOR DE LA PRIMER ENTRADA!!!! NOTIENE NADA QUE VER!!!!!

        estados = new EnumEstado[3][3];
        estados[0][0] = EnumEstado.X;
        estados[0][1] = EnumEstado.VACIO;
        estados[0][2] = EnumEstado.O;
        estados[1][0] = EnumEstado.VACIO;
        estados[1][1] = EnumEstado.VACIO;
        estados[1][2] = EnumEstado.VACIO;
        estados[2][0] = EnumEstado.VACIO;
        estados[2][1] = EnumEstado.VACIO;
        estados[2][2] = EnumEstado.VACIO;

        // Aqui el arreglo cambia para que la reja devuelva algo tal que: [X,O,_; _,_,_ ; _,_,_ ]

        tmp = new NodeGato(new Jugada(EnumTurno.COMPUTADORA, EnumGanador.POR_DEFINIR, simbolo, new Reja(estados)));
        ruta = anterior.breadthRejaSearch(new Reja(estados));
        if(ruta.getNode().getLevel().equals(-1)){ //Se cunple la condición, por que la reja no esta en el arbol OK.
            anterior.addChild(tmp);
        }
        anterior = tmp;
        System.out.println(raiz.toStringNice());
        /// ^^^ MAL MAL MAL POR QUE CAMBIA EL VALOR DE LA PRIMER ENTRADA!!!! NOTIENE NADA QUE VER!!!!!

    }
}
