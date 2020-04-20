package kedar.com.realtimestocks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalQuoteJSON {

    @SerializedName("Global Quote")
    @Expose
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuote globalQuote) {
        this.globalQuote = globalQuote;
    }

}