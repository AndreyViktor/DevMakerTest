package br.com.andrey.devmakertest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 28/12/2016.
 */

public class UpdateListTask extends AsyncTask<Object, Object, List<Contact>> {
    public UpdateListTask(Context context) {
        this.context = context;
    }

    private Context context;

    @Override
    protected List<Contact> doInBackground(Object[] objects) {
        try {
            WebClient client = new WebClient();
            String jsonString = client.get();

            JSONObject jo = new JSONObject(jsonString);
            JSONArray arrayContacts = jo.getJSONArray("contacts");
            List<Contact> contacts = new ArrayList<Contact>();

            for (int i=0;i<arrayContacts.length(); i++) {
                Contact contact = new Contact();
                contact.setId(arrayContacts.getJSONObject(i).getLong("id"));
                contact.setName(arrayContacts.getJSONObject(i).getString("name"));
                contact.setEmail(arrayContacts.getJSONObject(i).getString("email"));
                contact.setTelephone(arrayContacts.getJSONObject(i).getString("telephone"));

                contacts.add(contact);
            }
            return  contacts;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        Contact contact=contacts.get(0); //test
        Toast.makeText(context,contact.getId()+contact.getName()+contact.getEmail()+contact.getTelephone(),Toast.LENGTH_SHORT).show();
    }
}
