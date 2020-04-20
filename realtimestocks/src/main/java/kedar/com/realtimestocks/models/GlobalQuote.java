package kedar.com.realtimestocks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalQuote {

    @SerializedName("01. symbol")
    @Expose
    private String symbol;
    @SerializedName("02. open")
    @Expose
    private String open;
    @SerializedName("03. high")
    @Expose
    private String high;
    @SerializedName("04. low")
    @Expose
    private String low;
    @SerializedName("05. price")
    @Expose
    private String price;
    @SerializedName("06. volume")
    @Expose
    private String volume;
    @SerializedName("07. latest trading day")
    @Expose
    private String latestTradingDay;
    @SerializedName("08. previous close")
    @Expose
    private String previousClose;
    @SerializedName("09. change")
    @Expose
    private String change;
    @SerializedName("10. change percent")
    @Expose
    private String changePercent;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getLatestTradingDay() {
        return latestTradingDay;
    }

    public void setLatestTradingDay(String latestTradingDay) {
        this.latestTradingDay = latestTradingDay;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

}
