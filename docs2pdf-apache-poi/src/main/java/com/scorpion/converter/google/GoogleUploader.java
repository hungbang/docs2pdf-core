package com.scorpion.converter.google;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static com.scorpion.converter.utils.GoogleMimeType.MSWORD;
import static com.scorpion.converter.utils.GoogleMimeType.MS_WORD_DOCUMENT;

/**
 * Created by KAI on 8/25/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class GoogleUploader {
    public static final Logger LOGGER = LoggerFactory.getLogger(GoogleUploader.class);

    private String path;
    private static Drive driveService = null;
    private static GoogleUploader googleUploader = null;

    private GoogleUploader(){}

    public static GoogleUploader getInstance() {
        try {
            driveService = DriverServiceFactory.getInstance().getDriveService();
        } catch (IOException | GeneralSecurityException e) {
            LOGGER.error(GoogleUploader.class.getSimpleName(), e);
        }
        if (googleUploader == null)
            return new GoogleUploader();
        return googleUploader;
    }


    public File upload() throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(FilenameUtils.getBaseName(this.path));
        fileMetadata.setMimeType(MS_WORD_DOCUMENT);
        java.io.File filePath = new java.io.File(this.path);
        FileContent mediaContent = new FileContent(MS_WORD_DOCUMENT, filePath);
        return driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public GoogleUploader path(String path){
        this.path = path;
        return this;
    }
}
