import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import service.ServiceMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static service.ServiceMethods.processFiles;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        List<String> filePaths = Arrays.asList("in1.txt", "in2.txt");
//        String outputDir = System.getProperty("user.dir");
//        String prefix = "result_";
//
//        ServiceMethods.processFiles(filePaths, outputDir, prefix, false, false);

        Options options = new Options();
        options.addOption("o", "output", true, "Output directory");
        options.addOption("p", "prefix", true, "Prefix for output files");
        options.addOption("a", "append", false, "Append to existing files");
        options.addOption("s", "shortStats", false, "Short statistics");
        options.addOption("f", "fullStats", false, "Full statistics");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        List<String> filePaths = new ArrayList<>();
        for (String arg : cmd.getArgs()) {
            filePaths.add(arg);
        }

        String outputDir = cmd.getOptionValue("o", System.getProperty("user.dir"));
        String prefix = cmd.getOptionValue("p", "");
        boolean append = cmd.hasOption("a");
        boolean shortStats = cmd.hasOption("s");
        boolean fullStats = cmd.hasOption("f");

        processFiles(filePaths, outputDir, prefix, append, shortStats || !fullStats);
    }


}