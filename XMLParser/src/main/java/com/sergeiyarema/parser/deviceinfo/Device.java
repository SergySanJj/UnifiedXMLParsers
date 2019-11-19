package com.sergeiyarema.parser.deviceinfo;

import java.util.List;
import java.util.Objects;

public class Device implements Comparable<Device> {
    private String id;
    private String name;

    private String origin;
    private Integer price;
    private Type type;
    private Boolean critical;

    public Device() {
    }

    public Device(String id, String name, Integer price, Type type, Boolean critical) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.critical = critical;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ").append(id).append("\n");
        stringBuilder.append("name: ").append(name).append("\n");
        stringBuilder.append("id: ").append(price).append("\n");
        stringBuilder.append("type: ").append(type.toString());
        stringBuilder.append("is critical: ").append(critical).append("\n");

        return stringBuilder.toString();
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }

    public Boolean getPereferial() {
        return this.type.getPereferial();
    }

    public void setPereferial(Boolean pereferial) {
        this.type.setPereferial(pereferial);
    }

    public Integer getEnergyConsumption() {
        return this.type.getEnergyConsumption();
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.type.setEnergyConsumption(energyConsumption);
    }

    public Integer getHasCooler() {
        return this.type.getHasCoolers();
    }

    public void setHasCooler(Integer hasCooler) {
        this.type.setHasCoolers(hasCooler);
    }

    public Group getGroup() {
        return this.type.getGroup();
    }

    public void setGroup(Group group) {
        this.type.setGroup(group);
    }

    public List<Port> getPorts() {
        return this.type.getPorts();
    }

    public void setPorts(List<Port> ports) {
        this.type.setPorts(ports);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
