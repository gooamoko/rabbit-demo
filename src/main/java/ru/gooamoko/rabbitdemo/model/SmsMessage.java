package ru.gooamoko.rabbitdemo.model;


public class SmsMessage extends GenericRabbitMessage {
    private String phone;
    private String text;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
