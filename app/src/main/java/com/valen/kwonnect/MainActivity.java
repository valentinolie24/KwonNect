package com.valen.kwonnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAnggota;
    private ArrayList<AnggotaModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAnggota = findViewById(R.id.rv_anggota);
        rvAnggota.setHasFixedSize(true);
        data.addAll(DataAnggota.ambilDataAnggota());
        tampilDataCard();
    }

    private void tampilDataCard(){
        rvAnggota.setLayoutManager(new LinearLayoutManager(this));
        Card card = new Card(data, MainActivity.this);
        rvAnggota.setAdapter(card);
    }

    private void tampilDataGrid(){
        rvAnggota.setLayoutManager(new GridLayoutManager(this, 2));
        Grid Grid = new Grid(data, MainActivity.this);
        rvAnggota.setAdapter(Grid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampilan_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_card) {
            tampilDataCard();
        } else if (item.getItemId() == R.id.menu_grid) {
            tampilDataGrid();
        }
        return super.onOptionsItemSelected(item);
    }
}




