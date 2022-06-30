package entities;

public class CarUniqueObjectWoID implements Comparable<CarUniqueObjectWoID>{


    int YEAR_START;
    int YEAR_FINISH;
    String CAR_MAKE;
    String CAR_MODEL;
    String CAR_SUBMODEL;
    String CAR_DRIVE;

    public CarUniqueObjectWoID(int YEAR_START, int YEAR_FINISH, String CAR_MAKE, String CAR_MODEL, String CAR_SUBMODEL, String CAR_DRIVE) {
        this.YEAR_START = YEAR_START;
        this.YEAR_FINISH = YEAR_FINISH;
        this.CAR_MAKE = CAR_MAKE;
        this.CAR_MODEL = CAR_MODEL;
        this.CAR_SUBMODEL = CAR_SUBMODEL;
        this.CAR_DRIVE = CAR_DRIVE;
    }

    public int getYEAR_START() {
        return YEAR_START;
    }

    public int getYEAR_FINISH() {
        return YEAR_FINISH;
    }

    public String getCAR_MAKE() {
        return CAR_MAKE;
    }

    public String getCAR_MODEL() {
        return CAR_MODEL;
    }

    public String getCAR_SUBMODEL() {
        return CAR_SUBMODEL;
    }

    public String getCAR_DRIVE() {
        return CAR_DRIVE;
    }

    @Override
    public int compareTo(CarUniqueObjectWoID o) {
        return (CAR_MAKE.compareTo(o.getCAR_MAKE()))+(CAR_MODEL.compareTo(o.getCAR_MODEL()))
                +(CAR_SUBMODEL.compareTo(o.getCAR_MODEL()))+(CAR_DRIVE.compareTo(o.getCAR_DRIVE()))
                +(YEAR_START-o.getYEAR_START())+(YEAR_FINISH-o.getYEAR_FINISH());
    }
}
