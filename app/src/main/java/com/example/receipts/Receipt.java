package com.example.receipts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Receipt {
    private final String name;
    private final String time;
    private final String complexity;
    private int image;
    private final String type;
    private String ingridients;
    private String steps;



    Receipt(String name, String time,String complexity, int image,String type, String steps, String ingridients ){
        this.name = name;
        this.time = time;
        this.complexity = complexity; //R.string.receipt_easy;
        this.image = image;
        this.type = type; //R.string.receipt_type;
        this.steps = steps;
        this.ingridients = ingridients;

    }

    public String getTime(){
        return this.time;
    }
    public String getComplexity(){
        return this.complexity;
    }
    public int getImage(){
        return this.image;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }

    public String getSteps(){
        return this.steps;
    }

    public String getIngridients(){
        return this.ingridients;
    }


    public void setImage(int image){
        this.image = image;
    }

}
