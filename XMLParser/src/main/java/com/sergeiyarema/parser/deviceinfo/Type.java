package com.sergeiyarema.parser.deviceinfo;

import java.util.List;
import java.util.Objects;

public class Type {
    private Boolean pereferial;
    private Integer energyConsumption;
    private Integer hasCoolers;
    private Group group;
    private List<Port> ports;

    public Type() {
    }

    public Type(Boolean pereferial, Integer energyConsumption, Integer hasCoolers, Group group, List<Port> ports) {
        this.pereferial = pereferial;
        this.energyConsumption = energyConsumption;
        this.hasCoolers = hasCoolers;
        this.group = group;
        this.ports = ports;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("- is pereferial: ").append(pereferial).append("\n");
        stringBuilder.append("- energy consumption: ").append(energyConsumption).append(" WT").append("\n");
        if (hasCoolers != null)
            stringBuilder.append("- has coolers: ").append(hasCoolers).append("\n");
        stringBuilder.append("- group: ").append(group.toString()).append("\n");
        stringBuilder.append("- ports: ").append(portsListString()).append("\n");

        return stringBuilder.toString();
    }

    private String portsListString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Port port : ports) {
            stringBuilder.append(port.toString()).append("; ");
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pereferial, energyConsumption, hasCoolers, group, ports);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;

        Type type = (Type) o;
        return (pereferial == type.pereferial &&
                energyConsumption.equals(type.energyConsumption) &&
                hasCoolers == type.hasCoolers &&
                group.equals(type.group) &&
                ports.equals(type.ports));
    }

    public Boolean getPereferial() {
        return pereferial;
    }

    public void setPereferial(Boolean pereferial) {
        this.pereferial = pereferial;
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public Integer getHasCoolers() {
        return hasCoolers;
    }

    public void setHasCoolers(Integer hasCoolers) {
        this.hasCoolers = hasCoolers;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }
}
