package kedar.com.realtimestocks.streamdataio;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import kedar.com.realtimestocks.URLConstructor;
import kedar.com.eventsource.EventSource;

import static android.content.ContentValues.TAG;
import static kedar.com.realtimestocks.contants.API.CURRENCY_EXCHANGE_RATE;
import static kedar.com.realtimestocks.contants.API.STREAMDATAIO_XSD_TOKEN_KEY;
import static kedar.com.realtimestocks.contants.API.STREAMDATA_IO_PROXY_PREFIX;

public class RealTimeDataConnection {

    private String streamdataioAppToken;

    private String apiURL;

    private SSEHandlerStock sseHandler;

    private SSEHandlerCrypto sseHandlerCrypto;

    private EventSource eventSource;

    private PriceObservable priceObservable;

    private boolean isCrypto =  false;

    public RealTimeDataConnection(){

    }
//    public void startRealTimeStockPriceListening(String symbol, String streamdataioAppToken, String alphaAdvantageToken){
//        isCrypto = false;
//        HashMap<String, String> options = new HashMap<>();
//        options.put("function", REALTIME_GLOBAL_QUOTE);
//        options.put("symbol", symbol);
//        options.put("apikey", alphaAdvantageToken);
//        this.streamdataioAppToken = streamdataioAppToken;
//        apiURL = URLConstructor.getConstructedURLForStockAPI(options);
//    }

    public void startRealTimeStockPriceListening(String symbol, String streamdataioAppToken, String polygonApiKey){
        isCrypto = false;
        HashMap<String, String> options = new HashMap<>();
        options.put("symbol", symbol);
        options.put("apiKey", polygonApiKey);
        this.streamdataioAppToken = streamdataioAppToken;
        apiURL = URLConstructor.getConstructedURLForStockAPI(options);
    }

    public void startRealTimeCryptoPriceInUSDListening(String symbol, String streamdataioAppToken, String alphaAdvantageToken){
        isCrypto = true;
        HashMap<String, String> options = new HashMap<>();
        options.put("function", CURRENCY_EXCHANGE_RATE);
        options.put("from_currency", symbol);
        options.put("to_currency", "USD");
        options.put("apikey", alphaAdvantageToken);
        this.streamdataioAppToken = streamdataioAppToken;
        apiURL = URLConstructor.getConstructedURLForCryptoAPI(options);
    }

    public void connect(PriceObservable priceObservable) {

        // Create headers: Add the streamdata.io app token

        Map<String, String> headers = new HashMap<>();

        headers.put(STREAMDATAIO_XSD_TOKEN_KEY, streamdataioAppToken);



        // Create the EventSource with API URL & Streamdata.io authentication token

        try {

            if(isCrypto){
                sseHandlerCrypto = new SSEHandlerCrypto();
                sseHandlerCrypto.setPriceObservable(priceObservable);
            }else{
                sseHandler = new SSEHandlerStock();
                sseHandler.addPriceObservable(priceObservable);
            }
            eventSource = new EventSource(new URI(STREAMDATA_IO_PROXY_PREFIX), new URI(apiURL),isCrypto? sseHandlerCrypto: sseHandler , headers);

        } catch (URISyntaxException e) {

            e.printStackTrace();
        }
        // Start data receiving
        eventSource.connect();

    }


    public boolean isConnected(){
        return eventSource!= null && eventSource.isConnected();
    }


    /**

     * Closes the event source connection and dereference the EventSource object

     */

    public void disconnect() {

        // Disconnect the eventSource Handler

        if (eventSource!= null && eventSource.isConnected()) {

            try {

                eventSource.close();
            } catch (Exception e) {

                if ( Log.isLoggable(TAG, Log.ERROR)) {

                    Log.e(TAG, "Error on closing SSE", e);

                }

            }

        }
        // Dereferencing variable

        eventSource = null;

    }

    public LiveData<String> getStockData(){
        return sseHandler.getRealTimeData();
    }

    public LiveData<String> getCryptoData(){
        return sseHandlerCrypto.getRealTimeData();
    }

    public void setCrypto(boolean crypto) {
        isCrypto = crypto;
    }

    public void setPriceObservable(PriceObservable priceObservable) {
        this.priceObservable = priceObservable;
        sseHandlerCrypto.setPriceObservable(priceObservable);
    }
}
