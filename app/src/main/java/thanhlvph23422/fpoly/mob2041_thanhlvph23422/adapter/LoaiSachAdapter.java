package thanhlvph23422.fpoly.mob2041_thanhlvph23422.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhlvph23422.fpoly.mob2041_thanhlvph23422.R;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.dao.LoaiSachDAO;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.ItemClick;
import thanhlvph23422.fpoly.mob2041_thanhlvph23422.model.LoaiSach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    private ItemClick itemClick;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick =itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText("Tên Loại: "+list.get(position).getTenloai());
        holder.txtMaLoai.setText("Mã Loại: "+String.valueOf(list.get(position).getId()));

        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        //load data
                        list.clear();
                        list = loaiSachDAO.getDSLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "ko thể xóa loại sách này vì đã có sách thuộc thể loại này", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa Loại Sách Ko thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSach loaiSach = list.get(holder.getAdapterPosition());
                itemClick.onClickLoaiSach(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai, txtTenLoai;
        ImageView ivdelete, ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaloai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivdelete = itemView.findViewById(R.id.ivdel);
            ivEdit = itemView.findViewById(R.id.ivedit);
        }
    }
}
