package com.enchcrop.hotstarsimilar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by enchanter19 on 13/7/17.
 */

public class sports extends Fragment {
    ListView listView11;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sports, container, false);
        listView11=(ListView)rootView.findViewById(R.id.listbb);
        new kilomilo().execute(Globalurl.URLTEN);
         return rootView;
    }
    public class MovieAdap extends ArrayAdapter {
        private List<minten> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<minten> objects) {
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
                holder.imge=(ImageView) view.findViewById(R.id.imagejkj);
                holder.catname=(TextView)view.findViewById(R.id.test9);

                // holder.textname=(TextView) view.findViewById(R.id.txt2);
                // holder.textdescrip=(TextView)view.findViewById(R.id.txt3);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }
            minten ccitac=movieModelList.get(position);
            holder.catname.setText(ccitac.getNames());
            Picasso.with(context).load(ccitac.getImage1()).fit().into(holder.imge);

            return view;
        }
        class ViewHolder{
            //  public TextView textid,textname,textdescrip;
            public ImageView imge;
            public TextView catname;

        }
    }
    public class kilomilo extends AsyncTask<String,String, List<minten>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<minten> doInBackground(String... params) {
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
                List<minten> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    minten catego = gson.fromJson(finalObject.toString(), minten.class);
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
        protected void onPostExecute(final List<minten> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0)
            {
                MovieAdap adapter = new MovieAdap(getActivity(), R.layout.listrrxml, movieMode);
                listView11.setAdapter(adapter);
                listView11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        minten item = movieMode.get(position);

                        Intent intent = new Intent(getActivity(), youplay.class);
                        intent.putExtra("idd",item.getUrl1());
                        intent.putExtra("descr",item.getDescr());
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
