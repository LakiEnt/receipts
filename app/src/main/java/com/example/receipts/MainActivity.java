package com.example.receipts;

import static androidx.core.content.ContextCompat.startActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    private Button lang_choose, filter_type, filter_name;
    private boolean timerRunning = false;
    ArrayList<Receipt> receipts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        filter_type = findViewById(R.id.filter_type);
        filter_name = findViewById(R.id.filter_name);
        lang_choose = findViewById(R.id.lang_choose);

        lang_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intent);
            }
        });

        // TODO: перевести на разные языки

        String stepsForBorsht = "1. Нарежьте говядину на куски среднего размера. Положите мясо в большую кастрюлю и залейте холодной водой так, чтобы мясо было полностью покрыто. Доведите до кипения, затем уменьшите огонь и варите на медленном огне до мягкости мяса (обычно около 1-1,5 часов). Удаляйте пену, образующуюся на поверхности кастрюли. \n" +
                "\n"+
                "2. В то время как мясо варится, подготовьте овощи. Очистите и нарежьте свёклу, картофель, лук и морковь. Также нарежьте капусту и мелко нарежьте чеснок.\n"+
                "3. Когда мясо почти готово, добавьте нарезанные свёклу и картофель в кастрюлю. Варите до тех пор, пока овощи не станут мягкими.\n"+
                "4. В отдельной сковороде обжарьте нарезанный лук и морковь до мягкости и золотистого цвета. Добавьте томатную пасту и обжаривайте ещё несколько минут.\n"+
                "5. Добавьте обжаренные лук и морковь в кастрюлю с бульоном и овощами. Добавьте также нарезанную капусту и чеснок.\n"+
                "6. Продолжайте варить борщ на среднем огне ещё около 10-15 минут, чтобы все вкусы соединились.\n"+
                "7. После этого добавьте соль, перец и уксус по вкусу. Регулируйте количество уксуса в зависимости от предпочтений.\n"+
                "8. Подавайте борщ горячим, украсив каждую порцию сметаной и свежей зеленью по желанию. Подаётся борщ обычно с черным хлебом или пампушками.\n";
        String ingidientsForBorsht = "• 500 г говядины \n" +
                "• 2-3 крупных свёклы\n"+
                "• 2-3 средних картофелины\n"+
                "• 1 большая луковица\n"+
                "• 1-2 моркови\n"+
                "• 1/4 капусты\n"+
                "• 2-3 зубчика чеснока\n"+
                "• 2-3 столовые ложки томатной пасты\n"+
                "• 2-3 лавровых листа\n"+
                "• Соль и перец по вкусуа\n"+
                "• Уксус по вкусу\n"+
                "• Сметана и зелень для подачи";

        receipts.add(new Receipt("Борщт", "5 часов", "средне", R.drawable.borcht, "Суп", stepsForBorsht, ingidientsForBorsht ));
        receipts.add(new Receipt("Оладушки от бабушки", " 30 минут", "просто", R.drawable.olady, "Завтрак", stepsForBorsht, ingidientsForBorsht  ));
        receipts.add(new Receipt("Сырники", "30 минут", " средне", R.drawable.sirniks, "Завтрак" , stepsForBorsht, ingidientsForBorsht ));
        receipts.add(new Receipt("Дефлопе из палабы с семечками кациуса", "24 часа", " очень сложно", R.drawable.palaba, "Деликатес", stepsForBorsht, ingidientsForBorsht  ));


        ListView receiptList = findViewById(R.id.productList);
        com.example.receipts.ReceiptAdapter adapter = new com.example.receipts.ReceiptAdapter(this, R.layout.receipt_card, receipts);
        receiptList.setAdapter(adapter);


        filter_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList("name");
            }
        });

        filter_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList("type");
            }
        });
    }


    private void filterList(String type) {
        if(type == "name") {
            Comparator<Receipt> nameComparator = Comparator.comparing(Receipt::getName);
            Collections.sort(receipts, nameComparator);
            ListView receiptList = findViewById(R.id.productList);
            com.example.receipts.ReceiptAdapter adapter = new com.example.receipts.ReceiptAdapter(this, R.layout.receipt_card, receipts);
            receiptList.setAdapter(adapter);

        }
        if(type == "type") {
            Comparator<Receipt> nameComparator = Comparator.comparing(Receipt::getType);
            Collections.sort(receipts, nameComparator);
            ListView receiptList = findViewById(R.id.productList);
            com.example.receipts.ReceiptAdapter adapter = new com.example.receipts.ReceiptAdapter(this, R.layout.receipt_card, receipts);
            receiptList.setAdapter(adapter);

        }
    }
}