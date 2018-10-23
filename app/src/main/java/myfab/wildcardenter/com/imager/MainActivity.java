package myfab.wildcardenter.com.imager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String URL_HEAD = "https://pixabay.com/api/?key=10329489-8d1091e22da67810f84c5cd92&q=";
    private static final String URL_TAIL = "&image_type=photo&pretty=true";
    private ShimmerRecyclerView recyclerView;
    private ItemRecyclerAdapter itemRecyclerAdapter;
    private ArrayList<ItemViewModel> mItemList;
    private RequestQueue requestQueue;
    String str, sorted_str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            str = bundle.getString("mylta");
        }
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setDemoChildCount(3);
        recyclerView.setDemoLayoutReference(R.layout.item_layout);
        mItemList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        char[] chr=str.toCharArray();
        for (int i=0;i<chr.length;i++){
            if (chr[i]==' '){
                chr[i]='+';
            }

        }
        sorted_str=String.valueOf(chr);
        parseJSON();
    }

    private void parseJSON() {
        String url=URL_HEAD+sorted_str+URL_TAIL;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("hits");
                    for (int i=0;i< jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        String profile_img_url=object.getString("userImageURL");
                        String user_name=object.getString("user");
                        String img_url=object.getString("largeImageURL");
                        int mlike=object.getInt("likes");
                        int mViews=object.getInt("views");
                        int mDownloads=object.getInt("downloads");
                        String large_img_url=object.getString("largeImageURL");

                        mItemList.add(new ItemViewModel(profile_img_url,user_name,img_url,mlike
                                ,mViews,mDownloads,large_img_url));
                    }
                    itemRecyclerAdapter=new ItemRecyclerAdapter(MainActivity.this,mItemList);
                    recyclerView.setAdapter(itemRecyclerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}