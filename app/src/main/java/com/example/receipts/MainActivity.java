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
        String stepsForBorsht = getString(R.string.steps_for_borsht);
        String ingidientsForBorsht = getString(R.string.ingridients_for_bosht);

        String stepsForOlady = getString(R.string.steps_for_olady);
        String ingidientsForOlady = getString(R.string.ingridients_for_olady);

        String stepsForSirnik = getString(R.string.steps_for_sirnik);
        String ingidientsForSirnik = getString(R.string.ingridients_for_sirnik);

        String stepsForDeflope = getString(R.string.steps_for_deflope);
        String ingidientsForDeflope = getString(R.string.ingidients_for_deflope);

        String nameBorsht = getString(R.string.borcht_name);
        String timeBorsht = getString(R.string.borcht_time);

        String nameOlady = getString(R.string.olady_name);
        String nameSirniks= getString(R.string.sirniks);
        String nameDeflope = getString(R.string.deflope);

        String complexityEasy = getString(R.string.difficult_easy);
        String complexityMiddle = getString(R.string.difficult_middle);
        String complexityHard = getString(R.string.difficult_hard);
        String complexityExtraHard = getString(R.string.difficult_extra_hard);
        String[] types= {getString(R.string.type_breakfast), getString(R.string.type_delicates), getString(R.string.type_soup)};
        String[] time = {getString(R.string.time_day), getString(R.string.time_half)};

        receipts.add(new Receipt(nameBorsht, timeBorsht, complexityEasy, R.drawable.borcht, types[2], stepsForBorsht, ingidientsForBorsht ));
        receipts.add(new Receipt(nameOlady, time[1], complexityMiddle, R.drawable.olady, types[0], stepsForOlady, ingidientsForOlady  ));
        receipts.add(new Receipt(nameSirniks, time[1], complexityHard, R.drawable.sirniks, types[0] , stepsForSirnik, ingidientsForSirnik ));
        receipts.add(new Receipt(nameDeflope, time[0], complexityExtraHard, R.drawable.palaba, types[1], stepsForDeflope, ingidientsForDeflope  ));


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