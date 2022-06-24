package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="cars")
public class Car implements Comparable<Car> {

    @Id
    @Column(name = "CAR_ID")
    private int CAR_ID;
    @Column(name = "YEAR_START")
    private int YEAR_START;
    @Column(name = "YEAR_FINISH")
    private int YEAR_FINISH;
    @Column(name = "CAR_MAKE")
    private String CAR_MAKE;
    @Column(name = "CAR_MODEL")
    private String CAR_MODEL;
    @Column(name = "CAR_SUBMODEL")
    private String CAR_SUBMODEL;
    @Column(name = "CAR_DRIVE")
    private String CAR_DRIVE;

    @ManyToMany
    @JoinTable(name = "car_attributes_link",
            joinColumns = @JoinColumn(name = "CAR_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_ATT_ID"))
    private List<CarAttribute> carAttributeList;

    @OneToOne
    @JoinColumn(name = "CAR_ID")
    private Fitment carFitment;

    public Car() {
    }

    public Car(int YEAR_START, int YEAR_FINISH, String CAR_MAKE,
               String CAR_MODEL, String CAR_SUBMODEL, String CAR_DRIVE) {

        this.YEAR_START = YEAR_START;
        this.YEAR_FINISH = YEAR_FINISH;
        this.CAR_MAKE = CAR_MAKE;
        this.CAR_MODEL = CAR_MODEL;
        this.CAR_SUBMODEL = CAR_SUBMODEL;
        this.CAR_DRIVE = CAR_DRIVE;
    }

    public int getCAR_ID() {
        return CAR_ID;
    }

    public void setCAR_ID(int CAR_ID) {
        this.CAR_ID = CAR_ID;
    }

    public int getYEAR_START() {
        return YEAR_START;
    }

    public void setYEAR_START(int YEAR_START) {
        this.YEAR_START = YEAR_START;
    }

    public int getYEAR_FINISH() {
        return YEAR_FINISH;
    }

    public void setYEAR_FINISH(int YEAR_FINISH) {
        this.YEAR_FINISH = YEAR_FINISH;
    }

    public String getCAR_MAKE() {
        return CAR_MAKE;
    }

    public void setCAR_MAKE(String CAR_MAKE) {
        this.CAR_MAKE = CAR_MAKE;
    }

    public String getCAR_MODEL() {
        return CAR_MODEL;
    }

    public void setCAR_MODEL(String CAR_MODEL) {
        this.CAR_MODEL = CAR_MODEL;
    }

    public String getCAR_SUBMODEL() {
        return CAR_SUBMODEL;
    }

    public void setCAR_SUBMODEL(String CAR_SUBMODEL) {
        this.CAR_SUBMODEL = CAR_SUBMODEL;
    }

    public String getCAR_DRIVE() {
        return CAR_DRIVE;
    }

    public void setCAR_DRIVE(String CAR_DRIVE) {
        this.CAR_DRIVE = CAR_DRIVE;
    }


    public List<CarAttribute> getCarAttributeList() {
        return carAttributeList;
    }

    public void setCarAttributeList(List<CarAttribute> carAttributeList) {
        this.carAttributeList = carAttributeList;
    }


    @Override
    public String toString() {
        return "\r\n" +  "Car{" +
                "CAR_ID=" + CAR_ID + " ... "
                /*", YEAR_START=" */ + YEAR_START + "-"
                /*", YEAR_FINISH="*/ + YEAR_FINISH + " "
                /*", CAR_MAKE='"*/ + CAR_MAKE + '\'' + " "
                /*", CAR_MODEL='"*/ + CAR_MODEL + '\'' + " "
                /*", CAR_SUBMODEL='" */ + CAR_SUBMODEL + '\'' + " "
                /*", CAR_DRIVE='"*/ + CAR_DRIVE + '\'' +
               /* "\r\n" +
                ", carAttributeList=" + carAttributeList +
                "\r\n" +*/
                /*", carFitment=" + carFitment +*/
                '}';
    }


    public List<Car> getAllCArsFromDb() {

        List<Car> carsList = new List<Car>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Car> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Car car) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Car get(int index) {
                return null;
            }

            @Override
            public Car set(int index, Car element) {
                return null;
            }

            @Override
            public void add(int index, Car element) {

            }

            @Override
            public Car remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Car> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Car> listIterator(int index) {
                return null;
            }

            @Override
            public List<Car> subList(int fromIndex, int toIndex) {
                return null;
            }
        };


        return carsList;
    }


    public void carCompare(Car car1, Car car2) {

    }

    public ArrayList<Integer> carCompareToList(List<Car> listOfCars) {

        ArrayList<Integer> CAR_ID_LIST = new ArrayList<Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Integer get(int index) {
                return null;
            }

            @Override
            public Integer set(int index, Integer element) {
                return null;
            }

            @Override
            public void add(int index, Integer element) {

            }

            @Override
            public Integer remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Integer> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Integer> listIterator(int index) {
                return null;
            }

            @Override
            public List<Integer> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        CAR_ID_LIST.add(1);

        for (Car carFromList : listOfCars) {
            System.out.println("this.YEAR_START = " + this.YEAR_START + " ... carFromList.YEAR_START = " + carFromList.YEAR_START);
            if (this.YEAR_START == carFromList.YEAR_START)

           /* & (this.YEAR_FINISH == carFromList.YEAR_FINISH)
            & (this.CAR_MAKE == carFromList.CAR_MAKE)
            & (this.CAR_MODEL == carFromList.CAR_MODEL)            ) */ {
                CAR_ID_LIST.add(carFromList.CAR_ID);
            }
        }

        return CAR_ID_LIST;
    }

    @Override
    public int compareTo(Car o) {

        int result = this.CAR_DRIVE.length() - o.CAR_DRIVE.length();
        System.out.println("\r\n Comparison result = " + result);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car newCar = (Car) o;

        return YEAR_START == newCar.YEAR_START &&
                YEAR_FINISH == newCar.YEAR_FINISH &&
                CAR_MAKE.equals(newCar.CAR_MAKE)&&
                CAR_MODEL.equals(newCar.CAR_MODEL)&&
                CAR_SUBMODEL.equals(newCar.CAR_SUBMODEL)&&
                CAR_DRIVE.equals(newCar.CAR_DRIVE);
    }

}

