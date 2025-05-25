# Гоце Жилески, бр. на индекс 41208

## Control Flow Graph
![checkCart_cfg](https://github.com/user-attachments/assets/1aa366d3-5936-4984-a171-cb6223ebc273)


## 3.Цикломатската комплексност на овој код е 9,  
  1. Графот има 9 региони
  2. V(G)=E-N+2P=26-19+2=7+2=9  - каде што E=26 - бројот на рабови, N=19 бројот на јазли, P=1
  3. V(G)=P+1=8+1=9 - каде што P е бројот на предикатни јазли.

## 4.Тест случаи по Every statement критериум
 За да се постигне минимум за критериумот Every Statement доволни се 5 тест случаи:
 
 #### Се тестираат изрази кои ќе вратат -> RuntimeException ("allItems list can't be null!")
  ```java
// allItems is null -> throws exception
assertEquals("allItems list can't be null!",
        assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "1234567890")).getMessage());
```
 #### Се тестираат изрази кои ќе вратат грешка „Invalid item!“
  ```java
// Item with property name null throws exception
Item nullNameItem = new Item(null, 1, 100, 0);
assertEquals("Invalid item!",
      assertThrows(RuntimeException.class, () ->
              SILab2.checkCart(List.of(nullNameItem), "1234098712340987")).getMessage());
```
#### Се тестираат изразите кои ќе вратат грешка “Invalid card number!“ 
```java
Item milk = new Item("Milk", 10, 400, 0.50);
Item water = new Item("Water", 2, 100, 0);
List<Item> itemList = List.of(milk, water);
// Valid Items, invalid Card
assertEquals("Invalid card number!",
      assertThrows(RuntimeException.class, () ->
              SILab2.checkCart(itemList, null)).getMessage());
```

#### Се тестираат изразите кои ќе вратат грешка “Invalid character in card number!“ 
```java
// Card has invalid characters
  assertEquals("Invalid character in card number!",
      assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(itemList, "tr01123456780987")).getMessage());
```
#### Се тестираат изразите кои имаат успешно вратен резултат
```java
// Returns valid total, every statement visited
assertEquals(3920, SILab2.checkCart(itemList, "1298421398412398"));
```


## 5.Multiple Condition критериум
Со Multiple Condition критериумот за условот if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10) минималниот број на тестови е 4.

Бидејќи имаме два OR оператори:

FFF -- Кога сите 3 изрази се невистинити.

TXX -- Kога првиот израз е вистинит тогаш другите 2 се неважни - редундантни и не влијаат на исходот.

FTX -- Кога првиот израз е невистинит вториот израз е вистинит , третиот е неважен.

FFT -- Кога првиот и вториот израз се невистинити а третиот е вистинит.

Табеала на сите можни тестови за овај услов и како пример се земени првите 4.
![Screenshot 2025-05-25 211620](https://github.com/user-attachments/assets/1ff7bb50-cc1f-4f82-9980-58b0a9d5869e)


