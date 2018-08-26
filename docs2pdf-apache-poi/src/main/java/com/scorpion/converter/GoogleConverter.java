package com.scorpion.converter;

import com.google.api.services.drive.model.File;
import com.scorpion.converter.google.GoogleDownloader;
import com.scorpion.converter.google.GoogleUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by KAI on 8/26/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class GoogleConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(GoogleConverter.class);

    private String path;
    private static GoogleConverter googleConverter = null;
    private InputStream inputStream;

    public static GoogleConverter asConverter() {
        if (googleConverter == null)
            return new GoogleConverter();
        return googleConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public GoogleConverter path(String path) {
        this.path = path;
        return this;
    }

    public String convert() throws IOException {
        // upload file to google driver
        File file = GoogleUploader.getInstance().path(this.path).upload();
        LOGGER.info("Docs file is uploaded to drive google {}", file.getId());
        //export file to pdf
        return GoogleDownloader.getInstance().path(this.path).export(file.getId());
    }

}
