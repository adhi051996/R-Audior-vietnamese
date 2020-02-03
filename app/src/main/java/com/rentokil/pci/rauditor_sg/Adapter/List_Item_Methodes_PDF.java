package com.rentokil.pci.rauditor_sg.Adapter;

/**
 * Created by Murugan Kuppusamy on 04-Oct-17.
 * */
public class List_Item_Methodes_PDF {
    private String audi_name;
    private String audi_date;
    private String cus_name;
    private String doc_no;
    private String status;
    private String image;

    private int id;


    public List_Item_Methodes_PDF(int id, String audi_name, String cus_name, String audi_date, String doc_no, String status, String image) {
        this.audi_name = audi_name;
        this.cus_name = cus_name;
        this.audi_date = audi_date;
        this.doc_no = doc_no;
        this.status = status;
        this.image = image;

        this.id = id;
    }

    public String getCus_name() {
        return this.cus_name;
    }

    public int getId() {
        return this.id;
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
    public String getStatus() {
        return this.status;
    }
    public String getimage() {
        return this.image;
    }

}
