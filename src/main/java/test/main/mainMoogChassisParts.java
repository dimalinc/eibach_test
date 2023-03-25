package test.main;

import com.opencsv.CSVWriter;
import entities.*;
import entities.attributes_links.ItemPic;
import entities.objects.CsvRowObject;
import entities.objects.DbObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainMoogChassisParts {
    final static String brand="Moog Chassis Parts";
    final static String[] header = {"id","SKU","itemType","brand","Title"
            ,"Category","description","imgUrl","ATTRIBUTES"/*,"CarAttributes",
            "item","itemAttribute","carCategory","carCategoryString","csvAttributeObjectArrayList","csvAttributeValueStringMultimap"*/
    };

    static String csvFilePath="output_"+brand+".csv";
    public static Item newItem;

    public static void itemInit() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttribute.class).
                        buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            /*Car newCar = session.get(Car.class, 26944);
            System.out.println(newCar);*/
            /*Fitment newFitment = session.get(Fitment.class,9000);
            System.out.println(newFitment);*/
            newItem = session.get(Item.class, 2);
            System.out.println(newItem);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            factory.close();
        }
    }
    //keep 2 apostrophes H7006 ZON7763 53-291387 24-186056 24-324359 24-186728
    static String partNumber = "'" + "24-186728" + "'";

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttribute.class).
                        addAnnotatedClass(ItemPic.class).
                        buildSessionFactory();
        Session session = factory.getCurrentSession();

        int itemsCount=0;
        try {
            session.beginTransaction();
          /*  String categoriesQuery = "select * from items where ITEM_ID=999";
            SQLQuery sqlQuery1 = session.createSQLQuery(categoriesQuery);
            List queriedItems = sqlQuery1.list();*/
           /* newItem = session.createQuery
                    ("SELECT a FROM Item a where a.ITEM_PART_NO=" + partNumber,
                            Item.class).getResultList().get(0);*/
            // writing object to csv
            //  writeDataLineByLine(csvFilePath,csvRowObject.toStringArray());

            // newItem = session.load(Item.class,2);

            Long startItemList = System.currentTimeMillis();
            List<Item> itemList= session.createQuery
                    ("SELECT a FROM Item a where a.ITEM_MANUFACTURER='"+brand+"'",
                            Item.class).getResultList();
            itemsCount=itemList.size();
            System.out.println("Items queried in + " + (System.currentTimeMillis()-startItemList) + "millliseconds");

            ArrayList<CsvRowObject> csvRowObjectArrayList = new ArrayList<>();
            int n=0;
            for (Item newItem:itemList) {
                System.out.println(newItem);
                Long startItemBuild=System.currentTimeMillis();
                DbObject dbObject = new DbObject(newItem);
                CsvRowObject csvRowObject = new CsvRowObject(dbObject);
                csvRowObjectArrayList.add(csvRowObject);
                System.out.println(" * * * * * ");
                System.out.println(n++ + "___ csvRowObject = ");
                // System.out.println(csvRowObject);
                System.out.println("CsvItem build finished in + " + (System.currentTimeMillis()-startItemBuild) + "millliseconds");
            }

            List<String[]> stringArrayList = new ArrayList<>();
            for (CsvRowObject csvRowObject:csvRowObjectArrayList){
                stringArrayList.add(csvRowObject.toStringArray());
            }

            writeDataForCustomSeparatorCSV(csvFilePath,stringArrayList);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            factory.close();
        }

        System.out.println("Generating CSV for " +itemsCount + " done in " + (System.currentTimeMillis()-start)/1000 + " seconds or " + (System.currentTimeMillis()-start)/60000 + " minutes");
        System.out.println(System.getProperty("java.home"));
    }

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

    public static void writeDataForCustomSeparatorCSV(String filePath,List<String[]> data)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter with '|' as separator
            CSVWriter writer = new CSVWriter(outputfile/*, '^',
                    CSVWriter.NO_QUOTE_CHARACTER,
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
