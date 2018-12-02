package com.example.saint.webservicedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class SinhVienAdapter extends BaseAdapter  {
    MainActivity context;
    int Layout;
    List <SinhVien> sinhViens;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhViens) {
        this.context = context;
        Layout = layout;
        this.sinhViens = sinhViens;
    }
    public class ViewHolder {
        TextView txtHoten, txtNamSinh, txtDiaChi,Id;
        ImageView btnEditSV,btnDelSV;

        public ViewHolder() {
        }

    }

    @Override
    public int getCount() {
        return sinhViens.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null ;
       if (viewHolder == null){
           viewHolder = new ViewHolder();
           LayoutInflater inflater = LayoutInflater.from(context);
           view = inflater.inflate(Layout,null);
           viewHolder.txtHoten = view.findViewById(R.id.Namesinhvien);
           viewHolder.txtDiaChi = view.findViewById(R.id.DiaChisinhvien);
           viewHolder.txtNamSinh = view.findViewById(R.id.NamSinhsinhvien);
           viewHolder.btnEditSV = view.findViewById(R.id.btneditSV);
           viewHolder.btnDelSV = view.findViewById(R.id.btndelSV);
           viewHolder.Id = view.findViewById(R.id.IDsinhvien);
           view.setTag(viewHolder);
       }
       else {
           viewHolder = (ViewHolder) view.getTag();
       }
       final SinhVien sinhVien = sinhViens.get(i);
       viewHolder.txtHoten.setText("Họ và tên : " + sinhVien.getName());
       viewHolder.txtNamSinh.setText("Năm sinh : " +sinhVien.getNamSinh());
       viewHolder.txtDiaChi.setText("Địa chỉ : " +sinhVien.getDiaChi());
       viewHolder.Id.setText("ID : "+sinhVien.getId());
       viewHolder.btnEditSV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context,UpdateSV.class);
               intent.putExtra("dataSinhVien",sinhVien);
               context.startActivity(intent);
           }
       });
       viewHolder.btnDelSV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               XacNhanXoa(sinhVien.getName(),sinhVien.getId());
           }


       });
        return view;
    }
    private void XacNhanXoa(String ten, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa sinh viên "+ten+ " không ? ");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               context.DeleteSV("http://192.168.5.104/webservice/delete.php",id);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
