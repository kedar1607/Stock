package kedar.com.realtimestocks.endpoints;

import java.util.Map;

import kedar.com.realtimestocks.models.GlobalQuoteJSON;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static kedar.com.realtimestocks.contants.API.BASE_URL;

public interface IGetDataService {
    @GET(BASE_URL)
    Call<ResponseBody> getIntradayStockPrices(@QueryMap Map<String, String> options);

    @GET(BASE_URL)
    Call<GlobalQuoteJSON> getGlobalQuote(@QueryMap Map<String, String> options);
}
