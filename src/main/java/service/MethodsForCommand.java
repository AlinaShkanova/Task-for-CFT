package service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;

import static util.Constants.APPEND_OPTION;
import static util.Constants.FULL_STATISTIC_OPTION;
import static util.Constants.OUTPUT_OPTION;
import static util.Constants.PREFIX_OPTION;
import static util.Constants.QUOTES;
import static util.Constants.ROOT_DIRECTORY;
import static util.Constants.SHORT_STATISTIC_OPTION;

/**
 * Класс для обработки командной строки
 */
public class MethodsForCommand {
    /**
     * Метод для создания списка командных опций
     *
     * @return объект содержащий параметры для парсинга параметров
     * командной строки
     */
    protected static Options createOptions() {
        Options options = new Options();
        options.addOption(OUTPUT_OPTION,
                "output",
                true,
                "Каталог вывода");
        options.addOption(PREFIX_OPTION,
                "prefix",
                true,
                "Префикс для файлов вывода");
        options.addOption(APPEND_OPTION,
                "append",
                false,
                "Добавлять к существующим файлам");
        options.addOption(SHORT_STATISTIC_OPTION,
                "shortStatistics",
                false,
                "Краткая статистика");
        options.addOption(FULL_STATISTIC_OPTION,
                "fullStatistics",
                false,
                "Полная статистика");
        return options;
    }

    /**
     * Метод для парсинга командных аргументов
     *
     * @param options содержит параметры для разбора
     * @param args массив строк, содержит параметры командной строки
     * @return содержащит результаты командной строки
     * @throws org.apache.commons.cli.ParseException если произошла ошибка при разборе параметров
     */
    protected static CommandLine parseArgs(Options options, String[] args)
            throws org.apache.commons.cli.ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    /**
     * Метод для получения путей к файлам из командных аргументов
     *
     * @param cmd содержит параметры командной строки
     * @return список строк, содержащий пути к файлам
     */
    protected static List<String> getFilePaths(CommandLine cmd) {
        List<String> filePaths = new ArrayList<>();
        for (String arg : cmd.getArgs()) {
            filePaths.add(arg);
        }
        return filePaths;
    }

    /**
     * Метод для получения каталога вывода из командных опций
     * @param cmd содержит параметры командной строки
     * @return строка, содержащая путь к директории вывода
     */
    protected static String getOutputDir(CommandLine cmd) {
        return cmd.getOptionValue(OUTPUT_OPTION, System.getProperty(ROOT_DIRECTORY));
    }

    /**
     * Метод для получения префикса из командных опций
     *
     * @param cmd содержит параметры командной строки
     * @return строка, содержащая префикс
     */
    protected static String getPrefix(CommandLine cmd) {
        return cmd.getOptionValue(PREFIX_OPTION, QUOTES);
    }

    /**
     * Метод для проверки опций "-a", "-s" и "-f"
     *
     * @param cmd содержит параметры командной строки
     * @return массив содержит флаги в порядке возврата
     */
    protected static boolean[] getFlags(CommandLine cmd) {
        boolean append = cmd.hasOption(APPEND_OPTION);
        boolean shortStats = cmd.hasOption(SHORT_STATISTIC_OPTION);
        boolean fullStats = cmd.hasOption(FULL_STATISTIC_OPTION);
        return new boolean[] {append, shortStats, fullStats};
    }
}
