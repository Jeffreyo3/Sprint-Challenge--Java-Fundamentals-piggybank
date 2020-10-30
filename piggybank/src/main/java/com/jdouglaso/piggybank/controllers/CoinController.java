package com.jdouglaso.piggybank.controllers;


import com.jdouglaso.piggybank.models.Coin;
import com.jdouglaso.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {
    @Autowired
    CoinRepository coinRepo;

    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> findTotal() {
        List<Coin> coinList = new ArrayList<>();
        coinRepo.findAll().iterator().forEachRemaining(coinList::add);

        List<String> coinTotals = new ArrayList<>();
        double total = 0;
        for(Coin c : coinList) {
            int quantity = c.getQuantity();
            String cQuantity;

            total += quantity * c.getValue();

            if(quantity == 1) {
                cQuantity = quantity + " " + c.getName();
            } else {
                cQuantity = quantity + " " + c.getNameplural();
            }
            System.out.println(cQuantity);
            coinTotals.add(cQuantity);
        }
        String cTotal = "The piggy bank holds $" + total;
        System.out.println(cTotal);
        coinTotals.add(cTotal);

        return new ResponseEntity<>(coinTotals, HttpStatus.OK);
    }
}
