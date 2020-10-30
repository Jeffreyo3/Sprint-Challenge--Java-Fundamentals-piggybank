package com.jdouglaso.piggybank.repositories;

import com.jdouglaso.piggybank.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long> {
}
