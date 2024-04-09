package repository;

import dtos.ToyDto;

import java.util.List;

public interface ToyRepository {
    List<ToyDto> getAllToys();
}
