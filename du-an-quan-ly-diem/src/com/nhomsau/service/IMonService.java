/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhomsau.service;

import com.nhomsau.viewmodel.QuanLyMon;
import java.util.List;

/**
 *
 * @author Nguyen Duy Hung
 */
public interface IMonService {
    List<QuanLyMon> findAll();
    List<QuanLyMon> getMonTheoNganh(String idNganh,String idKy);
    List<QuanLyMon> getMonTheoGV(String idNganh,String idKy,String idGV); 
    QuanLyMon findOne(String id);
    List<QuanLyMon> findMon(String idky,String idNganh);
    void insert(QuanLyMon mon);
    void update (QuanLyMon mon);
    void delete(String idMon);
    String getTenMon(String id);
    List<QuanLyMon> getMonDaCo(String idKy,String idNganh); 
    List<QuanLyMon> getMonChuaCo(String idKy,String idNganh);
    String getIdMon(String maMon);
}
