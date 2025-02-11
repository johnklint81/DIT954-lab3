import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarWorkshopTest {
    private CarWorkshop<Car> w;

    @BeforeEach
    void setup() {
        int MAX_CARS = 3;
        this.w = new CarWorkshop<>(MAX_CARS);
    }

    @Test
    void loadCar() {
        Volvo240 volvo240 = new Volvo240();
        w.loadCar(volvo240);
        assertTrue(w.hasCar(volvo240));

        assertThrows(IllegalArgumentException.class, () -> w.loadCar(volvo240));
    }
    
    @Test
    void loadCar_whenWorkshopIsFull() {
        w.loadCar(new Volvo240());
        w.loadCar(new Saab95());
        w.loadCar(new Saab95());
        assertThrows(IllegalArgumentException.class, () -> w.loadCar(new Volvo240()));
    }


    @Test
    void collectCar() {
        Volvo240 volvo240 = new Volvo240();
        w.loadCar(volvo240);
        assertTrue(w.hasCar(volvo240));

        w.collectCar(volvo240);
        assertFalse(w.hasCar(volvo240));
        assertThrows(IllegalArgumentException.class, () -> w.collectCar(volvo240));
    }

    @Test
    void hasCar() {
        Volvo240 volvo240 = new Volvo240();
        assertFalse(w.hasCar(volvo240));
        w.loadCar(volvo240);
        assertTrue(w.hasCar(volvo240));
    }

    @Test
    void getCarAmount() {
        assertEquals(0, w.getCarAmount());

        w.loadCar(new Volvo240());
        assertEquals(1, w.getCarAmount());

        w.loadCar(new Saab95());
        assertEquals(2, w.getCarAmount());
    }
}