package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
public class ChartJsDataset {
    String label;
    List<Integer> data=new ArrayList<>();
    String borderColor = Color.corAleatória().getExadecimal();
}
