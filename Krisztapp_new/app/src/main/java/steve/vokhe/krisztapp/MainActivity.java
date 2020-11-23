package steve.vokhe.krisztapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  {
    public Toolbar toolbar;

    private RecyclerView recyclerView;
    private ExampleAdapter recycleAdapter;
    private RecyclerView.LayoutManager recycleLayoutManager;
    private ArrayList<Example> exampleList;
    private ArrayList<Example> deleteList = new ArrayList<>();
    private int cint =0;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText insertET;
    private EditText removeET;

    static MenuItem menuItem;
    static boolean isam = false;
    static TextView counter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createExampleList();
        buildRecycleView();

        counter = findViewById(R.id.counter_text);
        counter.setVisibility(View.GONE);
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        insertET = findViewById(R.id.edittext_insert);
        removeET = findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(insertET.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(removeET.getText().toString());
                removeItem(position);
            }
        });

        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isam = true;
                invalidateOptionsMenu();
                counter.setVisibility(View.VISIBLE);
                recycleAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    /*public static void Menureset() {
        isam = true;
        invalidateOptionsMenu();
        counter.setVisibility(View.VISIBLE);
        recycleAdapter.notifyDataSetChanged();
    }*/



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(isam){
            menu.clear();
            getMenuInflater().inflate(R.menu.main_menu,menu);
            menu.setGroupVisible(R.id.main_menu_group_2,true);
            menu.setGroupVisible(R.id.main_menu_group_1,false);
        } else {
            menu.setGroupVisible(R.id.main_menu_group_1,true);
            menu.setGroupVisible(R.id.main_menu_group_2,false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_menu_search:
                if(isam){
                    deleteList.clear();
                    isam=false;
                    Toast.makeText(MainActivity.this, "A receptek törlődtek!", Toast.LENGTH_SHORT).show();
                    counter.setVisibility(View.INVISIBLE);
                    invalidateOptionsMenu();
                    recycleAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Keresés", Toast.LENGTH_SHORT).show();
                    // search
                }
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void insertItem(int position){
        exampleList.add(position,new Example(R.drawable.ic_android_black_24dp,"Új item ezen a pozíción -> "+position,R.drawable.ic_baseline_ac_unit_24,R.drawable.ic_baseline_airplanemode_active_24,R.drawable.ic_baseline_attach_money_24,false));
        recycleAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        exampleList.remove(position);
        recycleAdapter.notifyItemRemoved(position);
    }

    public void createExampleList(){
        exampleList = new ArrayList<>();
        exampleList.add(new Example(R.drawable.ic_android_black_24dp,"Ez egy rather hosszú cam",R.drawable.ic_baseline_ac_unit_24,R.drawable.ic_baseline_airplanemode_active_24,R.drawable.ic_baseline_attach_money_24,false));
        exampleList.add(new Example(R.drawable.ic_baseline_arrow_left_24,"Ez meg egy másik ratcher hosszú cam",R.drawable.ic_baseline_attach_file_24,R.drawable.ic_baseline_anchor_24,R.drawable.ic_baseline_attach_file_24,false));
        exampleList.add(new Example(R.drawable.ic_android_black_24dp,"Ez egy rather hosszú cam",R.drawable.ic_baseline_ac_unit_24,R.drawable.ic_baseline_airplanemode_active_24,R.drawable.ic_baseline_attach_money_24,false));

    }
    // a visszagombnál ha isam akkor ki kell üríteni a deletelistet
    public void changeItem(int position, String text){

        if(!isam){
            exampleList.get(position).changeTitle(text);
            recycleAdapter.notifyItemChanged(position);
        } else if(exampleList.get(position).getActive()){
            exampleList.get(position).setActive(false);
            exampleList.get(position).changeBack();
            ExampleAdapter.x--;
            //deleteList.remove(position);
            recycleAdapter.notifyItemChanged(position);
        }else{
            ExampleAdapter.x++;
            exampleList.get(position).setActive(true);
            exampleList.get(position).changeIcon(R.drawable.ic_torles);
            deleteList.add(exampleList.get(position));
            recycleAdapter.notifyItemChanged(position);
        }

    }

    public void buildRecycleView(){
        recyclerView = findViewById(R.id.recycleViewRecept);
        recyclerView.setHasFixedSize(true);
        recycleLayoutManager = new LinearLayoutManager(this);
        recycleAdapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(recycleLayoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }
        });
    }
}