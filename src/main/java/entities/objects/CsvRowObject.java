package entities.objects;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import entities.*;
import entities.attributes_links.Car_attributes_link;
import entities.attributes_links.Fitment_attributes_link;
import entities.attributes_links.ItemPic;
import entities.attributes_links.Item_attributes_link;

import java.util.*;

public class CsvRowObject {
    // TODO: check slash \ import for manuals urls
    final ArrayList<String> charExceptionArrayList = new ArrayList<>(Arrays.asList("null",/*"/",*/ "\\", "$", "&", "%", "<", ">", "*", "#", "'", "\"", "`", "~", "(", ")", "[", "]", "{", "}", "|", "="));
    final ArrayList<String> exceptionsForDescArrayList = new ArrayList<>(Arrays.asList("Start", "Finish", "Full", "Price"));

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

        for (Fitment fitment:allFitmentList)
            if(csvRowObject.item.getITEM_ID()==fitment.getITEM_ID())
                csvRowObject.fitmentList.add(fitment);

            if(fitmentList!=null) {
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
        this.id=item.getITEM_ID();
       // System.out.println("Item id when building CsvRowObject = " + id);
        this.sku = item.getITEM_PART_NO();
        this.itemType = item.getITEM_TYPE();
        this.brand = item.getITEM_MANUFACTURER();
        itemAttributeList = item.getItemAttributeList();
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
        carCategoryAttributeString = buildCarCategoryAttributeString(carCategoryAttributesList);

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

    }

    private void generateTitle() {
        title = sku + " " + itemType + " " + brand;
    }

    private void generateItemDescription() {
        StringBuilder sb = new StringBuilder();
        for (String key : csvAttributeValueStringMultimap.keys()) {
            if ((!sb.toString().contains(key)) && (!containsExceptions(key, exceptionsForDescArrayList)))
                sb.append(key + ": "
                        + csvAttributeValueStringMultimap.get(key)).append(System.lineSeparator());
        }
        description = sb.toString().trim();
    }

    private boolean containsExceptions(String checkedString, ArrayList<String> exceptionsStringArrayList) {
        boolean b = false;
        for (String exception : exceptionsStringArrayList) {
            if ( (!checkedString.contains("http"))&&checkedString.contains(exception))  {
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
            sb.append(itemPic.getPIC_URL()).append(attributeSeparator);
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
                Double.parseDouble(csv_LiftStart_AttributeObject.getAttributeValue());
            } catch (NumberFormatException e) {
                e.printStackTrace();
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

    private String buildCarCategoryAttributeString(ArrayList<String> carCategoryAttributesList) {
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
            sb.append(car.getCAR_MAKE()).append(" ").append(car.getCAR_MODEL()).append(" ").append(car.getYEAR_START()).append("-").append(car.getYEAR_FINISH());
            if (!carCategoriesArrayList.contains(sb.toString()))
                carCategoriesArrayList.add(sb.toString());
        }
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
        // build csv attributes
        ArrayList<CsvAttributeObject> csvAttributeObjectArrayList = new ArrayList<>();
        for (String key : csvAttributeValueStringMultimap.keySet()) {
            int n = 0;
            for (String value : csvAttributeValueStringMultimap.get(key)) {
                if ( (value.length() < 127) && (!containsExceptions(key, exceptionsForDescArrayList)) )
                    csvAttributeObjectArrayList.add(new CsvAttributeObject(key, value, n++));
            }
        }
        /*StringBuilder sb = new StringBuilder();
        for (CsvAttributeObject csvAttributeObject:csvAttributeObjectArrayList){
            sb.append(csvAttributeObject.attributeName).append(": ").append(csvAttributeObject.attributeValue).append(": ") ;
        }*/

        // csvAttributesProcessed
        stringArray[8] = csvAttributeObjectArrayList.toString().replace("[", "").
                replace("]", "").replace(";,", ";")
                .replace("\r\n" + "Material", "")
                .replaceAll("(\\r|\\n)", "");
      /*  stringArray[5] = driveAttributeString;
        stringArray[6] = positionAttributeString;
        stringArray[7] = yearAttributeString;
        stringArray[8] = liftAttributeString;
        stringArray[9] = otherAttributeString;*/
        stringArray[9] = series + "\r\n" + upperMount + "\r\n" + lowerMount + "\r\n" + extendedLength + "\r\n" + collapsedLength;
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
                if ((stringArray[i] != null) && (i != 7))
                    // earlier was replaced by _
                    stringArray[i] = stringArray[i].replace(exc, "");
        }
        return stringArray;
    }
}