package modelos;

import arboleos.SearchRoute;

public interface SearchMethodsGato {
    SearchRouteGato depthSearch(Jugada data);

    SearchRouteGato breadthSearch(Jugada data);

    SearchRouteGato depthGanadorSearch();

    SearchRouteGato breadthGanadorSearch();


}
