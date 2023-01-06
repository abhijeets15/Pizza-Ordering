package com.example.rupizza;

public class ItemNY {
    String topping;
    int image;
    private boolean isChecked = false;

    public ItemNY(String topping, int image){
        this.topping = topping;
        this.image = image;
    }

    public ItemNY() {
        isChecked = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
