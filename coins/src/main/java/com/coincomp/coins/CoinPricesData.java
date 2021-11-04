package com.coincomp.coins;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;

@RestController
public class CoinPricesData {

    @Autowired
    private GetCoinPriceService getCoinPriceService;

    //returns our data after updating
    //front end requests at the /api mapping
    @GetMapping("/api")
    public Dictionary<String, String> index(){
        Dictionary<String, String> test;
        test = getCoinPriceService.updatePrices();
        return test;
    }

}
