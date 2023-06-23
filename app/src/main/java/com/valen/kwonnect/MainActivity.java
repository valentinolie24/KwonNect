package com.valen.kwonnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.valen.kwonnect.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAnggota;
    private final ArrayList<AnggotaModel> data = new ArrayList<>();
    private ActivityMainBinding binding;
    private Card cardAdapter;
    private Grid gridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvAnggota = binding.rvAnggota;
        rvAnggota.setHasFixedSize(true);

        if (!Utilities.checkValue(MainActivity.this, "xUserId")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        cardAdapter = new Card();
        gridAdapter = new Grid();
        binding.rvAnggota.setLayoutManager(new LinearLayoutManager(this));
        binding.rvAnggota.setAdapter(cardAdapter);
        binding.rvAnggota.setAdapter(gridAdapter);
        cardAdapter.setOnItemLongClickListener(new Card.OnItemLongClickListener() {
            public boolean onItemLongClick(View v, AnggotaModel anggotaModel, int position) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setGravity(Gravity.RIGHT);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int idMenu = item.getItemId();
                        if (idMenu == R.id.action_edit) {
                            Intent intent = new Intent(MainActivity.this, UpdateAnggota.class);
                            intent.putExtra("EXTRA_DATA", anggotaModel);
                            startActivity(intent);
                            return true;
                        }
                        else if (idMenu == R.id.action_delete) {
                            String id = anggotaModel.getId();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Konfirmasi");
                            builder.setMessage("Yakin ingin menghapus anggota " + data.get(position).getNama() + " ?");
                            builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteAnggota(id);
                                }
                            });
                            builder.setNegativeButton("Jangan Hapus", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                });
                return false;
            }
            @Override
            public void onLongClick(AnggotaModel anggota) {

            }

        });

        cardAdapter.setOnItemLongClickListener(new Card.OnItemLongClickListener() {
            public boolean onItemLongClick(View v, AnggotaModel anggotaModel, int position) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setGravity(Gravity.RIGHT);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int idMenu = item.getItemId();
                        if (idMenu == R.id.action_edit) {
                            Intent intent = new Intent(MainActivity.this, UpdateAnggota.class);
                            intent.putExtra("EXTRA_DATA", anggotaModel);
                            startActivity(intent);
                            return true;
                        }
                        else if (idMenu == R.id.action_delete) {
                            String id = anggotaModel.getId();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Konfirmasi");
                            builder.setMessage("Yakin ingin menghapus anggota " + data.get(position).getNama() + " ?");
                            builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteAnggota(id);
                                }
                            });
                            builder.setNegativeButton("Jangan Hapus", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                });
                return false;
            }
            @Override
            public void onLongClick(AnggotaModel anggota) {

            }

        });

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahAnggota.class);
                startActivity(intent);
            }
        });

        tampilDataCard();
    }

    private void deleteAnggota(String id) {
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.deleteAnggota(id);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1){
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        getAllAnggota();
                    } else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int findPositionById(String id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllAnggota();
    }

    public void getAllAnggota() {
        APIService apiService = Utilities.getRetrofit().create(APIService.class);
        Call<ValueData<List<AnggotaModel>>> call = apiService.getAnggota();
        call.enqueue(new Callback<ValueData<List<AnggotaModel>>>() {
            @Override
            public void onResponse(Call<ValueData<List<AnggotaModel>>> call, Response<ValueData<List<AnggotaModel>>> response) {
                if (response.isSuccessful()) {
                    ValueData<List<AnggotaModel>> valueData = response.body();
                    if (valueData != null && valueData.getSuccess() == 1) {
                        data.clear();
                        data.addAll(valueData.getData());
                        cardAdapter.setData(data); // Update data dalam adapter
                        cardAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, valueData.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to get data: " + valueData.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<List<AnggotaModel>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilDataCard() {
        rvAnggota.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new Card(data, MainActivity.this);
        rvAnggota.setAdapter(cardAdapter);
    }

    private void tampilDataGrid() {
        rvAnggota.setLayoutManager(new GridLayoutManager(this, 2));
        gridAdapter = new Grid(data, MainActivity.this);
        rvAnggota.setAdapter(gridAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampilan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_card) {
            tampilDataCard();
        } else if (item.getItemId() == R.id.menu_grid) {
            tampilDataGrid();
        }
        else if (item.getItemId() == R.id.action_logout){
            Utilities.clearUser(this);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
