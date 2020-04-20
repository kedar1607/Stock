package kedar.com.realtimestocks;

import java.util.HashMap;

import static kedar.com.realtimestocks.contants.API.BASE_URL;

public class URLConstructor {
//    public static String getConstructedURLForStockAPI(HashMap<String,String> options){
//        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
//        stringBuilder.append("?");
//        for (String option :options.keySet()) {
//            String valueParameter = options.get(option);
//            stringBuilder.append(option).append("=").append(valueParameter).append("&");
//        }
//        return stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
//    }

    public static String getConstructedURLForStockAPI(HashMap<String,String> options){
        return BASE_URL + options.get("symbol") + "?apiKey=" + options.get("apiKey");
    }

    public static String getConstructedURLForCryptoAPI(HashMap<String,String> options){
        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
        stringBuilder.append("?");
        for (String option :options.keySet()) {
            String valueParameter = options.get(option);
            stringBuilder.append(option).append("=").append(valueParameter).append("&");
        }
        return stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
    }
}
