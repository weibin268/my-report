package com.zhuang.report.util;

import com.zhuang.data.jdbc.JdbcUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class JasperUtilsTest {

    @Test
    public void test() {

        System.out.println(getClass().getResource("/jasper").getPath());
    }

    @Test
    public void toXls() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test01.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test01.xls");
        Map params = new HashMap();
        JasperUtils.toDocument(JasperUtils.DocumentType.Xls, jasperFile, docFile, params, JdbcUtils.getConnection());
    }

    @Test
    public void toPdf() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test01.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test01.pdf");
        Map params = new HashMap();
        JasperUtils.toDocument(JasperUtils.DocumentType.Pdf, jasperFile, docFile, params, JdbcUtils.getConnection());
    }

    @Test
    public void toHtml() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test01.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test01.html");
        Map params = new HashMap();
        JasperUtils.toDocument(JasperUtils.DocumentType.Html, jasperFile, docFile, params, JdbcUtils.getConnection());
    }

    @Test
    public void compile() {
        String file = JasperUtils.compile(getClass().getResource("/jasper/test01.jrxml").getPath());
        System.out.println(file);
    }
}