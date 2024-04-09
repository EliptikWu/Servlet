package utils;

import dtos.ToyDto;
import model.Toy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class FileUtils {

    public static void saveToys(File file, List<ToyDto> list) {

        try {
            FileOutputStream ficheroSalida = new FileOutputStream(file);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida);
            objetoSalida.writeObject(list);
            objetoSalida.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        public static List<Toy> getToys(File file) {

            List<Toy> toys = new ArrayList<>();
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                toys = (List<Toy>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no existe");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return toys;
        }




}