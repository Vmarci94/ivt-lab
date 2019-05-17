package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    public void fireTorpedo_All_(){
        when(torpedoStoreMock1.isEmpty()).thenReturn(false);
        when(torpedoStoreMock2.isEmpty()).thenReturn(true);
        when(torpedoStoreMock1.fire(1)).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertTrue(result);
    }

    @Test
    public void test_single(){
        when(torpedoStoreMock1.isEmpty()).thenReturn(false);
        when(torpedoStoreMock2.isEmpty()).thenReturn(true);
        when(torpedoStoreMock1.fire(1)).thenReturn(true);

        ship.fireTorpedo(FiringMode.ALL);
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertTrue(result);
    }

    @Test
    void fireTorpedo_Single_BigTest(){
        when(torpedoStoreMock1.isEmpty()).thenReturn(false);
        when(torpedoStoreMock2.isEmpty()).thenReturn(false);

        when(torpedoStoreMock1.fire(1)).thenReturn(true);
        when(torpedoStoreMock2.fire(1)).thenReturn(true);

        for(int i = 0; i<10; i++){
            ship.fireTorpedo(FiringMode.SINGLE);
            ship.fireTorpedo(FiringMode.SINGLE);
        }

        verify(torpedoStoreMock1, times(10)).fire(1);
        verify(torpedoStoreMock2, times(10)).fire(1);

    }


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
        when(torpedoStoreMock1.isEmpty()).thenReturn(false);
        when(torpedoStoreMock2.isEmpty()).thenReturn(true);

        when(torpedoStoreMock1.fire(1)).thenReturn(true);

        // Act
        ship.fireTorpedo(FiringMode.ALL);

        // Assert
        verify(torpedoStoreMock1, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_All_test3(){
        // Arrange
        when(torpedoStoreMock1.isEmpty()).thenReturn(true);
        when(torpedoStoreMock2.isEmpty()).thenReturn(false);

        when(torpedoStoreMock2.fire(1)).thenReturn(true);

        // Act
        ship.fireTorpedo(FiringMode.ALL);

        // Assert
        verify(torpedoStoreMock2, times(1)).fire(1);

    }

}
