package kedar.com.realtimestocks.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchBestMatches {

    @SerializedName("1. symbol")
    @Expose
    private String symbol;
    @SerializedName("2. name")
    @Expose
    private String name;
    @SerializedName("3. type")
    @Expose
    private String type;
    @SerializedName("4. region")
    @Expose
    private String region;
    @SerializedName("5. marketOpen")
    @Expose
    private String marketOpen;
    @SerializedName("6. marketClose")
    @Expose
    private String marketClose;
    @SerializedName("7. timezone")
    @Expose
    private String timezone;
    @SerializedName("8. currency")
    @Expose
    private String currency;
    @SerializedName("9. matchScore")
    @Expose
    private String matchScore;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMarketOpen() {
        return marketOpen;
    }

    public void setMarketOpen(String marketOpen) {
        this.marketOpen = marketOpen;
    }

    public String getMarketClose() {
        return marketClose;
    }

    public void setMarketClose(String marketClose) {
        this.marketClose = marketClose;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCurrency() {
        return currency;
    }

    public void set8Currency(String currency) {
        this.currency = currency;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

}