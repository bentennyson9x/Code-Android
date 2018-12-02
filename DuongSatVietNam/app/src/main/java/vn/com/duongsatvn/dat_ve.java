package vn.com.duongsatvn;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class dat_ve extends Activity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
     EditText ho_ten,Sdt;
     Spinner spinnerTPv;
     Spinner spinnerTP;
    RadioButton motChieu, khuHoi;
    Button Xacnhan;
    EditText DialogNgaydi, DialogNgay;
    public void InitPrefences(String Key, String Value){
    sharedPreferences = getSharedPreferences(Key,MODE_PRIVATE);
    sharedPreferences.getString(Key,Value);
    editor = sharedPreferences.edit();

    }
    private void AnhXA(){
        spinnerTPv = (Spinner) findViewById(R.id.spinnerTP1);
        spinnerTP = (Spinner) findViewById(R.id.spinnerTP);
        ho_ten = (EditText) findViewById(R.id.Hovatendatve);
        Sdt = (EditText) findViewById(R.id.sdtdatve);
        motChieu = (RadioButton) findViewById(R.id.datve1chieu);
        khuHoi = (RadioButton) findViewById(R.id.datvekhuhoi);
        Xacnhan= (Button) findViewById(R.id.xácnhan);
        DialogNgay= (EditText) findViewById(R.id.dialogNgay);
        DialogNgaydi= (EditText) findViewById(R.id.dialogngaydi);
    }
    private void ChonNgay (final EditText editText){
        final Calendar calendar =Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                calendar.set(year,month,dayOfMonth);
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
       AnhXA();
        final ArrayList<String> ThanhPho= new ArrayList<String>();
        ThanhPho.add("");
        ThanhPho.add("Hà Nội");
        ThanhPho.add("TP.Hồ Chí Minh");
        ThanhPho.add("Nam Định");
        ThanhPho.add("Nghệ An");
        ThanhPho.add("Nha Trang");
        ThanhPho.add("Đà Lạt");
        InitPrefences("hovaten","");
        InitPrefences("Sdt","Mặc định");
        ho_ten.setText(sharedPreferences.getString("hovaten",""));
        Sdt.setText(sharedPreferences.getString("Sdt",""));
        Xacnhan.setOnClickListener(this);
        ArrayAdapter adapter= new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,ThanhPho);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0) Toast.makeText(dat_ve.this,"Đã chọn thành công!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTPv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0) Toast.makeText(dat_ve.this,"Đã chọn thành công!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTP.setAdapter(adapter);
        spinnerTPv.setAdapter(adapter);
        DialogNgay.setOnClickListener(this);
        DialogNgaydi.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view==Xacnhan){
           editor.putString("hovaten", ho_ten.getText().toString());
           editor.putString("Sdt",Sdt.getText().toString());
            editor.commit();
            Toast.makeText(this, "Lưu thành công",Toast.LENGTH_SHORT).show();
        }
        else if (view==DialogNgaydi){
            ChonNgay(DialogNgaydi);
        }
        else if (view==DialogNgay){
            ChonNgay(DialogNgay);
        }
        else if (view==ho_ten){
        }

    }
}
