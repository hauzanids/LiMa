package com.example.lima;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class background1 extends AsyncTask <String, Void, String> {
    AlertDialog dialog;
    Context context;

    public background1(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Absen Status");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals("Absen Masuk Berhasil")){
            dialog.setTitle("Absen Status");
            dialog.setMessage("Absen Masuk Mahasiswa Berhasil");
            dialog.show();
            Intent i = new Intent(context, Absensi.class);
            i.putExtra("user_id", s);
            context.startActivity(i);
        }else if (s.equals("Absen Pulang Berhasil")){
            dialog.setTitle("Absen Status");
            dialog.setMessage("Absen Pulang Mahasiswa Berhasil");
            dialog.show();
            Intent i = new Intent(context, Absensi.class);
            i.putExtra("user_id", s);
            context.startActivity(i);
        }else{
            dialog.setTitle("Absen Status");
            dialog.setMessage("Absen Failed");
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String p = voids[0];
        String sid = voids[1];
        String user_id = voids[2];

        String connstr = "http://10.0.2.2/absen.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("p", "UTF-8")+"="+URLEncoder.encode(p,"UTF-8")
                    +"&&"+URLEncoder.encode("sid", "UTF-8")+"="+URLEncoder.encode(sid,"UTF-8")
                    +"&&"+URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line = "";

            while((line = reader.readLine()) != null){
                result += line;
            }

            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }

        return result;
    }
}
