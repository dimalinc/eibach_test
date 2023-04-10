package entities.objects;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import entities.*;
import entities.attributes_links.Car_attributes_link;
import entities.attributes_links.Fitment_attributes_link;
import entities.attributes_links.ItemPic;
import entities.attributes_links.Item_attributes_link;
import test.main.prop;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvRowObject {

    public static class CustomSortComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
          /*  Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[ ][a-zA-Z0-9]+[ ][0-9]{4}[-][0-9]{4}");
            Matcher matcher1 = pattern.matcher(o1);
            Matcher matcher2 = pattern.matcher(o2);
           try {
               if (matcher1.group(0).compareTo(matcher2.group(0)) > 0) {
                   return 1;
               }
               if (matcher1.group(0).compareTo(matcher2.group(0)) < 0) {
                   return -1;
               }
               if (matcher1.group(0).equals(matcher2.group(0)) ) {
                   pattern = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9]+[ ]a-zA-Z0-9]+[ ][0-9]{4}[-][0-9]{4}");
                   matcher1 = pattern.matcher(o1);
                   matcher2 = pattern.matcher(o2);
                   if (matcher1.group(0).compareTo(matcher2.group(0)) > 0) {
                       return 1;
                   }
                   if (matcher1.group(0).compareTo(matcher2.group(0)) < 0) {
                       return -1;
                   }
                   if (matcher1.group(0).equals(matcher2.group(0))  ) {
                       pattern = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9]+[a-zA-Z0-9]+[ ][a-zA-Z0-9]+[ ][0-9]{4}[-][0-9]{4}");
                       matcher1 = pattern.matcher(o1);
                       matcher2 = pattern.matcher(o2);
                       if (matcher1.group(0).compareTo(matcher2.group(0)) > 0) {
                           return 1;
                       }
                       if (matcher1.group(0).compareTo(matcher2.group(0)) < 0) {
                           return -1;
                       }
                   }
               }
           } catch (IllegalStateException e) {e.printStackTrace();}*/
               /* for (int i = 0; i < matcher1.group(0).length() - 1; i++) {
                    if (matcher1.group(0).charAt(i) > matcher2.group(0).charAt(i)) {
                        return 1;
                    }
                    if (matcher1.group(0).charAt(i) < matcher2.group(0).charAt(i)) {
                        return -1;
                    }
                }*/

            String s1=o1.substring(o1.indexOf(" "));
            String s2=o2.substring(o1.indexOf(" "));


            int i = s1.compareTo(s2 );
            System.out.println(i);
            if (i!=0) {
                return i;
            } else
            return returnCompareBytes(o1, o2);
        }

        private int returnCompareBytes(String key1, String key2) {
            for (int i = 0; i < key1.length() - 1; i++) {
                if (key1.charAt(i) > key2.charAt(i)) {
                    return 1;
                }
                if (key1.charAt(i) < key2.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }

    // TODO: check slash \ import for manuals urls
    final ArrayList<String> charExceptionArrayList = new ArrayList<>(Arrays.asList("null",/*"/",*/ "\\", "$", "&", "%", "<", ">", "*", "#", "'", "\"", "`", "~", "(", ")", "[", "]", "{", "}", "|", "="));
    final ArrayList<String> exceptionsForDescArrayList = new ArrayList<>(Arrays.asList("Start", "Finish", "Full", "Price"));
    Map<String, String> imgBrandsMap = new HashMap<String, String>() {{
        put("Bilstein", "bilstein");
        put("Eibach", "eibach");
        put("FOX", "fox");
        put("Fox Racing Shox - Truck & Offroad", "fox");
        put("Gabriel", "gabriel");
        put("Icon Vehicle Dynamics", "icon");
        put("King Shocks", "king");
        put("Koni", "koni");
        put("KYB Shocks", "kyb");
        put("Monroe", "monroe");
        put("Moog Chassis Parts", "moog");
        put("Old Man Emu", "ome");
        put("Pro Comp Suspension", "procomp");
        put("Rancho", "rancho");
        put("Skyjacker", "skyjacker");
        put("Zone Offroad", "zone");
    }};

    private static final String attributeSeparator = "; " /*+ "\r\n"*/;

    DbObject dbObject;
    //  List<String,String> csvAttributeValueStringList = new ArrayList<>();
    //содержит FitmentAttributes и CarAttributes сырые атрибуты из базы.
    Multimap<String, String> csvAttributeValueStringMultimap = LinkedHashMultimap.create();

    Item item;
    List<ItemPic> itemPicsList = new ArrayList<>();
    List<ItemAttribute> itemAttributeList;
    List<Fitment> fitmentList;
    List<Car> carList;
    List<FitmentAttribute> fitmentAttributeList;
    List<CarAttribute> carAttributeList;


    Map<Fitment, Car> mapFitmentCar = new LinkedHashMap<>();
    Map<Fitment, List<FitmentAttribute>> mapFitmentFitmentAttributesList = new LinkedHashMap<>();
    Map<Car, List<CarAttribute>> mapCarCarAttributesList = new LinkedHashMap<>();

    //TODO: проверить инициализацию item id ?
    int id/*=item.getITEM_ID()*/;
    String sku;
    String itemType;
    String brand;
    String series;
    String upperMount;
    String lowerMount;
    String extendedLength;
    String collapsedLength;
    String title;

    ArrayList<String> carCategoryAttributesList = new ArrayList<>();
    TreeSet<String> carCategoriyAttributesTreeSet = new TreeSet<>();
    String carCategoryAttributeString;

    String makeAttributeString;
    String modelAttributeString;
    String yearStartAttributeString;
    String yearFinishAttributeString;
    String driveAttributeString;
    ArrayList<String> driveAttributeStringArrayList = new ArrayList<>();

    String positionAttributeString;
    String yearAttributeString;
    String liftAttributeString;
    String otherAttributeString;

    String description;
    String imgUrl;

    ArrayList<CsvAttributeObject> csvAttributeObjectArrayList = new ArrayList<>();
    LinkedHashSet<CsvAttributeObject> yearCsvAttributeObjectsLinkedHashSet;

    public CsvRowObject(DbObject dbObject, List<ItemAttribute> allItemAttributeList,
                        List<Car> allCarList, List<CarAttribute> allCarAttributeList,
                        List<Fitment> allFitmentList, List<FitmentAttribute> allFitmentAttributeList,
                        List<Item_attributes_link> allItemAttributesLinkList,
                        List<Fitment_attributes_link> allfitmentAttributesLinkList,
                        List<Car_attributes_link> allCarAttributesLinkList) {

        CsvRowObject csvRowObject = new CsvRowObject(dbObject);


        for (Item_attributes_link item_attributes_link : allItemAttributesLinkList)
            for (ItemAttribute itemAttribute : allItemAttributeList)
                if ((csvRowObject.item.getITEM_ID() == item_attributes_link.getITEM_ID())
                        && (itemAttribute.getITEM_ATT_ID() == item_attributes_link.getITEM_ATT_ID()))
                    csvRowObject.itemAttributeList.add(itemAttribute);

        for (Fitment fitment : allFitmentList)
            if (csvRowObject.item.getITEM_ID() == fitment.getITEM_ID())
                csvRowObject.fitmentList.add(fitment);

        if (fitmentList != null) {
            for (Car car : allCarList)
                for (Fitment fitment : fitmentList)
                    if (fitment.getCAR_ID() == car.getCAR_ID())
                        csvRowObject.carList.add(car);


            for (Fitment_attributes_link fitment_attributes_link : allfitmentAttributesLinkList)
                for (FitmentAttribute fitmentAttribute : allFitmentAttributeList)
                    if (fitmentAttribute.getFIT_ATT_ID() == fitment_attributes_link.getFIT_ID())
                        csvRowObject.fitmentAttributeList.add(fitmentAttribute);

            for (Car_attributes_link car_attributes_link : allCarAttributesLinkList)
                for (CarAttribute carAttribute : allCarAttributeList)
                    if (carAttribute.getCAR_ATT_ID() == car_attributes_link.getCAR_ATT_ID())
                        csvRowObject.carAttributeList.add(carAttribute);
        }
    }

    public CsvRowObject(DbObject dbObject) {
        //  this.id = item.getITEM_ID();
        this.dbObject = dbObject;
        this.item = dbObject.getItem();
        this.id = item.getITEM_ID();
        // System.out.println("Item id when building CsvRowObject = " + id);
        this.sku = item.getITEM_PART_NO();
        this.itemType = item.getITEM_TYPE();
        this.brand = item.getITEM_MANUFACTURER();
        itemAttributeList = item.getItemAttributeList();

        this.carList=dbObject.getCarList();
        initLengthMountSeries();

        // generating YearAttributesList
        ArrayList<CsvAttributeObject> yearCsvAttributeObjectsArrayList = generateYearAttributesList(dbObject.getCarList());
        for (CsvAttributeObject yearCsvAttributeObject : yearCsvAttributeObjectsArrayList) {
            // checking duplicate year attributes
            if (!csvAttributeObjectArrayList.contains(yearCsvAttributeObject))
                csvAttributeObjectArrayList.add(yearCsvAttributeObject);
        }
        yearCsvAttributeObjectsLinkedHashSet = new LinkedHashSet<>(yearCsvAttributeObjectsArrayList);

        carCategoryAttributesList.addAll(generateCarCategoryAttributesList(dbObject.getCarList()));
        TreeSet<String> carMakesTreeSet = new TreeSet<>();
        TreeSet<String> carMakeAndModelTreeSet = new TreeSet<>();
        for (Car car : dbObject.getCarList()) {
            carMakesTreeSet.add(car.getCAR_MAKE());
            carMakeAndModelTreeSet.add(car.getCAR_MAKE() + " " + car.getCAR_MODEL());
        }

        // ToDo: проверить как работают новые carCategories через TreeSet
        carCategoriyAttributesTreeSet.addAll(carMakesTreeSet);
        carCategoriyAttributesTreeSet.addAll(carMakeAndModelTreeSet);
        carCategoriyAttributesTreeSet.addAll(carCategoryAttributesList);

        carCategoryAttributeString = buildCarCategoryAttributeString(carCategoriyAttributesTreeSet);

        this.fitmentList = item.getItemFitmentsList();
        mapsInit();
        csvAttributeValueStringMultimapInit();

        generatePositionAttributes();

        generateLiftCsvAttributes();

        generateDriveAttributesForCar();

        generateItemPicsUrlAttributes();
        // System.out.println("itemPicsList = " + itemPicsList);

        generateItemDescription();

        generateTitle();

        generateMakeModelAttributes();

    }

    private void generateMakeModelAttributes(){
        for (Car car:carList){
            csvAttributeValueStringMultimap.put("Make", car.getCAR_MAKE());
            csvAttributeValueStringMultimap.put("Model", car.getCAR_MODEL());
            csvAttributeValueStringMultimap.put("CarLine", car.getCAR_MAKE()+" "+car.getCAR_MODEL());
        }


    }

    private void generateTitle() {
        title = sku + " " + itemType + " " + brand;
    }

    private void generateItemDescription() {
        //  sorting by car
        Multimap<String, String> matcherFoundCsvAttributeValueStringMultimap = TreeMultimap.create(
              //  new CustomSortComparator(), Comparator.naturalOrder()
        );
        Multimap<String, String> notFoundCsvAttributeValueStringMultimap = LinkedHashMultimap.create();

        //[a-zA-Z0-9\-]
        //[^ ]+
        Pattern pattern = Pattern.compile("([ ][0-9]{4}[-][0-9]{4})|([^ ][ ][0-9]{4}[-][0-9]{4})");
        for (String key : csvAttributeValueStringMultimap.keys()) {
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                //   System.out.println("matcher.group(0)"+matcher.group(0));
                matcherFoundCsvAttributeValueStringMultimap.put(key.substring(key.indexOf(" ")), key);
            } else {
                notFoundCsvAttributeValueStringMultimap.put(key, key);
            }
        }
      //  System.out.println(matcherFoundCsvAttributeValueStringMultimap);
        StringBuilder sb = new StringBuilder();

        sb = new StringBuilder();
        for (String key : notFoundCsvAttributeValueStringMultimap.keySet()) {
            if ((!sb.toString().contains(key)) && (!containsExceptions(key, exceptionsForDescArrayList)))
                sb.append(key/*.replace("Position On Vehicle:","PositionOnVehicle")*/ +
                        ": " + csvAttributeValueStringMultimap.get(key)).append(System.lineSeparator());
        }
        for (String key0 : matcherFoundCsvAttributeValueStringMultimap.keySet()) {
            for (String key : matcherFoundCsvAttributeValueStringMultimap.get(key0))
                if ((!sb.toString().contains(key)) && (!containsExceptions(key, exceptionsForDescArrayList)))
                sb.append(key.replace("Position On Vehicle:","PositionOnVehicle") +
                        ": " + csvAttributeValueStringMultimap.get(key)).append(System.lineSeparator());
        }

        description = sb.toString().trim();
       // System.out.println(description);
    }

    private boolean containsExceptions(String checkedString, ArrayList<String> exceptionsStringArrayList) {
        boolean b = false;
        for (String exception : exceptionsStringArrayList) {
            if ((!checkedString.contains("http")) && checkedString.contains(exception)) {
                b = true;
                break;
            }
        }
        return b;
    }

    private void generateItemPicsUrlAttributes() {
        itemPicsList = item.getItemPicsList();
        StringBuilder sb = new StringBuilder();
        for (ItemPic itemPic : itemPicsList) {
            sb.append(itemPic.getFILE_NAME()).append(attributeSeparator);
        }
        imgUrl = sb.toString().trim();
    }

    // сгенерировать драйв-атрибуты для машины
    private void generateDriveAttributesForCar() {
        for (Fitment fitment : item.getItemFitmentsList()) {
            csvAttributeValueStringMultimap.put("Drive " + fitment.getFitmentCar().getCarLine(), fitment.getFitmentCar().getCAR_DRIVE());
            driveAttributeStringArrayList.add("Drive " + fitment.getFitmentCar().getCarLine() + " " + fitment.getFitmentCar().getCAR_DRIVE());
        }
    }

    private void generateLiftCsvAttributes() {

        ArrayList<CsvAttributeObject> liftStartCsvAttributeObjectArrayList = new ArrayList<>();
        ArrayList<CsvAttributeObject> liftFinishCsvAttributeObjectArrayList = new ArrayList<>();

        // init liftStart and LiftFinish multimaps
        for (String key : csvAttributeValueStringMultimap.keys()) {

            int n = 0;
            if ((key.startsWith("Lift Start ")))
                for (String csvAttValue : csvAttributeValueStringMultimap.get(key))
                    liftStartCsvAttributeObjectArrayList.add(new CsvAttributeObject(key, csvAttValue, n++));

            if ((key.startsWith("Lift Finish ")))
                for (String csvAttValue : csvAttributeValueStringMultimap.get(key))
                    liftFinishCsvAttributeObjectArrayList.add(new CsvAttributeObject(key, csvAttValue, n++));
        }

        for (CsvAttributeObject csv_LiftStart_AttributeObject : liftStartCsvAttributeObjectArrayList) {
            String attributeName = csv_LiftStart_AttributeObject.getAttributeName();
            String carLine = attributeName.replace("Lift Start ", "");

            double liftStartValue = 0;
            try {
                liftStartValue = Double.parseDouble(csv_LiftStart_AttributeObject.getAttributeValue());
            } catch (NumberFormatException e) {
               // e.printStackTrace();
                liftStartValue = 0;
            }

            double liftFinishValue = 0;
            for (CsvAttributeObject csvAttributeObject : liftFinishCsvAttributeObjectArrayList) {
                if (csvAttributeObject.getAttributeName().contains("Lift Finish " + carLine))
                    try {
                        liftFinishValue = Double.parseDouble(csvAttributeObject.getAttributeValue());
                    } catch (NumberFormatException e) {
                        // e.printStackTrace();
                         liftFinishValue = 0;
                    }
            }

            ArrayList<Double> liftRange = new ArrayList<>();
            double liftValue = liftStartValue;
            int n = 0;
            while (liftValue <= liftFinishValue) {
                liftRange.add(liftValue);
                csvAttributeValueStringMultimap.put("Lift " + carLine, String.valueOf(liftValue));
                csvAttributeObjectArrayList.add(new CsvAttributeObject("Lift " + carLine, String.valueOf(liftValue), n++));
                liftValue = liftValue + 0.25;
            }
            csvAttributeValueStringMultimap.put("Lift " + carLine, String.valueOf(liftFinishValue));
            csvAttributeObjectArrayList.add(new CsvAttributeObject("Lift " + carLine, String.valueOf(liftValue), n++));

        }
        //liftRangeArrayList to csvLiftRangeArrayList
    }

    private void generatePositionAttributes() {
        ArrayList<String> positionArrayList = new ArrayList<>();
        for (String key : csvAttributeValueStringMultimap.keys()) {
            if ((key.startsWith("Position")) && (!key.startsWith("Position On")))
                positionArrayList.add(key);
        }
        //  System.out.println("getting Position from multimap");
        for (String key : positionArrayList) {
            //  System.out.println( csvAttributeValueStringMultimap.get(key) );
            int n = 0;
            for (String attValue : csvAttributeValueStringMultimap.get(key)) {
                csvAttributeValueStringMultimap.put(key, attValue);
                csvAttributeObjectArrayList.add(new CsvAttributeObject(key, attValue, n++));
            }
        }
    }

    private void csvAttributeValueStringMultimapInit() {

        for (ItemAttribute itemAttribute : itemAttributeList) {
            csvAttributeValueStringMultimap.put(itemAttribute.getITEM_ATT_NAME() + " ", itemAttribute.getITEM_ATT_VALUE());
        }

        int numberOfFitmentAndCarAttributesForOneItem = itemAttributeList.size();

        for (Fitment fitment : fitmentList) {
            List<FitmentAttribute> fitmentAttributeList = mapFitmentFitmentAttributesList.get(fitment);
            numberOfFitmentAndCarAttributesForOneItem = numberOfFitmentAndCarAttributesForOneItem + fitmentAttributeList.size();
            for (FitmentAttribute fitmentAttribute : fitmentAttributeList) {
                Car car = mapFitmentCar.get(fitment);
                String carline = generateCarLine(car);

                String fiaName = fitmentAttribute.getFIT_ATT_NAME();
                String fiaValue = fitmentAttribute.getFIT_ATT_VALUE();
                csvAttributeValueStringMultimap.put(fiaName + " " + carline, fiaValue);

                List<CarAttribute> carAttributeList = car.getCarAttributeList();
                numberOfFitmentAndCarAttributesForOneItem = numberOfFitmentAndCarAttributesForOneItem + carCategoryAttributesList.size();
                for (CarAttribute carAttribute : carAttributeList) {
                    String carAttName = carAttribute.getCAR_ATT_NAME();
                    String carAttValue = carAttribute.getCAR_ATT_VALUE();
                    csvAttributeValueStringMultimap.put(carAttName + " " + carline, carAttValue);
                }
            }
        }
        System.out.println("* _ * _ * numberOfFitmentAndCarAttributesForOneItem = " + numberOfFitmentAndCarAttributesForOneItem);
    }

    private void mapsInit() {
        for (Fitment fitment : fitmentList) {
            mapFitmentCar.put(fitment, fitment.getFitmentCar());
            mapFitmentFitmentAttributesList.put(fitment, fitment.getFitmentAttributesList());
            mapCarCarAttributesList.put(fitment.getFitmentCar(), fitment.getFitmentCar().getCarAttributeList());
        }
    }


    private String generateCarLine(Car car) {
        StringBuilder sb = new StringBuilder();
        sb.append(car.getCAR_MAKE()).append(" ").append(car.getCAR_MODEL()).append(" ").append(car.getYEAR_START()).append("-").append(car.getYEAR_FINISH());
        return sb.toString();
    }

    private String buildCarCategoryAttributeString(TreeSet<String> carCategoryAttributesList) {

        StringBuilder sb = new StringBuilder();
        for (String s : carCategoryAttributesList) {
            sb.append(s).append(attributeSeparator);
        }
        if (sb.toString().length() > 2) sb.replace(sb.toString().length() - 2, sb.toString().length(), "");
        return sb.toString();
    }

    private void initLengthMountSeries() {
        for (ItemAttribute itemAttribute : itemAttributeList) {
            if (itemAttribute.getITEM_ATT_NAME().contains("Upper Mount"))
                upperMount = itemAttribute.getITEM_ATT_VALUE();
            if (itemAttribute.getITEM_ATT_NAME().contains("Lower Mount"))
                lowerMount = itemAttribute.getITEM_ATT_VALUE();
            if (itemAttribute.getITEM_ATT_NAME().contains("Extended Length"))
                extendedLength = itemAttribute.getITEM_ATT_VALUE();
            if (itemAttribute.getITEM_ATT_NAME().contains("Collapsed Length"))
                collapsedLength = itemAttribute.getITEM_ATT_VALUE();
            if (itemAttribute.getITEM_ATT_NAME().contains("Serie"))
                series = itemAttribute.getITEM_ATT_VALUE();
        }
    }

    private ArrayList<String> generateCarCategoryAttributesList(List<Car> carArrayList) {
        ArrayList<String> carCategoriesArrayList = new ArrayList<>();
        for (Car car : carArrayList) {
            StringBuilder sb = new StringBuilder();
            sb.append(car.getCAR_MAKE());
            if (!carCategoriesArrayList.contains(sb.toString()))
                carCategoriesArrayList.add(sb.toString());

            sb=new StringBuilder();
            sb.append(car.getCAR_MAKE()).append("/").append(car.getCAR_MODEL());
            if (!carCategoriesArrayList.contains(sb.toString()))
                carCategoriesArrayList.add(sb.toString());

            sb=new StringBuilder();
            sb.append(car.getCAR_MAKE()).append("/").
            append(car.getCAR_MAKE()).append(" ").append(car.getCAR_MODEL()).append("/").
            append(car.getCAR_MAKE()).append(" ").append(car.getCAR_MODEL()).append(" ").append(car.getYEAR_START()).append("-").append(car.getYEAR_FINISH());
            if (!carCategoriesArrayList.contains(sb.toString()))
                carCategoriesArrayList.add(sb.toString());

        }
       // System.out.println(carCategoriesArrayList);
        return carCategoriesArrayList;
    }

    private ArrayList<CsvAttributeObject> generateYearAttributesList(List<Car> carArrayList) {
        ArrayList<CsvAttributeObject> yearAttributesList = new ArrayList<>();
        for (Car car : carArrayList) {
            ArrayList<Integer> yearRangeList = generateYearRange(car);
            String carCategoryName = generateCarCategoryName(car);
            int n = 0;
            for (int year : yearRangeList) {
                CsvAttributeObject csvAttributeObject = new CsvAttributeObject("Year " + carCategoryName, String.valueOf(year), n++);
                // проверка на содержание такого же объекта
                if (!yearAttributesList.contains(csvAttributeObject))
                    yearAttributesList.add(csvAttributeObject);
                csvAttributeValueStringMultimap.put("Year " + carCategoryName, String.valueOf(year));
            }
        }
        return yearAttributesList;
    }

    private ArrayList<Integer> generateYearRange(Car car) {
        ArrayList<Integer> yearRangeList = new ArrayList<>();
        int yearsQty = car.getYEAR_FINISH() - car.getYEAR_START() + 1;
        for (int i = 0; i < yearsQty; i++) {
            yearRangeList.add(car.getYEAR_START() + i);
        }
        // System.out.println(" *** yearRangeList for " + car + "=" + yearRangeList);
        return yearRangeList;
    }

    private String generateCarCategoryName(Car car) {
        StringBuilder sb = new StringBuilder();
        sb.append(car.getCAR_MAKE()).append(" ").append(car.getCAR_MODEL()).append(" ").
                append(car.getYEAR_START()).append("-").append(car.getYEAR_FINISH());
        return sb.toString().trim();
    }


    private String generateCarCategoryAttributeString(ArrayList<String> carCategoryAttributesList) {

        StringBuilder categoryStringSb = new StringBuilder();
        for (String categoryAttribute : carCategoryAttributesList) {
            // TODO: add attribute number count Toyota 4Runner 2010-2022 Year: 2010 :1 :0;
            categoryStringSb.append(categoryAttribute).append(attributeSeparator);
        }
        return categoryStringSb.toString().trim();
    }

    @Override
    public String toString() {
        return "CsvRowObject{" +
                ", id=" + id + '\'' + "\r\n" +
                ", SKU='" + sku + '\'' + "\r\n" +
                ", itemType='" + itemType + '\'' + "\r\n" +
                ", brand='" + brand + '\'' + "\r\n" +
                ", upperMount='" + upperMount + '\'' + "\r\n" +
                ", lowerMount='" + lowerMount + '\'' + "\r\n" +
                ", extendedLength='" + extendedLength + '\'' + "\r\n" +
                ", collapsedLength='" + collapsedLength + '\'' + "\r\n" +

                ", makeAttributeString='" + makeAttributeString + '\'' + "\r\n" +
                ", modelAttributeString='" + modelAttributeString + '\'' + "\r\n" +
                ", yearStartAttributeString='" + yearStartAttributeString + '\'' + "\r\n" +
                ", yearFinishAttributeString='" + yearFinishAttributeString + '\'' + "\r\n" +
                ", driveAttributeString='" + driveAttributeString + '\'' + "\r\n" +
                ", positionAttributeString='" + positionAttributeString + '\'' + "\r\n" +
                ", yearAttributeString='" + yearAttributeString + '\'' + "\r\n" +
                ", liftAttributeString='" + liftAttributeString + '\'' + "\r\n" +
                ", otherAttributeString='" + otherAttributeString + '\'' + "\r\n" +
                ", description='" + description + '\'' + "\r\n" +
                ", imgUrl='" + imgUrl + '\'' + "\r\n" +

                "dbObject=" + dbObject + '\'' + "\r\n" +
                ", item=" + item + '\'' + "\r\n" +
                ", Title=" + title + '\'' + "\r\n" +
                ", fitmentList=" + fitmentList + '\'' + "\r\n" +
                ", mapFitmentCar=" + mapFitmentCar + '\'' + "\r\n" +
                ", mapFitmentFitmentAttributesList=" + mapFitmentFitmentAttributesList + '\'' + "\r\n" +
                ", mapCarCarAttributesList=" + mapCarCarAttributesList + '\'' + "\r\n" +

                ", itemAttributeList=" + itemAttributeList + '\'' + "\r\n" +
                ", carCategoryAttributesList=" + carCategoryAttributesList + '\'' + "\r\n" +
                ", carCategoryAttributeString='" + carCategoryAttributeString + '\'' + "\r\n" +

                ", csvAttributeObjectArrayList=" + csvAttributeObjectArrayList +
                "\r\n" + "\r\n" +
                ", csvAttributeValueStringMultimap=" + csvAttributeValueStringMultimap +
                // ", yearCsvAttributeObjectsLinkedHashSet=" + yearCsvAttributeObjectsLinkedHashSet +
                '}';
    }


    public String[] toStringArray() {
        String[] stringArray = new String[30];
        stringArray[0] = String.valueOf(id);
        stringArray[1] = sku;
        stringArray[2] = itemType;
        stringArray[3] = brand;
        stringArray[4] = title;
        stringArray[5] = carCategoryAttributeString;
        stringArray[6] = description;
        stringArray[7] = imgUrl;
        new test.main.prop();

        int last = Integer.max(imgUrl.lastIndexOf("/"),imgUrl.lastIndexOf("="))+1;

        stringArray[8] = prop.prod_img_link + imgBrandsMap.get(brand)+"/"+ imgUrl.substring(last);
        stringArray[9] = prop.loc_img_link + imgBrandsMap.get(brand)+"/"+ imgUrl.substring(last);
        stringArray[10] = prop.dots_img_link + imgBrandsMap.get(brand)+"/" + imgUrl.substring(last);
        // build csv attributes
        ArrayList<CsvAttributeObject> csvAttributeObjectArrayList = new ArrayList<>();
        for (String key : csvAttributeValueStringMultimap.keySet()) {
            int n = 0;
            for (String value : csvAttributeValueStringMultimap.get(key))
                try {
                    if (value != null) {
                        if ((value.length() < 127) && (!containsExceptions(key, exceptionsForDescArrayList)))
                            csvAttributeObjectArrayList.add(new CsvAttributeObject(key, value, n++));
                    } else csvAttributeObjectArrayList.add(new CsvAttributeObject(key, "0", n++));

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        /*StringBuilder sb = new StringBuilder();
        for (CsvAttributeObject csvAttributeObject:csvAttributeObjectArrayList){
            sb.append(csvAttributeObject.attributeName).append(": ").append(csvAttributeObject.attributeValue).append(": ") ;
        }*/

        // csvAttributesProcessed
        stringArray[11] = csvAttributeObjectArrayList.toString().replace("[", "").
                replace("]", "").replace(";,", ";")
                .replace("\r\n" + "Material", "")
                .replaceAll("(\\r|\\n)", "")
        .replaceAll("Position On Vehicle:","PositionOnVehicle");
      /*  stringArray[5] = driveAttributeString;
        stringArray[6] = positionAttributeString;
        stringArray[7] = yearAttributeString;
        stringArray[8] = liftAttributeString;
        stringArray[9] = otherAttributeString;*/
        stringArray[12] = series + "\r\n" + upperMount + "\r\n" + lowerMount + "\r\n" + extendedLength + "\r\n" + collapsedLength;
       /* stringArray[10] = fitmentList.toString();
        stringArray[11] = mapFitmentFitmentAttributesList.toString();
        stringArray[12] = mapFitmentCar.toString();
        stringArray[13] = mapCarCarAttributesList.toString();
        stringArray[14] = item.toString();
        stringArray[15] = itemAttributeList.toString();
        stringArray[16] = carCategoryAttributesList.toString();
        stringArray[17] = csvAttributeObjectArrayList.toString();
        stringArray[18] = csvAttributeValueStringMultimap.toString();*/
        /*stringArray[22] = ;
        stringArray[23] = ;
        stringArray[24] = ;
        stringArray[25] = ;
        stringArray[26] = ;
        stringArray[27] = ;
        stringArray[28] = ;
        stringArray[29] = ;*/

        // removing bad symbols
        for (int i = 0; i < stringArray.length; i++) {
            for (String exc : charExceptionArrayList)
                if ((stringArray[i] != null) && (i != 7)&& (i != 8)&& (i != 9)&& (i != 10))
                    // earlier was replaced by _
                    stringArray[i] = stringArray[i].replace(exc, "");
        }
        return stringArray;
    }

}