package thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.database.DbHelper;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.ThanhVien;

public class ThanhVienDAO {
    DbHelper dbHelper;
    public ThanhVienDAO(Context context){
        dbHelper = new DbHelper(context);

    }
    //model thanhvien
    //fragment QLphieuMuon
    public ArrayList<ThanhVien> getSDThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase  sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));

            }while (cursor.moveToNext());
        }
        return list;

    }
}
