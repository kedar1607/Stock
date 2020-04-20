package kedar.com.realtimestocks.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimelyStockInfo {

    @SerializedName("1. open")
    @Expose
    private String open;
    @SerializedName("2. high")
    @Expose
    private String high;
    @SerializedName("3. low")
    @Expose
    private String low;
    @SerializedName("4. close")
    @Expose
    private String close;
    @SerializedName("5. adjusted close")
    @Expose
    private String adjustedCLose;
    @SerializedName("6. volume")
    @Expose
    private String volume;
    @SerializedName("7. dividend amount")
    @Expose
    private String dividendAmount;
    @SerializedName("8. split coefficient")
    @Expose
    private String splitCoefficient;

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

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getAdjustedClose() {
        return adjustedCLose;
    }

    public void setAdjustedClose(String adjustedCLose) {
        this.adjustedCLose = adjustedCLose;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDividendAmount() {
        return dividendAmount;
    }

    public void setDividendAmount(String dividendAmount) {
        this.dividendAmount = dividendAmount;
    }

    public String getSplitCoefficient() {
        return splitCoefficient;
    }

    public void setSplitCoefficient(String splitCoefficient) {
        this.splitCoefficient = splitCoefficient;
    }

}