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
    UserListAdapter adapter;
    ListView listView;

    //кнопки для сортировки
    Button btn_name;
    Button btn_phone;
    Button btn_sex;

    //ArrayList<User> susers = new ArrayList<>();
   // ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //привязываем к разметке
        listView = findViewById(R.id.list);

        btn_name = findViewById(R.id.name);
        btn_phone = findViewById(R.id.phone_number);
        btn_sex = findViewById(R.id.sex);


        String json = null;
        byte[] buffer = null;
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

        adapter = new UserListAdapter(this, data.users);
        listView.setAdapter(adapter);

    }



    public void onClick(View v)
    {
        switch (v.getId())
        {
            //смотрим на что нажал пользователь и вызываем сортировку по этому критерию
            case R.id.name:
                adapter.sort_users("name");
                break;
            case R.id.phone_number:
                adapter.sort_users("phoneNumber");
                break;
            case R.id.sex:
                adapter.sort_users("sex");
                break;
        }
        //обновляем карточки
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }
}

