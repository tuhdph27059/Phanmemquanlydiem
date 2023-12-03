/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhomsau.repository.impl;

import com.nhomsau.domainmodel.BangDiem;
import com.nhomsau.domainmodel.Diem;
import com.nhomsau.repository.IDiemRepository;
import com.nhomsau.util.DBConnection;
import com.nhomsau.viewmodel.BangDiemTheoMon;
import com.nhomsau.viewmodel.QuanLyDiem;
import java.math.BigDecimal;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nguyen Duy Hung
 */
public class DiemRepository implements IDiemRepository {

    @Override
    public List<BangDiemTheoMon> thongKeDiemTheoMon(String idMon,String idKy,String idNganh,Float min,Float max,Integer top, String sort) {
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        sql.append("select ");
        if(top != null) sql.append(" top ").append(top);
        sql.append(" Diem.IdSinhVien,SinhVien_Lop.IdLop 'IdLop',Diem.IdMonHoc, Sum(Diem.Diem * DauDiem_Mon.HeSo/100) 'DiemTrungBinh' ");
        sql.append(" from Diem ");
        sql.append("join DauDiem_Mon on DauDiem_Mon.IdDauDiem = Diem.IdDauDiem and Diem.IdMonHoc = DauDiem_Mon.IdMon ");
        sql.append("join SinhVien_Lop on SinhVien_Lop.IdSinhVien = Diem.IdSinhVien ");
        sql.append(" join Lop on Lop.Id = SinhVien_Lop.IdLop and Lop.IdMon = Diem.IdMonHoc ");
        sql.append("join Users on users.Id = SinhVien_Lop.IdSinhVien and ChucVu = 1 ");
        sql.append("join Ky_Mon on Ky_Mon.IdMon = Diem.IdMonHoc and Ky_Mon.idNganh = Users.IdNganh ");
        sql.append(" where Ky_Mon.IdKy = ? and Diem.IdMonHoc = ? ");
        if(idNganh != null){
            sql.append(" and Users.IdNganh = ?");
        }
        sql.append(" group by Diem.IdSinhVien  ,SinhVien_Lop.IdLop ,Diem.IdMonHoc ");
        List<BangDiemTheoMon> listResults = new ArrayList<>();
        try {
            
            if(max != null && min != null){
            sql.append(" having Sum(Diem.Diem * DauDiem_Mon.HeSo/100) between ? and ?");
            if(sort != null){
                sql.append(" order by Sum(Diem.Diem * DauDiem_Mon.HeSo/100) ").append(sort);
            }   
                if(idNganh != null)
                rs = DBConnection.getDataFromQuery(sql.toString(),idKy,idMon,idNganh,min,max);
                else
                rs = DBConnection.getDataFromQuery(sql.toString(),idKy,idMon,min,max);
            }
            else{
                if(sort != null){
                sql.append(" order by Sum(Diem.Diem * DauDiem_Mon.HeSo/100) ").append(sort);
            }
                if(idNganh != null){
                    rs = DBConnection.getDataFromQuery(sql.toString(),idKy,idMon,idNganh );
                }else rs = DBConnection.getDataFromQuery(sql.toString(),idKy,idMon );
            }
            
            while(rs.next()){
                String idSv = rs.getString("IdSinhVien");
                String idLop = rs.getString("IdLop");
                double diemTB = rs.getDouble("DiemTrungBinh");
                String idMonHoc = rs.getString("IdMonHoc");
                BangDiemTheoMon bangDiemTheoMon = new BangDiemTheoMon(idSv, idLop, diemTB);
                bangDiemTheoMon.setIdMon(idMonHoc);
                listResults.add(bangDiemTheoMon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listResults;
    }
    
    @Override
    public List<BangDiemTheoMon> thongKeDiemTatCaMon(String idNganh,String idKy,Double min, Double max,Integer top, String sort){
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        sql.append("select ");
        if(top != null) sql.append(" top ").append(top);
        sql.append(" Diem.IdSinhVien,SinhVien_Lop.IdLop 'IdLop',Diem.IdMonHoc, Sum(Diem.Diem * DauDiem_Mon.HeSo/100) 'DiemTrungBinh' ");
        sql.append(" from Diem ");
        sql.append("join DauDiem_Mon on DauDiem_Mon.IdDauDiem = Diem.IdDauDiem and Diem.IdMonHoc = DauDiem_Mon.IdMon ");
        sql.append("join SinhVien_Lop on SinhVien_Lop.IdSinhVien = Diem.IdSinhVien ");
        sql.append(" join Lop on Lop.Id = SinhVien_Lop.IdLop and Lop.IdMon = Diem.IdMonHoc ");
        sql.append("join Users on users.Id = SinhVien_Lop.IdSinhVien and ChucVu = 1 ");
        sql.append("join Ky_Mon on Ky_Mon.IdMon = Diem.IdMonHoc and Ky_Mon.idNganh = Users.IdNganh ");
        sql.append(" where Ky_Mon.IdKy = ? ");
        if(idNganh != null){
            sql.append(" and Users.IdNganh = ?");
        }
        sql.append(" group by Diem.IdSinhVien  ,SinhVien_Lop.IdLop ,Diem.IdMonHoc ");
        List<BangDiemTheoMon> listResults = new ArrayList<>();
        try {
            
            if(max != null && min != null){
            sql.append(" having Sum(Diem.Diem * DauDiem_Mon.HeSo/100) between ? and ?");
            if(sort != null){
                sql.append(" order by Sum(Diem.Diem * DauDiem_Mon.HeSo/100) ").append(sort);
            }   
                if(idNganh != null)
                rs = DBConnection.getDataFromQuery(sql.toString(),idKy,idNganh,min,max);
                else
                rs = DBConnection.getDataFromQuery(sql.toString(),idKy,min,max);
            }
            else{
                if(sort != null){
                sql.append(" order by Sum(Diem.Diem * DauDiem_Mon.HeSo/100) ").append(sort);
            }
                if(idNganh != null){
                    rs = DBConnection.getDataFromQuery(sql.toString(),idKy,idNganh );
                }else rs = DBConnection.getDataFromQuery(sql.toString(),idKy );
            }
            
            while(rs.next()){
                String idSv = rs.getString("IdSinhVien");
                String idLop = rs.getString("IdLop");
                double diemTB = rs.getDouble("DiemTrungBinh");
                String idMonHoc = rs.getString("IdMonHoc");
                BangDiemTheoMon bangDiemTheoMon = new BangDiemTheoMon(idSv, idLop, diemTB);
                bangDiemTheoMon.setIdMon(idMonHoc);
                listResults.add(bangDiemTheoMon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listResults;
    }
    
    final String sql="select TenDauDiem, Diem from Diem "
            + " join DauDiem on Diem.IdDauDiem = DauDiem.Id "
            
            + " where IdMonHoc = ? and IdSinhVien = ? ";
    @Override
    public List<BangDiem> getDiem(String idsv,String idMon){
        List<BangDiem> list = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql,idMon,idsv);
            while(rs.next()){
                String tenDiem = rs.getString(1);
                double diem = Double.valueOf(rs.getBigDecimal(2)+"");
                BangDiem b = new BangDiem(tenDiem, diem);
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    DBConnection dBConnection;
    final String Insert_Sql = "insert into Diem (IdSinhVien,IdMonHoc,IdDauDiem,Diem,NgayNhap) values(?,?,?,?,getdate())";
    final String Select_Diem = "select Diem from Diem where IdSinhVien = ? and IdMonHoc = ? and IdDauDiem = ?";
    final String Update = "update Diem set Diem = ?, NgaySua = getdate() where idSinhVien = ? and IdMonHoc = ? and IdDauDiem = ?";
    final String select_sv = "select * from diem where idSinhVien = ? and IdMonHoc = ? and IdDauDiem = ?";
    
    public DiemRepository() {
        dBConnection = new DBConnection();
    }

    public float getDiem(String idSV, String idMonHoc, String idDauDiem) {
        float diem = -1;
        try {
            ResultSet rs = dBConnection.getDataFromQuery(Select_Diem, idSV, idMonHoc, idDauDiem);
            while (rs.next()) {
                diem = rs.getFloat("Diem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diem;
    }

    public int saveDiem(Diem d){
        int kq = DBConnection.ExcuteDungna(Insert_Sql, d.getIdSV(), d.getIdMonHoc(), d.getIdDauDiem(), d.getDiem());
        if(kq == 1){
            return 1;
        }else{
            return -1;
        }
    }
    
    public int updateDiem(Diem d){
        int kq = dBConnection.ExcuteDungna(Update, d.getDiem(), d.getIdSV(),d.getIdMonHoc(),d.getIdDauDiem());
        if(kq == 1){
            return 1;
        }else{
            return -1;
        }
    }
    
    public int checkSV(String idSV,String idMonHoc,String idDauDiem){
        
        try {
            ResultSet rs = dBConnection.getDataFromQuery(select_sv, idSV,idMonHoc,idDauDiem);
            if(rs.next()){
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public List<QuanLyDiem> getDiemByMon(String idDauDiem, String idMon, String idLop) {
        StringBuilder sql = new StringBuilder();
        sql.append("select Diem.IdSinhVien,Diem,ghichu from Diem ");
        sql.append("join Users on USers.Id = Diem.IdSinhVien ");
        sql.append("join Lop on Lop.IdMon = Diem.IdMonHoc ");
        sql.append(" join SinhVien_Lop on Lop.Id = SinhVien_Lop.IdLop and Diem.IdSinhVien = SinhVien_Lop.IdSinhVien ");
        sql.append("where IdDauDiem = ? and IdMonHoc = ? and Lop.Id = ? ");
        sql.append("order by HoTen");
        
        List<QuanLyDiem> result = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql.toString(), idDauDiem,idMon,idLop);
            while(rs.next()){
                String idUsers = rs.getString(1);
                
                BigDecimal diem = rs.getBigDecimal(2);
                
                String ghiChu = rs.getNString("GhiChu");
                
                QuanLyDiem qLDiem = new QuanLyDiem(idUsers, diem, ghiChu);
               result.add(qLDiem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public BangDiemTheoMon getDiemTrungBinhTheoId(String idSV, String idKy, String idNganh, String idMon) {
        StringBuilder sql = new StringBuilder();
        sql.append("select Diem.IdSinhVien  ,SinhVien_Lop.IdLop 'IdLop',Diem.IdMonHoc, Sum(Diem.Diem * DauDiem_Mon.HeSo/100) 'DiemTrungBinh'");
        sql.append(" from Diem ");
        sql.append("join DauDiem_Mon on DauDiem_Mon.IdDauDiem = Diem.IdDauDiem and Diem.IdMonHoc = DauDiem_Mon.IdMon ");
        sql.append("join SinhVien_Lop on SinhVien_Lop.IdSinhVien = Diem.IdSinhVien ");
        sql.append("join Lop on Lop.Id = SinhVien_Lop.IdLop and Lop.IdMon = Diem.IdMonHoc ");
        sql.append("join Users on users.Id = Diem.IdSinhVien ");
        sql.append("join Ky_Mon on Ky_Mon.IdMon = Diem.IdMonHoc and Users.IdNganh = Ky_Mon.IdNganh ");
        sql.append("where users.IdNganh = ? and Ky_Mon.IdKy = ? and Diem.IdMonHoc = ? and Diem.IdSinhVien = ? ");
        sql.append("group by Diem.IdSinhVien  ,SinhVien_Lop.IdLop ,Diem.IdMonHoc");
        List<BangDiemTheoMon> listResults = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql.toString(), idNganh,idKy,idMon,idSV);
            while(rs.next()){
                String idSv = rs.getString("IdSinhVien");
                String idLop = rs.getString("IdLop");
                double diemTB = rs.getDouble("DiemTrungBinh");
                BangDiemTheoMon bangDiemTheoMon = new BangDiemTheoMon(idSv, idLop, diemTB);
                listResults.add(bangDiemTheoMon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiemRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listResults.isEmpty() ? null : listResults.get(0);
    }
}
