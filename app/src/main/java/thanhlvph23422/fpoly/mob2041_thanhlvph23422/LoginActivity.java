package thanhlvph23422.fpoly.mob2041_thanhlvph23422;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);

        ThuThuDAO thuthuDAO = new ThuThuDAO(this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if (thuthuDAO.checkDangNhap(user,pass)){
                    //luu
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt",user);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));


                }else {
                    Toast.makeText(LoginActivity.this, "Tên Đăng Nhập Hoặc MK ko đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}