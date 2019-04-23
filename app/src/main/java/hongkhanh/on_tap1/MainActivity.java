package hongkhanh.on_tap1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    AdapterListView adapterListView;
    ArrayList <Model> arrayList;
    DBManager dbManager;
    Button btnAddMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControl();
        initData();
        initEvent();
        initDisplay();
    }


    @Override
    protected void onStart() {
        super.onStart();
    updatelist();
    }
    private void initDisplay() {

    }

    private void initEvent() {
        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.AddMember();
                updatelist();
            }
        });

       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               dbManager.DeleteMember( String.valueOf(arrayList.get(i).getId()) );
               updatelist();
               Toast.makeText(MainActivity.this, "Delete okay", Toast.LENGTH_SHORT).show();
               return false;
           }
       });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(MainActivity.this, UpdateMember.class);
               intent.putExtra("ID", arrayList.get(i).getId());
               startActivity(intent);
           }
       });
    }

    private void initData() {
        dbManager = new DBManager(this);

        arrayList = new ArrayList<>();
        arrayList = dbManager.GellAllMember();


        adapterListView = new AdapterListView(arrayList,this);
        listView.setAdapter(adapterListView);

    }

    private void initControl() {
        listView = findViewById(R.id.listview);
        btnAddMember = findViewById(R.id.btn_addMember);

    }

    private void updatelist() {
        arrayList.clear();
        if (adapterListView != null) {
            arrayList.addAll(dbManager.GellAllMember());
            adapterListView.notifyDataSetChanged();

        } else {
            arrayList = dbManager.GellAllMember();
        }
    }

}

