package test;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class connectionTest {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttributes.class).
                        buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            /*Car newCar = session.get(Car.class, 26944);
            System.out.println(newCar);*/

            Item newItem = session.get(Item.class,2);
            System.out.println(newItem);

            /*Fitment newFitment = session.get(Fitment.class,9000);
            System.out.println(newFitment);*/

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        finally {factory.close();}
    }
}
