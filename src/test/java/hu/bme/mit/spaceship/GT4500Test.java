package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GT4500Test {

    private GT4500 ship;
    private TorpedoStore torpedoStoreMock1;
    private TorpedoStore torpedoStoreMock2;

    @BeforeEach
    public void init() {
        torpedoStoreMock1 = mock(TorpedoStore.class);
        torpedoStoreMock2 = mock(TorpedoStore.class);
        this.ship = new GT4500(torpedoStoreMock1, torpedoStoreMock2);
    }

//    @Test
//    public void fireTorpedo_test1(){
//
//    }

    @Test
    public void fireTorpedo_Single_Success() {
        // Arrange
        //Itt megmondjuk, hogy ha meghívják a kimokkolt objektumnak a fire metódusát 2-es paraméterrel, akkor true-val térjen vissza.
        when(torpedoStoreMock1.fire(1)).thenReturn(true);
        when(torpedoStoreMock2.fire(1)).thenReturn(true);

        // Act
        // Itt hívódik meg valahogy (nézzük meg, hogyan)
        ship.fireTorpedo(FiringMode.SINGLE);
        ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        // Ellenörzi, hogy meghívták-e ennek az objektumnak (torpedoStoreMock1) a fire metódusát 2-es paraméterrel legalább egyszer
        verify(torpedoStoreMock1, times(1)).fire(1);
        verify(torpedoStoreMock2, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_All_Success() {
        // Arrange
        when(torpedoStoreMock1.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        verify(torpedoStoreMock1, times(0)).fire(1);
    }

}
