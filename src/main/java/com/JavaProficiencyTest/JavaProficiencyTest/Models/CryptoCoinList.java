package com.JavaProficiencyTest.JavaProficiencyTest.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CryptoCoinList {

    private List<CryptoCoin> data;

    public CryptoCoinList() {
        data = new ArrayList<>();
    }
}
