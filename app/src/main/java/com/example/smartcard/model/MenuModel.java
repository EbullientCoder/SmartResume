package com.example.smartcard.model;

public class MenuModel {

    private int icon,code;
    private boolean isSelected;


    public MenuModel(int icon, int code, boolean isSelected) {
        this.icon = icon;
        this.code = code;
        this.isSelected = isSelected;
    }

    public int getIcon() {
        return icon;
    }

    public int getCode() {
        return code;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
