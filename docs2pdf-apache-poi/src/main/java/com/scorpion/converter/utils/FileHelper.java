package com.scorpion.converter.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

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


    public static String file2Base64(String filePath) throws IOException {
        if (StringUtils.isNotBlank(filePath)) {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            return Base64.getEncoder().encodeToString(fileContent);
        }
        throw new IllegalArgumentException("Path of file is illegal");
    }


    public static String first3Char(String var) {
        if (StringUtils.isNotBlank(var))
            return StringUtils.left(var, 3);
        throw new IllegalArgumentException("Argument is null or empty.");
    }

    public static String last3Char(String var) {
        if (StringUtils.isNotBlank(var))
            return StringUtils.right(var, 3);
        throw new IllegalArgumentException("Argument is null or empty.");
    }

}
