package utils;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class csv_Writer {
    final static String[] header = {"id","SKU","itemType","brand","Title"
            ,"Category","description","imgUrl","ATTRIBUTES"/*,"CarAttributes",
            "item","itemAttribute","carCategory","carCategoryString","csvAttributeObjectArrayList","csvAttributeValueStringMultimap"*/
    };

    public static void writeDataLineByLine(String filePath,String[] data1)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(header);

            // add data to csv
            writer.writeNext(data1);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeDataForCustomSeparatorCSV(String filePath, List<String[]> data)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter with '^' as separator
            CSVWriter writer = new CSVWriter(outputfile, '^'/*,
                    CSVWriter.NO_QUOTE_CHARACTER*//*'n'
                    CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END*/);
            // create a List which contains String array
            /*List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Name", "Class", "Marks" });
            data.add(new String[] { "Aman", "10", "620" });
            data.add(new String[] { "Suraj", "10", "630" });*/
            writer.writeAll(data);
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
