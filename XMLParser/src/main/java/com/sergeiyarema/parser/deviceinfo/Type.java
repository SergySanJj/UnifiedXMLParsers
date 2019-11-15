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
}
