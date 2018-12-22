package com.example.mosqu.funfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class mrttime extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrttime);

        final String[] stat = new String[23];
        final String[] time = new String[3];

        for (int i=0;i<stat.length;i++){
            stat[i]=new String();
        }

        for (int i=0;i<time.length;i++){
            time[i]=new String();
        }

        final Spinner spinner = findViewById(R.id.spinner);
        final Spinner spinner2 = findViewById(R.id.spinner2);

        stat[0]="南港展覽館";
        stat[1]="南港";
        stat[2]="昆陽";
        stat[3]="後山埤";
        stat[4]="永春";
        stat[5]="市政府";
        stat[6]="國父紀展館";
        stat[7]="忠孝敦化";
        stat[8]="忠孝復興";
        stat[9]="忠孝新生";
        stat[10]="善導寺";
        stat[11]="台北車站";
        stat[12]="西門";
        stat[13]="龍山寺";
        stat[14]="江子翠";
        stat[15]="新埔";
        stat[16]="板橋";
        stat[17]="府中";
        stat[18]="亞東醫院";
        stat[19]="海山";
        stat[20]="土城";
        stat[21]="永寧";
        stat[22]="頂埔";

        time[0]="早午餐";
        time[1]="下午茶";
        time[2]="聚餐";

        myAdapter tranAdapter1 = new myAdapter(stat,R.layout.activity_list);
        spinner.setAdapter(tranAdapter1);

        myAdapter tranAdapter2 = new myAdapter(time,R.layout.activity_list);
        spinner2.setAdapter(tranAdapter2);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statstring = stat[spinner.getSelectedItemPosition()];
                String timestring = time[spinner2.getSelectedItemPosition()];
                Intent i = new Intent(mrttime.this, food.class);    //設定Intent從mrttime切換到food
                Bundle b = new Bundle();
                b.putString("mrtstat", statstring);
                b.putString("timelist", timestring);
                i.putExtras(b);
                startActivity(i);       //啟動intent
            }
        });
    }



    private class myAdapter extends BaseAdapter {
        private  String[] items;
        private int view;

        public myAdapter(String[] items, int view) {
            this.items = items; //將車站初始化為傳入的陣列
            this.view = view;   //將layout的id設為傳入的id
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //取得第position個物件的View
            convertView = getLayoutInflater().inflate(view, parent, false);  //設定該物件的布局，例如如果view中最外層的width設定是match_parent，view的width就會是parent的width等
            TextView name1 = convertView.findViewById(R.id.textView); //取得view中的textView
            name1.setText(items[position]);//將textView的文字設定成在stations中對應位置的東西
            return convertView;  //將設定好的東西回傳出去
        }
    }
}
