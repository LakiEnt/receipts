package com.example.receipts;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    private Button button_en, button_es, button_ru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_page);

        button_en  = findViewById(R.id.btn_lang_en);
        button_es = findViewById(R.id.btn_lang_es);
        button_ru = findViewById(R.id.btn_lang_ru);

        button_ru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("ru");
            }
        });

        button_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
            }
        });

        button_es.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("es");
            }
        });

    }



    private void setLanguage(String languageCode){
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
