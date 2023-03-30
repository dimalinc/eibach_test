package test.main;

import com.opencsv.CSVWriter;
import entities.*;
import entities.attributes_links.Car_attributes_link;
import entities.attributes_links.Fitment_attributes_link;
import entities.attributes_links.ItemPic;
import entities.attributes_links.Item_attributes_link;
import entities.objects.CsvRowObject;
import entities.objects.CsvRowObject4Lists;
import entities.objects.DbObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main_ZoneOffroad_Lists {
    final static String brand = "Zone Offroad";
    final static String[] header = {"id", "SKU", "itemType", "brand", "Title"
            , "Category", "description", "imgUrl", "ATTRIBUTES"/*,"CarAttributes",
            "item","itemAttribute","carCategory","carCategoryString","csvAttributeObjectArrayList","csvAttributeValueStringMultimap"*/
    };

    static String csvFilePath = "output_" + brand + "_TEST_lists" + ".csv";
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
                        addAnnotatedClass(Item_attributes_link.class).
                        addAnnotatedClass(Fitment_attributes_link.class).
                        addAnnotatedClass(Car_attributes_link.class).
                        buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<ItemAttribute> allItemAttributeList = new ArrayList<>();
        List<Car> allCarList = new ArrayList<>();
        List<CarAttribute> allCarAttributeList = new ArrayList<>();
        List<Fitment> allFitmentList = new ArrayList<>();
        List<FitmentAttribute> allFitmentAttributeList = new ArrayList<>();
        List<Item_attributes_link> allItemAttributesLinkList = new ArrayList<>();
        List<Fitment_attributes_link> allfitmentAttributesLinkList = new ArrayList<>();
        List<Car_attributes_link> allCarAttributesLinkList = new ArrayList<>();
        List<ItemPic> allItemPicsListList = new ArrayList<>();
        try {
            long startListsInit = System.currentTimeMillis();
            session.beginTransaction();
            // all lists init
            allItemAttributeList = session.createQuery
                    ("SELECT a FROM ItemAttribute a ",
                            ItemAttribute.class).getResultList();
            System.out.println("allItemAttributeList " + allItemAttributeList.size());
            allCarList = session.createQuery("SELECT a FROM Car a",
                    Car.class).getResultList();
            System.out.println("allCarList " + allCarList.size());
            allCarAttributeList = session.createQuery("SELECT a FROM CarAttribute a",
                    CarAttribute.class).getResultList();
            System.out.println("allCarAttributeList " + allCarAttributeList.size());
            allFitmentList = session.createQuery("SELECT a FROM Fitment a",
                    Fitment.class).getResultList();
            System.out.println("allFitmentList " + allFitmentList.size());
            allFitmentAttributeList = session.createQuery("SELECT a FROM FitmentAttribute a",
                    FitmentAttribute.class).getResultList();
            System.out.println("allFitmentAttributeList " + allFitmentAttributeList.size());
            allItemAttributesLinkList = session.createQuery("SELECT a FROM Item_attributes_link a",
                    Item_attributes_link.class).getResultList();
            System.out.println("allItemAttributesLinkList " + allItemAttributesLinkList.size());
            allfitmentAttributesLinkList = session.createQuery("SELECT a FROM Fitment_attributes_link a",
                    Fitment_attributes_link.class).getResultList();
            System.out.println("allfitmentAttributesLinkList " + allfitmentAttributesLinkList.size());
            allCarAttributesLinkList = session.createQuery("SELECT a FROM Car_attributes_link a",
                    Car_attributes_link.class).getResultList();
            System.out.println("allCarAttributesLinkList " + allCarAttributesLinkList.size());
            allItemPicsListList = session.createQuery("SELECT a FROM ItemPic a",
                    ItemPic.class).getResultList();
            System.out.println("allItemPicsListList " + allItemPicsListList.size());
            System.out.println("Lists init finished in " + (System.currentTimeMillis() - startListsInit) / 1000 + " seconds");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            //  session.close();
        }


        session = factory.getCurrentSession();
        int itemsCount = 0;
        try {
            //  session.beginTransaction();
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
            List<Item> itemList = session.createQuery
                    ("SELECT a FROM Item a where a.ITEM_MANUFACTURER='" + brand + "'",
                            Item.class).getResultList();

            itemsCount = itemList.size();
            System.out.println("Items queried in " + (System.currentTimeMillis() - startItemList) / 1000 + " seconds");

            ArrayList<CsvRowObject4Lists> csvRowObjectArrayList = new ArrayList<>();
            int n = 0;
            for (Item newItem : itemList) {
                System.out.println(newItem);
                Long startItemBuild = System.currentTimeMillis();
                DbObject dbObject = new DbObject(newItem, allItemAttributeList,
                        allCarList, allCarAttributeList
                        , allFitmentList, allFitmentAttributeList
                        , allItemAttributesLinkList
                        , allfitmentAttributesLinkList
                        , allCarAttributesLinkList
                        , allItemPicsListList);
                CsvRowObject4Lists csvRowObject = new CsvRowObject4Lists(dbObject);
                // CsvRowObject csvRowObject = new CsvRowObject(dbObject);
                csvRowObjectArrayList.add(csvRowObject);
                System.out.println(" * * * * * ");
                System.out.println(n++ + "___ csvRowObject = ");
                // System.out.println(csvRowObject);
                System.out.println("CsvItem build finished in + " + (System.currentTimeMillis() - startItemBuild) + "millliseconds");
            }

            List<String[]> stringArrayList = new ArrayList<>();
            for (CsvRowObject4Lists csvRowObject : csvRowObjectArrayList) {
                stringArrayList.add(csvRowObject.toStringArray());
            }

            writeDataForCustomSeparatorCSV(csvFilePath, stringArrayList);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            factory.close();
        }

        System.out.println("Generating CSV for " + itemsCount + " done in " + (System.currentTimeMillis() - start) / 1000 + " seconds or " + (System.currentTimeMillis() - start) / 60000 + " minutes");
        System.out.println(System.getProperty("java.home"));
    }

    public static void writeDataLineByLine(String filePath, String[] data1) {
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeDataForCustomSeparatorCSV(String filePath, List<String[]> data) {
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
