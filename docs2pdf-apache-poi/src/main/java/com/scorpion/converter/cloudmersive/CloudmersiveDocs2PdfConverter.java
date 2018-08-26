package com.scorpion.converter.cloudmersive;

import com.scorpion.converter.utils.FileHelper;
import com.scorpion.exception.Docs2PdfConverterException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.*;
import com.cloudmersive.client.ConvertDocumentApi;

import static com.scorpion.converter.utils.FileHelper.baseDir;

/**
 * Created by KAI on 8/26/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class CloudmersiveDocs2PdfConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(CloudmersiveDocs2PdfConverter.class);

    private static final String apikey = "737d009f-b405-43bf-9e8e-0725ba6bce71";
    private String path;
    private static CloudmersiveDocs2PdfConverter docsToPdfConverter = null;
    private InputStream inputStream;
    private static ApiClient apiClient;

    public static CloudmersiveDocs2PdfConverter asConverter() {
        apiClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) apiClient.getAuthentication("Apikey");
        apiKey.setApiKey(apikey);
        apiClient.setApiKey(apikey);

        if (docsToPdfConverter == null)
            return new CloudmersiveDocs2PdfConverter();
        return docsToPdfConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public CloudmersiveDocs2PdfConverter path(String path) {
        this.path = path;
        return this;
    }


    public void convert() throws Docs2PdfConverterException {

        ConvertDocumentApi apiInstance = new ConvertDocumentApi();
        apiInstance.setApiClient(apiClient);
        File inputFile = new File(this.path); // File | Input file to perform the operation on.
        try {
            byte[] result = apiInstance.convertDocumentDocxToPdf(inputFile);
            FileUtils.writeByteArrayToFile(new File(baseDir + File.separator +
                    FileHelper.generateDestinationFileName(this.path, "pdf")), result);
        } catch (IOException | ApiException e) {
            LOGGER.error(getClass().getSimpleName(), e);
            throw new Docs2PdfConverterException();
        }
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public CloudmersiveDocs2PdfConverter apiClient(ApiClient apiClient){
        this.apiClient = apiClient;
        return this;
    }
}
