package com.example.andy.aiforphotonic;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button inputVertexChip;
    int soDinh = 9;
    int soCanh = 12;
    boolean xet[];
    int goal, start;
    Queue<Integer> q;
    List<Integer> TracesRoad;
    Department departments [] = new Department[100000];
    Button startAlgorithms;
    EditText vertexStart,vertexEnd;
    GridView gridView;
    ArrayList<Chips> chips;
    CustomAdapter adapter;
    public boolean BFS_algorithms(int start) {
        long startTime = System.nanoTime();
        xet = new boolean[100000];
        q = new LinkedList<>();
        Arrays.fill(xet, false);
        int currentChip;
        q.add(start);
        xet[start] = true;
        chips.get(start).setTrace(start);
        while (q.isEmpty() == false) {
            currentChip = q.peek();
            q.poll();
           // Log.e("xem","Dinh chon: "+currentChip);
            if (currentChip == goal) {
                long endTime = System.nanoTime();
                long t = endTime - startTime;
                Log.e("xem","Tong time : "+t+" nanotime");
                return true;
            }
            for (int i = 0; i < chips.get(currentChip).getKe().size(); i++) {
                if (xet[chips.get(currentChip).getKe().get(i)] == false&&chips.get(currentChip).xetActiveKe(chips.get(currentChip).getKe().get(i))) {
                 //   Log.e("xem","Dinh xet: "+chips.get(currentChip).getKe().get(i));
                    xet[chips.get(currentChip).getKe().get(i)] = true;
                    q.add(chips.get(currentChip).getKe().get(i));
                    chips.get(chips.get(currentChip).getKe().get(i)).setTrace(currentChip);
                }
            }
        }

        return false;
    }
    public void DisplayRoad() {
        int vertexCurrent = goal;
         TracesRoad = new ArrayList<>();
        TracesRoad.add(chips.get(vertexCurrent).getTrace());
        StringBuilder result = new StringBuilder();
        while (chips.get(vertexCurrent).getTrace() != start) {
            vertexCurrent = chips.get(vertexCurrent).getTrace();
       //     Log.e("xem", String.valueOf(vertexCurrent));
            TracesRoad.add(chips.get(vertexCurrent).getTrace());
        }
        TracesRoad.add(chips.get(vertexCurrent).getTrace());
        Collections.reverse(TracesRoad);
        Log.e("xem","\nThe road goes from " + (start+1) + " to " + (goal+1) + " is : ");
        for (int i = 1; i < TracesRoad.size(); i++) {

                result.append((TracesRoad.get(i)+1) + "-->");

        }
        result.append(goal+1);
        Log.e("xem", result.toString());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            gate[5].setButtonTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
//        }
        gridView = findViewById(R.id.mainActivityGridView);
        chips = new ArrayList<>();
        initDepartment(36);
        initChips(soDinh);
        adapter = new CustomAdapter(this,R.layout.item_chips,chips);
        gridView.setAdapter(adapter);
        Click();
    }

    private void initPositionChip(int u, int v,String position) {
        chips.get(u).getKe().add(v);
        chips.get(v).getKe().add(u);
        if(position.equalsIgnoreCase("Tren")){
            chips.get(u).setKeTren(chips.get(v).getChipName()-1);
            chips.get(v).setKeDuoi(chips.get(u).getChipName()-1);
        }
        else if (position.equalsIgnoreCase("Duoi")){
            chips.get(u).setKeDuoi(chips.get(v).getChipName()-1);
            chips.get(v).setKeTren(chips.get(u).getChipName()-1);
        }
        else if (position.equalsIgnoreCase("Phai")){
            chips.get(u).setKePhai(chips.get(v).getChipName()-1);
            chips.get(v).setKeTrai(chips.get(u).getChipName()-1);
        }
        else if (position.equalsIgnoreCase("Trai")){
            chips.get(u).setKeTrai(chips.get(v).getChipName()-1);
            chips.get(v).setKePhai(chips.get(u).getChipName()-1);
        }

    }

    private void initDepartment(int n) {
        for (int i=1;i<=n;i++){
            departments[i]= new Department(i);
        }
        addRelationshipDepartment_Gate(departments[1],1,4);
        addRelationshipDepartment_Gate(departments[2],5,8);
        addRelationshipDepartment_Gate(departments[3],9,12);
        addRelationshipDepartment_Gate(departments[4],13,16);
        addRelationshipDepartment_Gate(departments[5],17,20);
        addRelationshipDepartment_Gate(departments[6],21,24);
        addRelationshipDepartment_Gate(departments[7],25,28);
        addRelationshipDepartment_Gate(departments[8],29,32);
        addRelationshipDepartment_Gate(departments[9],33,36);
        addRelationshipDepartment_Gate(departments[10],37,40);
        addRelationshipDepartment_Gate(departments[11],41,44);
        addRelationshipDepartment_Gate(departments[12],45,48);
        addRelationshipDepartment_Gate(departments[13],49,52);
        addRelationshipDepartment_Gate(departments[14],53,56);
        addRelationshipDepartment_Gate(departments[15],57,60);
        addRelationshipDepartment_Gate(departments[16],61,64);
        addRelationshipDepartment_Gate(departments[17],65,68);
        addRelationshipDepartment_Gate(departments[18],69,72);
        addRelationshipDepartment_Gate(departments[19],73,76);
        addRelationshipDepartment_Gate(departments[20],77,80);
        addRelationshipDepartment_Gate(departments[21],81,84);
        addRelationshipDepartment_Gate(departments[22],85,88);
        addRelationshipDepartment_Gate(departments[23],89,92);
        addRelationshipDepartment_Gate(departments[24],93,96);
        addRelationshipDepartment_Gate(departments[25],97,100);
        addRelationshipDepartment_Gate(departments[26],101,104);
        addRelationshipDepartment_Gate(departments[27],105,108);
        addRelationshipDepartment_Gate(departments[28],109,112);
        addRelationshipDepartment_Gate(departments[29],113,116);
        addRelationshipDepartment_Gate(departments[30],117,120);
        addRelationshipDepartment_Gate(departments[31],121,124);
        addRelationshipDepartment_Gate(departments[32],125,128);
        addRelationshipDepartment_Gate(departments[33],129,132);
        addRelationshipDepartment_Gate(departments[34],133,136);
        addRelationshipDepartment_Gate(departments[35],137,140);
        addRelationshipDepartment_Gate(departments[36],141,144);

    }

    private void initChips(int n) {
    for (int i=1;i<=n;i++){
        chips.add(new Chips(i));
    }
    //1
    chips.get(0).setCucTren(departments[1]);
    chips.get(0).setCucDuoi(departments[3]);
    chips.get(0).setCucTrai(departments[2]);
    chips.get(0).setCucPhai(departments[4]);
    //2
    chips.get(1).setCucTren(departments[5]);
    chips.get(1).setCucDuoi(departments[8]);
    chips.get(1).setCucTrai(departments[6]);
    chips.get(1).setCucPhai(departments[7]);
    //3
    chips.get(2).setCucTren(departments[9]);
    chips.get(2).setCucDuoi(departments[12]);
    chips.get(2).setCucTrai(departments[10]);
    chips.get(2).setCucPhai(departments[11]);
    //4
    chips.get(3).setCucTren(departments[13]);
    chips.get(3).setCucDuoi(departments[16]);
    chips.get(3).setCucTrai(departments[14]);
    chips.get(3).setCucPhai(departments[15]);
    //5
    chips.get(4).setCucTren(departments[17]);
    chips.get(4).setCucDuoi(departments[20]);
    chips.get(4).setCucTrai(departments[18]);
    chips.get(4).setCucPhai(departments[19]);
    //6
    chips.get(5).setCucTren(departments[21]);
    chips.get(5).setCucDuoi(departments[24]);
    chips.get(5).setCucTrai(departments[22]);
    chips.get(5).setCucPhai(departments[23]);
    //7
    chips.get(6).setCucTren(departments[25]);
    chips.get(6).setCucDuoi(departments[28]);
    chips.get(6).setCucTrai(departments[26]);
    chips.get(6).setCucPhai(departments[27]);
    //8
    chips.get(7).setCucTren(departments[29]);
    chips.get(7).setCucDuoi(departments[32]);
    chips.get(7).setCucTrai(departments[30]);
    chips.get(7).setCucPhai(departments[31]);
    //9
    chips.get(8).setCucTren(departments[33]);
    chips.get(8).setCucDuoi(departments[36]);
    chips.get(8).setCucTrai(departments[34]);
    chips.get(8).setCucPhai(departments[35]);

        initPositionChip(0,1,"Phai");
        initPositionChip(1,2,"Phai");
        initPositionChip(0,3,"Duoi");
        initPositionChip(1,4,"Duoi");
        initPositionChip(2,5,"Duoi");
        initPositionChip(3,4,"Phai");
        initPositionChip(4,5,"Phai");
        initPositionChip(3,6,"Duoi");
        initPositionChip(4,7,"Duoi");
        initPositionChip(5,8,"Duoi");
        initPositionChip(6,7,"Phai");
        initPositionChip(7,8,"Phai");
    }

    private void addRelationshipDepartment_Gate(Department ref, int start, int end) {
        Gate []gateTemp = new Gate[5];
        for (int i = 1;i<=4;i++){
           for (int j=start;j<=end;j++){
               gateTemp[i]= new Gate(j);
               if (j>=1&&j<=16){
                   gateTemp[i].setNameOfChipInclude(1);
               }
               else if (j>=17&&j<=32){
                   gateTemp[i].setNameOfChipInclude(2);
               }
               else if (j>=33&&j<=48){
                   gateTemp[i].setNameOfChipInclude(3);
               }
               else if (j>=49&&j<=64){
                   gateTemp[i].setNameOfChipInclude(4);
               }
               else if (j>=65&&j<=80){
                   gateTemp[i].setNameOfChipInclude(5);
               }
               else if (j>=81&&j<=96){
                   gateTemp[i].setNameOfChipInclude(6);
               }
               else if (j>=97&&j<=112){
                   gateTemp[i].setNameOfChipInclude(7);
               }
               else if (j>=113&&j<=128){
                   gateTemp[i].setNameOfChipInclude(8);
               }
               else if (j>=129&&j<=144){
                   gateTemp[i].setNameOfChipInclude(9);
               }
               i++;
           }
        }
        ref.setGateName(gateTemp);
    }

    private void Click() {
        inputVertexChip.setOnClickListener(this);
        startAlgorithms.setOnClickListener(this);


    }

    private void addView() {
        startAlgorithms = findViewById(R.id.mainActivityBFS);
        vertexStart = findViewById(R.id.ActivityMainInputBatdau);
        vertexEnd = findViewById(R.id.ActivityMainInputKetthuc);
        inputVertexChip = findViewById(R.id.mainActivityInput);
    }

    @Override
    public void onClick(View v) {
        if (v == inputVertexChip) {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setTitle("Hộp thoại nhập vị trí chip");
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_init_bfs);
            final EditText inputchipsU =dialog.findViewById(R.id.inputChipU);
            final EditText inputchipsV =dialog.findViewById(R.id.inputChipV);
            Button btnThem = dialog.findViewById(R.id.ThemCanhChip);
            Button btnXacNhan = dialog.findViewById(R.id.XacNhanInit);
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View k) {
                    RadioButton trai = dialog.findViewById(R.id.dialogInitRadioTrai);
                    RadioButton phai = dialog.findViewById(R.id.dialogInitRadioPhai);
                    RadioButton tren = dialog.findViewById(R.id.dialogInitRadioTren);
                    RadioButton duoi = dialog.findViewById(R.id.dialogInitRadioDuoi);

                    int u = Integer.parseInt(inputchipsU.getText().toString());
                    int v = Integer.parseInt(inputchipsV.getText().toString());
                    chips.get(u).getKe().add(v);
                    chips.get(v).getKe().add(u);
                    if (trai.isChecked()) {
                        chips.get(u).setKeTrai(chips.get(v).getChipName());
                        chips.get(v).setKePhai(chips.get(u).getChipName());
                    } else if (phai.isChecked()) {
                        chips.get(u).setKePhai(chips.get(v).getChipName());
                        chips.get(v).setKeTrai(chips.get(u).getChipName());
                    } else if (tren.isChecked()) {
                        chips.get(u).setKeTren(chips.get(v).getChipName());
                        chips.get(v).setKeDuoi(chips.get(u).getChipName());
                    } else if (duoi.isChecked()) {
                        chips.get(u).setKeDuoi(chips.get(v).getChipName());
                        chips.get(v).setKeTren(chips.get(u).getChipName());
                    }
                    TextView soCanhLienKet = dialog.findViewById(R.id.dialogInitSoCanhLienKet);
                    soCanhLienKet.setText(String.valueOf(Integer.parseInt(soCanhLienKet.getText().toString()) + 1));
                    Toast.makeText(MainActivity.this, "Thêm thành công ! ", Toast.LENGTH_SHORT).show();
                }
            });
            btnXacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
            dialog.show();
        }
        if (v == startAlgorithms){
            start = Integer.parseInt(vertexStart.getText().toString())-1;
            goal = Integer.parseInt(vertexEnd.getText().toString())-1;
            if (BFS_algorithms(start)==true) DisplayRoad();
            else Log.e("xem","khong tim thay duong di");
        }
    }

 
}
