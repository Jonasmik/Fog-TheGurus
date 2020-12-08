package com.yourcompany.exceptions.bom;

import com.yourcompany.exceptions.bom.NoSuchMaterialExist;

public class UnsatisfiableCarport extends Exception {
    public UnsatisfiableCarport(NoSuchMaterialExist e) {
        super(e);
    }
}
