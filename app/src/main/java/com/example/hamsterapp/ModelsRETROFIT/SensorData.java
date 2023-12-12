package com.example.hamsterapp.ModelsRETROFIT;
public class SensorData {
    private String Sensor;
    private String msg;
    private String last_value;
    private int id;

    public String getSensor() {
        return Sensor;
    }

    public void setSensor(String sensor) {
        this.Sensor = sensor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLastValue() {
        return last_value;
    }

    public void setLastValue(String lastValue) {
        this.last_value = lastValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
