package thanhlvph23422.fpoly.mob2041_thanhlvph23422;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.ThuThuDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.fragment.QLLoaiSachFragment;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.fragment.QLPhieuMuonFragment;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.fragment.ThongKeTop10Fragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SachDAO dao = new SachDAO(this);
//        dao.getDSDauSach();

        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        NavigationView navigationView = findViewById(R.id.navigationView);

        drawerLayout = findViewById(R.id.drawableLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mQLPhieuMuon:
                        fragment = new QLPhieuMuonFragment();
                        break;
                    case R.id.mQLLoaiSach:
                        fragment = new QLLoaiSachFragment();
                        break;
                    case R.id.mThoat:
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    case R.id.mDoimatKhau:
                        showDialogDoiMatKhau();
                        break;
                    case R.id.mTop10:
                        fragment = new ThongKeTop10Fragment();
                        break;
                    default:
                        fragment = new QLPhieuMuonFragment();
                        break;
                }
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.framelayout,fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void showDialogDoiMatKhau(){
        AlertDialog.Builder builder =  new AlertDialog.Builder(this)
                .setNegativeButton("Câp Nhật",null)
                .setPositiveButton("Hủy",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText edtOldPass = view.findViewById(R.id.edtPassOld);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtReNewPaw = view.findViewById(R.id.edtReNewPass);

        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String reNewPass = edtReNewPaw.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || reNewPass.equals("")){
                    Toast.makeText(MainActivity.this, "Nhập Đày Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (newPass.equals(reNewPass)){
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                        String matt = sharedPreferences.getString("matt","");
                        //cập nhật
                        ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                        int check = thuThuDAO.capNhapMatKhau(matt,oldPass,newPass);
                        //nếu check =1 thì cập nhật thành công
                        if (check == 1){
                            Toast.makeText(MainActivity.this, "Cập Nhật Mật khẩu Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //nếu nó == 0 sẽ là ko tìm thấy ng dùng đó
                        }else if (check == 0){
                            Toast.makeText(MainActivity.this, "Mật Khẩu Cũ Ko Đúng", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(MainActivity.this, "Cập Nhât Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Nhập Mật Khẩu Không Trùng Với Nhau", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}