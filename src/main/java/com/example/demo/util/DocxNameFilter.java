package com.example.demo.util;

import java.io.File;
import java.io.FilenameFilter;

public class DocxNameFilter implements FilenameFilter {

    public static final String EXTENSION = "docx";

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(EXTENSION);
    }
}
