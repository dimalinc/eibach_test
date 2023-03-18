package readFromBDB;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class carsUniqueWrite {

    public static SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttribute.class).
                        addAnnotatedClass(CarUnique.class).
                        buildSessionFactory();
        return factory;
    }

    public static void main(String[] args) {

        Session session = getSessionFactory().getCurrentSession();
        HashSet<CarUnique> carUniqueHashSet = new HashSet<CarUnique>();

        ArrayList<CarUnique> carUniqueArrayList= new  ArrayList<CarUnique>();

        List<Car> allCarsList;

        try {

            int n=0;
            session.getTransaction().begin();

            allCarsList =
                    session.createQuery("SELECT a FROM Car a", Car.class).getResultList();

            System.out.println("First car from allcarList = " + allCarsList.get(0));


            for (Car car : allCarsList) {
                CarUnique carUnique = new CarUnique(car);
                carUniqueHashSet.add(carUnique);
              //  System.out.println(carUnique + " added to HashSet");
              //  car.setCAR_UNIQUE_ID(carUnique.getCAR_UNIQUE_ID());
                session.save(car);
            }

        /*  System.out.println("hashSetcarUniqueObjectWoID.size()" + hashSetcarUniqueObjectWoID.size());
            for(CarUniqueObjectWoID carUniqueObjectWoID: hashSetcarUniqueObjectWoID)
            carUniqueArrayList.add(new CarUnique(n++,carUniqueObjectWoID.getYEAR_START(),carUniqueObjectWoID.getYEAR_FINISH(),carUniqueObjectWoID.getCAR_MAKE(),carUniqueObjectWoID.getCAR_MODEL(),carUniqueObjectWoID.getCAR_SUBMODEL(),carUniqueObjectWoID.getCAR_DRIVE()));
*/
            session.getTransaction().commit();
            session.close();

            session = getSessionFactory().getCurrentSession();
            session.getTransaction().begin();

            for (CarUnique carUnique : carUniqueHashSet) {
             //   System.out.println(carUnique + "saving");
                session.persist(carUnique);
            }
            System.out.println("carUnique list length = " + carUniqueHashSet.size());

            session.getTransaction().commit();
            session.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            getSessionFactory().close();
        }
    }

}
