package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ListView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
{


    ListView listView;
    ArrayList<User> susers = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = null;
        byte[] buffer = null;
        //для работы с внутренним json необходим  AssetManager
        AssetManager textik = getResources().getAssets();
        try
        {
            //получаем данные из json
            InputStream is = getAssets().open("users_contact.json");
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        Gson gson = new Gson();
        UserCard data = gson.fromJson(json, UserCard.class);

        UserListAdapter adapter;
        adapter = new UserListAdapter(this, data.users);

        listView.setAdapter(adapter);

    }

}

