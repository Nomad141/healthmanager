package com.graduationproject.healthmanager.model;

public class PDFResult extends Result{
    public PDFResult(int code, String message, String pdfpath) {
        super(code, message);
        this.pdfpath = pdfpath;
    }

    public String getPdfpath() {
        return pdfpath;
    }

    public void setPdfpath(String pdfpath) {
        this.pdfpath = pdfpath;
    }

    private String pdfpath;
}
