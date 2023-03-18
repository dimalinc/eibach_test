package test;

import entities.*;
import entities.objects.CsvRowObject;
import entities.objects.DbObject;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class main {
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
                        buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
          /*  String categoriesQuery = "select * from items where ITEM_ID=999";
            SQLQuery sqlQuery1 = session.createSQLQuery(categoriesQuery);
            List queriedItems = sqlQuery1.list();*/
            newItem = session.createQuery
                    ("SELECT a FROM Item a where a.ITEM_PART_NO=" + partNumber,
                            Item.class).getResultList().get(0);
            // newItem = session.load(Item.class,2);
            System.out.println(newItem);
            DbObject dbObject = new DbObject(newItem);
            CsvRowObject csvRowObject = new CsvRowObject(dbObject);

            System.out.println(" * * * * * ");
            System.out.println("csvRowObject = ");
            System.out.println(csvRowObject);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            factory.close();
        }
        System.out.println("Generating item done in " + (System.currentTimeMillis()-start)/1000 + " seconds or " + (System.currentTimeMillis()-start)/60000 + " minutes");
        System.out.println(System.getProperty("java.home"));
    }

}
