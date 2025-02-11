import java.awt.*;

public abstract class Vehicle implements Movable {
    // We need to discuss whether we want to use interfaces also...
    // Inheritance is nice here because the subclasses share a lot of functionality

    private double[] currentPosition = {0, 0};    // (x, y)
    private double currentDirection = 0; // Angle in degrees (from unit vector perspective)
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed = 0; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    // Should not be a property of car, but for now it is
    // Maybe this is what is found as 0.01 in speedFactor?
    // protected final static double timestep = 0.1;

    // Constructor, maybe we should have more fields in this one?
    // I don't like that the subclasses initialises nrDoors, enginePower, etc,
    // to hard-coded values.
    protected Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }
    // Override is strictly speaking not necessary here since we implement
    // the required method by the interface for the first time. But it is not
    // wrong either.
    @Override
    public void move() {
        // currentPosition is a 2D array: [x, y]
        // atan2 gives us signed direction in radians
        double direction = Math.atan2(currentPosition[1], currentPosition[0]);
        double positionChange = getCurrentSpeed();

        currentPosition[0] += positionChange * Math.cos(direction);
        currentPosition[1] += positionChange * Math.sin(direction);
    }
    // We could use radians below if we wanted to
    @Override
    public void turnLeft(double angle) {
        currentDirection = (currentDirection + angle) % 360;
    }
    @Override
    public void turnRight(double angle) {
        currentDirection = (currentDirection - angle) % 360;
    }
    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getCurrentDirection() {
        return currentDirection;
    }
    public double[] getCurrentPosition() {
        return currentPosition;
    }

    public Color getColor() {
        return color;
    }
    public String getModelName() {
        return modelName;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }
    // Protected because it is not meant to be changed directly by the user.
    // Abstract because it can change depending on model of car
    protected abstract double speedFactor();
    // Protected because meant to be overridden and used by subclasses.
    // If they are protected we can still change them in our subclass while
    // preventing external access.

    private void setCurrentSpeed(double speed) {
        currentSpeed = Math.min(Math.max(speed, 0), getEnginePower());
    }
    protected void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("gas amount must be in interval [0, 1].");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("brake amount must be in interval [0, 1].");
        }
        decrementSpeed(amount);
    }
}
