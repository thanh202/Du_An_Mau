package thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.database.DbHelper;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.Sach;

public class SachDAO {
    DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    //lấy toàn bộ đầu sách có ở trong thư viện
    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
