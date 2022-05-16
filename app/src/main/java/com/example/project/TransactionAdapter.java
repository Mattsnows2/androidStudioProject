package com.example.project;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Models.Receipts;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private ArrayList<Transaction> transactions;
    private OnClickListener onClickListener;
    private DatabaseReference mDatabase;

    TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setOnClickListener(OnClickListener listener) { this.onClickListener = listener; }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){



        }else{
            Log.i("y2","pas connécté");
        }
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.capital_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.amount.setText(transactions.get(position).getTransactions());
      /*  viewHolder.amount.setText(String.valueOf(transactions.get(position).getAmount()));
        viewHolder.currency.setText(transactions.get(position).getCurrency());
        viewHolder.date.setText((CharSequence) transactions.get(position).getDate());
        viewHolder.icon.setImageResource(transactions.get(position).getIconId());*/
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        final String[] capital3 = new String[1];
        Date date = new Date();
      /*  Query receipts =mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts");
        receipts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> receipts = new ArrayList<String>();
                for (DataSnapshot postSnaphot: snapshot.getChildren()){

                    receipts.add(postSnaphot.getValue().toString());


                    for(int i =0; i<receipts.size(); i++){
                      //  viewHolder.amount.setText(receipts.get(i));


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



    }

    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        TextView amount;
        TextView currency;
        TextView date;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            //label = itemView.findViewById(R.id.tv_label);
          amount = itemView.findViewById(R.id.tv_amount);
          //  currency = itemView.findViewById(R.id.tv_currency);
            //date = itemView.findViewById(R.id.tv_date);
            //icon = itemView.findViewById(R.id.iv_icon);

            itemView.setOnClickListener(v -> onClickListener.onClick(transactions.get(getBindingAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Transaction transaction);
    }
}