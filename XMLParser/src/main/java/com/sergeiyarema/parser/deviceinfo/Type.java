package com.sergeiyarema.parser.deviceinfo;

/*•	Type (должно быть несколько) – периферийное либо нет, энергопотребление (ватт), наличие кулера (есть либо нет),
     группа комплектующих (устройства ввода-вывода, мультимедийные), порты (COM, USB, LPT).
     */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Type {
    private Boolean pereferial;
    private Integer energyConsumption;
    private Boolean hasCooler;
    private Group group;
    private List<Port> ports;

    public Type(Boolean pereferial, Integer energyConsumption, Boolean hasCooler, Group group, List<Port> ports) {
        this.pereferial = pereferial;
        this.energyConsumption = energyConsumption;
        this.hasCooler = hasCooler;
        this.group = group;
        this.ports = ports;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("- is pereferial: ").append(pereferial).append("\n");
        stringBuilder.append("- energy consumption: ").append(energyConsumption).append(" WT").append("\n");
        stringBuilder.append("- has cooler: ").append(hasCooler).append("\n");
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
        return Objects.hash(pereferial, energyConsumption, hasCooler, group, ports);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;

        Type type = (Type) o;
        return (pereferial == type.pereferial &&
                energyConsumption.equals(type.energyConsumption) &&
                hasCooler == type.hasCooler &&
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

    public Boolean getHasCooler() {
        return hasCooler;
    }

    public void setHasCooler(Boolean hasCooler) {
        this.hasCooler = hasCooler;
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
