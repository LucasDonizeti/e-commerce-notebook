package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica;

import java.util.Random;

/**
 * author LucasDonizeti
 */
public enum Color {
    AQUAMARINE("#7FFFD4"),
    BLACK("#000000"),
    BLUE("#0000FF"),
    BLUEVIOLET("#8A2BE2"),
    CHARTREUSE("#7FFF00"),
    CYAN("#00FFFF"),
    DARKBLUE("#00008B"),
    FUCHSIA("#FF00FF"),
    GOLD("#FFD700"),
    GREY("#808080"),
    GREEN("#00FF00"),
    GREENYELLOW("#ADFF2F"),
    INDIGO("#4B0082"),
    ORANGERED("#FF4500"),
    PURPLE("#800080"),
    REF("#FF0000"),
    ROYALBLUE("#4569E1"),
    YELLOW("#FFFF00"),
    MAROON("#80000"),
    DEEPPINK("#FF1493"),
    DARKMAGENTA("#8B008B"),
    CHOCOLATE("#D2691E"),
    DARKOLIVEEGREEN("#556B2F"),
    LIME("#00FF00"),
    SPRINGGREEN("#00FA9A"),
    DARKSLATEGRAY("#2F4F4F");


    String exadecimal;

    Color(String color){
        this.exadecimal=color;
    }

    public String getExadecimal(){
        return exadecimal;
    }

    public static Color corAleatória(){
        return Color.values()[new Random().nextInt(Color.values().length)];
    }

}
