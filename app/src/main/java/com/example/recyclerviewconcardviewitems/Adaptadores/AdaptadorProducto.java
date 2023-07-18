package com.example.recyclerviewconcardviewitems.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewconcardviewitems.R;

import java.util.List;

import com.example.recyclerviewconcardviewitems.Modelos.Producto;
import com.example.recyclerviewconcardviewitems.ViewImages;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ProductoViewHolder>{

    private Context Ctx;
    private List<Producto> lstProductos;
    public AdaptadorProducto(Context mCtx, List<Producto> productos) {
        this.lstProductos = productos;
        Ctx=mCtx;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lyitemproducto, null);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = lstProductos.get(position);
        holder.textViewName.setText(producto.getNombres());
        holder.textViewPrecio.setText(producto.getPrecio());
        holder.textViewDescription.setText(producto.getDescripcion());
        holder.textViewURLAvatar.setText(producto.getWebsite());
        Glide.with(Ctx)
                .load(producto.getUrlavatar())
                .into(holder.imageView);

        holder.cardviewImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putStringArray("images", producto.getImages());
                b.putString("title", producto.getNombres());
                b.putString("description", producto.getDescripcion());
                Intent intent = new Intent(Ctx, ViewImages.class);
                intent.putExtras(b);
                Ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstProductos.size();
    }


    class ProductoViewHolder extends RecyclerView.ViewHolder {
        CardView cardviewImages;
        TextView textViewName, textViewURLAvatar, textViewDescription, textViewPrecio;
        ImageView imageView;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.txtTitulo);
            textViewPrecio = itemView.findViewById(R.id.txtPrecio);
            textViewURLAvatar = itemView.findViewById(R.id.txtAvatar);
            textViewDescription = itemView.findViewById(R.id.txtDescripcion);
            imageView = itemView.findViewById(R.id.imgAvatar);
            cardviewImages=itemView.findViewById(R.id.cardProducto);
        }
    }
}
