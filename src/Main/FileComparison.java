package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileComparison {
    public static void main(String[] args) throws IOException {
        File f = new File("<PATH>/BinaryTree/src/Main/resources");
        ArrayList<File> files = new ArrayList<>(Arrays.asList(f.listFiles()));

        final String COMMA_DELIMITER = ";";
        final String NEW_LINE_SEPARATOR = "\n";

        //CSV file header
        String FILE_HEADER = "TC 1;TC 1 Lines;TC 2;TC 2 Lines;Common Lines; Similarity Ratio";
        FileWriter fileWriter = new FileWriter("/Users/hazhar/Documents/finalResult.csv");

        fileWriter.append(FILE_HEADER);
        fileWriter.append(NEW_LINE_SEPARATOR);

        System.out.println("***************************************************************************************");
        System.out.println("File Name 1\t\t\t\t | File Name 2\t\t\t\t | Common Lines\t\t| Ratio");
        System.out.println("***************************************************************************************");
        int i = 0;
        for(File file1 : files) {
            for(File file2 : files) {
                if(!file1.getName().equalsIgnoreCase(file2.getName()) && i < files.size() - 1) {
                    Double numberOfLines1 = 0.0;
                    Double numberOfLines2 = 0.0;
                    FileInputStream fstream1 = new FileInputStream(file1);
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));

                    int commonLines = 0;
                    String line1 = "";
                    while ((line1 = br1.readLine()) != null) {
                        FileInputStream fstream2 = new FileInputStream(file2);
                        BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
                        numberOfLines1++;
                        String line2 = "";
                        numberOfLines2 = 0.0;
                        while ((line2 = br2.readLine()) != null) {
                            numberOfLines2++;
                            if (line1.trim().equalsIgnoreCase(line2.trim())) {
                                commonLines++;
                            }
                        }
                    }
                    Double ratio;
                    if(numberOfLines1 < numberOfLines2) {
                        ratio =  commonLines / numberOfLines1;
                    }
                    else {
                        ratio =  commonLines/numberOfLines2;
                    }
                    fileWriter.append(file1.getName().substring(0, file1.getName().indexOf(".")));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(numberOfLines1));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(file2.getName().substring(0, file2.getName().indexOf(".")));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(numberOfLines2));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(commonLines));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(ratio));
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    System.out.println(file1.getName().substring(0, file1.getName().indexOf(".")) + "(" + numberOfLines1 + ")\t\t | " +
                            file2.getName().substring(0, file2.getName().indexOf(".")) + "(" + numberOfLines2 + ")\t\t | " +
                            commonLines + "\t\t\t\t| " +
                            ratio);
                }
            }
            i++;
        }
        System.out.println("Results are stored in Documents folder." );
        fileWriter.flush();
        fileWriter.close();
    }
}
