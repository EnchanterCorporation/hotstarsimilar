package com.enchcrop.hotstarsimilar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import it.sephiroth.android.library.widget.AdapterView;
import it.sephiroth.android.library.widget.HListView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by enchanter19 on 13/7/17.
 */

public class home extends Fragment {
    ViewPager mViewPager;
    GridView gridView;
    HListView listviewww,listViewyuyu,listviewfgfg;
    private HorizontalGridView horizontalGridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);
        listviewww=(HListView)rootView.findViewById(R.id.oklk);
        listViewyuyu=(HListView)rootView.findViewById(R.id.oklk2);
        listviewfgfg=(HListView)rootView.findViewById(R.id.oklk1);
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        pageAdapter adapterView = new pageAdapter(getActivity());
        mViewPager.setAdapter(adapterView);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);

        new kilomilo().execute(Globalurl.URLGRID);
        new kilomilo1().execute(Globalurl.URLENGLISH);
        new kilomilo2().execute(Globalurl.URLTAMIL);



         return rootView;

        // Calculated single Item Layout Width for each grid element ....

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
        private List<gridlist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<gridlist> objects) {
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
                holder.imge=(ImageView) view.findViewById(R.id.image12);
                holder.catname=(TextView)view.findViewById(R.id.test1);


                // holder.textname=(TextView) view.findViewById(R.id.txt2);
                // holder.textdescrip=(TextView)view.findViewById(R.id.txt3);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }
            gridlist ccitac=movieModelList.get(position);
            holder.catname.setText(ccitac.getNames());

            Picasso.with(context).load(ccitac.getImage()).fit().into(holder.imge);

            return view;
        }
        class ViewHolder{
            //  public TextView textid,textname,textdescrip;
            public ImageView imge;
            public TextView catname,category12;

        }
    }
    public class kilomilo extends AsyncTask<String,String, List<gridlist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<gridlist> doInBackground(String... params) {
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
                List<gridlist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    gridlist catego = gson.fromJson(finalObject.toString(), gridlist.class);
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
        protected void onPostExecute(final List<gridlist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0)
            {
                MovieAdap adapter = new MovieAdap(getActivity(), R.layout.gridhome, movieMode);
                listviewww.setAdapter(adapter);
                listviewww.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        gridlist item = movieMode.get(position);

                        Intent intent12 = new Intent(getContext(),youplay.class);
                        intent12.putExtra("idd",item.getUrl());
                        intent12.putExtra("descr",item.getDescrip());
                        intent12.putExtra("time",item.getTime());
                        startActivity(intent12);

                    }
                });


            }
            else
            {
                Toast.makeText(getActivity(),"Check your internet connection",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public class MovieAdap1 extends ArrayAdapter {
        private List<englishlist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap1(Context context, int resource, List<englishlist> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context =context;
            this.resource = resource;
            inflater = (LayoutInflater)context. getSystemService(LAYOUT_INFLATER_SERVICE);
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

                // holder.textname=(TextView) view.findViewById(R.id.txt2);
                // holder.textdescrip=(TextView)view.findViewById(R.id.txt3);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }
            englishlist ccitac=movieModelList.get(position);
            holder.catname.setText(ccitac.getNames());
            Picasso.with(context).load(ccitac.getImage()).fit().into(holder.imge);

            return view;
        }
        class ViewHolder{
            //  public TextView textid,textname,textdescrip;
            public ImageView imge;
            public TextView catname;

        }
    }
    public class kilomilo1 extends AsyncTask<String,String, List<englishlist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<englishlist> doInBackground(String... params) {
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
                List<englishlist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    englishlist catego = gson.fromJson(finalObject.toString(), englishlist.class);
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
        protected void onPostExecute(final List<englishlist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0)
            {
                MovieAdap1 adapter = new MovieAdap1(getActivity(), R.layout.gridhome33, movieMode);
                listViewyuyu.setAdapter(adapter);
                listViewyuyu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        englishlist item = movieMode.get(position);

                        Intent intent12 = new Intent(getActivity(),youplay.class);
                        intent12.putExtra("idd",item.getUrl());
                        intent12.putExtra("descr",item.getDescrip());
                        intent12.putExtra("time",item.getTime());

                        startActivity(intent12);
                    }
                });




            }
            else
            {
                Toast.makeText(getActivity(),"Check your internet connection",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class MovieAdap2 extends ArrayAdapter {
        private List<tamillist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap2(Context context, int resource, List<tamillist> objects) {
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

                // holder.textname=(TextView) view.findViewById(R.id.txt2);
                // holder.textdescrip=(TextView)view.findViewById(R.id.txt3);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }
            tamillist ccitac=movieModelList.get(position);
            holder.catname.setText(ccitac.getNames());
            Picasso.with(context).load(ccitac.getImage()).fit().into(holder.imge);

            return view;
        }
        class ViewHolder{
            //  public TextView textid,textname,textdescrip;
            public ImageView imge;
            public TextView catname;

        }
    }
    public class kilomilo2 extends AsyncTask<String,String, List<tamillist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<tamillist> doInBackground(String... params) {
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
                List<tamillist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    tamillist catego = gson.fromJson(finalObject.toString(), tamillist.class);
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
        protected void onPostExecute(final List<tamillist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0)
            {
                MovieAdap2 adapter = new MovieAdap2(getActivity(), R.layout.gridhome33, movieMode);
                listviewfgfg.setAdapter(adapter);
                listviewfgfg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tamillist item = movieMode.get(position);

                        Intent intent = new Intent(getActivity(), youplay.class);
                        intent.putExtra("idd",item.getUrl());
                        intent.putExtra("descr",item.getDescrip());
                        intent.putExtra("time",item.getTime());


                        // intent.putExtra("days",itm.getMonths());
                        //intent.putExtra("username",item.getUsername());
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
