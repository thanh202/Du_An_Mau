package thanhlvph23422.fpoly.mob2041_thanhlvph23422.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.R;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.adapter.PhieuMuonAdapter;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.PhieuMuonDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.SachDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.ThanhVienDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.PhieuMuon;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.Sach;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.ThanhVien;

public class QLPhieuMuonFragment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerQLPhieuMuon;
    ArrayList<PhieuMuon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qlphieumuon, container,false);

        recyclerQLPhieuMuon = view.findViewById(R.id.recyclerQLPhieuMuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
//chuyen vao loadData
        //giao dien: ok

        //data

        //adapter
        loadData();

        //ThanhVienDAO
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void loadData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerQLPhieuMuon.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(list,getContext());
        recyclerQLPhieuMuon.setAdapter(adapter);
    }
    //them
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_phieumuon,null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
//        EditText edtTien = view.findViewById(R.id.edtTien);                 //ko cần nhập giá trị nữa
        getDaTaThanhVien(spnThanhVien);
        getDaTaSach(spnSach);

        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //lay ma thanhvien
                HashMap<String, Object> hsTV =(HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                //lay ma sach
                HashMap<String,Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                int tien = (int) hsSach.get("giathue");


                themPhieuMuon(matv,masach,tien);




            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    //lay data hoten thanhvien
    private void getDaTaThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getSDThanhVien();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv",tv.getMatv());
            hs.put("hoten",tv.getHoten());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);

    }
    private void getDaTaSach(Spinner spnSach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getDSDauSach();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (Sach sc : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach",sc.getMasach());
            hs.put("tensach",sc.getTensach());
            hs.put("giathue",sc.getGiathue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);

    }
    private void themPhieuMuon(int matv, int masach, int tien){
        //lay ma thuthu
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");
        //lấy ngày hiện tại
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay,0, tien);
        boolean kiemtra = phieuMuonDAO.ThemPhieuMuon(phieuMuon);
        if (kiemtra){
            Toast.makeText(getContext(), "thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
