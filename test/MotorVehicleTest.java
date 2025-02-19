import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class MotorVehicleTest {

    @Test
    void move() {
        MotorVehicle saab = new Saab95();
        double initialX = saab.getPos().getX();
        double initialY = saab.getPos().getY();
        saab.move();
        assertEquals(initialX, saab.getPos().getX());
        assertEquals(initialY, saab.getPos().getX());
    }

    @Test
    void turnLeft() {
        MotorVehicle saab = new Saab95();
        double rad = Math.PI / 2;
        saab.turnLeft(rad);
        assertEquals(Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void turnRight() {
        MotorVehicle saab = new Saab95();
        double rad = Math.PI / 2;
        saab.turnRight(rad);
        assertEquals(-Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void getNrDoors() {
        MotorVehicle saab = new Saab95();
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        MotorVehicle saab = new Saab95();
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        MotorVehicle saab = new Saab95();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void getCurrentDirection() {
        MotorVehicle saab = new Saab95();
        assertEquals(0, saab.getCurrentDirection());
    }

    @Test
    void getCurrentPosition() {
        MotorVehicle saab = new Saab95();
        assertEquals(0, saab.getPos().getX());
        assertEquals(0, saab.getPos().getY());
    }

    @Test
    void getColor() {
        MotorVehicle saab = new Saab95();
        assertEquals(Color.BLACK, saab.getColor());
    }

    @Test
    void setColor() {
        MotorVehicle saab = new Saab95();
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    void startEngine() {
        MotorVehicle saab = new Saab95();
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        MotorVehicle saab = new Saab95();
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        // Create an anonymous subclass just for testing.
        // Allows us to instantiate an abstract subclass.
        MotorVehicle saab = new Saab95();
        // enginePower = 125, turbo = off,
        // speed is 125 * 0.01 * 1 = 1.25
        assertEquals(1.25, saab.speedFactor());
        MotorVehicle volvo = new Volvo240();
        // enginePower = 100, trimFactor = 1.25
        // speed is 100 * 0.01 * 1.25 = 1.25
        assertEquals(1.25, volvo.speedFactor());
    }
}