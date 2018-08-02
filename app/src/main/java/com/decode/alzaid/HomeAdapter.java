package com.decode.alzaid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;



public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private List<String> ItemList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        //Bitmap bmp;

        public MyViewHolder(View view) {
            super(view);
            button = (Button)view.findViewById(R.id.home_item_button);
        }
    }


    public HomeAdapter(List<String> ItemList, Context context) {
        this.ItemList = ItemList;
        mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //Movie movie = ItemList.get(position);
        if(position==0||position==1)
            holder.button.setBackgroundColor(mcontext.getResources().getColor(R.color.darkRed));
        else if(position==2||position==3)
            holder.button.setBackgroundColor(mcontext.getResources().getColor(R.color.lightRed));
        else if(position==4||position==5)
            holder.button.setBackgroundColor(mcontext.getResources().getColor(R.color.lighterRed));


        holder.button.setText(ItemList.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ItemList.get(position).equals("New Question")) {
                    Intent intent = new Intent(mcontext,QuestionActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
                else if(ItemList.get(position).equals("Home")){
                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+"latitude"+","+"longitude");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(mapIntent);

                }
                else if(ItemList.get(position).equals("Call Home")){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:0123456789"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
                else if(ItemList.get(position) == "Settings"){
                    Intent intent = new Intent(mcontext,SettingsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(mcontext,TrackerUpdate.class);
                    mcontext.startService(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }



}

