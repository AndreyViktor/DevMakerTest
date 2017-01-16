package br.com.andrey.devmakertest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 28/12/2016.
 */

public class UpdateListTask extends AsyncTask<Object, Object, Object> {

    private Context context;
    private final ListView listView;

    public UpdateListTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            WebClient client = new WebClient();
            String jsonString = client.get();

            JSONObject jo = new JSONObject(jsonString);
            JSONArray arrayContacts = jo.getJSONArray("contacts");
            List<Contact> contacts = new ArrayList<>();

            for (int i=0;i<arrayContacts.length(); i++) {
                Contact contact = new Contact();
                contact.setId(arrayContacts.getJSONObject(i).getLong("id"));
                contact.setName(arrayContacts.getJSONObject(i).getString("name"));
                contact.setEmail(arrayContacts.getJSONObject(i).getString("email"));
                contact.setTelephone(arrayContacts.getJSONObject(i).getString("telephone"));

                contacts.add(contact);
            }
            ContactDAO dao = new ContactDAO(context);
            dao.updateContacts(contacts);
            dao.close();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        ContactDAO dao = new ContactDAO(context);
        List<Contact> contacts = dao.getContacts();
        dao.close();

        ArrayAdapter<Contact> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1, contacts);
        listView.setAdapter(adapter);

    }
}
