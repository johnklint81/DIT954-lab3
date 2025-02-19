import java.awt.*;

public abstract class Truck extends MotorVehicle {
    protected Truck(int nrDoors, double enginePower, Color color, String modelName, Vec2 startOffset) {
        super(nrDoors, enginePower, color, modelName, startOffset);
    }
}
