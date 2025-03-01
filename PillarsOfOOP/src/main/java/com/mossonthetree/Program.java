package com.mossonthetree;

/*
1. Encapsulation
2. Polymorphism
 */

// Person
// -> Software engineer

public class Program {
    public static void main(String[] args) {
        SoftwareEngineer me = new SoftwareEngineer();
        // Person is-a SoftwareEngineer

        me.firstName = "Henner";
        me.lastName = "Johnson";

        me.sayHello();
        me.writeCode("Java");

        Person meAsPerson = me;

        meAsPerson.sayHello();

        Person dosfol = new Person();
        dosfol.firstName = "Dosfol";
        dosfol.lastName = "Hjaar";

        dosfol.sayHello();

        Person gabriel = new SoftwareEngineer();
        gabriel.firstName = "Gabriel";
        gabriel.lastName = "De la Vega";

        SoftwareEngineer gabrielAsSE = (SoftwareEngineer)gabriel;

        gabrielAsSE.sayHello();
        gabrielAsSE.writeCode("Java");

        gabrielAsSE.travel(2);
        System.out.println(gabrielAsSE.getMode());
        System.out.println(gabrielAsSE.kilometersTraveled);



        JsonSerializer json = new JsonSerializer();
        json.write(dosfol);

        Car car = new Car();
        car.model = "Scirocco";
        car.year = "2009";

        car.travel(1000);
        System.out.println(car.getMode());
        System.out.println(car.kilometersTraveled);

        json.write(gabrielAsSE);

        JsonSerializable thing = new Person();
    }
}
