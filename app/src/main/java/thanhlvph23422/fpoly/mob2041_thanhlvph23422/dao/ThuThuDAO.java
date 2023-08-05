package thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DbHelper(context);


    }
    //dang nhạp
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt,matkhau});
        if (cursor.getCount() != 0){
            //...
            return true;
        }else {
            return false;
        }
    }
    public int capNhapMatKhau(String username,String OldPass,String newPass){
        //check xem đúng mật khẩu hay ko thông qua câu lệnh truy vấn SELECT
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?",new String[]{username, OldPass});
        //Nếu như tìm ra thì sẽ thực hiện công việc update lại mk
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau",newPass);
            //update lại mk thông qua câu lệnh newPass
            long check = sqLiteDatabase.update("THUTHU",contentValues,"matt = ?", new String[]{username});
            if (check == -1)
                return  -1;
            return 1;
            //update thành công sẽ là 1 còn ko sẽ là -1
        }
        return 0;
    }
}
