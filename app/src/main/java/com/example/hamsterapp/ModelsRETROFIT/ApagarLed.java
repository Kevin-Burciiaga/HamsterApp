package com.example.hamsterapp.ModelsRETROFIT;

public class ApagarLed {
    private int led_value;

    public ApagarLed(int led_value) {
        this.led_value = led_value;
    }

    public int getLed_value() {
        return led_value;
    }

    public void setLed_value(int led_value) {
        this.led_value = led_value;
    }
}
