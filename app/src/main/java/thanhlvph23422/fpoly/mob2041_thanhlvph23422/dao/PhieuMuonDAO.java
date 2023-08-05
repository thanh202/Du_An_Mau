package thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.database.DbHelper;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.PhieuMuon;

public class PhieuMuonDAO {
    DbHelper dbHelper;
    public  PhieuMuonDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuMuon> getDSPhieuMuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.mapm, pm.matv, tv.hoten, pm.matt, tt.hoten, pm.masach, sc.tensach, pm.ngay, pm.trasach, pm.tienthue FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc  WHERE pm.matv = tv.matv and pm.matt = tt.matt AND pm.masach = sc.masach ORDER BY pm.mapm DESC",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));

            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean ThayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trasach",1);
        long check = sqLiteDatabase.update("PHIEUMUON",contentValues,"mapm = ?",new String[]{String.valueOf(mapm)});
        if (check == -1){
            return false;
        }
        return true;
    }
    //dialog_them_phieumuon
    public boolean ThemPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //mapm integer primary key autoincrement, matv integer references THANHVIEN(matv), matt text references THUTHHU(matt),masach integer references SACH(masach), ngay text, trasach integer, tienthue integer
       // contentValues.put("mapm",phieuMuon.getMapm());
        contentValues.put("matv",phieuMuon.getMatv());
        contentValues.put("matt",phieuMuon.getMatt());
        contentValues.put("masach",phieuMuon.getMasach());
        contentValues.put("ngay",phieuMuon.getNgay());
        contentValues.put("trasach",phieuMuon.getTrasach());
        contentValues.put("masach",phieuMuon.getMasach());
        contentValues.put("tienthue",phieuMuon.getTienthue());

        long check = sqLiteDatabase.insert("PHIEUMUON",null,contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
}
