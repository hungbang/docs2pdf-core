package com.scorpion.converter.google;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.scorpion.converter.utils.FileHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import static com.scorpion.converter.utils.GoogleMimeType.MS_WORD_DOCUMENT;
import static com.scorpion.converter.utils.GoogleMimeType.PDF;

/**
 * Created by KAI on 8/26/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class GoogleDownloader {
    public static final Logger LOGGER = LoggerFactory.getLogger(GoogleDownloader.class);

    private String path;
    private static Drive driveService = null;
    private static GoogleDownloader googleDownloader = null;

    private GoogleDownloader() {
    }

    public static GoogleDownloader getInstance() {
        try {
            driveService = DriverServiceFactory.getInstance().getDriveService();
        } catch (IOException | GeneralSecurityException e) {
            LOGGER.error(GoogleDownloader.class.getSimpleName(), e);
        }
        if (googleDownloader == null)
            return new GoogleDownloader();
        return googleDownloader;
    }

    public void download(String fileId) throws IOException {

        //write output stream to file in java
        try (FileOutputStream fileOutputStream = new FileOutputStream(new java.io.File(FileHelper.generateDestinationFileName(this.path, "pdf")))) {
            driveService.files().get(fileId)
                    .executeMediaAndDownloadTo(fileOutputStream);
        }
    }

    public String export(String fileId) throws IOException {
        final String path = FileHelper.generateDestinationFileName(this.path, "pdf");
        try (FileOutputStream fileOutputStream = new FileOutputStream(new java.io.File(path))) {
            driveService.files().export(fileId, MS_WORD_DOCUMENT)
                    .executeMediaAndDownloadTo(fileOutputStream);
        }
        return path;

    }

    public void setPath(String path) {
        this.path = path;
    }
    public GoogleDownloader path(String path){
        this.path = path;
        return this;
    }
}
