package com.coincomp.coins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;

@RestController
public class CoinsController {

    @Autowired
    private GetCoinPriceService getCoinPriceService;

    //mapping for base url
    //used for testing purposes
    @GetMapping("/")
    public Dictionary<String, String> index(){
        Dictionary<String, String> test;
        test = getCoinPriceService.updatePrices();
        return test;
    }

}
