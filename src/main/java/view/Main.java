package view;

import dtos.ToyDto;
import model.Category;
import repository.ToyRepositoryImpl.ToyRepositoryImpl;
import service.ToyService;
import service.impl.ThreadServiceImpl;
import service.impl.ToyServiceImpl;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws Exception {

        String option = "1";
        ToyService toyService = new ToyServiceImpl();
        ToyRepositoryImpl toyRepository = new ToyRepositoryImpl();
        ThreadServiceImpl threadService = new ThreadServiceImpl();

        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n Menu" +
                    "\n1. Add a new toy" +
                    "\n2. Inform how many toys there are for each type" +
                    "\n3. Report the total number of toy" +
                    "\n4. Report the total value of all the toys in the store" +
                    "\n5. Decrease the existence of a toy" +
                    "\n6. Increase the existence of a toy" +
                    "\n7. Inform the type of which there are more toys" +
                    "\n8. Report the type of which there are fewer toys" +
                    "\n9. Obtain toys with a value greater than (indicated by the user)" +
                    "\n10. Order the number of toy stocks by type from lowest to highest" +
                    "\n11. Exit");

            option = scan.next();

            switch (option) {
                case "1": {
                    //System.out.println(toyService.addToy());
                    break;
                }
                case "2": {
                    System.out.println(toyService.listToyByCategory(Category.Male));
                    break;
                }
                case "3": {
                    CompletableFuture.runAsync(() ->{
                        threadService.waiting();
                            });
                    threadService.slowTime(3000);
                    System.out.println(toyService.listAllToy());
                    break;
                }
                case "4": {
                    CompletableFuture thread = CompletableFuture.supplyAsync(()->{
                        throw new RuntimeException("The data may have been modified");
                    });
                    CompletableFuture thread2 = thread.exceptionally(error -> {
                        System.out.println("Exception: " + error.toString());
                        return 777;
                    });
                    thread2.thenAccept(result -> {
                        System.out.println("Error: " + result + " (Data is recovering...)");
                    });
                    threadService.slowTime(3000);
                    System.out.println(toyService.allPriceToy());
                    break;
                }
                case "5": {
                    System.out.println("Enter the toy name");
                    String name = scan.next();
                    System.out.println("Enter the amount");
                    int amount = Integer.parseInt(scan.next());
                    ToyDto toyDto = toyService.toySearch(name);
                    System.out.println(toyService.toyDecrease(String.valueOf(toyDto),amount));
                    break;
                }
                case "6": {
                    System.out.println("Enter the toy name");
                    String name = scan.next();
                    System.out.println("Enter the amount");
                    int amount = Integer.parseInt(scan.next());
                    ToyDto toyDto = toyService.toySearch(name);
                    System.out.println(toyService.toyIncrease(String.valueOf(toyDto),amount));
                    break;
                }
                case "7": {
                    System.out.println(toyService.maxToy());
                    break;
                }
                case "8": {
                    System.out.println(toyService.minToy());
                    break;
                }
                case "9": {
                    System.out.println(toyService.expensiveToy());
                    break;
                }
                case "10": {
                    System.out.println(toyService.toyOrdered());
                    break;
                }
                default:
                    System.out.println("DOES NOT EXIST");

            }

        } while (!option.equals("1"));
    }
}