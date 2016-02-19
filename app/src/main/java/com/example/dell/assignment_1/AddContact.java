package com.example.dell.assignment_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by DELL on 2/9/2016.
 */
public class AddContact extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
   private static final int RESULT_LOAD_IMAGE=1;
    ImageView image_to_upload;
    public static int count=0;
    Uri selectedImage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        selectedImage=null;

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_contact_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.date_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Date_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.month_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Month_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);


        Spinner spinner2 = (Spinner) findViewById(R.id.year_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Year_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        count=Integer.parseInt(getIntent().getExtras().getString("count"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_contact_menu, menu);
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
        if(id==R.id.action_save_contact){
            EditText Name=(EditText)findViewById(R.id.contact_name_text);
            EditText Number=(EditText)findViewById(R.id.phone_number_text);
            Spinner month=(Spinner)findViewById(R.id.month_spinner);
            Spinner date=(Spinner)findViewById(R.id.date_spinner);
            Spinner year=(Spinner)findViewById(R.id.year_spinner);
        if(Name.getText().toString().length()!=0&&Number.getText().toString().length()!=0) {
            SharedPreferences sp = getSharedPreferences("contacts", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Name " + count, Name.getText().toString());
            editor.putString("Number " + count, Number.getText().toString());
            editor.putString("Date " + count, date.getSelectedItem().toString());
            editor.putString("Month " + count, month.getSelectedItem().toString());
            editor.putString("Year " + count, year.getSelectedItem().toString());
            image_to_upload = (ImageView) findViewById(R.id.contactPhoto);

            if (selectedImage != null && !selectedImage.equals(Uri.EMPTY)) {
                editor.putString("imageUri " + count, selectedImage.toString());
            } else {
                editor.putString("imageUri " + count, "");
            }
            editor.putString("count", Integer.toString(count));

            editor.apply();//to save above info
            count++;
            finish();
            finish();
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            }
            else{
            Toast.makeText(this,"Name and Number Fields can not be empty",Toast.LENGTH_LONG).show();

            }

        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       //following are for checking if request has been made by our function
        //and also i data has been selected or not
        if(requestCode==RESULT_LOAD_IMAGE &&resultCode==RESULT_OK&&data!=null){
            selectedImage=data.getData();
            image_to_upload.setImageURI(selectedImage);
        }

    }

    public void selectPhotoFromGallery(View view){
    image_to_upload=(ImageView)findViewById(R.id.contactPhoto);
    Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
    }

}
