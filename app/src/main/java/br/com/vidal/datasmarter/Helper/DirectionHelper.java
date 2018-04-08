package br.com.vidal.datasmarter.Helper;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.vidal.datasmarter.model.Coordenada;
import br.com.vidal.datasmarter.model.Rota;

/**
 * Created by mauricio on 4/8/18.
 */

public class DirectionHelper {

    private static String API_KEY = "AIzaSyAfE27DdNgl1lmc-Qsx6ITCv3IbgxT0K4w";

    public List<Rota> getRoute(Coordenada origem, Coordenada destino) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);

        DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
        apiRequest.origin(new com.google.maps.model.LatLng(origem.getLat(), origem.getLng()));
        apiRequest.destination(new com.google.maps.model.LatLng(destino.getLat(), destino.getLng()));
        apiRequest.mode(TravelMode.TRANSIT); //set travelling mode
        //apiRequest.alternatives(true);
        apiRequest.language("pt-BR");
        //apiRequest.setCallback(new DirectionsCallback());
        return getRoutes(apiRequest.await().routes[0]);
    }


    private List<Rota> getRoutes(DirectionsRoute route){
        List<Rota> lista = new ArrayList<>();
        int integracao = 0;
        for(DirectionsStep step : route.legs[0].steps){
            if(step.travelMode.equals(TravelMode.TRANSIT)){
                Rota r = new Rota();
                r.setDescricao(step.transitDetails.line.shortName);
                r.setIntegracao(integracao);
                Coordenada ini = new Coordenada();
                ini.setLat((float) step.startLocation.lat);
                ini.setLng((float) step.startLocation.lng);
                Coordenada fim = new Coordenada();
                fim.setLat((float) step.endLocation.lat);
                fim.setLng((float) step.endLocation.lng);
                r.setOrigem(ini);
                r.setDestino(fim);
                if(integracao == 0){
                    r.setIni(new Date());
                }else{
                    r.setIni(step.transitDetails.departureTime.toDate());
                }
                r.setFim(step.transitDetails.arrivalTime.toDate());
                integracao++;
                lista.add(r);
            }
        }
        return lista;
    }


    public class DirectionsCallback  implements PendingResult.Callback<DirectionsResult> {

        @Override
        public void onResult(DirectionsResult result) {
            DirectionsRoute[] routes = result.routes;
        }

        @Override
        public void onFailure(Throwable e) {

        }
    }





}
