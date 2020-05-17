package com.example.customadapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class UserListAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<User> users;
    // TODO: реализовать сортировку по каждому из полей
    // класса: sex, name, phoneNumber

    public UserListAdapter(Context ctx, ArrayList<User> users)
    {
        this.ctx = ctx;
        this.users = users;
    }

    @Override
    //получает коллекцию
    public int getCount()
    {
        return users.size();
    }
    //получает элемент
    @Override
    public Object getItem(int position)
    {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // получаем данные из коллекции
        Date begin = new Date();
        User u = users.get(position);

        // создаём разметку (контейнер)
        convertView = LayoutInflater.from(ctx).
                inflate(R.layout.useritem, parent, false);
        // получаем ссылки на элементы интерфейса
        ImageView ivUserpic = convertView.findViewById(R.id.userpic);
        ivUserpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
            }
        });

        TextView tvName = convertView.findViewById(R.id.name);
        TextView tvPhone = convertView.findViewById(R.id.phone);

        // задаём содержание
        tvName.setText(u.name);
        tvPhone.setText(u.phoneNumber);
        switch (u.sex)
        {
            case MAN: ivUserpic.setImageResource(R.drawable.user_man); break;
            case WOMAN: ivUserpic.setImageResource(R.drawable.user_woman); break;
            case UNKNOWN: ivUserpic.setImageResource(R.drawable.user_unknown); break;
        }
        Date finish = new Date();
        Log.d("mytag", "time: "+(finish.getTime()-begin.getTime()));
        return convertView;
    }


    public void sort_users(String str)
    {
        switch (str) {
            //сортировка коллекции по имени

            case "name":
                Collections.sort(users, new Comparator<User>() {
                    public int compare(User o1, User o2) {
                        return o1.name.compareTo(o2.name);
                    }
                });

             //сортировка коллекции по телефону
            case "phoneNumber":
                Collections.sort(users, new Comparator<User>() {
                    public int compare(User o1, User o2) {
                        return o1.phoneNumber.compareTo(o2.phoneNumber);
                    }
                });
                break;

            //сортировка коллекции по полу
            case "sex":
                Collections.sort(users, new Comparator<User>() {
                    public int compare(User o1, User o2) {
                        return o1.sex.compareTo(o2.sex);
                    }
                });
                break;


        }
    }
}
