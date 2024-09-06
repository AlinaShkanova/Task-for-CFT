import service.ServiceMethods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> filePaths = Arrays.asList("in1.txt", "in2.txt");
        String outputDir = System.getProperty("user.dir");
        String prefix = "result_";

        ServiceMethods.processFiles(filePaths, outputDir, prefix, false, false);
    }

}