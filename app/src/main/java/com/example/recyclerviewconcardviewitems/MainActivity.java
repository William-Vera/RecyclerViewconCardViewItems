package com.example.recyclerviewconcardviewitems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.recyclerviewconcardviewitems.Adaptadores.AdaptadorProducto;
import com.example.recyclerviewconcardviewitems.Modelos.Producto;
import com.example.recyclerviewconcardviewitems.WebService.Asynchtask;
import com.example.recyclerviewconcardviewitems.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://dummyjson.com/products",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Producto> lstUsuarios = new ArrayList<Producto> ();
        try {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaProductos= JSONlista.getJSONArray("products");
            lstUsuarios = Producto.JsonObjectsBuild(JSONlistaProductos);
            int resId = R.anim.layout_animation_down_to_up;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                    resId);
            recyclerView.setLayoutAnimation(animation);
            AdaptadorProducto adaptadorProducto = new AdaptadorProducto(this, lstUsuarios);
            recyclerView.setAdapter(adaptadorProducto);
        }
        catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}