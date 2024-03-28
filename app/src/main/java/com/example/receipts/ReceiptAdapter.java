package com.example.receipts;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

class ReceiptAdapter extends ArrayAdapter<Receipt> {
    private final LayoutInflater inflater;
    private final int layout;
    private final ArrayList<Receipt> receiptList;
    public final Context context;

        ReceiptAdapter(Context context, int resource, ArrayList<Receipt> receipts) {
            super(context, resource, receipts);

            this.receiptList = receipts;
            this.layout = resource;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
        }


    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Receipt receipt = receiptList.get(position);

        String text = context.getString(R.string.receipt_card_name) + ": " + receipt.getName();
        viewHolder.nameView.setText(text);

        text = context.getString(R.string.receipt_card_time) + ": " + receipt.getTime();
        viewHolder.timeView.setText(text);

        text = context.getString(R.string.receipt_card_difficult) + ": " + receipt.getComplexity();
        viewHolder.difficultView.setText(text);

        text = context.getString(R.string.receipt_card_type) + ": " + receipt.getType();
        viewHolder.typeView.setText(text);

        viewHolder.imageView.setImageResource(receipt.getImage());

        viewHolder.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReceiptActivity.class);

                intent.putExtra("name", receipt.getName());
                intent.putExtra("ingridients", receipt.getIngridients());
                intent.putExtra("steps", receipt.getSteps());
                intent.putExtra("image", Integer.toString(receipt.getImage()));

                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        final TextView nameView, timeView, difficultView, typeView ;
        ImageView imageView;
        Button buttonView;

        ViewHolder(View view){
            nameView = view.findViewById(R.id.receipt_name);
            timeView = view.findViewById(R.id.receipt_time);
            difficultView = view.findViewById(R.id.receipt_difficult);
            typeView = view.findViewById(R.id.receipt_type);
            imageView =view.findViewById(R.id.receipt_image);
            buttonView = view.findViewById(R.id.button_to_receipt);
        }
    }
}
