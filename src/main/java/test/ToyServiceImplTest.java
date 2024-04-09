package test;

import dtos.ToyDto;
import model.Category;
import org.junit.Before;
import org.junit.Test;
import service.impl.ToyServiceImpl;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ToyServiceImplTest {
    private ToyServiceImpl service;

    @Before
    public void setup(){
        service = new ToyServiceImpl();
    }

    @Test
    public void addToy_test() throws Exception {
        Long id = Long.valueOf(1);
        String name = "Terreneitor";
        Double price = Double.valueOf(30.00);
        Integer amount = 4;
        String category = String.valueOf(Category.Unisex);
        ToyDto toyToAdd = new ToyDto(id,name,price,amount,category);
        List<ToyDto> expected = Collections.singletonList(toyToAdd);
        //List<ToyDto> result = service.addToy();
        //assertEquals(expected,result);
    }
    @Test
    public void listToyByCategory_test() throws Exception {
        Map<Category,Integer> mapExpected = new TreeMap<>();
        mapExpected.put(Category.Unisex,3);
        mapExpected.put(Category.Female,2);
        mapExpected.put(Category.Male,2);
        Map<Category,Integer> result = service.showByType();
        assertEquals(mapExpected,result);
    }

    @Test
    public void maxToy_test() throws Exception {
        Map<Category, Integer> testMap = Map.of(Category.Unisex,20,Category.Male,15,Category.Female,30);
        Map.Entry<Category,Integer> expected = testMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        Map.Entry<String, Long> result = service.maxToy();
        Map.Entry<Category, Long> expectedLong = new AbstractMap.SimpleEntry<>(expected.getKey(),
                expected.getValue().longValue());
        assertEquals(expectedLong, result);
    }
    @Test
    public void minToy_test() throws Exception {
        Map<Category, Integer> testMap = Map.of(Category.Unisex,30,Category.Male,10,Category.Female,50);
        Map.Entry<Category,Integer> expected = testMap.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);
        String result = service.minToy();
        Map.Entry<Category, Long> expectedLong = new AbstractMap.SimpleEntry<>(expected.getKey(),
                expected.getValue().longValue());
        assertEquals(expectedLong, result);
    }
    /*@Test
    public void allPriceToy_test() throws Exception{
        service.addToy(new ToyDto(1L,"Blin",300.0,11, Category.Unisex));
        service.addToy(new ToyDto(5L,"Sword",22.0,1,Category.Unisex));
        int expectedTotal = 1200+2000;
        List<Double> result = service.allPriceToy();
        assertEquals(expectedTotal,result);
    }*/
    @Test
    public void listAllToy_test() throws Exception {
    }
    @Test
    public void expensiveToy_test() throws Exception{
    }
    @Test
    public void toyOrdered_test() throws Exception{
        Map<Category,Integer> unsortedMap = new HashMap<>();
        unsortedMap.put(Category.Unisex,1);
        unsortedMap.put(Category.Female,3);
        unsortedMap.put(Category.Male,2);
        Map<Category,Integer> expectedMap = new HashMap<>();
        expectedMap.put(Category.Male,1);
        expectedMap.put(Category.Female,2);
        expectedMap.put(Category.Unisex,3);
        Map<Category,Integer> result = (Map<Category, Integer>) service.toyOrdered();
        assertEquals(expectedMap,result);
    }
    @Test
    public void toySearch_test() throws Exception{
    }
    @Test
    public void toyDecrease_test() throws Exception{
        Long id = Long.valueOf(1);
        String name = "Terreneitor";
        String category = String.valueOf(Category.Unisex);
        Double price = Double.valueOf(200.00);
        Integer amount = 1;
        int newAmount = 4;
        ToyDto toyToAdd = new ToyDto(id,name, price, amount + newAmount, category);
        List<ToyDto> listExpected = Collections.singletonList(toyToAdd);
        List<ToyDto> result = service.toyDecrease(String.valueOf(toyToAdd),newAmount);
        assertEquals(listExpected,result);
    }
    @Test
    public void toyIncrease_test() throws Exception{
        Long id = Long.valueOf(1);
        String name = "Terreneitor";
        String category = String.valueOf(Category.Unisex);
        Double price = Double.valueOf(200.00);
        Integer amount = 1;
        int newAmount = 4;
        ToyDto toyToAdd = new ToyDto(id,name, price, amount + newAmount, category);
        assertThrows(Exception.class,()->service.toyIncrease(String.valueOf(toyToAdd),newAmount));
    }
}
