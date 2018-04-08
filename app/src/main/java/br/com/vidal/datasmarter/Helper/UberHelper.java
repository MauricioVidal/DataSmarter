package br.com.vidal.datasmarter.Helper;

import android.os.AsyncTask;

import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.PriceEstimate;
import com.uber.sdk.rides.client.model.PriceEstimatesResponse;
import com.uber.sdk.rides.client.services.RidesService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import br.com.vidal.datasmarter.model.Coordenada;
import br.com.vidal.datasmarter.model.Rota;
import retrofit2.Response;

/**
 * Created by mauricio on 4/8/18.
 */

public class UberHelper {

    private static String CLIENTE_ID = "AOPDSo0l3e30mPDcJdow75--5LxgC3Zy";
    private static String SERVIDOR_TOKEN = "t1MOHgQU2VCu-fGMaQG95yMacjkhobEqaJwDHCn0";


    public Rota getUber(Coordenada origem, Coordenada destino) throws ExecutionException, InterruptedException {
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId(CLIENTE_ID) //This is necessary
                .setServerToken(SERVIDOR_TOKEN)
                .setEnvironment(SessionConfiguration.Environment.SANDBOX) //Useful for testing your app in the sandbox environment
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.RIDE_WIDGETS)) //Your scopes for authentication here
                .build();
        ServerTokenSession session = new ServerTokenSession(config);
        Response<PriceEstimatesResponse> result = new Estimate(session).execute(origem.getLat(), origem.getLng(), destino.getLat(), destino.getLng() ).get();
        Rota rota = new Rota();
        rota.setOrigem(origem);
        rota.setDestino(destino);
        rota.setDescricao("uber");
        Calendar fim = Calendar.getInstance();
        PriceEstimate priceEstimate = result.body().getPrices().get(0);
        fim.add(Calendar.SECOND, priceEstimate.getDuration());
        rota.setFim(fim.getTime());
        rota.setPreco((priceEstimate.getHighEstimate() + priceEstimate.getLowEstimate())/2 * priceEstimate.getSurgeMultiplier());
        return rota;
    }

    public class Estimate extends AsyncTask<Float, Void, Response<PriceEstimatesResponse>>{

        private ServerTokenSession session;

        public Estimate(ServerTokenSession session){
            this.session = session;
        }


        @Override
        protected Response<PriceEstimatesResponse> doInBackground(Float... floats) {
            RidesService uber = UberRidesApi.with(session).build().createService();
            Response response = null;
            try {
                response = uber.getPriceEstimates(floats[0], floats[1], floats[2], floats[3]).execute();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }
    }




}
