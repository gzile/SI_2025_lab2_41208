import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        // allItems equal null throws exception
        assertEquals("allItems list can't be null!",
                assertThrows(RuntimeException.class, () ->
                        SILab2.checkCart(null, "1234098712340987")).getMessage());

        // Item with property name null throws exception
        Item nullNameItem = new Item(null, 1, 100, 0);
        assertEquals("Invalid item!",
                assertThrows(RuntimeException.class, () ->
                        SILab2.checkCart(List.of(nullNameItem), "1234098712340987")).getMessage());

        
        Item milk = new Item("Milk", 10, 400, 0.50);
        Item water = new Item("Water", 2, 100, 0);
        List<Item> itemList = List.of(milk, water);
        // Valid Items, invalid Card
        assertEquals("Invalid card number!",
                assertThrows(RuntimeException.class, () ->
                        SILab2.checkCart(itemList, null)).getMessage());

        // Card has invalid characters
        assertEquals("Invalid character in card number!",
                assertThrows(RuntimeException.class, () ->
                        SILab2.checkCart(itemList, "tr01123456780987")).getMessage());

        // Returns valid total, every statement visited
        assertEquals(2170, SILab2.checkCart(itemList, "1234098712340987"));
    }

    @Test
        public void testCheckCartMultipleCondition() {
                // Користиме валиден број на картичка за сите овие тестови за да се фокусираме
                // само на однесувањето на item условот и да не фрли исклучок за картичка.
                String validCard = "1298421398412398";

                // Тест случај 1: A=F, B=F, C=F (FFF) -> price<=300, discount<=0, quantity<=10
                List<Item> items1 = new ArrayList<>();
                items1.add(new Item("P1", 10, 300, 0.0));
                assertEquals(3000.0, SILab2.checkCart(items1, validCard), 0.001); // 300*10 = 3000

                // Тест случај 2: A=T, B=F, C=F (TXX) -> price>300, discount<=0, quantity<=10
                List<Item> items5 = new ArrayList<>();
                items5.add(new Item("P2", 10, 301, 0.0));
                assertEquals((301 * 10) - 30, SILab2.checkCart(items5, validCard), 0.001); // (3010 - 30) = 2980

                // Тест случај 3: A=F, B=T, C=F (FTX) -> price<=300, discount>0, quantity<=10
                List<Item> items3 = new ArrayList<>();
                items3.add(new Item("P3", 10, 300, 0.1));
                assertEquals((300 * (1 - 0.1) * 10) - 30, SILab2.checkCart(items3, validCard), 0.001); // (2700 - 30) =  2670

                // Тест случај 4: A=F, B=F, C=T (FFT) -> price<=300, discount<=0, quantity>10
                List<Item> items2 = new ArrayList<>();
                items2.add(new Item("P4", 11, 300, 0.0));
                assertEquals((300 * 11) - 30, SILab2.checkCart(items2, validCard), 0.001); // (3300 - 30) = 3270
        }
}
