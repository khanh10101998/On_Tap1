package hongkhanh.on_tap1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMember extends AppCompatActivity {
    EditText edtName, edtAge, edtAvatar;
    Button btnUpdata;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);
        initControl();
        initData();
        initEvent();
        initDisplay();
    }


    private void initControl() {
        edtName = findViewById(R.id.edt_name_update);
        edtAge = findViewById(R.id.edt_age_update);
        edtAvatar = findViewById(R.id.edt_avatar_update);
        btnUpdata = findViewById(R.id.btn_Update);
    }

    private void initData() {
        dbManager = new DBManager(this);
    }

    private void initEvent() {
        btnUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ID =  getIntent().getIntExtra("ID",-1);
                String id = String.valueOf(ID);
                dbManager.UpdateMember(id,edtAvatar.getText().toString(),edtAge.getText().toString(),edtName.getText().toString());
                Toast.makeText(UpdateMember.this, "Update Ok", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void initDisplay() {

    }
}
