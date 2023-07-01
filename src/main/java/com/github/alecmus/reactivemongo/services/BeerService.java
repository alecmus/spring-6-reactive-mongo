package com.github.alecmus.reactivemongo.services;

import com.github.alecmus.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Mono;

public interface BeerService {

    Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDto);

    Mono<BeerDTO> getById(String beerId);
}
