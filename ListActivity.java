package br.com.andrey.devmakertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView list = (ListView) findViewById(R.id.listContacts);
        updateListView(list);
    }

    private void updateListView(ListView list) {
        ContactDAO dao = new ContactDAO(this);
        List<Contact> contacts = dao.getContacts();
        dao.close();
        ArrayAdapter<Contact> adapter =
                new ArrayAdapter<Contact>(this, android.R.layout.simple_expandable_list_item_1, contacts);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update_list:
                new UpdateListTask(this).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }
}

