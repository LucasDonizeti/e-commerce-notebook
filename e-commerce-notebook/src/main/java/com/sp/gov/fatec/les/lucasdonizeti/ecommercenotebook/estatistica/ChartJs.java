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
public class ChartJs {
    List<String> labels = new ArrayList<>();
    List<ChartJsDataset> datasets=new ArrayList<>();
}
