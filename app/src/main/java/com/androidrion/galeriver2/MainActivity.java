package com.androidrion.galeriver2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity implements ViewSwitcher.ViewFactory {

    Context context;
    int imageItem;
    Integer[] images = {
            R.drawable.adobexd, R.drawable.behance, R.drawable.dribbble,
            R.drawable.figma, R.drawable.ilustrator, R.drawable.invision,
            R.drawable.photoshop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        Gallery gallery = findViewById(R.id.galeri);

        final ImageSwitcher imageSwitcher = findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(MainActivity.this);

        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));

        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));

        imageSwitcher.setAlpha(Float.parseFloat("50.0"));

        gallery.setAdapter(new ImageAdapter(this));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                imageItem = position;
                imageSwitcher.setImageResource(images[position]);
            }
        });
    }

    private class ImageAdapter extends BaseAdapter
    {
        Context context;

        ImageAdapter(Context context)
        {
            this.context = context;
        }

        public int getCount()
        {
            return images.length;
        }

        public Object getItem(int position)
        {
            return images[position];
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView = new ImageView(this.context);
            imageView.setImageResource(images[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));

            return imageView;
        }
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[imageItem]);
        return imageView;
    }
}
