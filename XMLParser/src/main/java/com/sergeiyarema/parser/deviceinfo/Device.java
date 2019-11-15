package com.sergeiyarema.parser.deviceinfo;

/*
5.	Компьютеры.
Компьютерные комплектующие имеют следующие характеристики:
•	Name – название комплектующего.
•	Origin – страна производства.
•	Price – цена (0 – n рублей).
•	Type (должно быть несколько) – периферийное либо нет, энергопотребление (ватт), наличие кулера (есть либо нет),
     группа комплектующих (устройства ввода-вывода, мультимедийные), порты (COM, USB, LPT).
•	Critical – критично ли наличие комплектующего для работы компьютера.
Корневой элемент назвать Device.

 */

import java.util.Objects;

public class Device implements Comparable<Device> {
    private String id;
    private String name;
    private Integer price;
    private Type type;
    private Boolean critical;

    @Override
    public int compareTo(Device device) {
        return name.compareTo(device.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, type, critical);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;

        Device device = (Device) o;
        return (id.equals(device.id) &&
                name.equals(device.name) &&
                price.equals(device.price) &&
                type.equals(device.type) &&
                critical.equals(device.critical));
    }
}
