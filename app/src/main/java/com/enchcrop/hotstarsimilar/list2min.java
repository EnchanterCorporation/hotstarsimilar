package com.enchcrop.hotstarsimilar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class list2min extends AppCompatActivity {
    ListView listViewuu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2min);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listViewuu=(ListView)findViewById(R.id.listrry);
        setSupportActionBar(toolbar);
        String lan=getIntent().getStringExtra("idd12");
        String s1=Globalurl.URL_LANGBASED+lan;
        new kilomilo().execute(s1);


    }
    public class MovieAdap extends ArrayAdapter {
        private List<listbased> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<listbased> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context =context;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
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
                holder.time1=(TextView)view.findViewById(R.id.test99);

                // holder.textname=(TextView) view.findViewById(R.id.txt2);
                // holder.textdescrip=(TextView)view.findViewById(R.id.txt3);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }
            listbased ccitac=movieModelList.get(position);
            holder.catname.setText(ccitac.getNames());
            holder.time1.setText(ccitac.getTime());
            Picasso.with(context).load(ccitac.getImage()).fit().into(holder.imge);

            return view;
        }
        class ViewHolder{
            //  public TextView textid,textname,textdescrip;
            public ImageView imge;
            public TextView catname,time1;

        }
    }
    public class kilomilo extends AsyncTask<String,String, List<listbased>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<listbased> doInBackground(String... params) {
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
                List<listbased> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    listbased catego = gson.fromJson(finalObject.toString(), listbased.class);
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
        protected void onPostExecute(final List<listbased> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0)
            {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.listrrxml, movieMode);
                listViewuu.setAdapter(adapter);
                listViewuu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        listbased item = movieMode.get(position);

                        Intent intent = new Intent(getApplicationContext(), youplay.class);
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
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
