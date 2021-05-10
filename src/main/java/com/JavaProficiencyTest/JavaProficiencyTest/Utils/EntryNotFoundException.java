package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class EntryNotFoundException extends BadRequestAlertException {

    public EntryNotFoundException(String entry) {
        super(entry + " not found");
    }
}