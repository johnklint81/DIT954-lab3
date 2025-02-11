import java.awt.*;
import java.util.ArrayDeque;

public class CarTransport extends Truck {
    private boolean raisedBed = false;
    private final ArrayDeque<Car> loadedCars = new ArrayDeque<>();

    protected CarTransport() {
        super(2, 300, Color.BLACK, "Stor Eric");
    }

    public boolean isRaisedBed() {
        return this.raisedBed;
    }

    public void raiseBed() {
        if(getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Speed > 0");
        }
        if(isRaisedBed()) {
            throw new IllegalArgumentException("Bed is already raised");
        }
        this.raisedBed = true;
    }


    public void lowerBed() {
        // Cant happen if raiseBed() is correct
        if(getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Speed > 0");
        }
        if(!isRaisedBed()) {
            throw new IllegalArgumentException("Bed is already lowered");
        }
        this.raisedBed = false;
    }

    @Override
    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("gas amount must be in interval [0, 1].");
        }
        if (isRaisedBed()) {
            throw new IllegalArgumentException("Bed is raised");
        }

        incrementSpeed(amount);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    public void loadCar(Car car) {
        if (!isRaisedBed()) {
            throw new IllegalArgumentException("Bed is raised");
        }
        if (isCarInRange(car)) {
            throw new IllegalArgumentException("Vehicle is too far away");
        }

        this.loadedCars.add(car);
    }

    private boolean isCarInRange(Car car) {
        return Math.abs(getCurrentPosition()[0] - car.getCurrentPosition()[0]) > 5
                || Math.abs(getCurrentPosition()[1] - car.getCurrentPosition()[1]) > 5;
    }

    public Car unloadCar(){
        if(this.loadedCars.isEmpty()) {
            throw new IllegalArgumentException("CarTransport is empty");
        }
        if (!isRaisedBed()) {
            throw new IllegalArgumentException("Bed is raised");
        }

        return this.loadedCars.removeLast();
    }

    public ArrayDeque<Car> getLoadedCars() {
        if (this.loadedCars.isEmpty()) {
            throw new IllegalArgumentException("Car is empty");
        }
        return loadedCars;
    }

    @Override
    public void turnLeft(double angle) {
        super.turnLeft(angle);

        for (Vehicle loadedVehicle : this.loadedCars) {
            loadedVehicle.turnLeft(angle);
        }
    }

    @Override
    public void turnRight(double angle) {
        super.turnRight(angle);

        for (Vehicle loadedVehicle : this.loadedCars) {
            loadedVehicle.turnRight(angle);
        }
    }
    @Override
    public void move() {
        super.move();

        for (Vehicle loadedVehicle : this.loadedCars) {
            loadedVehicle.move();
        }
    }
}
