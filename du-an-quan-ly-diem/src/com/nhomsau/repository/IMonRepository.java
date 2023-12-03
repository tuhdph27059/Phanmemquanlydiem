/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhomsau.repository;

import com.nhomsau.domainmodel.Mon;
import com.nhomsau.viewmodel.QuanLyMon;
import java.util.List;

/**
 *
 * @author Nguyen Duy Hung
 */
public interface IMonRepository {
    List<QuanLyMon> findAll();
    List<QuanLyMon> getMonTheoNganh(String idNganh,String idKy); 
    List<QuanLyMon> getMonTheoGV(String idNganh,String idKy,String idGV); 
    QuanLyMon findOne(String idMon);
    void insert(Mon mon);
    void delete(String idMon);
    void update(Mon mon);
    List<QuanLyMon> findMon(String idky,String idNganh);
    String getTenMon(String id);
    List<QuanLyMon> getMonDaCo(String idKy,String idNganh); 
    List<QuanLyMon> getMonChuaCo(String idKy,String idNganh); 
    String getIdMon(String maMon);
    int getTinChi(String idNganh,String tenMon);
    int getTinChi1(String idNganh);
    String getTenMonNganh(String idNganh);
    String getIdByName(String name);
}
