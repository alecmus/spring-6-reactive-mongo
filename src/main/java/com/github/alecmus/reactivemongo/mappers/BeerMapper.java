package com.github.alecmus.reactivemongo.mappers;

import com.github.alecmus.reactivemongo.domain.Beer;
import com.github.alecmus.reactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
