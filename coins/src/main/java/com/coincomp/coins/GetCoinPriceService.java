package com.coincomp.coins;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;

import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

@Service
public class GetCoinPriceService {

    private final RestTemplate restTemplate;

    //initialize a dictionary to hold our fetch prices
    //for a larger dictionary, a dict of arrays or objects with exchange,currency,buy/sell
    //would be preferred
    Dictionary<String, String> priceDict = new Hashtable<>()
    {{
        put("geminiBTCBuy", "0.00");
        put("geminiBTCSell","0.00");
        put("geminiETHBuy", "0.00");
        put("geminiETHSell", "0.00");

        put("binanceBTCBuy", "0.00");
        put("binanceBTCSell", "0.00");
        put("binanceETHBuy", "0.00");
        put("binanceETHSell", "0.00");
    }};

    public GetCoinPriceService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //calls our two functions to update prices returning updated prices
    public Dictionary<String, String> updatePrices(){
        geminiPrice();
        binancePrice();
        return priceDict;
    }

    //fetches data from gemini
    public void geminiPrice() {
        String urlBTC = "https://api.gemini.com/v1/book/btcusd?limit_bids=5&limit_asks=5";
        String urlETH = "https://api.gemini.com/v1/book/ethusd?limit_bids=5&limit_asks=5";

        fetchBuy(urlBTC,"gemini", "BTC");
        fetchSell(urlBTC,"gemini", "BTC");
        fetchBuy(urlETH,"gemini", "ETH");
        fetchSell(urlETH,"gemini", "ETH");

    }

    //fetches data from binance
    public void binancePrice(){
        String urlBTC = "https://api.binance.com/api/v3/depth?symbol=BTCUSDT&limit=5";
        String urlETH = "https://api.binance.com/api/v3/depth?symbol=ETHUSDT&limit=5";

        fetchBuy(urlBTC,"binance","BTC");
        fetchSell(urlBTC,"binance","BTC");
        fetchBuy(urlETH,"binance","ETH");
        fetchSell(urlETH,"binance","ETH");
    }

    public void fetchBuy(String url, String exchange, String currency){
        float avgAsk = 0.00F;
        String jsonString = restTemplate.getForObject(url, String.class);
        JSONObject obj = new JSONObject(jsonString);
        JSONArray asks = obj.getJSONArray("asks");

        //handles each exchange's api data separately by exchange
        //fetches first 5 asks and averages them to reach sell price
        if (Objects.equals(exchange, "binance")) {
            for (int i = 0; i < asks.length(); i++) {
                avgAsk += asks.getJSONArray(i).getFloat(0);
            }
        } else if (Objects.equals(exchange, "gemini")) {
            for (int i = 0; i < asks.length(); i++) {
                avgAsk += Float.parseFloat(
                        obj.getJSONArray("asks").getJSONObject(i).getString("price")
                );
            }
        }

        avgAsk = avgAsk/asks.length();
        priceDict.put(exchange + currency + "Buy",String.format(
                        "%.2f", new BigDecimal(avgAsk)
                )
        );
    }

    public void fetchSell(String url, String exchange, String currency){
        float avgBid = 0F;
        String jsonString = restTemplate.getForObject(url, String.class);
        JSONObject obj = new JSONObject(jsonString);
        JSONArray bids = obj.getJSONArray("bids");

        //handles each exchange's api data separately by exchange
        //fetches first 5 bids and averages them to reach an average sell price
        if (Objects.equals(exchange, "binance")) {
            for (int i = 0; i < bids.length(); i++) {
                avgBid += bids.getJSONArray(i).getFloat(0);
            }
        } else if (Objects.equals(exchange, "gemini")) {
            for (int i = 0; i < bids.length(); i++) {
                avgBid += Float.parseFloat(
                        obj.getJSONArray("bids").getJSONObject(i).getString("price")
                );
            }
        }

        avgBid = avgBid/bids.length();
        priceDict.put(exchange + currency + "Sell",String.format(
                "%.2f", new BigDecimal(avgBid)
                )
        );
    }
}
