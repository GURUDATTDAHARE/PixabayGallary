package com.gurudattdahare.photos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
     private ArrayList<String> list=new ArrayList<>();
    String url ="https://pixabay.com/api/?key=18936531-6427c0baa69b1fd7701494011&q=yellow+flowers&image_type=photo";
     private RecyclerView recyclerView;
     private RecyclerView.LayoutManager layoutManager;
     private RecyclerView.Adapter adapter;

     private RequestQueue queue;

     private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        searchView=findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String s="https://pixabay.com/api/?key=18936531-6427c0baa69b1fd7701494011&q="+query+"&image_type=photo";
                list.clear();
                Json(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


   // String url1="https://jsonplaceholder.typicode.com/posts";
     queue=Volley.newRequestQueue(this);
     Json(url);

    }
    public void Json(String CurrentURL){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, CurrentURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array= response.getJSONArray("hits");
                    for(int i=0;i<array.length();i++)
                    {
                        String s=array.getJSONObject(i).getString("webformatURL");
                        list.add(s);
                        Log.d("guru1","responce "+array.getJSONObject(i));
                    }
                    adapter=new Adapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("guru","error");
            }
        });
        queue.add(jsonObjectRequest);
    }
}