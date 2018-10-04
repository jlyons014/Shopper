package com.example.joe.shopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class AddItem extends AppCompatActivity {

    Intent intent;

    Bundle bundle;

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();

        //get the id in the bundle
        id = bundle.getLong("_id");

            }


    /**
     * This method sets the Action Bar of the Main Activity to whatever
     * is defined in the menu main menu resource
     *
     * @param menu Menu object
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set the action bar of the main activity to whatever is defined
        //in the menu main menu resource
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //get the id of t he item selected
        switch (item.getItemId()) {
            case R.id.action_home:
                intent = new Intent(this, MainActivity.class);


                startActivity(intent);
                return true;


            case R.id.action_create_list:
                //Initialize an intent for the create list activity, start intent,
                //return true if the id in the item selected is for the create list
                //activity
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;

            case R.id.action_add_item :
                intent = new Intent(this, AddItem.class);
                intent.putExtra("_id", id);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void addItem(Menu menuItem) {

    }

}
