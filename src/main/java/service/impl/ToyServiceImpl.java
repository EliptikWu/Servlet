package service.impl;

import dtos.ToyDto;
import mapping.ToyMapper;
import model.Category;
import model.Toy;
import repository.ToyRepository;
import repository.ToyRepositoryImpl.ToyRepositoryImpl;
import service.ToyService;
import utils.Constants;
import utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ToyServiceImpl implements ToyService {
    static List<ToyDto> toys;

    public ToyServiceImpl() {
        ToyRepository repoCustomer = new ToyRepositoryImpl();
        toys = repoCustomer.getAllToys();
    }

    /*@Override
    public void addToy(ToyDto toy) throws Exception {
        Toy newToy = ToyMapper.mapFrom(toy);
        toys.add(ToyDto);
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toys);
        getAllToys();

    }*/
    @Override
    public List<ToyDto> listToyByCategory(Category category) throws Exception {
        return toys.stream()
                .filter(e -> e.category().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public Map.Entry<String, Long> maxToy() throws Exception {
        return  toys.stream().collect(Collectors.groupingBy(ToyDto::category, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .orElse(null);
    }

    @Override
    public String minToy() throws Exception {
        return toys.stream()
                .collect(Collectors.groupingBy(ToyDto::category, Collectors.summingInt(ToyDto::amount)))
                .entrySet()
                .stream()
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public Map<Category, Integer> showByType() throws Exception {
        Map<Category,Integer> showByType = new TreeMap<>();
        for(ToyDto toy : toys){
            String type = toy.category();
            showByType.put(Category.valueOf(type),showByType.getOrDefault(type,0)+1);
        }
        return showByType;
    }
    @Override
    public List<ToyDto> listAllToy() {
        return toys;
    }

    @Override
    public List<Double> allPriceToy() throws Exception{
        return toys.stream()
                .map(ToyDto::price)
                .collect(Collectors.toList());
    }

    @Override
    public List<ToyDto> expensiveToy() throws Exception {
        Map<String, ToyDto> expensiveProduct = new HashMap<>();
        for (ToyDto toy : toys) {
            expensiveProduct
                    .merge(String.valueOf(toy.category()), toy,
                            (e, r) -> e.price() > r.price() ? e : r);
        }
        return new ArrayList<>(expensiveProduct.values());
    }

    @Override
    public Boolean verifyExist(String name) {
        return toys.stream().anyMatch(e -> e.name().equalsIgnoreCase(name));
    }

    @Override
    public ToyDto toySearch(String name) throws Exception {
        List<Toy> list = toys.stream().filter(toyList-> Objects.equals(toyList.name(), name))
                .findFirst().stream().map(ToyMapper::mapFrom).toList();
        return null;
    }
    private ToyDto findToyByName(String toyName) throws Exception {
        return toys.stream()
                .filter(toy -> toy.name().equalsIgnoreCase(toyName))
                .findFirst()
                .orElseThrow(() -> new Exception("Toy not found: " + toyName));
    }

    private ToyDto findToyByCategory(Category category) throws Exception {
        return toys.stream()
                .filter(toy -> toy.category().equals(category))
                .findFirst()
                .orElseThrow(() -> new Exception("Toy not found: " + category));
    }
    @Override
    public List<ToyDto> toyIncrease(String toyName, int amount) throws Exception {
        ToyDto toyToUpdate = findToyByName(toyName);
        toyToUpdate.amount();
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toys);
        return null;
    }
    @Override
    public List<ToyDto> toyDecrease(String toyName, int amount) throws Exception {
        ToyDto toyToUpdate = findToyByName(toyName);
        if (toyToUpdate.amount() < amount)
            throw new Exception("Insufficient quantity");
        toyToUpdate.amount();
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toys);
        return null;
    }
    @Override
    public List<ToyDto> toyOrdered() throws Exception {
        return toys.stream()
                .sorted(Comparator.comparingInt(ToyDto::amount))
                .collect(Collectors.toList());
    }
}
