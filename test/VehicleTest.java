import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void move() {
        Vehicle saab = new Saab95();
        double initialX = saab.getCurrentPosition()[0];
        double initialY = saab.getCurrentPosition()[1];
        saab.move();
        assertEquals(initialX, saab.getCurrentPosition()[0]);
        assertEquals(initialY, saab.getCurrentPosition()[1]);
    }

    @Test
    void turnLeft() {
        Vehicle saab = new Saab95();
        double rad = Math.PI / 2;
        saab.turnLeft(rad);
        assertEquals(Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void turnRight() {
        Vehicle saab = new Saab95();
        double rad = Math.PI / 2;
        saab.turnRight(rad);
        assertEquals(-Math.PI / 2, saab.getCurrentDirection());
    }

    @Test
    void getNrDoors() {
        Vehicle saab = new Saab95();
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        Vehicle saab = new Saab95();
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        Vehicle saab = new Saab95();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void getCurrentDirection() {
        Vehicle saab = new Saab95();
        assertEquals(0, saab.getCurrentDirection());
    }

    @Test
    void getCurrentPosition() {
        Vehicle saab = new Saab95();
        assertEquals(0, saab.getCurrentPosition()[0]);
        assertEquals(0, saab.getCurrentPosition()[1]);
    }

    @Test
    void getColor() {
        Vehicle saab = new Saab95();
        assertEquals(Color.BLACK, saab.getColor());
    }

    @Test
    void setColor() {
        Vehicle saab = new Saab95();
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @Test
    void startEngine() {
        Vehicle saab = new Saab95();
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        Vehicle saab = new Saab95();
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        // Create an anonymous subclass just for testing.
        // Allows us to instantiate an abstract subclass.
        Vehicle saab = new Saab95();
        // enginePower = 125, turbo = off,
        // speed is 125 * 0.01 * 1 = 1.25
        assertEquals(1.25, saab.speedFactor());
        Vehicle volvo = new Volvo240();
        // enginePower = 100, trimFactor = 1.25
        // speed is 100 * 0.01 * 1.25 = 1.25
        assertEquals(1.25, volvo.speedFactor());
    }
}