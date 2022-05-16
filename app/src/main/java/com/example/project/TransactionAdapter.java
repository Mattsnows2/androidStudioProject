package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private ArrayList<Transaction> transactions;
    private OnClickListener onClickListener;

    TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setOnClickListener(OnClickListener listener) { this.onClickListener = listener; }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.capital_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.label.setText(transactions.get(position).getLabel());
        viewHolder.amount.setText(String.valueOf(transactions.get(position).getAmount()));
        viewHolder.currency.setText(transactions.get(position).getCurrency());
        //viewHolder.name.setText(transaction.get(position).getDate());
        viewHolder.icon.setImageResource(transactions.get(position).getIconId());
    }

    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        TextView amount;
        TextView currency;
        //TextView date;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tv_label);
            amount = itemView.findViewById(R.id.tv_amount);
            currency = itemView.findViewById(R.id.tv_currency);
            //date = itemView.findViewById(R.id.tv_date);
            icon = itemView.findViewById(R.id.iv_icon);

            itemView.setOnClickListener(v -> onClickListener.onClick(transactions.get(getBindingAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Transaction transaction);
    }
}