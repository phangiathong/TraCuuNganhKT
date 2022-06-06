package com.academy.tracuunganhkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.academy.tracuunganhkt.database.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ListView listViewEconomics;
    ArrayList<Economics> arrayListEconomics;
    EconomicsAdapter adapterEconomics;
    Button btnFindAll;
    EditText edtInput;
    TextView textMenu, textCount;
    int flag=0;
    int count = 0;

    Database database;
    Connection connect;
    Cursor cursorAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        database = new Database(this);
        btnFindAll = findViewById(R.id.btnFindAll);
        edtInput = findViewById(R.id.edtInput);
        textMenu = findViewById(R.id.textMenu);
        textCount = findViewById(R.id.textCount);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listViewEconomics = findViewById(R.id.listview);
        arrayListEconomics = new ArrayList<>();
        adapterEconomics = new EconomicsAdapter(this,R.layout.layout_economics,arrayListEconomics);

        listViewEconomics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, DetailsEconomicActivity.class);
                Economics objectEconomic = arrayListEconomics.get(position);
                String idObject = objectEconomic.getId();
                int levelObject = objectEconomic.getLevel();
                String nameObject = objectEconomic.getName();
                String contentObject = objectEconomic.getContent();

                intent.putExtra("id",idObject);
                intent.putExtra("level",levelObject);
                intent.putExtra("name",nameObject);
                intent.putExtra("content",contentObject);

                startActivity(intent);
            }
        });

//        ConnectionHelper connectionHelper = new ConnectionHelper();
//        connect = connectionHelper.connectionclass();
//        String queryAll = "select KTQD.MA_NGANH, KTQD.CAP, KTQD.TEN_NGANH, GIAI_THICH.NOI_DUNG " +
//                "from KTQD full join GIAI_THICH " +
//                "ON KTQD.ID_NGANH = GIAI_THICH.ID_NGANH";

        cursorAll = database.getDataEconomics();
        getQuery(cursorAll);

//        GetConnectSQL(queryAll);
        btnFindAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListEconomics.clear();
                String getInput = edtInput.getText().toString();

                switch (flag){
                    case 1:
                        arrayListEconomics.clear();
                        Cursor cursor1 = database.getDataByName(getInput);
                        getQuery(cursor1);
                        break;
                    case 2:
                        arrayListEconomics.clear();
                        Cursor cursor2 = database.getDataByCode(getInput);
                        getQuery(cursor2);
                        break;
                    case 3:
                        arrayListEconomics.clear();
                        Cursor cursor3 = database.getDataByContent(getInput);
                        getQuery(cursor3);
                        break;
                    case 4:
                        arrayListEconomics.clear();
                        Cursor cursor4 = database.getDataEconomics();
                        getQuery(cursor4);
                        break;
                    default:
                        getQuery(cursorAll);
                        break;
                }

                count = arrayListEconomics.size();
                textCount.setText("Có "+count+" mã ngành được tìm thấy!");
                if (flag==4){
                    textCount.setText("");
                }
            }
        });
    }

    public void getQuery(Cursor cursor) {
        while (cursor.moveToNext()) {
            String maNganh = cursor.getString(0);
            int cap = Integer.parseInt(cursor.getString(1));
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);

            Economics economics = new Economics(maNganh,cap, ten, noiDung);
            arrayListEconomics.add(economics);
            adapterEconomics.notifyDataSetChanged();
            listViewEconomics.setAdapter(adapterEconomics);
        }

        if (arrayListEconomics.size()==0 || edtInput.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Không Tìm Thấy!", Toast.LENGTH_SHORT).show();
            textCount.setText("");
        }
    }

    public void resetView() {
        edtInput.setText("");
        textCount.setText("");
        getQuery(cursorAll);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Lựa chọn menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menuName:
                Toast.makeText(getApplicationContext(), "Tìm Theo Tên", Toast.LENGTH_SHORT).show();
                flag=1;
                textMenu.setText("Theo tên");
                resetView();
                return true;
            case R.id.menuId:
                Toast.makeText(getApplicationContext(), "Tìm Theo Mã", Toast.LENGTH_SHORT).show();
                flag=2;
                textMenu.setText("Theo mã");
                resetView();
                return true;
            case R.id.menuExplain:
                Toast.makeText(getApplicationContext(), "Tìm Theo Giải Thích", Toast.LENGTH_SHORT).show();
                flag=3;
                textMenu.setText("Giải thích");
                resetView();
                return true;
            case R.id.menuAll:
                Toast.makeText(getApplicationContext(), "Tìm Tất Cả", Toast.LENGTH_SHORT).show();
                flag=4;
                textMenu.setText("Tất cả");
                resetView();
                return true;
            default:
                return false;
        }
    }

//    public void GetConnectSQL(String query){
//        try {
//            if (connect!=null){
//                Log.d("Infor","Connect successfull");
//
//                Statement st = connect.createStatement();
//                ResultSet rs = st.executeQuery(query);
//
//                while (rs.next()){
//                    String code = rs.getString(1);
//                    int level = (int) rs.getFloat(2);
//                    String name = rs.getString(3);
//                    String content = rs.getString(4);
//
//                    Economics economics = new Economics(code,level,name,content);
////                    arrayListEconomics.add(economics);
////                    adapterEconomics.notifyDataSetChanged();
////                    listViewEconomics.setAdapter(adapterEconomics);
//                    database.addEconomics(economics);
//                }
//                if (arrayListEconomics.size()==0){
//                    Toast.makeText(getApplicationContext(), "Không Tìm Thấy!", Toast.LENGTH_SHORT).show();
//                }
//            }else {
//                Log.d("False","Check Again");
//            }
//
//        }catch (Exception ex){
//            Log.e("Error",ex.getMessage());
//        }
//    }
}