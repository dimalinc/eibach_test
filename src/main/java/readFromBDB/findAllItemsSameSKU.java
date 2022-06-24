package readFromBDB;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class findAllItemsSameSKU {

    public static SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttributes.class).
                        buildSessionFactory();
        return factory;
    }

    public static void main(String[] args) {


        String partNumber = "'CS048R'";

        Session session = getSessionFactory().getCurrentSession();

        List<Car> allCarsList = null;

        try {
            session.beginTransaction();

            List<Item> itemsOfPartNumber = session.createQuery
                    ("SELECT a FROM Item a where a.ITEM_PART_NO=" + partNumber,
                            Item.class).getResultList();

            System.out.println("itemsOfPartNumber list:");
            System.out.println(itemsOfPartNumber);

            // get list of carIDs for itemsList

            List<Fitment> allFitmentList = session.createQuery
                    ("SELECT a FROM Fitment a where a.fitmentItem="
                                    + itemsOfPartNumber.get(0).getITEM_ID(),
                            Fitment.class).getResultList();

            allCarsList = new ArrayList<>();

            for (Item item : itemsOfPartNumber) {
                List<Fitment> tempAllFitmentList = session.createQuery
                        ("SELECT a FROM Fitment a where a.fitmentItem="
                                        + item.getITEM_ID(),
                                Fitment.class).getResultList();

           /*     System.out.println("FitmentList for each" + item);
                System.out.println(tempAllFitmentList);*/

                allFitmentList.addAll(tempAllFitmentList);

                for (Fitment fitment : tempAllFitmentList) {

                    List<Car> tempCarsList =
                            session.createQuery("SELECT a FROM Car a where a.CAR_ID="
                                            + fitment.getFitmentCar().getCAR_ID()
                                    , Car.class).getResultList();

               /*     System.out.print("tempCarsList:  ");
                    System.out.println(tempCarsList);*/

                    allCarsList.addAll(tempCarsList);
                }
            }

         /*   System.out.println("********************");
            System.out.println("allFitmentList.size = " + allFitmentList.size());
            System.out.println("********************");
            System.out.println(allFitmentList);
            System.out.println("********************");
            System.out.println("allCarsList.size = " + allCarsList.size());
            System.out.println("********************");
            System.out.println(allCarsList);*/

         /*   List<Car> allCarsList =
                    session.createQuery("SELECT a FROM Car a"
                            , Car.class).getResultList();*/

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            getSessionFactory().close();
        }

        HashMap<ArrayList<String>, ArrayList<Integer>> uniqueMakeModelYearHashMap=new HashMap<>();
       // uniqueMakeModelYearHashMap.put(new ArrayList<>(),new ArrayList<>());

        for (Car car:allCarsList) {
            ArrayList<String> makeModelDriveStringList = new ArrayList<>();
            makeModelDriveStringList.add(car.getCAR_MAKE());
            makeModelDriveStringList.add(car.getCAR_MODEL());
            makeModelDriveStringList.add(car.getCAR_DRIVE());

            if (uniqueMakeModelYearHashMap.containsKey(makeModelDriveStringList) )
                uniqueMakeModelYearHashMap.get(makeModelDriveStringList).add(car.getCAR_ID());

            else
            {    ArrayList<Integer> newIntegerArrayList = new ArrayList<>();
                newIntegerArrayList.add(car.getCAR_ID());
            uniqueMakeModelYearHashMap.put(makeModelDriveStringList,newIntegerArrayList);
            }
        }

        System.out.println("uniqueMakeModelYearHashMap size = " + uniqueMakeModelYearHashMap.size());
        System.out.println("--------------");
        System.out.println(uniqueMakeModelYearHashMap);

        //--------------//



 /*
        HashMap<Car,ArrayList<Car>> uniqueCarsHashMap = new HashMap();
        uniqueCarsHashMap.put(allCarsList.get(0),new ArrayList<Car>());

      for (Car car1 : allCarsList) {
            for (Car car2 : allCarsList) {
                if (!car1.getCAR_MAKE().equals(car2.getCAR_MAKE())
                        &!(car1.getCAR_MODEL().equals(car2.getCAR_MODEL()))
                        &!(car1.getCAR_DRIVE().equals(car2.getCAR_DRIVE())))
                {ArrayList<Car> newCarArrayList = new ArrayList<Car>();
                    newCarArrayList.add(car2);
                    uniqueCarsHashMap.put(car2,newCarArrayList);}
                else
                {
                    uniqueCarsHashMap.get(car2)
                    uniqueCarsHashMap.put(,)
                }
            }
        }
        System.out.println("uniqueCarsHashSet size = " + uniqueCarsHashMap.size());
        System.out.println(uniqueCarsHashMap);*/

        // TODO решить HashSet или SortedSet
/*        TreeSet<Car> setUniqueCars = new TreeSet<Car>(new Comparator<Car>() {

            public int compare(Car car1, Car car2) {
                // return 0 if objects are equal in terms of your properties
                if (car1.getCAR_MAKE().equals(car2.getCAR_MAKE())
                        && (car1.getCAR_MODEL().equals(car2.getCAR_MODEL())
                        && (car1.getCAR_DRIVE().equals(car2.getCAR_DRIVE()))))
                    return 0;
else
                return 1;
            }

          *//*  @Override
            public int compare(Car car1, Car car2)
            {

                // for comparison
                int MAKE = car1.getCAR_MAKE().compareTo(
                        car2.getCAR_MAKE());
                int MODEL = car1.getCAR_MODEL().compareTo(
                        car2.getCAR_MODEL());
                int DRIVE = car1.getCAR_DRIVE().compareTo(
                        car2.getCAR_DRIVE());

                // 3-level comparison
                return (MAKE == 0) ? MODEL
                        : DRIVE;
            }*//*
        });

                setUniqueCars.addAll(allCarsList);*/

        // TODO решить HashSet или SortedSet

       /* HashSet<Car> setUniqueCars = new HashSet<>();

        setUniqueCars.add(allCarsList.get(0));
        for (Car uniqueCar:setUniqueCars) {
            for (Car car:allCarsList) {

            if (!uniqueCar.getCAR_MAKE().equals(car.getCAR_MODEL())&
                    !uniqueCar.getCAR_MAKE().equals(car.getCAR_MAKE())&
                    !uniqueCar.getCAR_DRIVE().equals(car.getCAR_DRIVE())
            )
                setUniqueCars.add(car);
            }
        }
        setUniqueCars.addAll(allCarsList);*/

        // TODO внес изменения в компаратор класса Car

       /* System.out.println("Set UniqueCars size = " + setUniqueCars.size());
        System.out.println(setUniqueCars);

        HashMap<Car, ArrayList<Car>> uniqueCarsHashMap = new HashMap();

        for (Car uniqueCar : setUniqueCars) {
            ArrayList<Car> similarCarsArrayList = new ArrayList<>();
            for (Car car2 : allCarsList) {

                if (uniqueCar.getCAR_MAKE().equals(car2.getCAR_MAKE()) &
                        (uniqueCar.getCAR_MODEL().equals(car2.getCAR_MODEL())) &
                        (uniqueCar.getCAR_DRIVE().equals(car2.getCAR_DRIVE())))
                {
                    similarCarsArrayList.add(car2);
                }
            }
            System.out.println("---***---");
            System.out.println("uniqueCar = " + uniqueCar);
            System.out.println("similarCarsArrayList size =" + similarCarsArrayList.size());
            System.out.println("similarCarsArrayList = " + similarCarsArrayList);
            uniqueCarsHashMap.put(uniqueCar, similarCarsArrayList);
        }*/

        System.out.println("------------------------------------");
       // System.out.println("uniqueCarsHashMap size: " + uniqueCarsHashMap.size());
       // System.out.println(uniqueCarsHashMap);

      /*  for (Car car:uniqueCarsHashMap.keySet()) {
            System.out.println(car);
            System.out.println("similarCarsSize = "+(uniqueCarsHashMap.get(car).size()));
        }*/

    }


}


