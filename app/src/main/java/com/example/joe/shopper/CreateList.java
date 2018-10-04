package com.example.joe.shopper;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateList extends AppCompatActivity {


    Intent intent;

    //declare EditTexts
    EditText nameEditText;
    EditText storeEditText;
    EditText dateEditText;

    //declare calendar
    Calendar calendar;

    //declare dbhandler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize EditTexts
        nameEditText = findViewById(R.id.nameEditText);
        storeEditText = findViewById(R.id.storeEditText);
        dateEditText = findViewById(R.id.dateEditText);

        //initialize calendar
        calendar = Calendar.getInstance();

        //create and initialize a DatePickerDialog and register an OnDateSetListener to it
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //set the Calendar year, month, and day to the year, month and day
                //set in the DatePicker
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                //call in a method that updates the date EditText with the date that was set
                //in the DatePicker
                updateDueDate();

            }
        };

        //register an OnClickListener to the date EditText
        dateEditText.setOnClickListener(new View.OnClickListener(){
            /**
             * This method gets called when the date EditText is clicked
             * @param view because the date EditText that calls this method is
             *             technically considered a view, we must pass the method
             *             a View
             */
            @Override
            public void onClick(View view) {
                //display DatePickerDialog with current date selected
                new DatePickerDialog(CreateList.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)        ).show();
            }
        });
        dbHandler = new DBHandler(this, null);

    }
        /**
         * This method sets the Action Bar of the Main Activity to whatever
         * is defined in the menu main menu resource
         *
         * @param menu Menu object
         * @return
         */

        public boolean onCreateOptionsMenu(Menu menu) {
            // set the action bar of the main activity to whatever is defined
            //in the menu main menu resource
            getMenuInflater().inflate(R.menu.menu_create_list, menu);
            return true;
        }


        /**
         * This method gets called when an item in the overflow menu is selected
         *
         * @param item
         * @return
         */

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
                default:
                    return super.onOptionsItemSelected(item);


            }
        }


        /**
         * This method gets called when the add_list menu item gets pushed
         *
         * @param menuItem because the add_list item that calls this method is
         *                 a menu item, we must pass the method a MenuItem
         */

        public void createList(MenuItem menuItem) {

            //get data input in EditTexts and store it in strings
            String name = nameEditText.getText().toString();
            String store = storeEditText.getText().toString();
            String date = dateEditText.getText().toString();

            //trim Strings and see if any are equal to an empty string
            if (name.trim().equals("") || (store.trim().equals("")) || date.trim().equals("")) {
                //required data hasn't been input, so display toast
                Toast.makeText(this, "Please enter a name, store, and date!", Toast.LENGTH_LONG).show();

            } else {
                //required data has been input, update the database and display a different toast
                dbHandler.addShoppingList(name, store, date);
                Toast.makeText(this, "Shopping List added!", Toast.LENGTH_LONG).show();

            }
        }
        public void updateDueDate() {
            //this method gets called when the date is set in the DatePickerDialog

            //Create a SimpleDateFormat
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            //Apply the SimpleDateFormat to the date set in the DatePickerDialog and
            //set the formatted date in the date EditText
            dateEditText.setText(simpleDateFormat.format(calendar.getTime()));

        }



    }









