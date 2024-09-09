package service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;

public class MethodsForCommand {

    // Метод для создания списка командных опций
    protected static Options createOptions() {
        Options options = new Options();
        options.addOption("o", "output", true, "Каталог вывода");
        options.addOption("p", "prefix", true, "Префикс для файлов вывода");
        options.addOption("a", "append", false, "Добавлять к существующим файлам");
        options.addOption("s", "shortStatistics", false, "Краткая статистика");
        options.addOption("f", "fullStatistics", false, "Полная статистика");
        return options;
    }

    // Метод для парсинга командных аргументов
    protected static CommandLine parseArgs(Options options, String[] args) throws org.apache.commons.cli.ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    // Метод для получения путей к файлам из командных аргументов
    protected static List<String> getFilePaths(CommandLine cmd) {
        List<String> filePaths = new ArrayList<>();
        for (String arg : cmd.getArgs()) {
            filePaths.add(arg);
        }
        return filePaths;
    }

    // Метод для получения каталога вывода из командных опций
    protected static String getOutputDir(CommandLine cmd) {
        return cmd.getOptionValue("o", System.getProperty("user.dir"));
    }

    // Метод для получения префикса из командных опций
    protected static String getPrefix(CommandLine cmd) {
        return cmd.getOptionValue("p", "");
    }

    // Метод для проверки опций "-a", "-s" и "-f"
    protected static boolean[] getFlags(CommandLine cmd) {
        boolean append = cmd.hasOption("a");
        boolean shortStats = cmd.hasOption("s");
        boolean fullStats = cmd.hasOption("f");
        return new boolean[] {append, shortStats, fullStats};
    }
}
