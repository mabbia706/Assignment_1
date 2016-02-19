package com.example.dell.assignment_1;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by DELL on 2/11/2016.
 */
public class ShowContact extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.show_contact_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle data=getIntent().getExtras();
        ImageView im=(ImageView)findViewById(R.id.showContactPhoto);
        if(data.getString("image_path").length()>0) {
            im.setImageURI(Uri.parse(data.getString("image_path")));
        }
        else{
            im.setImageResource(R.drawable.default_contact_photo);
        }
        TextView tv=(TextView)findViewById(R.id.show_contact_name_text);
        tv.setText(data.getString("name"));

        TextView tv1=(TextView)findViewById(R.id.show_phone_number_text);
        tv1.setText(data.getString("number"));

        TextView tv2=(TextView)findViewById(R.id.show_date_text);
        tv2.setText(data.getString("date"));


        TextView tv3=(TextView)findViewById(R.id.show_month_text);
        tv3.setText(data.getString("month"));


        TextView tv4=(TextView)findViewById(R.id.show_year_text);
        tv4.setText(data.getString("year"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_contact_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
