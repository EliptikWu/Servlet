package service;

import dtos.ToyDto;
import model.Category;

import java.util.List;
import java.util.Map;

public interface ToyService {
    //void addToy(ToyDto toyDto) throws Exception;

    List<ToyDto> listToyByCategory(Category category) throws Exception;

    Map<Category, Integer> showByType() throws Exception;

    List<ToyDto> listAllToy() throws Exception;

    Map.Entry<String, Long> maxToy() throws Exception;
    String minToy() throws Exception;

    Object allPriceToy() throws Exception;

    List<ToyDto> expensiveToy() throws Exception;

    List<ToyDto> toyOrdered() throws Exception;

    Boolean verifyExist(String name) throws Exception;

    ToyDto toySearch(String name) throws Exception;

    List<ToyDto> toyDecrease(String toyName, int amount)throws Exception;
    List<ToyDto> toyIncrease(String toyName, int amount) throws Exception;



}
