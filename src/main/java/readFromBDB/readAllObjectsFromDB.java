package readFromBDB;

import entities.*;
import entities.attributes_links.Car_attributes_link;
import entities.attributes_links.Fitment_attributes_link;
import entities.attributes_links.Item_attributes_link;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class readAllObjectsFromDB {

    public static SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class).addAnnotatedClass(CarAttribute.class).
                        addAnnotatedClass(Item.class).addAnnotatedClass(ItemAttribute.class).
                        addAnnotatedClass(Fitment.class).addAnnotatedClass(FitmentAttribute.class).
                        buildSessionFactory();
        return factory;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int numberOfItemsQueried=0;
        int ii=0;
        int iii=0;


        Session session = getSessionFactory().getCurrentSession();

        List<Car> allCarsList = null;

        try {
            session.beginTransaction();

           /* List<Item> itemsOfPartNumber = session.createQuery
                    ("SELECT a FROM Item a where a.ITEM_PART_NO=" + partNumber,
                            Item.class).getResultList();*/
            List<Item> itemsList = session.createQuery("SELECT a FROM Item a", Item.class).getResultList();
            Map<String,Item> itemsMap= new HashMap<String,Item>();
            for (Item item:itemsList) { itemsMap.put(String.valueOf(item.getITEM_ID()),item); }

           /* List<ItemAttribute> itemAttributesList = session.createQuery("SELECT a FROM ItemAttribute a", ItemAttribute.class).getResultList();
            Map<Integer,ItemAttribute> itemAttributesMap= new HashMap<Integer,ItemAttribute>();
            for (ItemAttribute itemAttribute:itemAttributesList) { itemAttributesMap.put(itemAttribute.getITEM_ATT_ID(),itemAttribute); }

            List<Car> carsList = session.createQuery("SELECT a FROM Car a",Car.class).getResultList();
            Map<String,Car> carsMap= new HashMap<String,Car>();
            for (Car car:carsList) { carsMap.put(String .valueOf( car.getCAR_ID() ),car); }

            List<CarAttribute> carAttributesList = session.createQuery("SELECT a FROM CarAttribute a",CarAttribute.class).getResultList();
            Map<String,CarAttribute> carAttributesMap= new HashMap<String,CarAttribute>();
            for (CarAttribute carAttribute:carAttributesList) { carAttributesMap.put(String.valueOf (carAttribute.getCAR_ATT_ID() ),carAttribute); }

            List<Fitment> fitmentsList = session.createQuery
                    ("SELECT a FROM Fitment a",
                            Fitment.class).getResultList();
            Map<String,Fitment> fitmentsMapByItemId= new HashMap<String,Fitment>();
            for (Fitment fitment:fitmentsList) { fitmentsMapByItemId.put(String.valueOf( fitment.getITEM_ID() ),fitment); }
            Map<String,Fitment> fitmentsMapByFitId= new HashMap<String,Fitment>();
            for (Fitment fitment:fitmentsList) { fitmentsMapByFitId.put(String.valueOf( fitment.getFIT_ID() ),fitment); }
            Map<String,Fitment> fitmentsMapByCarId= new HashMap<String,Fitment>();
            for (Fitment fitment:fitmentsList) { fitmentsMapByCarId.put(String.valueOf( fitment.getCAR_ID() ),fitment); }

            List<FitmentAttribute> fitmentAttributesList = session.createQuery("SELECT a FROM FitmentAttribute a",FitmentAttribute.class).getResultList();
            Map<String,FitmentAttribute> fitmentAttributesMap= new HashMap<String,FitmentAttribute>();
            for (FitmentAttribute fitmentAttribute:fitmentAttributesList) { fitmentAttributesMap.put(String.valueOf( fitmentAttribute.getFIT_ATT_ID() ),fitmentAttribute); }
*/
          /*  int iiii=0;
            for (Item item:itemsList) {
                iiii++;
                long startItem = System.currentTimeMillis();
                System.out.println(item);
                System.out.println("Item reading finished " + (System.currentTimeMillis() - startItem)+ " mili seconds");
                for (Fitment fitment:item.getItemFitmentsList()) {
                    long startFitment = System.currentTimeMillis();
                    System.out.println(fitment);
                    System.out.println("Fitment reading finished " + (System.currentTimeMillis() - startFitment)+ " mili seconds");
                    long startGetFitmentCar = System.currentTimeMillis();
                    Car car = fitment.getFitmentCar();
                    System.out.println(car);;
                    System.out.println("getFitmentCar reading finished " + (System.currentTimeMillis() - startGetFitmentCar)+ " mili seconds");

                    // data fields processing
                    //get attributes
                    long startgetItemAttributeList = System.currentTimeMillis();
                    System.out.println(item.getItemAttributeList());
                    System.out.println("getItemAttributeList reading finished " + (System.currentTimeMillis() - startgetItemAttributeList)+ " mili seconds");
                    long startgetFitmentAttributesList = System.currentTimeMillis();
                    System.out.println(fitment.getFitmentAttributesList());
                    System.out.println("getFitmentAttributesList reading finished " + (System.currentTimeMillis() - startgetFitmentAttributesList)+ " mili seconds");
                    long startgetCarAttributeList = System.currentTimeMillis();
                    System.out.println(car.getCarAttributeList());
                    System.out.println("getCarAttributeList reading finished " + (System.currentTimeMillis() - startgetCarAttributeList)+ " mili seconds");

                }
                if (iiii>10) break;
            }*/
            for (Item item:itemsList) {
                iii++;
                StringBuilder sb = new StringBuilder();
                sb.append(item);
                List<ItemAttribute> itemAttributeList = item.getItemAttributeList();
                sb.append(itemAttributeList);
                for (Fitment fitment:item.getItemFitmentsList()) {
                    sb.append(fitment);
                    List<FitmentAttribute> fitmentAttributeList = fitment.getFitmentAttributesList();                    sb.append(fitment);
                    sb.append(fitmentAttributeList);
                    Car car = fitment.getFitmentCar();
                    sb.append(car);
                    List<CarAttribute> carAttributeList = car.getCarAttributeList();
                    sb.append(carAttributeList);
                }
                System.out.println(sb.toString());
                System.out.println(iii);
                if (iii>1000) break;
            }

         /*   String item_attributes_links_Query = "select * from item_attributes_link";
            SQLQuery sqlQuery_item_attributes_link = session.createSQLQuery(item_attributes_links_Query);
            List item_attributes_links_List = sqlQuery_item_attributes_link.list();
            ArrayList<Item_attributes_link> Item_attributes_linkArrayList = new ArrayList<>();
            Iterator Item_attributes_link_Iterator = item_attributes_links_List.iterator();
            while (Item_attributes_link_Iterator.hasNext()) {
                Object[] object = (Object[]) Item_attributes_link_Iterator.next();
                Item_attributes_linkArrayList.add(new Item_attributes_link((Integer)object[0],(Integer)object[1],(Integer)object[2])); }
            System.out.println("Item_attributes_linkArrayList.size = " + Item_attributes_linkArrayList.size());
            System.out.println("reading Item_attributes_links " + (System.currentTimeMillis() - start) / 1000 + " seconds");

            String car_attributes_links_Query = "select * from car_attributes_link";
            SQLQuery sqlQuery_car_attributes_link = session.createSQLQuery(car_attributes_links_Query);
            List car_attributes_links_List = sqlQuery_car_attributes_link.list();
            ArrayList<Car_attributes_link> Car_attributes_linkArrayList = new ArrayList<>();
            Iterator Car_attributes_link_Iterator = car_attributes_links_List.iterator();
            while (Car_attributes_link_Iterator.hasNext()) {
                Object[] object = (Object[]) Car_attributes_link_Iterator.next();
                Car_attributes_linkArrayList.add(new Car_attributes_link((Integer)object[0],(Integer)object[1],(Integer)object[2])); }
            System.out.println("Car_attributes_linkArrayList.size = " + Car_attributes_linkArrayList.size());
            System.out.println("reading Car_attributes_links " + (System.currentTimeMillis() - start) / 1000 + " seconds");

            String fitment_attributes_links_Query = "select * from fitment_attributes_link";
            SQLQuery sqlQuery_fitment_attributes_link = session.createSQLQuery(fitment_attributes_links_Query);
            List fitment_attributes_links_List = sqlQuery_fitment_attributes_link.list();
            ArrayList<Fitment_attributes_link> fitment_attributes_linkArrayList = new ArrayList<>();
            Iterator fitment_attributes_link_Iterator = fitment_attributes_links_List.iterator();
            while (fitment_attributes_link_Iterator.hasNext()) {
                Object[] object = (Object[]) fitment_attributes_link_Iterator.next();
                fitment_attributes_linkArrayList.add(new Fitment_attributes_link((Integer)object[0],(Integer)object[1],(Integer)object[2])); }
            System.out.println("Fitment_attributes_linkArrayList.size = " + fitment_attributes_linkArrayList.size());
            System.out.println("reading Fitment_attributes_links " + (System.currentTimeMillis() - start) / 1000 + " seconds");
*/
            // get all cars for item takes 10-12+sec need to make faster

           /* long startItemsList = System.currentTimeMillis();
            for (Item item: itemsList) {
                List<Car> carsOfItemList = new ArrayList<>();
                if (ii>100) break;
                long startItemFitmentsList = System.currentTimeMillis();
                for (Fitment fitmentOfItem: item.getItemFitmentsList()) {
                    carsOfItemList.add(fitmentOfItem.getFitmentCar());
                    // carsOfItemList.add(carsList.get( carsList.indexOf(fitmentOfItem.getFitmentCar())  ) ) ;
                    System.out.println(fitmentOfItem.getFitmentAttributesList());
                }
                System.out.println(" --- ItemFitmentsList finished " + (System.currentTimeMillis() - startItemFitmentsList) + " mili seconds");


                // Car carOfItem = fitmentsMapByItemId.get(item.getITEM_ID()).getFitmentCar();

                System.out.println(carsOfItemList);
                ii++;
                long startCarOfItemList = System.currentTimeMillis();
                for (Car carOfItem:carsOfItemList){
                    System.out.println("  carOfItem.getCarAttributeList() " + carOfItem.getCarAttributeList());
                //    System.out.println(carOfItemList.getCarFitment());
                }
                System.out.println(" --- CarOfItemList finished " + (System.currentTimeMillis() - startCarOfItemList) + " mili seconds");

            }
            System.out.println(" --- ItemsList finished " + (System.currentTimeMillis() - startItemsList) + " mili seconds");
*/

            // get all attributes for item - takes 50ms for item no need to invent
            /*int iii=0;
            for (Item item: itemsList) {
                iii++;
                long startGettingItemAttributeList = System.currentTimeMillis();
                System.out.println(iii + "item.getItemAttributeList().size() = " + item.getItemAttributeList().size());
                System.out.println(iii + " reading ItemAttributeList " + (System.currentTimeMillis() - startGettingItemAttributeList) + "miliseconds");
                if (iii>50) break;
            }*/

            /*System.out.println("items list:");
            System.out.println(itemsList);*/
         /*   System.out.println("-------------");
            System.out.println("items map size = " + itemsMap.size());
            System.out.println("cars map size = " + carsMap.size());
            System.out.println("fitments map size = " + fitmentsMap.size());
            System.out.println("-------------");*/

           /* for (Item item:itemsList) {
                long itemStart = System.currentTimeMillis();

                numberOfItemsQueried++;
                // don't process reservoir mounts
                if (item.getITEM_ID()!=149) {
                    System.out.println("_*_*_*_");
                    System.out.println("_*_*_*_");
                    System.out.println(item);
                    System.out.println("_*_*_*_");
                    System.out.println(item.getItemAttributeList());
                    System.out.println("-----");
                    System.out.println(item.getItemFitmentsList());
                    System.out.println("-----");

                    for (Fitment fitment : item.getItemFitmentsList()) {
                        System.out.println(fitment.getFitmentCar());
                    }
                    *//*List<Car> carsListForItem = session.createQuery
                            ("SELECT a FROM Car a where a.CAR_ID=" + item.getItemFitmentsList().get(0).getFitmentCar().getCAR_ID(),
                                    Car.class).getResultList();*//*

                    System.out.println("_*_*_*_");
                    System.out.println( " ___  " + "reading item  " + item.getITEM_ID() + " ___  " + (System.currentTimeMillis() - itemStart) / 1000 + " seconds");
                }
                   // if (numberOfItemsQueried>1000) break;
                }*/

            // get list of carIDs for itemsList
           /* List<Fitment> allFitmentList = session.createQuery
                    ("SELECT a FROM Fitment a where a.fitmentItem="
                                    + itemsList.get(0).getITEM_ID(),
                            Fitment.class).getResultList();
            System.out.println("----");
            System.out.println(allFitmentList);

            allCarsList = new ArrayList<>();*/

          /*  for (Item item : itemsList) {
                List<Fitment> tempAllFitmentList = session.createQuery
                        ("SELECT a FROM Fitment a where a.fitmentItem="
                                        + item.getITEM_ID(),
                                Fitment.class).getResultList();

           *//*     System.out.println("FitmentList for each" + item);
                System.out.println(tempAllFitmentList);*//*

                allFitmentList.addAll(tempAllFitmentList);

                for (Fitment fitment : tempAllFitmentList) {

                    List<Car> tempCarsList =
                            session.createQuery("SELECT a FROM Car a where a.CAR_ID="
                                            + fitment.getFitmentCar().getCAR_ID()
                                    , Car.class).getResultList();

               *//*     System.out.print("tempCarsList:  ");
                    System.out.println(tempCarsList);*//*

                    allCarsList.addAll(tempCarsList);
                }
            }*/

         /* System.out.println("********************");
            System.out.println("allFitmentList.size = " + allFitmentList.size());
            System.out.println("********************");
            System.out.println(allFitmentList);
            System.out.println("********************");
            System.out.println("allCarsList.size = " + allCarsList.size());
            System.out.println("********************");
            System.out.println(allCarsList);*/

         /*  List<Car> allCarsList =
                    session.createQuery("SELECT a FROM Car a"
                            , Car.class).getResultList();*/

            /*System.out.println("items number = "+ itemsList.size() );
            System.out.println("fitments number = ");
            System.out.println("cars number = ");*/

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            getSessionFactory().close();
        }

        System.out.println("reading all objects finished " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"
                + "  for " + iii + " items" );

    }

}
