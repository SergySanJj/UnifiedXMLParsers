package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceinfo.Group;
import com.sergeiyarema.parser.deviceinfo.Port;
import com.sergeiyarema.parser.deviceinfo.Type;

import java.util.ArrayList;

public class DeviceHandler implements DefaultHandler<Device> {
    protected Device device;
    protected Type type;

    FieldTypes currentState;

    public DeviceHandler() {
        device = new Device();
        type = new Type();

        currentState = FieldTypes.None;
    }

    @Override
    public void onTagStart(String tag) {
        switch (tag) {
            case "name":
                currentState = FieldTypes.Name;
                break;
            case "origin":
                currentState = FieldTypes.Origin;
                break;
            case "price":
                currentState = FieldTypes.Price;
                break;
            case "type":
                currentState = FieldTypes.Type;
                break;
            case "pereferial":
                currentState = FieldTypes.Pereferial;
                break;
            case "energy-consumption":
                currentState = FieldTypes.EnergyConsumption;
                break;
            case "has-cooler":
                currentState = FieldTypes.HasCooler;
                break;
            case "group":
                currentState = FieldTypes.Group;
                break;
            case "ports-root":
                type.setPorts(new ArrayList<>());
                break;
            case "port":
                currentState = FieldTypes.Port;
                break;
            case "critical":
                currentState = FieldTypes.Critical;
                break;
            default:
                currentState = FieldTypes.None;
        }
    }

    @Override
    public void onTagEnd(String tag) {
    }

    @Override
    public void setAttribute(String attributeName, String value) {
        if (value == null) value = "";

        if (attributeName.equals("id")) device.setId(value);
    }

    @Override
    public void setTag(String information) {
        switch (currentState) {
            case Name:
                device.setName(information);
                break;
            case Origin:
                device.setOrigin(information);
                break;
            case Price:
                device.setPrice(Integer.valueOf(information));
                break;
            case Pereferial:
                type.setPereferial(Boolean.valueOf(information));
                break;
            case EnergyConsumption:
                type.setEnergyConsumption(Integer.valueOf(information));
                break;
            case HasCooler:
                type.setHasCoolers(Integer.valueOf(information));
                break;
            case Group:
                type.setGroup(Group.valueOf(information));
                break;
            case Port:
                type.getPorts().add(Port.valueOf(information));
                break;
            case Critical:
                device.setCritical(Boolean.valueOf(information));
                break;
            default:
                break;
        }

        currentState = FieldTypes.None;
    }

    @Override
    public Device getParseResult() {
        device.setType(type);
        return device;
    }
}
