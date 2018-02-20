package com.example.kinhangpoon.simpsonscharacter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kinhangpoon.simpsonscharacter.model.Characters;
import com.example.kinhangpoon.simpsonscharacter.ui.details.DetailFragment;
import com.example.kinhangpoon.simpsonscharacter.ui.GridFragment;
import com.example.kinhangpoon.simpsonscharacter.ui.TitleFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainView,MyInterface{
//    IPresenter iPresenter;
    ProgressDialog pd;
    ArrayList<Characters> charactersList;
    MyInterface myInterface;

    public String url ="http://api.duckduckgo.com/?q=simpsons+characters&format=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        iPresenter = new Presenter(this);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();

        charactersList = new ArrayList<>();
        StringRequest myStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject characterObject = new JSONObject(response);
                    JSONArray jsonArray = characterObject.getJSONArray("RelatedTopics");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        String[] text = data.getString("Text").trim().split("-");
                        String myTitle = text[0];
                        Log.i("myTitle",myTitle);
                        String myDescription = text[1];
                        Log.i("myDescription",myDescription);
                        JSONObject icon = data.getJSONObject("Icon");
                        String myImage = icon.getString("URL").equals("")?"https://image.freepik.com/free-icon/picture-frame-with-mountain-image_318-40293.jpg":icon.getString("URL");
                        Log.i("myImage",myImage);

                        charactersList.add(new Characters(myTitle,myDescription,myImage));
                    }
                    Characters.charactersList = charactersList;
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main,new TitleFragment()).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(myStringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.list){
            getSupportFragmentManager().beginTransaction().replace(R.id.main,new TitleFragment()).commit();
        }
        if(id == R.id.grid){
            getSupportFragmentManager().beginTransaction().replace(R.id.main,new GridFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void myMethod(int position, ArrayList<Characters> charactersList) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putParcelableArrayList("charactersList",charactersList);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        if(checkIsTablet()){
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main2,detailFragment).commit();
        }
        else{
            setTitle(charactersList.get(position).getTitle());
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main,detailFragment).commit();
        }

    }
    private boolean checkIsTablet() {
        boolean isTablet = false;
        Display display = MainActivity.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        float widthInches = metrics.widthPixels / metrics.xdpi;
        float heightInches = metrics.heightPixels / metrics.ydpi;
        double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
        if (diagonalInches >= 7.0) {
            isTablet = true;
        }

        return isTablet;
    }


    /*@Override
    public void detailFragment(boolean isTablet,DetailFragment detailFragment) {
        if(isTablet){
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main2,detailFragment).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main,detailFragment).commit();
        }
    }

    @Override
    public void setAppTitle(String s) {
        setTitle(s);
    }

    @Override
    public Display showDisplay() {
        return MainActivity.this.getWindowManager().getDefaultDisplay();
    }*/
}
