package com.example.dell.assignment_1;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> values;
    private ArrayList<String>image;
    public ImageAdapter(Context c) {
        mContext = c;
    }
    public ImageAdapter(Context c,ArrayList<String> values,ArrayList<String> image) {
        mContext = c;
        this.values=values;
        this.image=image;
    }

    public int getCount() {
        return values.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gd;
    if (convertView == null) {
        gd = new GridView(mContext);
        gd = inflater.inflate(R.layout.grid_style, null);
        TextView tv = (TextView) gd.findViewById(R.id.contact_gallery_label);
        tv.setText(values.get(position));
        ImageView img = (ImageView) gd.findViewById(R.id.contact_gallery_photo);
        String str = values.get(position);
        if (image.get(position).length() > 0) {
            img.setImageURI(Uri.parse(image.get(position)));
        } else {
            img.setImageResource(R.drawable.default_contact_photo);
        }

    } else {
        gd = convertView;
    }
    return gd;


    }

    // references to our images
    private Integer[] mThumbIds = {
    };
}

