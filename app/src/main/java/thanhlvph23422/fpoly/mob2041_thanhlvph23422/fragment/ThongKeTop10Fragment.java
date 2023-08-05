package thanhlvph23422.fpoly.mob2041_thanhlvph23422.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.R;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.adapter.Top10Adapter;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.ThongKeDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.Sach;

public class ThongKeTop10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10,container,false);

        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);

        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        recyclerTop10.setAdapter(adapter);

        return view;
    }
}
