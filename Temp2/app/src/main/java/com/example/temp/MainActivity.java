package com.example.temp;

import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.sqrt;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Chips> chips;
    Button inputVertexChip;
    int soDinh = 9;
    boolean xet[];
    int goal, start;
    Queue<Integer> q;
    List<Gate> TracesRoad;
    Button startAlgorithms;
    EditText vertexStart,vertexEnd;
    int indexStartInChip, indexGoalInChip;
    GridView gridView;
    CustomAdapter adapter;
    boolean []Approval;
    int GateCount=1;
    int chipChuaStart,chipChuaGoal;
    PriorityQueue<Gate> gatePriorityQueue = new PriorityQueue<Gate>(new Comparator<Gate>() {
        @Override
        public int compare(Gate o1, Gate o2) {
            if(o1.fx>o2.fx) return 1;
            else if (o1.fx<o2.fx) return -1;
            return 0;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        chips = new ArrayList<>();
        initChip(9);
        adapter = new CustomAdapter(this,R.layout.item_chips,chips);
        gridView.setAdapter(adapter);
        click();
      //  Log.e("xem",chips.get(1).getGates()[1].getNameGate()+" "+chips.get(1).getGates()[1].getCheckBox());
    }


    private boolean AStarAlgorithms() {
        Init();
        Approval= new boolean[10000];
        chips.get(chipChuaStart).getGates()[indexStartInChip].setDistanceOfGateToRoot(0);
        chips.get(chipChuaStart).getGates()[indexStartInChip].setFx(0);
        gatePriorityQueue.add(chips.get(chipChuaStart).getGates()[indexStartInChip]);
//        chips.get(chipChuaStart).getGates()[indexStartInChip].getCheckBox().setChecked(true);
//        Log.e("Xem", String.valueOf(chips.get(chipChuaStart).getGates()[indexStartInChip].getNameGate()));
//        adapter.notifyDataSetChanged();
        while (gatePriorityQueue.isEmpty()!=true){
//            for (Gate temp : gatePriorityQueue){
//                Log.e("xemmm",temp.getNameGate()+"  "+temp.getFx());
//            }
//            Log.e("xemmm", String.valueOf(gatePriorityQueue.peek().getNameGate()));
//            Log.e("xemmm","KT");
            Gate currentGate = gatePriorityQueue.peek();
            gatePriorityQueue.poll();
            if (currentGate.getNameGate()== goal){
                return true;
            }
            Approval[currentGate.getNameGate()]=true;
            for (int i=0;i<currentGate.getGateKe().size();i++){
                if (Approval[currentGate.getGateKe().get(i).getNameGate()]== false){
                    currentGate.getGateKe().get(i).setDistanceOfGateToRoot(currentGate.distanceOfGateToRoot + TinhDoDai2Diem(currentGate.ToaDoX,currentGate.ToaDoY,currentGate.getGateKe().get(i).ToaDoX,currentGate.getGateKe().get(i).ToaDoY));
                    double fx = currentGate.getGateKe().get(i).distanceOfGateToRoot
                            +TinhDoDai2Diem(currentGate.getGateKe().get(i).ToaDoX,currentGate.getGateKe().
                            get(i).ToaDoY,chips.get(chipChuaGoal).getGates()[indexGoalInChip].ToaDoX,chips.get(chipChuaGoal).getGates()[indexGoalInChip].ToaDoY);
                    currentGate.getGateKe().get(i).setFx(fx);
                    gatePriorityQueue.add(currentGate.getGateKe().get(i));
                    Approval[currentGate.getGateKe().get(i).getNameGate()]=true;
                    currentGate.getGateKe().get(i).setTrace(currentGate);
                }
            }
        }
        return false;
    }

    private void DisplayRoad() {
       int chipg= getNameChipContentGate(goal);
       Gate currentGate= chips.get(chipg).getGates()[indexGoalInChip];
        TracesRoad = new ArrayList<>();
        TracesRoad.add(currentGate);
        TracesRoad.add(currentGate.getTrace());
        StringBuilder result = new StringBuilder();
        while (currentGate.getTrace().getNameGate()!=start) {
            currentGate = currentGate.getTrace();
            TracesRoad.add(currentGate.getTrace());
        }
        Collections.reverse(TracesRoad);
        Log.e("xem","\nThe road goes from " + (start) + " to " + (goal) + " is : ");
        for (int i = 0; i < TracesRoad.size()-1; i++) {

            result.append((TracesRoad.get(i).getNameGate()) + "-->");

        }
        result.append((TracesRoad.get(TracesRoad.size()-1).getNameGate()));
        Log.e("xem", result.toString());
    }

    private void Init() {
        chipChuaStart = getNameChipContentGate(start);
        chipChuaGoal = getNameChipContentGate(goal);
        for (int i=0;i<16;i++){
            if(chips.get(chipChuaStart).getGates()[i].getNameGate()==start) indexStartInChip = i;
            if (chips.get(chipChuaGoal).getGates()[i].getNameGate()==goal) indexGoalInChip=i;
        }
        for (int i=0;i<16;i++){
            if(chips.get(chipChuaStart).getGates()[i].getNameGate()==start) indexStartInChip = i;
            if (chips.get(chipChuaGoal).getGates()[i].getNameGate()==goal) indexGoalInChip=i;
        }
        for (int j=0;j<chips.get(chipChuaStart).getGates()[indexStartInChip].getGateKe().size();j++){
          if ( chips.get(chipChuaStart).getGates()[j].nameGate == start ) continue;
          chips.get(chipChuaStart).getGates()[j].distanceOfGateToRoot = TinhDoDai2Diem(chips.get(chipChuaStart).getGates()[j].ToaDoX,chips.get(chipChuaStart).getGates()[j].ToaDoY,
                  chips.get(chipChuaStart).getGates()[indexStartInChip].ToaDoX,chips.get(chipChuaStart).getGates()[indexStartInChip].ToaDoY);
          //Log.e("xem",chips.get(chipChua).getGates()[j].getNameGate()+"  "+ String.valueOf(chips.get(chipChua).getGates()[j].distanceOfGateToRoot));
        }
    }

    private int getNameChipContentGate(int u) {
        u=u-1;
        int chipu=0;
        if (u>0&&u<17){
            chipu=0;
        }
        else if (u>=17&&u<33){
            chipu=1;
        }
        else if (u>=33&&u<49){
            chipu=2;
        }
        else if (u>=49&&u<65){
            chipu=3;
        }
        else if (u>=65&&u<81){
            chipu=4;
        }
        else if (u>=81&&u<97){
            chipu=5;
        }
        else if (u>=97&&u<113){
            chipu=6;
        }
        else if (u>=113&&u<129){
            chipu=7;
        }
        else if (u>=129&&u<145){
            chipu=8;
        }
        return  chipu;
    }


    private void click() {
        inputVertexChip.setOnClickListener(this);
        startAlgorithms.setOnClickListener(this);
    }

    private void initChip(int n) {

        for (int i=1;i<=n;i++){
            chips.add(new Chips(i));
            Gate [] tempGate= new Gate[16];
            chips.get(i-1).setGates(tempGate);
            for (int j=0;j<16;j++){
                chips.get(i-1).getGates()[j]= new Gate(GateCount) ;
           //     Log.e("xem",chips.get(i-1).getChipName()+"   "+ String.valueOf(chips.get(i-1).getGates()[j].getNameGate()));
                GateCount++;
                if(GateCount==soDinh*16+1) break;

            }
        }
        noiKetNoiCacCong(soDinh);
        noiKetNoiCacCongNgoaiChip(9,21);
        noiKetNoiCacCongNgoaiChip(25,37);
        noiKetNoiCacCongNgoaiChip(13,49);
        noiKetNoiCacCongNgoaiChip(29,65);
        noiKetNoiCacCongNgoaiChip(45,81);
        noiKetNoiCacCongNgoaiChip(57,69 );
        noiKetNoiCacCongNgoaiChip(73,85 );
        noiKetNoiCacCongNgoaiChip(61,97 );
        noiKetNoiCacCongNgoaiChip(77,113 );
        noiKetNoiCacCongNgoaiChip(93,129 );
        noiKetNoiCacCongNgoaiChip(105,117 );
        noiKetNoiCacCongNgoaiChip(121,133 );
        initTrongSo();
    }
    private double TinhDoDai2Diem(int x1, int y1, int x2, int y2){
        double result = sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return result;
    }

    private void initTrongSo() {
        int x=0,y=12;
        for (int i=0;i<soDinh;i++){
            if (i==0||i==1||i==2) {
                x+=4;
                if (i==0) x=0;
            }
            else if (i==3||i==4||i==5){
                x+=4;
                if (i==3) {
                    x=0;
                    y-=6;
                }
            }
            else if (i==6||i==7||i==8){
                x+=4;
                if (i==6) {
                    x=0;
                    y-=6;
                }
            }

            for (int j=0;j<16;j++){
                if(j==0){
                    chips.get(i).getGates()[j].ToaDoX=1+x;
                    chips.get(i).getGates()[j].ToaDoY=6+y;
                }
                else if (j==1){
                    chips.get(i).getGates()[j].ToaDoX=2+x;
                    chips.get(i).getGates()[j].ToaDoY=6+y;
                }
                else if (j==2){
                    chips.get(i).getGates()[j].ToaDoX=3+x;
                    chips.get(i).getGates()[j].ToaDoY=6+y;
                }
                else if (j==3){
                    chips.get(i).getGates()[j].ToaDoX=4+x;
                    chips.get(i).getGates()[j].ToaDoY=6+y;
                }
                else if (j==4){
                    chips.get(i).getGates()[j].ToaDoX=1+x;
                    chips.get(i).getGates()[j].ToaDoY=5+y;
                }
                else if (j==5){
                    chips.get(i).getGates()[j].ToaDoX=1+x;
                    chips.get(i).getGates()[j].ToaDoY=4+y;
                }
                else if (j==6){
                    chips.get(i).getGates()[j].ToaDoX=1+x;
                    chips.get(i).getGates()[j].ToaDoY=3+y;
                }
                else if (j==7){
                    chips.get(i).getGates()[j].ToaDoX=1+x;
                    chips.get(i).getGates()[j].ToaDoY=2+y;
                }
                else if (j==8){
                    chips.get(i).getGates()[j].ToaDoX=4+x;
                    chips.get(i).getGates()[j].ToaDoY=5+y;
                }
                else if (j==9){
                    chips.get(i).getGates()[j].ToaDoX=4+x;
                    chips.get(i).getGates()[j].ToaDoY=4+y;
                }
                else if (j==10){
                    chips.get(i).getGates()[j].ToaDoX=4+x;
                    chips.get(i).getGates()[j].ToaDoY=3+y;
                }
                else if (j==11){
                    chips.get(i).getGates()[j].ToaDoX=4+x;
                    chips.get(i).getGates()[j].ToaDoY=2+y;
                }
                else if (j==12){
                    chips.get(i).getGates()[j].ToaDoX=1+x;
                    chips.get(i).getGates()[j].ToaDoY=1+y;
                }
                else if (j==13){
                    chips.get(i).getGates()[j].ToaDoX=2+x;
                    chips.get(i).getGates()[j].ToaDoY=1+y;
                }
                else if (j==14){
                    chips.get(i).getGates()[j].ToaDoX=3+x;
                    chips.get(i).getGates()[j].ToaDoY=1+y;
                }
                else if (j==15){
                    chips.get(i).getGates()[j].ToaDoX=4+x;
                    chips.get(i).getGates()[j].ToaDoY=1+y;
                }
            }
        }
//    for (int i=0;i<9;i++){
//        for (int j=0;j<16;j++){
//            Log.e("xem", chips.get(i).getGates()[j].nameGate+"    "+chips.get(i).getGates()[j].getToaDoX()+"   "+chips.get(i).getGates()[j].getToaDoY());
//        }
//    }

    }
    private void HamDanhGiaFx(){
    }


    private void noiKetNoiCacCongNgoaiChip(int u, int v){
        int chipu=0; int chipv=0;
        int indexGateU=0,indexGateV=0;
        if (u>0&&u<17){
            chipu=0;
        }
        else if (u>=17&&u<33){
            chipu=1;
        }
        else if (u>=33&&u<49){
            chipu=2;
        }
        else if (u>=49&&u<65){
            chipu=3;
        }
        else if (u>=65&&u<81){
            chipu=4;
        }
        else if (u>=81&&u<97){
            chipu=5;
        }
        else if (u>=97&&u<113){
            chipu=6;
        }
        else if (u>=113&&u<129){
            chipu=7;
        }
        else if (u>=129&&u<145){
            chipu=8;
        }
        for (int i=0;i<16;i++){
            if (chips.get(chipu).getGates()[i].nameGate==u) indexGateU=i;
        }
        if (v>0&&v<17){
            chipv=0;
        }
        else if (v>=17&&v<33){
            chipv=1;
        }
        else if (v>=33&&v<49){
            chipv=2;
        }
        else if (v>=49&&v<65){
            chipv=3;
        }
        else if (v>=65&&v<81){
            chipv=4;
        }
        else if (v>=81&&v<97){
            chipv=5;
        }
        else if (v>=97&&v<113){
            chipv=6;
        }
        else if (v>=113&&v<129){
            chipv=7;
        }
        else if (v>=129&&v<145){
            chipv=8;
        }
        for (int i=0;i<16;i++){
            if (chips.get(chipv).getGates()[i].nameGate==v) indexGateV=i;
        }
        int saveV=v;
        int saveIndexV=indexGateV;
        for (int j=0;j<4;j++){
            v=saveV;
            indexGateV=saveIndexV;
            for (int i=0;i<4;i++){
                chips.get(chipu).getGates()[indexGateU].getGateKe().add(chips.get(chipv).getGates()[indexGateV]);
                chips.get(chipv).getGates()[indexGateV].getGateKe().add(chips.get(chipu).getGates()[indexGateU]);
            //    Log.e("xem",chips.get(chipu).getGates()[indexGateU].nameGate + "  "+ chips.get(chipv).getGates()[indexGateV].nameGate);
                v++;
                indexGateV++;
            }
            u++;
            indexGateU++;
        }
    }

    private void noiKetNoiCacCong(int n) {
        int chipName=0;
       for (chipName=0;chipName<9;chipName++){
           int gateMin=0;
           int gateCount=4;
           int gateSave=4;
           int temp=12;
           for (int k=0;k<12;k++){
               gateCount=gateSave;
               for (int i=0;i<temp;i++){
                   chips.get(chipName).getGates()[gateMin].getGateKe().add(chips.get(chipName).getGates()[gateCount]);
                   chips.get(chipName).getGates()[gateCount].getGateKe().add(chips.get(chipName).getGates()[gateMin]);
                  // Log.e("xem", chips.get(chipName).getGates()[gateMin].nameGate+"  "+chips.get(chipName).getGates()[gateCount].nameGate);
                   gateCount++;
               }
               if (k==3||k==7) gateSave+=4;
               gateMin++;
               if (gateMin==4||gateMin==8) temp-=4;
           }
       }
    }

    private void addView() {
        startAlgorithms = findViewById(R.id.mainActivityBFS);
        vertexStart = findViewById(R.id.ActivityMainInputBatdau);
        vertexEnd = findViewById(R.id.ActivityMainInputKetthuc);
        inputVertexChip = findViewById(R.id.mainActivityInput);
        gridView = findViewById(R.id.mainActivityGridView);

    }

    @Override
    public void onClick(View v) {
        if (v == inputVertexChip) {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setTitle("Hộp thoại nhập vị trí chip");
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_init_bfs);
            final EditText inputGateU =dialog.findViewById(R.id.inputChipU);
            final EditText inputGateV =dialog.findViewById(R.id.inputChipV);
            Button btnThem = dialog.findViewById(R.id.ThemCanhChip);
            Button btnXacNhan = dialog.findViewById(R.id.XacNhanInit);
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View k) {
                    noiKetNoiCacCongNgoaiChip(Integer.parseInt(inputGateU.getText().toString()),Integer.parseInt(inputGateV.getText().toString()));
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
       else if (v==startAlgorithms){
//            chips.get(0).getGates()[0].getCheckBox().setChecked(true);
//            adapter.notifyDataSetChanged();
            start = Integer.parseInt(vertexStart.getText().toString());
            goal = Integer.parseInt(vertexEnd.getText().toString());
            if ( AStarAlgorithms()==true)  DisplayRoad();
            else Log.e("xem","khong tim thay duong di");
          //  AStarAlgorithms();
        }
    }


}
