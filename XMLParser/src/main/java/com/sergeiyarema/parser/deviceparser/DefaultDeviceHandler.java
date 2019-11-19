package com.sergeiyarema.parser.deviceparser;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceinfo.Group;
import com.sergeiyarema.parser.deviceinfo.Port;
import com.sergeiyarema.parser.deviceinfo.Type;

import java.util.ArrayList;

public class DefaultDeviceHandler implements DefaultHandler<Device> {
    protected Device device;
    protected Type type;

    FieldNames currentState;

    public DefaultDeviceHandler() {
        device = new Device();
        type = new Type();
        device.setType(type);

        currentState = FieldNames.None;
    }

    @Override
    public void onTagStart(String tag) {
        switch (tag) {
            case "name":
                currentState = FieldNames.Name;
                break;
            case "origin":
                currentState = FieldNames.Origin;
                break;
            case "price":
                currentState = FieldNames.Price;
                break;
            case "type":
                currentState = FieldNames.Type;
                break;
            case "pereferial":
                currentState = FieldNames.Pereferial;
                break;
            case "energy-consumption":
                currentState = FieldNames.EnergyConsumption;
                break;
            case "has-cooler":
                currentState = FieldNames.HasCooler;
                break;
            case "group":
                currentState = FieldNames.Group;
                break;
            case "ports-root":
                type.setPorts(new ArrayList<>());
                break;
            case "port":
                currentState = FieldNames.Port;
                break;
            case "critical":
                currentState = FieldNames.Critical;
                break;
            default:
                currentState = FieldNames.None;
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

        currentState = FieldNames.None;
    }

    @Override
    public Device getParseResult() {
        return device;
    }
}
