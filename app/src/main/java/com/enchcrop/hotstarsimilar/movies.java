package com.enchcrop.hotstarsimilar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by enchanter19 on 13/7/17.
 */

public class movies extends Fragment {
    ViewPager mViewPager;
    GridView grid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movies, container, false);
         mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        grid=(GridView)rootView.findViewById(R.id.gridview2);
        new kilomilo().execute(Globalurl.URLLANG);
        pageAdapter adapterView = new pageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);

        return rootView;

    }


    public class MyTimerTask extends TimerTask
    {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(mViewPager.getCurrentItem()==0)
                    {
                        mViewPager.setCurrentItem(1);

                    }else if(mViewPager.getCurrentItem()==1)
                    {
                        mViewPager.setCurrentItem(2);

                    }else if(mViewPager.getCurrentItem()==2)
                    {
                        mViewPager.setCurrentItem(3);

                    }

                }
            });

        }
    }

    public class MovieAdap extends ArrayAdapter {
        private List<languagelist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<languagelist> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context =context;
            this.resource = resource;
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getViewTypeCount() {
            return 1;
        }
        @Override
        public int getItemViewType(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if(view == null){
                view = inflater.inflate(resource,null);
                holder = new ViewHolder();
                holder.imge=(ImageView) view.findViewById(R.id.image122);
                holder.catname=(TextView)view.findViewById(R.id.test11);
                holder.lang=(TextView)view.findViewById(R.id.lang);

                // holder.textname=(TextView) view.findViewById(R.id.txt2);
                // holder.textdescrip=(TextView)view.findViewById(R.id.txt3);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }
            languagelist ccitac=movieModelList.get(position);
            holder.catname.setText(ccitac.getLang());
            holder.lang.setText(ccitac.getLang());
            Picasso.with(context).load(ccitac.getImage()).fit().into(holder.imge);

            return view;
        }
        class ViewHolder{
            //  public TextView textid,textname,textdescrip;
            public ImageView imge;
            public TextView catname,lang;

        }
    }
    public class kilomilo extends AsyncTask<String,String, List<languagelist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<languagelist> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("result");
                List<languagelist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    languagelist catego = gson.fromJson(finalObject.toString(), languagelist.class);
                    milokilo.add(catego);
                }
                return milokilo;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(final List<languagelist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0)
            {
                MovieAdap adapter = new MovieAdap(getActivity(), R.layout.langdes, movieMode);
                grid.setAdapter(adapter);
                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        languagelist item = movieMode.get(position);

                        Intent intent = new Intent(getActivity(), list2min.class);
                        intent.putExtra("idd12",item.getSno());
                        startActivity(intent);
                    }
                });


            }
            else
            {
                Toast.makeText(getActivity(),"Check your internet connection",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
