package com.example.recyclerviewconcardviewitems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.example.recyclerviewconcardviewitems.Adaptadores.AdaptadorProducto;
import com.example.recyclerviewconcardviewitems.Adaptadores.ImageAdapter;

import java.util.Arrays;
import java.util.List;

public class ViewImages extends AppCompatActivity {
    RecyclerView rImages;
    TextView txtTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);

        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                resId);

        txtTitulo=findViewById(R.id.textView);
        rImages=findViewById(R.id.rImages);
        rImages.setLayoutManager(new GridLayoutManager(this,2));

        Bundle bl=this.getIntent().getExtras();
        String[] images=getIntent().getStringArrayExtra("images");

        rImages.setLayoutAnimation(animation);
        txtTitulo.setText(bl.getString("title"));
        ImageAdapter imgAdapter=new ImageAdapter(this, images);
        rImages.setAdapter(imgAdapter);
        /*for (int i=0; i<images.length;i++)
        {
            Log.i("image", images[i]);
        }*/
    }
}