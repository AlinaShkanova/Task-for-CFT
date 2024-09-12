import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import service.MethodsForCommand;

import java.io.IOException;
import java.util.List;

import static service.ServiceMethods.processFiles;

public class Main extends MethodsForCommand {
    public static void main(String[] args) throws IOException, ParseException {
        // Создание объекта optional
        Options options = createOptions();
        // Создание обхекта cmd и парсинг командной строки
        CommandLine cmd = parseArgs(options, args);
        // Получение пути
        List<String> filePaths = getFilePaths(cmd);
        // Получение директории
        String outputDir = getOutputDir(cmd);
        // Получение префикса
        String prefix = getPrefix(cmd);
        // Получение флагов
        boolean[] flags = getFlags(cmd);
        // Обработка файлов
        processFiles(filePaths, outputDir, prefix, flags[0], flags[1] || !flags[2]);
    }
}