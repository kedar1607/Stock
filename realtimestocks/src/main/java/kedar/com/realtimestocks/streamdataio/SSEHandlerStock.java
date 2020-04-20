package kedar.com.realtimestocks.streamdataio;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import kedar.com.eventsource.EventSourceHandler;
import kedar.com.eventsource.MessageEvent;
import kedar.com.realtimestocks.models.Last;
import kedar.com.realtimestocks.models.StockInfo;

import static android.content.ContentValues.TAG;

public class SSEHandlerStock implements EventSourceHandler {


    private JsonNode data;

    private MutableLiveData<String> liveJson = new MutableLiveData<>();

    private List<PriceObservable> priceObservables = new ArrayList<>();


    public SSEHandlerStock() { }

    /**

     * SSE handler for connection starting

     */

    @Override

    public void onConnect() {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "SSE Connected");
        }
        /* Do whatever you like in when the stream gets open */
    }



    /* SSE incoming message handler */

    @Override
    public void onMessage(String event, MessageEvent message) throws IOException {

        if ("data".equals(event)) {
            // SSE message is the first snapshot
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readTree(message.data) ;
            Handler mainHandler = new Handler(Looper.getMainLooper());

            //This is used for alpha advantage
//            Runnable myRunnable = new Runnable() {
//                @Override
//                public void run() {
//                    String price = data.get("GlobalQuote").get("05. price").textValue();
//                    liveJson.setValue(price);
//                    priceObservable.newPrice(price);
//                }
//            };
            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    final String price = data.get("last").get("price").toString();
                    final String symbol = data.get("symbol").textValue();
                    liveJson.setValue(price);
                    priceObservables.forEach(new Consumer<PriceObservable>() {
                        @Override
                        public void accept(PriceObservable it) {
                            it.newPrice(new StockInfo(new Last(null,null,Double.parseDouble(price),null,null), null,symbol));
                        }
                    });
                }
            };
            mainHandler.post(myRunnable);






        } else if ("patch".equals(event)) {

            // SSE message is a patch
            try {

                ObjectMapper mapper = new ObjectMapper();

                JsonNode tempNode  = mapper.readTree(message.data);

                Handler mainHandler = new Handler(Looper.getMainLooper());

                for (final JsonNode op:tempNode) {
                    if(op.get("op").textValue().equals("replace") && op.get("path").textValue().equals("/last/price") ){
                        Runnable myRunnable = new Runnable() {
                            @Override
                            public void run() {
                                final String price = op.get("value").toString();
                                final String symbol = data.get("symbol").textValue();
                                liveJson.setValue(price);
                                priceObservables.forEach(new Consumer<PriceObservable>() {
                                    @Override
                                    public void accept(PriceObservable it) {
                                        it.newPrice(new StockInfo(new Last(null,null,Double.parseDouble(price),null,null), null,symbol));
                                    }
                                });
                            }
                        };
                        mainHandler.post(myRunnable);
                    }
                }

                //Used for Alpha advantage
//                if(data.size() >= 0 &&
//                        data.get(0).has("path") &&
//                        data.get(0).get("path").textValue().contains("price")) {
//
//                    Handler mainHandler = new Handler(Looper.getMainLooper());
//
//
//                    Runnable myRunnable = new Runnable() {
//                        @Override
//                        public void run() {
//                            String price = data.get(0).get("value").textValue();
//                            liveJson.setValue(price);
//                            priceObservable.newPrice(price);
//                        }
//                    };
//                    mainHandler.post(myRunnable);
//                }
            } catch (Exception e) {

                e.printStackTrace();

            }

        } else {

            throw new RuntimeException("Unexpected SSE message: " + event);

        }

    }

    public LiveData<String> getRealTimeData(){
        return liveJson;
    }

    /* SSE error Handler */

    @Override
    public void onError(Throwable t) {
        /* Do whatever you like in case of error */
    }
    /* SSE Handler for connection interruption */
    @Override
    public void onClosed(boolean willReconnect) {
        /* Do whatever you like when the stream gets stopped */
    }

    public void addPriceObservable(PriceObservable priceObservable) {
        this.priceObservables.add(priceObservable);
    }

    public void removePriceObservable(PriceObservable priceObservable){
        this.priceObservables.remove(priceObservable);
    }

}
