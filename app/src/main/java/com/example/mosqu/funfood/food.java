package com.example.mosqu.funfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class food extends AppCompatActivity {
    private ListView listview;
    private TextView mrt;
    private TextView tim;
    private ArrayAdapter<String> adapter;
    // private ArrayAdapter<String> item = new ArrayAdapter<String>();

    class Food {
        String name;
        float lat;
        float lon;
    }

    class Foods2 {
        String stat;
        String time;
        List<Food> foods;
    }

    String foodJson = "[" +
        "{" +
        "   stat : \"南港展覽館\", " + "" +
        "   time : \"早午餐\", " +
        "   foods : [" +
        "       {" +
        "           name : \"A\", " +
        "           lat : 25.05, " +
        "           lon : 121.61" +
        "       }, " +
        "       {" +
        "           name : \"B\", " +
        "           lat : 25.06, " +
        "           lon : 121.62 " +
        "       } " +
        "       ] " +
        "}]";

    class Foods {
        String stat;
        String time;
        List<String> foods;

        public Foods(String stat, String time, String... foods) {        //String... = String, String, String.......
            this.stat = stat;
            this.time = time;
            this.foods = new ArrayList<>();
            for(int i = 0; i < foods.length; i++) {
                this.foods.add(foods[i]);
            }
        }
    }

    Foods[] foods = {
            new Foods("南港展覽館", "早午餐", "小南港-懶人私廚"),
            new Foods("南港展覽館", "下午茶", "Ratio Coffee", "S one Cafe'"),
            new Foods("南港展覽館", "聚餐", "武侍酒日式居酒屋", "餡老滿", "樂雅樂"),
            new Foods("南港", "早午餐", "貳樓", "漾漾好時餐廳"),
            new Foods("南港", "下午茶", "杏桃鬆餅屋", "鼓豆咖啡", "Conch Baking 手作餅乾烘焙屋" ,"南港CHAFFEE"),
            new Foods("南港", "聚餐", "敘日全日餐廳", "大肆鍋物", "豆腐村", "高麗園韓式烤肉"),
            new Foods("昆陽", "早午餐", "Bocy Patisserie & Bistro", "Routine"),
            new Foods("昆陽", "下午茶", "Bocy Patisserie & Bistro", "Routine"),
            new Foods("昆陽", "聚餐", "二郭頭", "穀旦云涮涮鍋"),
            new Foods("後山埤", "早午餐", "N+n 喫早餐", "點子營養早餐吧", "藍色早餐吧"),
            new Foods("後山埤", "下午茶", "布蘭梅德國下午茶館", "B", "C"),
            new Foods("後山埤", "聚餐", "輕鬆私房菜", "竹村居酒屋"),
            new Foods("永春", "早午餐", "Mr.papa Waffle&Cafe比利時鬆餅", "Mr.Old", "城翔早午餐"),
            new Foods("永春", "下午茶", "聞山咖啡", "咖竅 Cotcha Coffee", "ERZMOMENT 笛瑟甜點工坊", "Colive Tea"),
            new Foods("永春", "聚餐", "古記雞.信義店.私房菜居酒屋", "小人大食", "小鮮肉涮涮屋"),
            new Foods("市政府", "早午餐", "HIVE巢", "We & Me Cafe"),
            new Foods("市政府", "下午茶", "LeTAO小樽洋?子舖", "佳佳甜品"),
            new Foods("市政府", "聚餐", "韓姜熙的小廚房", "WOOSAN", "韓斤麻浦", "靜岡勝政日式豬排", "丸龜製麵"),
            new Foods("國父紀展館", "早午餐", "At First Brunch 緣來早午餐", "Moi Cafe"),
            new Foods("國父紀展館", "下午茶", "微兜", "Street Churros", "Toast Chat"),
            new Foods("國父紀展館", "聚餐", "alamode Tabl", "Dancing Pig 豬跳舞小餐館", "潭洞韓式燒烤餐廳", "泰滾 Rolling Thai 泰式火鍋"),
            new Foods("忠孝敦化", "早午餐", "貓咪先生的朋友", "Pinknic野餐吧", "初衣食午 One Fifteen", "BIG TIME美式靈魂早午餐"),
            new Foods("忠孝敦化", "下午茶", "若水臨品鍋", "尚咖啡", "草莓工房"),
            new Foods("忠孝敦化", "聚餐", "啤調客 Beeru", "叁和院(參和院)台灣風格飲食", "胖東西廚房 CoolZi", "花酒藏 A-Plus"),
            new Foods("忠孝復興", "早午餐", "樂。野食", "紙箱外的貓腳印", "悄悄好食"),
            new Foods("忠孝復興", "下午茶", "紅豆食府", "PINEDE", "Tartine Bakery"),
            new Foods("忠孝復興", "聚餐", "菜豚屋", "川巴子熱炒", "WOODSTONE 木石披薩餐廳"),
            new Foods("忠孝新生", "早午餐", "光合箱子", "天和鮮物", "以馬內利鮮魚湯", "杭州小籠包"),
            new Foods("忠孝新生", "下午茶", "遠流別境 libLAB", "幾米品牌概念店", "建北黑砂糖剉冰", "貓下去 MEOWVELOUS CAFÉ"),
            new Foods("忠孝新生", "聚餐", "小姑食記", "橋下大叔", "陳記燒臘"),
            new Foods("善導寺", "早午餐", "烤司院碳烤吐司專賣", "阜杭豆漿", "丘比手作吐司早午餐", "艾搗蛋手工蛋餅"),
            new Foods("善導寺", "下午茶", "二條通綠島小夜曲", "Monteur 夢甜屋洋菓子店", "KONAYUKI 粉雪北海道 Style Cafe", "Bonnie Sugar"),
            new Foods("善導寺", "聚餐", "韓之棧", "青葉新樂園", "美麗島沖繩風味居酒屋"),
            new Foods("台北車站", "早午餐", "蛋要酷手工蛋餅專賣店", "Nola Kitchen 紐澳良小廚", "Tame Moose", "Coco Brother 椰 兄弟"),
            new Foods("台北車站", "下午茶", "小器食堂", "Dazzling Cafe 蜜糖吐司", "DIAMOND BEAR 鑽石熊烘焙", "天糖時尚燉奶甜品"),
            new Foods("台北車站", "聚餐", "明德素食園", "元泰食創意料理", "Nancy No.6 Apt 南西六號公寓"),
            new Foods("西門", "早午餐", "貳拾陸巷 Somebody Café", "花嘴廚房", "In% HAIR Salon & café", "金花碳烤吐司專賣"),
            new Foods("西門", "下午茶", "Guru House", "八拾捌茶輪番所", "譚仔三哥米線", "點水樓", "武昌街老天祿滷味"),
            new Foods("西門", "聚餐", "amba意舍吃吧chiba", "梅村日本料理", "嚐飽圖", "打狗霸"),
            new Foods("龍山寺", "早午餐", "NEST burger", "日初Moni Café", "煮飯研究所"),
            new Foods("龍山寺", "下午茶", "蘇家肉圓油粿", "豐原排骨酥", "布袋鮮之蚵", "三六仔圓店"),
            new Foods("龍山寺", "聚餐", "艋舺夜市 / 蔥抓餅", "艋舺夜市 / 山豬肉香腸", " 艋舺夜市 / 東港旗魚黑輪", "懷念愛玉冰", "北港甜湯"),
            new Foods("江子翠", "早午餐", "石頭肉圓", "厝邊", "無店名蛋餅", "老王豆漿店"),
            new Foods("江子翠", "下午茶", "艾白旅生 Alba Cafe", "Mr. Butter 奶油先生", "藍色微光"),
            new Foods("江子翠", "聚餐", "小喬新疆羊肉串", "彭園湘菜館", "9%酒趴旋轉串燒Bar", "鉄工場專賣燒肉"),
            new Foods("新埔", "早午餐", "OVEN COFFEE", "咖樂 Show the Color", "甜福Fuku Brunch", "PUCHI LOFT 小倉庫早午餐", "FOUND HOUSE 方屋餐廳"),
            new Foods("新埔", "下午茶", "MATTER CAFÉ", "Oyami Café", "翁林林Café", "182 Pancake", "巷左轉"),
            new Foods("新埔", "聚餐", "NU PASTA杯杯麵", "長鼻子泰式咖哩", "Box Diner", "鄰居家 Next Door", "豐華小館"),
            new Foods("板橋", "早午餐", "TOAST BOX", "Hivi Brunch", "好初早餐 Deli", "N.Y. BAGELS", "merci café"),
            new Foods("板橋", "下午茶", "亞尼克果子工房", "跳舞香水perfume dance", "典藏33", "Awhile 外兒小館", "糖村"),
            new Foods("板橋", "聚餐", "梁季港式小火鍋", "AIYA 藍屋日本料理", "聚．北海道昆布鍋", "Howfun 好飯食堂", "開飯川食堂"),
            new Foods("府中", "早午餐", "灣島 呷早頓", "第46號倉庫早午餐", "板橋三隻小豬", "Vicking's cafe 維京女王", "野豬核桃早午餐"),
            new Foods("府中", "下午茶", "Caffe bene", "Wapasta 義磚義瓦", "Melbourne Press Café", "Jouons Ensemble Pâtisserie", "微時光"),
            new Foods("府中", "聚餐", "原燒", "六必居潮州沙鍋粥", "隱居 いざかや居酒屋", "新野町燒肉居酒屋", "新葡苑餐廳"),
            new Foods("亞東醫院", "早午餐", "向陽晨間飲食館", "維京女王", "花吃", "多來家CAFÉ"),
            new Foods("亞東醫院", "下午茶", "NU PASTA杯杯麵", "米迦千層乳酪蛋糕專賣店", "歐客佬咖啡農場", "稻町森法式甜點舖"),
            new Foods("亞東醫院", "聚餐", "薩莉亞義式餐廳", "舒果", "哈瓦那", "有乾人麻辣火鍋"),
            new Foods("海山", "早午餐", "早安公雞", "Homa 咖啡", "怡客咖啡"),
            new Foods("海山", "下午茶", "醍味咖啡簡餐下午茶", "馬可先生麵包坊", "ANTLER PARK 鹿角公園"),
            new Foods("海山", "聚餐", "彼得公雞地中海餐廳", "漉", "阿城鵝肉"),
            new Foods("土城", "早午餐", "蕃茄村漢堡"),
            new Foods("土城", "下午茶", "找餐先生"),
            new Foods("土城", "聚餐", "饗饌", "御廚巨籠宴", "石頭日式炭火燒肉"),
            new Foods("永寧", "早午餐", "紫的寶", "金食堂 三隻熊"),
            new Foods("永寧", "下午茶", "AMO阿默典藏蛋糕"),
            new Foods("永寧", "聚餐", "永寧二號出口牛肉麵", "泰之雲泰式料理", "和春日式料理", "五八烏龍麵", "幸福咖哩"),
            new Foods("頂埔", "早午餐", "彎Corner", "福利早餐店", "拉亞漢堡", "喜多多早餐店", "咪兒咖啡早午餐"),
            new Foods("頂埔", "下午茶", "貓腳丫咖啡"),
            new Foods("頂埔", "聚餐", "頂埔伍麵線甜不辣", "海瑛越南牛肉河粉", "金鼎焢肉飯"),
    };

    Foods2 foods2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Bundle b = getIntent().getExtras();         //從intent取得bundle
        String str1 = b.getString("mrtstat");
        String str2 = b.getString("timelist");


        final List<Foods2> foods2List = new Gson().fromJson(foodJson, new TypeToken<List<Foods2>>(){}.getType());
        mrt = findViewById(R.id.textView3);
        tim = findViewById(R.id.textView4);
        listview = findViewById(R.id.listView);

        mrt.setText(str1);
        tim.setText(str2);

        for (int i = 0; i < foods.length; i++) {
            if ((foods[i].stat.equals(str1) && (foods[i].time.equals(str2)))) {
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foods[i].foods);
                listview.setAdapter(adapter);
            }
        }
        /*
        for(int i = 0; i < foods2List.size(); i++) {
            if(foods2List.get(i).stat.equals(str1)&&foods2List.get(i).time.equals(str2)) {
                foods2 = foods2List.get(i);
            }
        }
        */
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Food food = foods2.foods.get(position);
                Food food = foods2List.get(0).foods.get(0);
                Intent intent = new Intent(food.this, map.class);
                Bundle bundle = new Bundle();
                bundle.putString("json", new Gson().toJson(food));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
