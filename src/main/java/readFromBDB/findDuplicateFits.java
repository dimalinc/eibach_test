package readFromBDB;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class findDuplicateFits {

    public static SessionFactory  getSessionFactory() {
         SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttributes.class).
                        buildSessionFactory();
         return factory;
    }

    public static ArrayList<ArrayList<Object>> equalCarArrayListOfArrayLists;

    public static void main(String[] args) {

        String partNumber = "'2781'";

        ArrayList<Car> listOfCarsForSelectedFitment = new ArrayList<Car>();
        // оба списка выходят одинаковыми так как объекты отличаются айдишниками
        HashSet<Car> listOfCarsEqualToBigList = new HashSet<Car>();
        //listOfCarsForSelectedFitment.add(new Car());

        Session session = getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            List<Car> allCarsList =
                    session.createQuery("SELECT a FROM newCar a", Car.class).getResultList();

            Item itemGotByPartnumber = (Item) session.createQuery
                    ("SELECT a FROM newItem a where a.ITEM_PART_NO=" + partNumber,
                    Item.class).getResultList().get(0);

            System.out.println("itemGotByPartnumber.getITEM_PART_NO() " + itemGotByPartnumber.getITEM_PART_NO());

            System.out.println("___---___--- ___---___--- ___---___--- ___---___--- ___---___---");


            System.out.println("\r\n  carlist size: " + allCarsList.size());

            /*Car newCar = session.get(Car.class, 26944);
            System.out.println(newCar);*/

            /* Fitment newFitment = session.get(Fitment.class,9000);
            System.out.println(newFitment);*/

            Item newItem = session.get(Item.class,itemGotByPartnumber.getITEM_ID());


            System.out.println(newItem);
            System.out.println("\r\n" + " * * * ItemPrinted  * * * * * * " );
          //  System.out.println( " * * *  Cars for this item are: " + "\r\n");

            for (Fitment fitment: newItem.getItemFitmentsList()) {

                listOfCarsForSelectedFitment.add( fitment.getFitmentCar() );
            }
            System.out.println(" -**- listOfCarsForSelectedFitment.size()" + listOfCarsForSelectedFitment.size());

            System.out.println("listOfCarsForSelectedFitment " + "\r\n"
                    + Arrays.toString(listOfCarsForSelectedFitment.toArray()).replaceAll("},", "},"
                    + System.getProperty("line.separator" ) ) );

            System.out.println("___---___--- ___---___--- ___---___--- ___---___--- ___---___---");


            for (Car carFromBigList:allCarsList) {
                for (Car car:listOfCarsForSelectedFitment) {
                    if (carFromBigList.equals(car) )
                        listOfCarsEqualToBigList.add(car) ;
                }
            }

            System.out.println("listOfCarsEqualToBigList.size() = " + listOfCarsEqualToBigList.size() + "\r\n");

            System.out.println("listOfCarsEqualToBigList " + "\r\n"
                    + Arrays.toString(listOfCarsEqualToBigList.toArray()).replaceAll("},", "},"
                    + System.getProperty("line.separator" ) ) );

        ///////////

            for (Car carFromBigList:allCarsList) {
                for (Car car:listOfCarsForSelectedFitment) {
                    if ( (carFromBigList.getCAR_MAKE().equals(car.getCAR_MAKE())  )
                            | (carFromBigList.getCAR_MODEL().equals(car.getCAR_MODEL()) )
                            | ( carFromBigList.getCAR_DRIVE().equals(car.getCAR_DRIVE()) )

                    ) {
                        ArrayList<Object> objectArrayList = new ArrayList<Object>();
                        objectArrayList.add(car);

                        // при каких условиях добавляем новую машину, а при каких + добавзяем айдишник к существующей


                        objectArrayList.add(car.getCAR_ID());

                        equalCarArrayListOfArrayLists.add(objectArrayList);

                    }
                }
            }

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        finally {getSessionFactory().close();}
    }
}