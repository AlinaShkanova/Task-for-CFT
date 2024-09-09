import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import service.MethodsForCommand;

import java.io.IOException;
import java.util.List;

import static service.ServiceMethods.processFiles;

public class Main extends MethodsForCommand {
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
//        List<String> filePaths = Arrays.asList("in1.txt", "in2.txt");
//        String outputDir = System.getProperty("user.dir");
//        String prefix = "result_";
//
//        ServiceMethods.processFiles(filePaths, outputDir, prefix, false, false);

        Options options = createOptions();

        CommandLine cmd = parseArgs(options, args);
        List<String> filePaths = getFilePaths(cmd);
        String outputDir = getOutputDir(cmd);
        String prefix = getPrefix(cmd);
        boolean[] flags = getFlags(cmd);
        processFiles(filePaths, outputDir, prefix, flags[0], flags[1] || !flags[2]);


    }


}