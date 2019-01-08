package com.zhuang.report.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.*;
import java.sql.Connection;
import java.util.Map;

public class JasperUtils {

    enum DocumentType {
        Html,
        Xls,
        Pdf
    }

    public static void toDocument(DocumentType documentType, String jasperFile, String docFile, Map params, Connection connection) {
        try {
            toDocument(documentType, new FileInputStream(jasperFile), new FileOutputStream(docFile), params, connection);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void toDocument(DocumentType documentType, InputStream jasperInputStream, OutputStream docOutputStream, Map params, Connection connection) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperInputStream, params, connection);
            JRAbstractExporter exporter = null;
            ExporterOutput exporterOutput = null;
            if (documentType == DocumentType.Html) {
                exporter = new HtmlExporter();
                exporterOutput = new SimpleHtmlExporterOutput(docOutputStream);
            } else if (documentType == DocumentType.Xls) {
                exporter = new JRXlsExporter();
                exporterOutput = new SimpleOutputStreamExporterOutput(docOutputStream);
            } else if (documentType == DocumentType.Pdf) {
                exporter = new JRPdfExporter();
                exporterOutput = new SimpleOutputStreamExporterOutput(docOutputStream);
            }
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(exporterOutput);
            exporter.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public static String compile(String sourceFileName) {
        try {
            return JasperCompileManager.compileReportToFile(sourceFileName);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

}
