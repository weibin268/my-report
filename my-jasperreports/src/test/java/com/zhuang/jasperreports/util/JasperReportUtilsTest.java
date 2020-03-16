package com.zhuang.jasperreports.util;

import com.zhuang.data.jdbc.JdbcUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperReportUtilsTest {

    public static class Person {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() {

        System.out.println(getClass().getResource("/jasper").getPath());
    }

    @Test
    public void toXls() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test01.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test01.xls");
        Map params = new HashMap();
        JasperReportUtils.toDocument(JasperReportUtils.DocumentType.Xls, jasperFile, docFile, params, JdbcUtils.getConnection());
    }

    @Test
    public void toPdf() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test01.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test01.pdf");
        Map params = new HashMap();
        JasperReportUtils.toDocument(JasperReportUtils.DocumentType.Pdf, jasperFile, docFile, params, JdbcUtils.getConnection());
    }

    @Test
    public void toHtml() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test01.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test01.html");
        Map params = new HashMap();
        JasperReportUtils.toDocument(JasperReportUtils.DocumentType.Html, jasperFile, docFile, params, JdbcUtils.getConnection());
    }

    @Test
    public void toHtml2() throws FileNotFoundException {
        InputStream jasperFile = getClass().getResourceAsStream("/jasper/test02.jasper");
        OutputStream docFile = new FileOutputStream(getClass().getResource("/jasper").getPath() + "/test02.html");
        Map params = new HashMap();
        List<Person> dataList = new ArrayList<Person>();
        Person person = new Person();
        person.setName("zwb");
        dataList.add(person);
        JasperReportUtils.toDocument(JasperReportUtils.DocumentType.Html, jasperFile, docFile, params, dataList);
    }

    @Test
    public void compile() {
        String file = JasperReportUtils.compile(getClass().getResource("/jasper/test01.jrxml").getPath());
        System.out.println(file);
    }
}