import service.ServiceMethods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static util.Constants.ROOT_DIRECTORY;

/**
 * Класс для запуска утилиты с кнопки в среде разработки, параметры задавать
 * с помошью переменных
 */
public class ButtonStart {
    public static void main(String[] args) throws IOException {
        // Путь к тестовым файлам
        List<String> filePaths = Arrays.asList("in1.txt", "in2.txt");
        // Задание дириктории (по дефолту в корне)
        String outputDir = System.getProperty(ROOT_DIRECTORY);
        // Задание префикса
        String prefix = "result_";
        // Обработка файлов
        ServiceMethods.processFiles(filePaths, outputDir, prefix, false, false);
    }
 }
