package com.rentokil.pci.rauditor_vn.Adapter;

public class List_Item_PDF_Methodes {

    private String audi_name;
    private String audi_date;
    private String cus_name;
    private String txtcusid;
    private String doc_no;
    private String pay_stat;






    public List_Item_PDF_Methodes( String audi_name, String cus_name, String audi_date, String txtcusid, String doc_no) {
        this.audi_name = audi_name;
        this.cus_name = cus_name;
        this.audi_date = audi_date;
        this.doc_no = doc_no;
        this.txtcusid = txtcusid;



    }

    public String getCus_name() {
        return this.cus_name;
    }
    public String getAudi_name() {
        return this.audi_name;
    }
    public String getDoc_no() {
        return this.doc_no;
    }
    public String getAudi_date() {
        return this.audi_date;
    }
    public String getcus_id() {
        return this.txtcusid;
    }



}


