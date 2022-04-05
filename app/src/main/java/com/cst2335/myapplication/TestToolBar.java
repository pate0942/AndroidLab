package com.cst2335.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TestToolBar extends AppCompatActivity
{


    private Object ActionBarDrawerToggle;
    public static boolean finishTestToolbar = false;
    private Object Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tool_bar);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        MenuItem choice1 = findViewById(R.id.itemChoice1);
        MenuItem choice2 = findViewById(R.id.itemChoice2);
        MenuItem choice3 = findViewById(R.id.itemChoice3);
        MenuItem choice4 = findViewById(R.id.itemChoice4);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_menu);
        setSupportActionBar(myToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Menu menu = navigationView.getMenu();
        Menu submenu = menu.addSubMenu("NavigationView");
        submenu.add("Chat Page ");
        submenu.add("Weather Forecast ");
        submenu.add("Go back to login ");

        navigationView.invalidate();

        navigationView.setNavigationItemSelectedListener((item)->
        {
            if(item == submenu.getItem(0))
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

            else if(item == submenu.getItem(1))
            {
                Toast.makeText(getApplicationContext(),"Sorry this page is not available right now",Toast.LENGTH_LONG).show();
            }

            else if(item == submenu.getItem(2))
            {
                finishTestToolbar = true;
                Intent intent2 = new Intent(this, MainActivity.class);
                finishActivity(500);
                startActivityForResult(intent2,500);
            }


            return false;
        });


    }

    private void setSupportActionBar() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.itemChoice1:
            {
                Toast.makeText(getApplicationContext(),"You clicked on item 1",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.itemChoice2:
            {
                Toast.makeText(getApplicationContext(),"You clicked on item 2",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.itemChoice3:
            {
                Toast.makeText(getApplicationContext(),"You clicked on item 3",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.itemChoice4:
            {
                Toast.makeText(getApplicationContext(),"You clicked on item 4",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.toolbar:
            {
                Toast.makeText(getApplicationContext(),"You clicked on the overflow menu",Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


}