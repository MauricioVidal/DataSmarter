package br.com.vidal.datasmarter;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.RideRequestButtonCallback;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.error.ApiError;
import com.uber.sdk.rides.client.model.PriceEstimatesResponse;
import com.uber.sdk.rides.client.services.RidesService;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import br.com.vidal.datasmarter.Helper.DirectionHelper;
import br.com.vidal.datasmarter.Helper.RouteHelper;
import br.com.vidal.datasmarter.model.Coordenada;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RouteHelper helper = new RouteHelper();
        DirectionHelper directionHelper = new DirectionHelper();
        Coordenada origem = new Coordenada();
        origem.setLat(-22.8141707F);
        origem.setLng(-47.0591102F);

        Coordenada destinado = new Coordenada();
        destinado.setLat(-22.9060632F);
        destinado.setLng(-47.0723366F);


        try {
            helper.routeOtimization(directionHelper.getRoute(origem, destinado), null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
