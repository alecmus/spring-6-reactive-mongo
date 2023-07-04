package com.github.alecmus.reactivemongo.web.fn;

import com.github.alecmus.reactivemongo.model.BeerDTO;
import com.github.alecmus.reactivemongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BeerHandler {
    private final BeerService beerService;

    public Mono<ServerResponse> deleteBeerById(ServerRequest request) {
        return beerService.deleteBeerById(request.pathVariable("beerId"))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> pathBeerById(ServerRequest request) {
        return request.bodyToMono(BeerDTO.class)
                .map(beerDTO -> beerService
                        .patchBeer(request.pathVariable("beerId"), beerDTO))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateBeerById(ServerRequest request) {
        return request.bodyToMono(BeerDTO.class)
                .map(beerDTO -> beerService
                        .updateBeer(request.pathVariable("beerId"), beerDTO))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> createNewBeer(ServerRequest request) {
        return beerService.saveBeer(request.bodyToMono(BeerDTO.class))
                .flatMap(beerDTO -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromPath(BeerRouterConfig.BEER_PATH_ID)
                                .build(beerDTO.getId()))
                        .build());
    }

    public Mono<ServerResponse> getBeerById(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(beerService.getById(request.pathVariable("beerId")), BeerDTO.class);
    }

    public Mono<ServerResponse> listBeers(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }
}
