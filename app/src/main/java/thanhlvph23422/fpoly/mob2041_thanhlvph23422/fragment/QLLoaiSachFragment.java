package thanhlvph23422.fpoly.mob2041_thanhlvph23422.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.R;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.adapter.LoaiSachAdapter;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.LoaiSachDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.ItemClick;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.LoaiSach;

public class QLLoaiSachFragment extends Fragment {
    RecyclerView recyclerLoaiSach ;
    LoaiSachDAO dao;
    EditText edtLoaiSach;
    int maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach,container,false);

        recyclerLoaiSach = view.findViewById(R.id.recyclerLoaiSach);
        edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);

        dao = new LoaiSachDAO(getContext());
        loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();

                if (dao.themLoaiSach(tenloai)){
                    //thong + load danh sach
                    loadData();
                    edtLoaiSach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maloai, tenloai);
                if (dao.thayDoiLoaiSach(loaiSach)){
                    loadData();
                    edtLoaiSach.setText("");

                }else {
                    Toast.makeText(getContext(), "Thay đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);

        ArrayList<LoaiSach> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(LoaiSach loaiSach) {
                edtLoaiSach.setText(loaiSach.getTenloai());
                maloai = loaiSach.getId();
            }
        });
        recyclerLoaiSach.setAdapter(adapter);

    }
}
