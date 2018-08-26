package com.scorpion.converter.utils;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by KAI on 8/25/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class FileHelper {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);
    public final static String baseDir = "/Users/KAI/PROJECT/JAVA/docs-to-pdf-parrent";

    public static String generateDestinationFileName(String path, String extension) {
        return FilenameUtils.getBaseName(path).concat(".").concat(extension);
    }



}
