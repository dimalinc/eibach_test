package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cars_unique")
public class CarUnique implements Comparable<CarUnique> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CAR_UNIQUE_ID")
    private int CAR_UNIQUE_ID;
    /*@Column(name = "CAR_ID")
    private int CAR_ID;*/
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

   /* private int CAR_ID;
    public int getCAR_ID() {
        return CAR_ID;
    }
    public void setCAR_ID(int CAR_ID) {
        this.CAR_ID = CAR_ID;
    }*/

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "CAR_ID")
    private List<Car> carsList = new ArrayList<Car>();

    public List<Car> getCarsList() {
        return carsList;
    }
    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }

    /*public List<CarUnique> getCarUniqueList() {
        return carUniqueList;
    }
    public void setCarUniqueList(List<CarUnique> carUniqueList) {
        this.carUniqueList = carUniqueList;
    }
*/

 /*   @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarUnique other = (CarUnique) obj;
        if (CAR_ID == 0) {
            if (other.getCAR_ID() != 0)
                return false;
        } else if (!CAR_MAKE.equals(other.CAR_MAKE))
            return false;
     else if (!CAR_MODEL.equals(other.CAR_MODEL))
            return false;
 else if (!CAR_DRIVE.equals(other.CAR_DRIVE))
        return false;
         else if (!CAR_SUBMODEL.equals(other.CAR_SUBMODEL))
        return false;
        return true;
    }*/

    public CarUnique() {
    }

    public CarUnique(int CAR_ID, int YEAR_START, int YEAR_FINISH, String CAR_MAKE,
                     String CAR_MODEL, String CAR_SUBMODEL, String CAR_DRIVE) {

      //  this.CAR_ID = CAR_ID;
        this.YEAR_START = YEAR_START;
        this.YEAR_FINISH = YEAR_FINISH;
        this.CAR_MAKE = CAR_MAKE;
        this.CAR_MODEL = CAR_MODEL;
        this.CAR_SUBMODEL = CAR_SUBMODEL;
        this.CAR_DRIVE = CAR_DRIVE;
    }

    public CarUnique(Car car) {
        //this.CAR_ID = car.getCAR_ID();
        this.YEAR_START = car.getYEAR_START();
        this.YEAR_FINISH = car.getYEAR_FINISH();
        this.CAR_MAKE = car.getCAR_MAKE();
        this.CAR_MODEL = car.getCAR_MODEL();
        this.CAR_SUBMODEL = car.getCAR_SUBMODEL();
        this.CAR_DRIVE = car.getCAR_DRIVE();
    }


    public int getCAR_UNIQUE_ID() {
        return CAR_UNIQUE_ID;
    }

    public void setCAR_UNIQUE_ID(int CAR_UNIQUE_ID) {
        this.CAR_UNIQUE_ID = CAR_UNIQUE_ID;
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

    @Override
    public String toString() {
        return "\r\n" + "CarUnique{" +
             //   "CAR_ID=" + CAR_ID + " ... "
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


    public List<CarUnique> getAllCArsFromDb() {
        List<CarUnique> carsList = new List<CarUnique>() {
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
            public Iterator<CarUnique> iterator() {
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
            public boolean add(CarUnique car) {
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
            public boolean addAll(Collection<? extends CarUnique> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends CarUnique> c) {
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
            public CarUnique get(int index) {
                return null;
            }

            @Override
            public CarUnique set(int index, CarUnique element) {
                return null;
            }

            @Override
            public void add(int index, CarUnique element) {

            }

            @Override
            public CarUnique remove(int index) {
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
            public ListIterator<CarUnique> listIterator() {
                return null;
            }

            @Override
            public ListIterator<CarUnique> listIterator(int index) {
                return null;
            }

            @Override
            public List<CarUnique> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        return carsList;
    }

    public void carCompare(CarUnique car1, CarUnique car2) {

    }

    public ArrayList<Integer> carCompareToList(List<CarUnique> listOfCars) {

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

        for (CarUnique carFromList : listOfCars) {
          //  System.out.println("this.YEAR_START = " + this.YEAR_START + " ... carFromList.YEAR_START = " + carFromList.YEAR_START);
            if (this.YEAR_START == carFromList.YEAR_START)

           /* & (this.YEAR_FINISH == carFromList.YEAR_FINISH)
            & (this.CAR_MAKE == carFromList.CAR_MAKE)
            & (this.CAR_MODEL == carFromList.CAR_MODEL)            ) */ {
             //   CAR_ID_LIST.add(carFromList.CAR_ID);
            }
        }

        return CAR_ID_LIST;
    }

    @Override
    public int compareTo(CarUnique o) {

        int result = this.CAR_DRIVE.length() - o.CAR_DRIVE.length()
                + this.CAR_MAKE.length() - o.CAR_MAKE.length()
                + this.CAR_MODEL.length() - o.CAR_MODEL.length()
                + this.YEAR_START - o.YEAR_START
                + this.YEAR_FINISH - o.YEAR_FINISH ;
        // System.out.println("\r\n Comparison result = " + result);

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

        CarUnique newCar = (CarUnique) o;

        return YEAR_START == newCar.YEAR_START &&
                YEAR_FINISH == newCar.YEAR_FINISH &&
                CAR_MAKE.equals(newCar.CAR_MAKE) &&
                CAR_MODEL.equals(newCar.CAR_MODEL) &&
                CAR_SUBMODEL.equals(newCar.CAR_SUBMODEL) &&
                CAR_DRIVE.equals(newCar.CAR_DRIVE);
    }

    @Override
    public int hashCode() {
        return CAR_SUBMODEL.hashCode();
    }

}

