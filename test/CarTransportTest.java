import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTransportTest {

    CarTransport t;

    @BeforeEach
    void setup() {
        t = new CarTransport();
    }

    @Test
    void getBedAngle() {
        assertFalse(t.isRaisedBed());

        t.raiseBed();
        assertTrue(t.isRaisedBed());
    }

    @Test
    void raiseBed() {
        t.raiseBed();
        assertTrue(t.isRaisedBed());
        assertThrows(IllegalArgumentException.class, () -> t.raiseBed());

        t.lowerBed();
        t.gas(0.5);
        assertThrows(IllegalArgumentException.class, () -> t.raiseBed());
    }

    @Test
    void lowerBed() {
        t.raiseBed();

        t.lowerBed();
        assertFalse(t.isRaisedBed());

        assertThrows(IllegalArgumentException.class, () -> t.lowerBed());
    }

    @Test
    void gas() {
        double amount = 0.9;
        double currentSpeed = t.getCurrentSpeed();
        t.gas(amount);
        double newSpeed = t.getCurrentSpeed();

        assertTrue(currentSpeed <= newSpeed);
        assertThrows(IllegalArgumentException.class, () -> t.gas(1.5));
        assertThrows(IllegalArgumentException.class, () -> t.gas(-1.5));
    }

    @Test
    void speedFactor() {
        double speedValue = t.getEnginePower() * 0.01;
        assertEquals(speedValue, t.speedFactor());
    }

    @Test
    void loadCar() {
        Car saab = new Saab95();
        t.raiseBed();
        t.loadCar(saab);
        t.lowerBed();
        assertThrows(IllegalArgumentException.class, () -> t.loadCar(saab));
        assertEquals(saab, t.getLoadedCars().getFirst());

        Car volvo = new Volvo240();
        t.raiseBed();
        t.loadCar(volvo);
        assertEquals(volvo, t.getLoadedCars().getLast());
    }

    @Test
    void unloadCar() {
        Car saab = new Saab95();

        assertThrows(IllegalArgumentException.class, () -> t.loadCar(saab));

        t.raiseBed();
        t.loadCar(saab);
        assertEquals(saab, t.unloadCar());

        assertThrows(IllegalArgumentException.class, () -> t.unloadCar());

        t.loadCar(saab);
        Car volvo = new Volvo240();
        t.loadCar(volvo);
        assertEquals(volvo, t.unloadCar());
    }

    @Test
    void getLoadedCars() {
        Car saab = new Saab95();
        t.raiseBed();
        t.loadCar(saab);
        assertEquals(saab, t.getLoadedCars().getFirst());
    }


}
