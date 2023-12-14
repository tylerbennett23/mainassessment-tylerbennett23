package com.example.restester;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.restester.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;


import java.io.DataOutputStream;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    int i = 0;
    int y = 0;
    int x = 0;
    int z = 0;
    private String customerName;
    private String customerPhoneNumber;
    private String FinalBooking;
    private TextView date;
    String uname;
    String phonetxt;
    private String meal;
    private int tablesize;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        date = new TextView(this);


    }

    //brings you to the login page
    public void welcome(View view){
        setContentView(R.layout.activity_login);
    }

    //this is the signing in page
    public void loginpage(View view){

        z = 0;

        //gets input username
        EditText username = (EditText)findViewById(R.id.UserTxtBox);
        String Username = username.getText().toString();
        uname = Username;

        //gets input password
        EditText password = (EditText)findViewById(R.id.PassTxtBox);
        String Password = password.getText().toString();
        phonetxt = Password;

        //for incorrect credentials output
        TextView credentials = (TextView)findViewById(R.id.IncorrectCretxt);


        //checks to see if inputs are correct
        if(Username.equals("test")){
            if(Password.equals("123456")){
                setContentView(R.layout.fragment_gallery);
            }else{
                credentials.setVisibility(View.VISIBLE);
            }
        }else{
            credentials.setVisibility(View.VISIBLE);
        }
    }

    //takes you to the create account page
    public void createaccountpge(View view){
        setContentView(R.layout.fragment_slideshow);
    }

    //this "signs out" but just sends them back to the login page
    public void signout(View view){
        if(z ==1) {
            setContentView(R.layout.activity_login);
        }else{
            setContentView(R.layout.fragment_gallery);
        }
    }

    //brings back to login page from create account
    public void backtologin(View view){
        setContentView(R.layout.activity_login);
    }

    //brings you to forgot password page
    public void fpassword(View view){
        setContentView(R.layout.activity_test);
    }

    //brings you to booking page and can tell if your a guest or logged in
    public void bookingbtn(View view){
        if(z==1) {
            EditText Name = (EditText) findViewById(R.id.customerName1);
            EditText pnumber = (EditText) findViewById(R.id.customerPhoneNumber1);
            customerPhoneNumber = pnumber.getText().toString();
            customerName = Name.getText().toString();
        }else{
            customerName = uname;
            customerPhoneNumber = phonetxt;
        }
        setContentView(R.layout.activity_meal);
    }

    //makes x = 0 so we know its breakfast
    public void breakfast(View view){
        x = 0;
        setContentView(R.layout.activity_booking);
    }

    //makes x = 1 so we know its lunch
    public void lunch(View view){
        x = 1;
        setContentView(R.layout.activity_booking);
    }

    //makes x = 2 so we know its dinner
    public void dinner(View view){
        x = 2;
        setContentView(R.layout.activity_booking);
    }

    //brings you to outside booking
    public  void outsidebtn(View view){
        i = 0;
        seatingArea = "Outside";

        setContentView(R.layout.activity_days);
        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + month + "-" + dayOfMonth;
                // Update the global TextView with the selected date
                date.setText(selectedDate.toString());
            }
        });
    }

    private String seatingArea;

    //brings you to inside booking
    public void insidebtn(View view){
        i = 1;
        seatingArea = "Inside";
        setContentView(R.layout.activity_days);
        CalendarView calendarView = findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + month + "-" + dayOfMonth;
                // Update the global TextView with the selected date
                date.setText(selectedDate.toString());
            }
        });
    }

    //this brings you back to the main screen
    public void backtomain(View view){
        setContentView(R.layout.fragment_gallery);
    }

    //this will let you just put in your name and phone number to book
    public void guest(View view){
        z = 1;
        setContentView(R.layout.activity_guest);
    }

    //this will bring you to the setings
    public void settings(View view){
        setContentView(R.layout.activity_settings);
    }

    //this will bring you to the change username page
    public void cusernamepge(View view){
        setContentView(R.layout.activity_cusername);
    }

    //this will bring you to the change email page
    public void cemailpage(View view){
        setContentView(R.layout.activity_cemail);
    }

    //this will bring you to the notification settings
    public void cnotificationpge(View view){
        setContentView(R.layout.activity_notifications);
    }

    //this will bring you to the review page
    public void review(View view){
        setContentView(R.layout.activity_review);
    }

    //brings you to the food menu
    public void menubtn(View view){
        setContentView(R.layout.activity_menu);
    }


    //this whole functions takes the btn inputs, and converts them into numbers, and then displays the inputs marking as 'booked'
    //it also makes sure that the number of people are equal or less then 10
    //this will also upload the reservation details onto the RESTful api
    public void fbookingbtn(View view) throws JSONException {

        TextView incnumber = (TextView) findViewById(R.id.Peoplecount);
        EditText number = (EditText) findViewById(R.id.numpeople);


        int pnum = Integer.parseInt(number.getText().toString());

        if (pnum <= 10) {
            if (i == 0) {
                y = y + 1;
                int id = y;
                setContentView(R.layout.activity_gbooked);

                TextView fdate = (TextView) findViewById(R.id.fday2);
                TextView seatingArea = (TextView) findViewById(R.id.seatingArea3);
                TextView tableSize = (TextView) findViewById(R.id.npeople2);
                TextView meal1 = (TextView) findViewById(R.id.Meal);
                // TextView textview = (TextView) findViewById(R.id.textView18);


                TextView phone = (TextView) findViewById(R.id.customerphone);
                TextView name = (TextView) findViewById(R.id.customername);

                fdate.setText(date.getText().toString());
                tableSize.setText(String.valueOf(pnum));
                tablesize = pnum;
                phone.setText(customerPhoneNumber);
                name.setText(customerName);
                seatingArea.setText("Outside");
                if (x == 0) {
                    meal1.setText("Breakfast");
                    meal = "Breakfast";
                } else {
                    if (x == 1) {
                        meal = "Lunch";
                        meal1.setText("Lunch");
                    } else {
                        if (x == 2) {
                            meal = "Dinner";
                            meal1.setText("Dinner");
                        }
                    }
                }
            } else {
                y = y + 1;
                int id = y;
                setContentView(R.layout.activity_gbooked);

                TextView fdate = (TextView) findViewById(R.id.fday2);
                TextView seatingArea = (TextView) findViewById(R.id.seatingArea3);
                TextView tableSize = (TextView) findViewById(R.id.npeople2);
                TextView meal1 = (TextView) findViewById(R.id.Meal);
                // TextView textview = (TextView) findViewById(R.id.textView18);


                TextView phone = (TextView) findViewById(R.id.customerphone);
                TextView name = (TextView) findViewById(R.id.customername);

                fdate.setText(date.getText().toString());
                tableSize.setText(String.valueOf(pnum));
                tablesize = pnum;
                phone.setText(customerPhoneNumber);
                name.setText(customerName);
                seatingArea.setText("Inside");
                if (x == 0) {
                    meal = "Breakfast";
                    meal1.setText("Breakfast");
                } else {
                    if (x == 1) {
                        meal = "lunch";
                        meal1.setText("Lunch");
                    } else {
                        if (x == 2) {
                            meal = "dinner";
                            meal1.setText("Dinner");
                        }
                    }
                }
            }

        } else {
            incnumber.setVisibility(View.VISIBLE);
        }


        // Construct the URL for the API endpoint
        String apiUrl = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";
        String Date = date.getText().toString();


        // Create the reservation JSON object
        JSONObject reservationObject = new JSONObject();
        reservationObject.put("customerName", customerName);
        reservationObject.put("customerPhoneNumber", customerPhoneNumber);
        reservationObject.put("meal", meal);
        reservationObject.put("seatingArea", seatingArea);
        reservationObject.put("tableSize", tablesize);
        reservationObject.put("date", Date);




        System.out.println("Request Payload: " + reservationObject);

        // Make the reservation API call using AsyncTask
        makeReservation(apiUrl, reservationObject);

    }

    private static class ReservationTask extends AsyncTask<Void, Void, String> {
        private final String apiUrl;
        private final JSONObject reservationObject;

        ReservationTask(String apiUrl, JSONObject reservationObject) {
            this.apiUrl = apiUrl;
            this.reservationObject = reservationObject;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Open connection
                URL url = new URL(apiUrl);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Write the reservationObject to the request body
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(reservationObject.toString().getBytes());
                }

                // Get the response code
                int responseCode = conn.getResponseCode();

                // Read the response stream
                InputStream inputStream = (responseCode == HttpsURLConnection.HTTP_OK)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

                // Use BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Close the connection
                conn.disconnect();

                // Return the API response
                return response.toString();

            } catch (IOException e) {
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Update UI or handle the result as needed
            System.out.println("API Response: " + result);
        }
    }

    // This method initiates the reservation API call
    private void makeReservation(String apiUrl, JSONObject reservationObject) {
        new ReservationTask(apiUrl, reservationObject).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}

