package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.TractionType;
import com.crystal.ovs.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class CrudCarTests {
    @Test
    public void getNumberOfRowsTest() { // works
        int expectedNumberOfRows = 3;
        int numberOfRows = CrudCar.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test
    public void selectAllFromCarTest() { // works
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotNull(existingCars);
        Assert.assertNotEquals(0, existingCars.size());
        Assert.assertEquals(Car.class, existingCars.get(0).getClass());
    }

    @Test
    public void insertIntoCarTest() throws ValidationException { // works
        int initialRowNumber = CrudCar.getNumberOfRows();
        Car car = new Car(1,"Ford", "Mustang", "5sd5a6f58ds9f7s8", 2022, CarType.SPORTS,12,
                2, TractionType.RWD, 2, Color.CYAN);
        CrudCar.insertCar(car);
        Assert.assertEquals(initialRowNumber + 1, CrudCar.getNumberOfRows());
    }

    @Test
    public void deleteFromCarByIdTest() { // works
        int initialRowNumber = CrudCar.getNumberOfRows();
        int id = 3;
        CrudCar.deleteCar(id);
        Assert.assertEquals(initialRowNumber - 1, CrudCar.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() { // works
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        CrudCar.updateCar(car);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarBrandByIdTest() { // works
        CrudCar.updateCarBrandById(5, "Renault");
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarModelByIdTest() { // works
        CrudCar.updateCarModelById(5, "Symbol");
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarVINByIdTest() { // works
        CrudCar.updateCarVINById(5, "VIN123");
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarManufacturingYearByIdTest() { // works
        CrudCar.updateCarManufacturingYearById(5, 2021);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarTypeByIdTest() { // works
        CrudCar.updateCarTypeById(5, CarType.SUV);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarEngineIdByIdTest() { // works
        CrudCar.updateCarEngineIdById(5, 12);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarTransmissionIdByIdTest() { // works
        CrudCar.updateCarTransmissionIdById(5, 1);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarTractionTypeByIdTest() { // works
        CrudCar.updateCarTractionTypeById(5, TractionType.RWD);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarNumberOfDoorsByIdTest() { // works
        CrudCar.updateCarNumberOfDoorsById(5, 2);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void updateCarColorByIdTest() { // works
        CrudCar.updateCarColorById(5, Color.CYAN);
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        List<Car> existingCars = CrudCar.selectAllCars();
        Assert.assertNotEquals(car, Objects.requireNonNull(existingCars).get(0));
    }

    @Test
    public void getCarsFromResultSet() throws SQLException { // works
        List<Car> actual = CrudCar.selectAllCars();
        Assert.assertEquals(CrudCar.getNumberOfRows(), Objects.requireNonNull(actual).size());
    }

    @Test
    public void validateCarTest(){
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        java.util.List<String> validateCar = CrudCar.validateCar(car);

        Assert.assertTrue(validateCar.isEmpty());
    }

    @Test
    public void colorTests() {
        Color c = getColorByName("blue");
        System.out.println(c);
    }

    public static Color getColorByName(String name) {
        try {
            return (Color)Color.class.getField(name.toUpperCase()).get(null);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }
}
