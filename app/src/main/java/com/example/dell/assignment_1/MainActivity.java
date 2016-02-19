package com.example.dell.assignment_1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private int total;
    GridView gd;
    SharedPreferences sp;
    ImageAdapter IA;
    private AlertDialog.Builder dialogBuilder;
    private ArrayList<String> label;
    private ArrayList<String> imagePath;
    private ArrayList<String> number;
    private ArrayList<String> month;
    private ArrayList<String> date;
    private ArrayList<String> year;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle contact_data=getIntent().getExtras();

         sp=getSharedPreferences("contacts", Context.MODE_PRIVATE);
        String total_contacts;
            total_contacts = sp.getString("count", "");
        if(total_contacts!="") {
            total = Integer.parseInt(total_contacts);
            label=new ArrayList<String>();
            imagePath=new ArrayList<String>();
            number=new ArrayList<String>();
            date=new ArrayList<String>();
            month=new ArrayList<String>();
            year=new ArrayList<String>();
            int i=0;
            for (; i <= total; i++) {
                String Name = sp.getString("Name " + i, "");
                imagePath.add(sp.getString("imageUri " +i,""));
                number.add(sp.getString("Number "+i,""));
                date.add(sp.getString("Date "+i,""));
                month.add(sp.getString("Month " + i, ""));
                year.add(sp.getString("Year " + i, ""));
             label.add(Name);
            }
            total=i;
                gd = (GridView) findViewById(R.id.contact_gallery);
            IA=new ImageAdapter(this, label, imagePath);
                gd.setAdapter(IA);
                gd.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, ShowContact.class);
                        i.putExtra("number", number.get(position));
                        i.putExtra("image_path", imagePath.get(position));
                        i.putExtra("date", date.get(position));
                        i.putExtra("month", month.get(position));
                        i.putExtra("year", year.get(position));
                        i.putExtra("name", label.get(position));
                        startActivity(i);
                    }
                });

            gd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    dialogBuilder=new AlertDialog.Builder(MainActivity.this);
                    dialogBuilder.setTitle("Delete Contact?");
                    final int _id=position;
                    dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int i=_id;
                            for(;i<MainActivity.this.total;i++){
                                int next=i+1;
                                sp.edit().putString("Name "+i,sp.getString("Name "+next,"")).apply();
                                sp.edit().putString("Number " + i, sp.getString("Number " + next, "")).apply();
                                sp.edit().putString("Date " + i, sp.getString("Date " + next, "")).apply();
                                sp.edit().putString("Year " + i, sp.getString("Year " + next, "")).apply();
                                sp.edit().putString("Month " + i, sp.getString("Month " + next, "")).apply();
                                sp.edit().putString("imageUri " + i, sp.getString("imageUri " + next, "")).apply();
                            }

                            int temp=Integer.parseInt(sp.getString("count",""));
                            temp--;
                            sp.edit().putString("count", Integer.toString(temp)).apply();
                            sp.edit().remove("Name " + i).apply();
                            sp.edit().remove("Number " + i).apply();
                            sp.edit().remove("Date " + i).apply();
                            sp.edit().remove("Year "+ i).apply();
                            sp.edit().remove("Month "+i).apply();
                            sp.edit().remove("imageUri "+ i).apply();
                            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                             //   MainActivity.this.recreate();
                            //} else {
                                final Intent intent = MainActivity.this.getIntent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                MainActivity.this.finish();
                                MainActivity.this.overridePendingTransition(0, 0);
                                MainActivity.this.startActivity(intent);
                                MainActivity.this.overridePendingTransition(0, 0);
                            //}
                            Toast.makeText(getApplicationContext(),"Contact deleted successfully", Toast.LENGTH_LONG).show();

                        }
                    });
                    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog delete_contact=dialogBuilder.create();
                    delete_contact.show();
                    return true;
                }
            });
        }
        else{
            total=0;
        }




    }
public void decrementTotal(){
    this.total--;
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_add_contact) {

            Intent i=new Intent(this,AddContact.class);
            i.putExtra("count",Integer.toString(total));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
