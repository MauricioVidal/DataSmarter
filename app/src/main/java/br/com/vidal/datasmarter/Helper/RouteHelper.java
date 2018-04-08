package br.com.vidal.datasmarter.Helper;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.vidal.datasmarter.model.Coordenada;
import br.com.vidal.datasmarter.model.Rota;

/**
 * Created by mauricio on 4/8/18.
 */

public class RouteHelper {


    private double custo(List<Rota> solucao){
        double custo = 0;
        for(Rota r : solucao ){
            custo += r.getPreco();
        }
        return custo;
    }

    public List<Rota> routeOtimization(List<Rota> lista, Date fim) throws ExecutionException, InterruptedException, IOException, ApiException {
        List<Rota> solucaoIni = lista;
        if(lista.size() == 3){
            Rota uber = new UberHelper().getUber(lista.get(0).getOrigem(), lista.get(2).getDestino());
            uber.setIni(lista.get(0).getIni());

            List<Rota> s1 = new ArrayList<>();
            s1.add(uber);

            Rota uber2 = new UberHelper().getUber(lista.get(0).getOrigem(), lista.get(1).getDestino());
            uber2.setIni(lista.get(0).getIni());

            List<Rota> s2 = new DirectionHelper().getRoute(uber.getDestino(), lista.get(2).getDestino());
            s2.add(0, uber2);

            Rota uber3 = new UberHelper().getUber(lista.get(0).getOrigem(), lista.get(0).getDestino());
            uber3.setIni(lista.get(0).getIni());

            List<Rota> s3 = new DirectionHelper().getRoute(uber.getDestino(), lista.get(2).getDestino());
            s2.add(0, uber3);



        }
        return null;
    }


}
