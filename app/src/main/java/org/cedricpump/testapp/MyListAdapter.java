package org.cedricpump.testapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {
    Model model;
    Context context;

    public MyListAdapter(Context context, Model model){
        super(context, R.layout.listview_layout);
        this.model = model;
        this.context = context;
    }

    @Override
    public int getCount() {
        return model.photos.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder vh = new ViewHolder();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_layout, parent, false);
            vh.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            vh.imageText = (TextView) convertView.findViewById(R.id.imageText);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
            Photo photo = (Photo) model.photos.get(position);
            vh.imageView.setImageBitmap(photo.bytedata);
            vh.imageText.setText(photo.name);

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView imageText;
    }
}

