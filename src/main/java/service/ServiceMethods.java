package service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.INTEGER_NUMBERS;

public class ServiceMethods {

    public static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void separateData(List<String> lines,
                                    List<Integer> integers,
                                    List<Float> floats,
                                    List<String> strings) {
        for (String line : lines) {
            try {
                int intValue = Integer.parseInt(line);
                integers.add(intValue);
            } catch (NumberFormatException e) {
                try {
                    float floatValue = Float.parseFloat(line);
                    floats.add(floatValue);
                } catch (NumberFormatException ex) {
                    strings.add(line);
                }
            }
        }
    }

    public static void processFiles(List<String> filePaths,
                                    String outputDir,
                                    String prefix,
                                    boolean append,
                                    boolean shortStats) throws IOException {
        List<Integer> integers = new ArrayList<>();
        List<Float> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (String filePath : filePaths) {
            List<String> lines = readFile(filePath);
            separateData(lines, integers, floats, strings);
        }

        if (!integers.isEmpty()) {
            writeToFile(outputDir, prefix + "integers.txt", integers, append);
            if (shortStats) {
                printStats(INTEGER_NUMBERS, integers);
            } else {
                printStatsFull(INTEGER_NUMBERS, integers);
            }
        }
        if (!floats.isEmpty()) {
            writeToFile(outputDir, prefix + "floats.txt", floats, append);
            if (shortStats) {
                printStats("Вещественные числа", floats);
            } else {
                printStatsFull("Вещественные числа", floats);
            }
        }
        if (!strings.isEmpty()) {
            writeToFile(outputDir, prefix + "strings.txt", strings, append);
            if (shortStats) {
                printStats("Строки", strings);
            } else {
                printStatsFull("Строки", strings);
            }
        }
    }

    private static void printStats(String typeName, List<?> data) {
        System.out.println("Статистика для " + typeName + ":");
        System.out.println("Количество элементов: " + data.size());
    }

    private static void printStatsFull(String typeName, List<?> data) {
        System.out.println("Статистика для " + typeName + ":");
        System.out.println("Количество элементов: " + data.size());

        if (data.stream().allMatch(Number.class::isInstance)) {
            List<Number> numbers = (List<Number>) data;
            double sum = numbers.stream().mapToDouble(Number::doubleValue).sum();
            double average = sum / numbers.size();
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее значение: " + average);
        } else if (data.stream().allMatch(String.class::isInstance)) {
            List<String> strings = (List<String>) data;
            int minLength = strings.stream().mapToInt(String::length).min().getAsInt();
            int maxLength = strings.stream().mapToInt(String::length).max().getAsInt();
            System.out.println("Минимальная длина строки: " + minLength);
            System.out.println("Максимальная длина строки: " + maxLength);
        }
    }

    /**
     * Записывает данные в файл
     *
     * @param outputDir каталог
     * @param fileName имя файла
     * @param data данные
     * @param append флаг, указываеит перезаписать файл или добавить к существующему файлу
     * @throws IOException ошибка при записи в файл
     */
    public static void writeToFile(String outputDir, String fileName, List<?> data, boolean append)
            throws IOException {
        File file = new File(outputDir, fileName);
        if (file.exists() && !append) {
            file.delete();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, !append))) {
            for (Object value : data) {
                writer.write(value.toString());
                writer.newLine();
            }
        }
    }

}
