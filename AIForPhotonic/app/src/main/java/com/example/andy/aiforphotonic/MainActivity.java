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
    CheckBox gate[]= new CheckBox[100000];
    Button inputVertexChip;
    Chips chips[] = new Chips[100000];
    int soDinh = 9;
    int soCanh = 12;
    boolean xet[];
    int goal, start;
    Queue<Integer> q;
    List<Integer> TracesRoad;
    Department departments [] = new Department[100000];
    Button startAlgorithms;
    EditText vertexStart,vertexEnd;
    public boolean BFS_algorithms(int start) {
        xet = new boolean[100000];
        q = new LinkedList<>();
        Arrays.fill(xet, false);
        int currentChip;
        q.add(start);
        xet[start] = true;
        chips[start].setTrace(start);
        while (q.isEmpty() == false) {
            currentChip = q.peek();
            q.poll();
            Log.e("xem","Dinh chon: "+currentChip);
            if (currentChip == goal) {
                return true;
            }
            for (int i = 0; i < chips[currentChip].getKe().size(); i++) {
                Log.e("xem","Dinh xet: "+chips[currentChip].getKe().get(i));
                if (xet[chips[currentChip].getKe().get(i)] == false&&chips[currentChip].xetActiveKe(chips[currentChip].getKe().get(i))) {
                    xet[chips[currentChip].getKe().get(i)] = true;
                    q.add(chips[currentChip].getKe().get(i));
                    chips[chips[currentChip].getKe().get(i)].setTrace(currentChip);
                }
            }
        }
        return false;
    }
    public void DisplayRoad() {
        int vertexCurrent = goal;
         TracesRoad = new ArrayList<>();
        TracesRoad.add(chips[vertexCurrent].getTrace());
        StringBuilder result = new StringBuilder();
        while (chips[vertexCurrent].getTrace() != start) {
            vertexCurrent = chips[vertexCurrent].getTrace();
            Log.e("xem", String.valueOf(vertexCurrent));
            TracesRoad.add(chips[vertexCurrent].getTrace());
        }
        TracesRoad.add(chips[vertexCurrent].getTrace());
        Collections.reverse(TracesRoad);
        Log.e("xem","\nThe road goes from " + start + " to " + goal + " is : ");
        for (int i = 1; i < TracesRoad.size(); i++) {

                result.append(TracesRoad.get(i) + "-->");

        }
        result.append(goal);
        Log.e("xem", result.toString());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            gate[5].setButtonTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
        }
        initDepartment(36);
        initChips(soDinh);
        Click();
    }

    private void initPositionChip(int u, int v,String position) {
        chips[u].getKe().add(v);
        chips[v].getKe().add(u);
        if(position.equalsIgnoreCase("Tren")){
            chips[u].setKeTren(chips[v].getChipName());
            chips[v].setKeDuoi(chips[u].getChipName());
        }
        else if (position.equalsIgnoreCase("Duoi")){
            chips[u].setKeDuoi(chips[v].getChipName());
            chips[v].setKeTren(chips[u].getChipName());
        }
        else if (position.equalsIgnoreCase("Phai")){
            chips[u].setKePhai(chips[v].getChipName());
            chips[v].setKeTrai(chips[u].getChipName());
        }
        else if (position.equalsIgnoreCase("Trai")){
            chips[u].setKeTrai(chips[v].getChipName());
            chips[v].setKePhai(chips[u].getChipName());
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
        chips[i]= new Chips(i);
    }
    //1
    chips[1].setCucTren(departments[1]);
    chips[1].setCucDuoi(departments[3]);
    chips[1].setCucTrai(departments[2]);
    chips[1].setCucPhai(departments[4]);
    //2
    chips[2].setCucTren(departments[5]);
    chips[2].setCucDuoi(departments[8]);
    chips[2].setCucTrai(departments[6]);
    chips[2].setCucPhai(departments[7]);
    //3
    chips[3].setCucTren(departments[9]);
    chips[3].setCucDuoi(departments[12]);
    chips[3].setCucTrai(departments[10]);
    chips[3].setCucPhai(departments[11]);
    //4
    chips[4].setCucTren(departments[13]);
    chips[4].setCucDuoi(departments[16]);
    chips[4].setCucTrai(departments[14]);
    chips[4].setCucPhai(departments[15]);
    //5
    chips[5].setCucTren(departments[17]);
    chips[5].setCucDuoi(departments[20]);
    chips[5].setCucTrai(departments[18]);
    chips[5].setCucPhai(departments[19]);
    //6
    chips[6].setCucTren(departments[21]);
    chips[6].setCucDuoi(departments[24]);
    chips[6].setCucTrai(departments[22]);
    chips[6].setCucPhai(departments[23]);
    //7
    chips[7].setCucTren(departments[25]);
    chips[7].setCucDuoi(departments[28]);
    chips[7].setCucTrai(departments[26]);
    chips[7].setCucPhai(departments[27]);
    //8
    chips[8].setCucTren(departments[29]);
    chips[8].setCucDuoi(departments[32]);
    chips[8].setCucTrai(departments[30]);
    chips[8].setCucPhai(departments[31]);
    //9
    chips[9].setCucTren(departments[33]);
    chips[9].setCucDuoi(departments[36]);
    chips[9].setCucTrai(departments[34]);
    chips[9].setCucPhai(departments[35]);
    //
        initPositionChip(1,2,"Phai");
        initPositionChip(2,3,"Phai");
        initPositionChip(1,4,"Duoi");
        initPositionChip(2,5,"Duoi");
        initPositionChip(3,6,"Duoi");
        initPositionChip(4,5,"Phai");
        initPositionChip(5,6,"Phai");
        initPositionChip(4,7,"Duoi");
        initPositionChip(5,8,"Duoi");
        initPositionChip(6,9,"Duoi");
        initPositionChip(7,8,"Phai");
        initPositionChip(8,9,"Phai");



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
        gate[1] = findViewById(R.id.cong1);
        gate[2] = findViewById(R.id.cong2);
        gate[3] = findViewById(R.id.cong3);
        gate[4] = findViewById(R.id.cong4);
        gate[5] = findViewById(R.id.cong5);
        gate[6] = findViewById(R.id.cong6);
        gate[7] = findViewById(R.id.cong7);
        gate[8] = findViewById(R.id.cong8);
        gate[9] = findViewById(R.id.cong9);
        gate[10] = findViewById(R.id.cong10);
        gate[11] = findViewById(R.id.cong11);
        gate[12] = findViewById(R.id.cong12);
        gate[13] = findViewById(R.id.cong13);
        gate[14] = findViewById(R.id.cong14);
        gate[15] = findViewById(R.id.cong15);
        gate[16] = findViewById(R.id.cong16);
        gate[17] = findViewById(R.id.cong17);
        gate[18] = findViewById(R.id.cong18);
        gate[19] = findViewById(R.id.cong19);
        gate[20] = findViewById(R.id.cong20);
        gate[21] = findViewById(R.id.cong21);
        gate[22] = findViewById(R.id.cong22);
        gate[23] = findViewById(R.id.cong23);
        gate[24] = findViewById(R.id.cong24);
        gate[25] = findViewById(R.id.cong25);
        gate[26] = findViewById(R.id.cong26);
        gate[27] = findViewById(R.id.cong27);
        gate[28] = findViewById(R.id.cong28);
        gate[29] = findViewById(R.id.cong29);
        gate[30] = findViewById(R.id.cong30);
        gate[31] = findViewById(R.id.cong31);
        gate[32] = findViewById(R.id.cong32);
        gate[33] = findViewById(R.id.cong33);
        gate[34] = findViewById(R.id.cong34);
        gate[35] = findViewById(R.id.cong35);
        gate[36] = findViewById(R.id.cong36);
        gate[37] = findViewById(R.id.cong37);
        gate[38] = findViewById(R.id.cong38);
        gate[39] = findViewById(R.id.cong39);
        gate[40] = findViewById(R.id.cong40);
        gate[41] = findViewById(R.id.cong41);
        gate[42] = findViewById(R.id.cong42);
        gate[43] = findViewById(R.id.cong43);
        gate[44] = findViewById(R.id.cong44);
        gate[45] = findViewById(R.id.cong45);
        gate[46] = findViewById(R.id.cong46);
        gate[47] = findViewById(R.id.cong47);
        gate[48] = findViewById(R.id.cong48);
        gate[49] = findViewById(R.id.cong49);
        gate[50] = findViewById(R.id.cong50);
        gate[51] = findViewById(R.id.cong51);
        gate[52] = findViewById(R.id.cong52);
        gate[53] = findViewById(R.id.cong53);
        gate[54] = findViewById(R.id.cong54);
        gate[55] = findViewById(R.id.cong55);
        gate[56] = findViewById(R.id.cong56);
        gate[57] = findViewById(R.id.cong57);
        gate[58] = findViewById(R.id.cong58);
        gate[59] = findViewById(R.id.cong59);
        gate[60] = findViewById(R.id.cong60);
        gate[61] = findViewById(R.id.cong61);
        gate[62] = findViewById(R.id.cong62);
        gate[63] = findViewById(R.id.cong63);
        gate[64] = findViewById(R.id.cong64);
        gate[65] = findViewById(R.id.cong65);
        gate[66] = findViewById(R.id.cong66);
        gate[67] = findViewById(R.id.cong67);
        gate[68] = findViewById(R.id.cong68);
        gate[69] = findViewById(R.id.cong69);
        gate[70] = findViewById(R.id.cong70);
        gate[71] = findViewById(R.id.cong71);
        gate[72] = findViewById(R.id.cong72);
        gate[73] = findViewById(R.id.cong73);
        gate[74] = findViewById(R.id.cong74);
        gate[75] = findViewById(R.id.cong75);
        gate[76] = findViewById(R.id.cong76);
        gate[77] = findViewById(R.id.cong77);
        gate[78] = findViewById(R.id.cong78);
        gate[79] = findViewById(R.id.cong79);
        gate[80] = findViewById(R.id.cong80);
        gate[81] = findViewById(R.id.cong81);
        gate[82] = findViewById(R.id.cong82);
        gate[83] = findViewById(R.id.cong83);
        gate[84] = findViewById(R.id.cong84);
        gate[85] = findViewById(R.id.cong85);
        gate[86] = findViewById(R.id.cong86);
        gate[87] = findViewById(R.id.cong87);
        gate[88] = findViewById(R.id.cong88);
        gate[89] = findViewById(R.id.cong89);
        gate[90] = findViewById(R.id.cong90);
        gate[91] = findViewById(R.id.cong91);
        gate[92] = findViewById(R.id.cong92);
        gate[93] = findViewById(R.id.cong93);
        gate[94] = findViewById(R.id.cong94);
        gate[95] = findViewById(R.id.cong95);
        gate[96] = findViewById(R.id.cong96);
        gate[97] = findViewById(R.id.cong97);
        gate[98] = findViewById(R.id.cong98);
        gate[99] = findViewById(R.id.cong99);
        gate[100] = findViewById(R.id.cong100);
        gate[101] = findViewById(R.id.cong101);
        gate[102] = findViewById(R.id.cong102);
        gate[103] = findViewById(R.id.cong103);
        gate[104] = findViewById(R.id.cong104);
        gate[105] = findViewById(R.id.cong105);
        gate[106] = findViewById(R.id.cong106);
        gate[107] = findViewById(R.id.cong107);
        gate[108] = findViewById(R.id.cong108);
        gate[109] = findViewById(R.id.cong109);
        gate[110] = findViewById(R.id.cong110);
        gate[111] = findViewById(R.id.cong111);
        gate[112] = findViewById(R.id.cong112);
        gate[113] = findViewById(R.id.cong113);
        gate[114] = findViewById(R.id.cong114);
        gate[115] = findViewById(R.id.cong115);
        gate[116] = findViewById(R.id.cong116);
        gate[117] = findViewById(R.id.cong117);
        gate[118] = findViewById(R.id.cong118);
        gate[119] = findViewById(R.id.cong119);
        gate[120] = findViewById(R.id.cong120);
        gate[121] = findViewById(R.id.cong121);
        gate[122] = findViewById(R.id.cong122);
        gate[123] = findViewById(R.id.cong123);
        gate[124] = findViewById(R.id.cong124);
        gate[125] = findViewById(R.id.cong125);
        gate[126] = findViewById(R.id.cong126);
        gate[127] = findViewById(R.id.cong127);
        gate[128] = findViewById(R.id.cong128);
        gate[129] = findViewById(R.id.cong129);
        gate[130] = findViewById(R.id.cong130);
        gate[131] = findViewById(R.id.cong131);
        gate[132] = findViewById(R.id.cong132);
        gate[133] = findViewById(R.id.cong133);
        gate[134] = findViewById(R.id.cong134);
        gate[135] = findViewById(R.id.cong135);
        gate[136] = findViewById(R.id.cong136);
        gate[137] = findViewById(R.id.cong137);
        gate[138] = findViewById(R.id.cong138);
        gate[139] = findViewById(R.id.cong139);
        gate[140] = findViewById(R.id.cong140);
        gate[141] = findViewById(R.id.cong141);
        gate[142] = findViewById(R.id.cong142);
        gate[143] = findViewById(R.id.cong143);
        gate[144] = findViewById(R.id.cong144);
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
                    chips[u].getKe().add(v);
                    chips[v].getKe().add(u);
                    if (trai.isChecked()) {
                        chips[u].setKeTrai(chips[v].getChipName());
                        chips[v].setKePhai(chips[u].getChipName());
                    } else if (phai.isChecked()) {
                        chips[u].setKePhai(chips[v].getChipName());
                        chips[v].setKeTrai(chips[u].getChipName());
                    } else if (tren.isChecked()) {
                        chips[u].setKeTren(chips[v].getChipName());
                        chips[v].setKeDuoi(chips[u].getChipName());
                    } else if (duoi.isChecked()) {
                        chips[u].setKeDuoi(chips[v].getChipName());
                        chips[v].setKeTren(chips[u].getChipName());
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
            start = Integer.parseInt(vertexStart.getText().toString());
            goal = Integer.parseInt(vertexEnd.getText().toString());
            CheckGateClick();
            if (BFS_algorithms(start)==true) DisplayRoad();
            else Log.e("xem","khong tim thay duong di");
        }
    }

    private void CheckGateClick() {
        int count=1;
        for (int i=1;i<=36;i++){
            for (int j=1;j<=4;j++){
                departments[i].getGateName()[j].setChecked(gate[count].isChecked());
                count++;
            }
        }
    }
}
