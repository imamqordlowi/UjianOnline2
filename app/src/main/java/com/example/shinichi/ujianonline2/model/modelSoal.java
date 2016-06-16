package com.example.shinichi.ujianonline2.model;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ShiNiChI on 6/5/2016.
 */
public class modelSoal implements Serializable{

    private String id;
    private String id_group_soal;
    private String nomor_soal_pg;
    private String soal;
    private String opsi_a;
    private String opsi_b;
    private String opsi_c;
    private String opsi_d;
    private String opsi_e;
    private String jawaban;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getid_group_soal() { return id_group_soal;
    }

    public void setid_group_soal(String id_group_soal) {
        this.id_group_soal = id_group_soal;
    }

    public String getnomor_soal_pg() {
        return nomor_soal_pg;
    }

    public void setnomor_soal_pg(String nomor_soal_pg) {
        this.nomor_soal_pg = nomor_soal_pg;
    }

    public String getsoal() {
        return soal;
    }

    public void setsoal(String soal) {
        this.soal = soal;
    }

    public String getopsi_a() {
        return opsi_a;
    }

    public void setopsi_a(String opsiA) {
        this.opsi_a = opsi_a;
    }

    public String getopsi_b() {
        return opsi_b;
    }

    public void setopsi_b(String opsi_b) {
        this.opsi_b = opsi_b;
    }

    public String getopsi_c() {
        return opsi_c;
    }

    public void setopsi_c(String opsi_c) {
        this.opsi_c = opsi_c;
    }

    public String getopsi_d() {
        return opsi_d;
    }

    public void setopsi_d(String opsi_d) {
        this.opsi_d = opsi_d;
    }

    public String getopsi_e() {  return opsi_e;
    }

    public void setopsi_e(String opsi_e) {
        this.opsi_e = opsi_e;
    }

    public String getjawaban() {
        return jawaban;
    }

    public void setjawaban(String jawaban) {
        this.jawaban = jawaban;
    }

}
