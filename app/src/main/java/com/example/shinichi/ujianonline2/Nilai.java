package com.example.shinichi.ujianonline2;

/**
 * Created by ShiNiChI on 6/6/2016.
 */
public class Nilai {

    private String idgs;
    private String nis;
    private String nilai;


    public Nilai (String idgs, String nis, String nilai)
    {

        this.setIdgs(idgs);
        this.setNilai(nilai);
        this.setNis(nis);

    }

    public String getIdgs() {
        return idgs;
    }

    public void setIdgs(String idgs) {
        this.idgs = idgs;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
