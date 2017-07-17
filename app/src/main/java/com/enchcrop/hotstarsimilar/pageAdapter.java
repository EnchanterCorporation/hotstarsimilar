package com.enchcrop.hotstarsimilar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class pageAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
   private Integer [] images ={R.drawable.image1,R.drawable.image2};

    public pageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.slide,null);
        ImageView imageView=(ImageView)rootView.findViewById(R.id.image);
        imageView.setImageResource(images[position]);
        ViewPager vp= (ViewPager) container;
        vp.addView(rootView,0);
        return rootView;



    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp= (ViewPager) container;
        View view =(View) object;
        vp.removeView(view);
    }
}
