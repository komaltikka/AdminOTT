package com.example.adminott.activities;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.adminott.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerviewAdapter  extends RecyclerView.Adapter<RecyclerviewAdapter.MyviewHolder> {

    private List<Product> products;
    private Context context;

    public RecyclerviewAdapter(Context context, List<Product> products){

        this.products = products;
        this.context=context;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclarview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        holder.tvOffer.setText(products.get(position).getpOffer());
        holder.tvTittle.setText(products.get(position).getpName());
        Picasso.with(context).load(products.get(position).getpImage()).into(holder.imgTitle);
    }

    @Override
    public int getItemCount() {
        if(products != null){
            return products.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView tvOffer,tvTittle;
        ImageView imgTitle;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvOffer = (TextView)itemView.findViewById(R.id.tvOffer);
            tvTittle = (TextView)itemView.findViewById(R.id.tvTitle);
            imgTitle = (ImageView)itemView.findViewById(R.id.imgTitle);
        }
    }

}
