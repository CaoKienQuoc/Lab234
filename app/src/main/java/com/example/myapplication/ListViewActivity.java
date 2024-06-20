package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    android.widget.ListView lvMonhoc;
    Button btnThem, btnCapNhat;
    EditText edtMonHoc;
    ArrayList<String> arraycourse;
    int vitri = -1;  // Initialize vitri to -1 to indicate no item selected
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.list_view);

        lvMonhoc = (android.widget.ListView) findViewById(R.id.ListViewMonHoc);
        arraycourse = new ArrayList<>();
        arraycourse.add("Android");
        arraycourse.add("PHP");
        arraycourse.add("IOS");
        arraycourse.add("Unity");
        arraycourse.add("ASP.net");
        ArrayAdapter adapter = new ArrayAdapter(
                ListViewActivity.this,
                android.R.layout.simple_list_item_1,
                arraycourse
        );
        btnThem = (Button) findViewById(R.id.buttonThem);
        edtMonHoc = (EditText) findViewById(R.id.editTextMonHoc);
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhat);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Hiển thị nút quay lại trên Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Thiết lập tiêu đề cho Toolbar
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Tắt tiêu đề mặc định
        toolbarTitle.setText("Danh sách khóa học (ListView)"); // Thiết lập tiêu đề tùy chỉnh

        // Xử lý sự kiện khi nhấn vào nút quay lại
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        lvMonhoc.setAdapter(adapter);

        lvMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
                edtMonHoc.setText(arraycourse.get(positon));
                vitri = positon;
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monhoc = edtMonHoc.getText().toString();
                if (monhoc.isEmpty()) {
                    Toast.makeText(ListViewActivity.this, "Vui lòng nhập tên khóa học", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (vitri != -1) {  // Check if an item is selected
                    arraycourse.set(vitri, monhoc);
                    adapter.notifyDataSetChanged();
                    edtMonHoc.setText(""); // Clear the input field
                    vitri = -1;  // Reset vitri after updating
                }
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monhoc = edtMonHoc.getText().toString();
                if (monhoc.isEmpty()) {
                    Toast.makeText(ListViewActivity.this, "Vui lòng nhập tên khóa học", Toast.LENGTH_SHORT).show();
                    return;
                }
                arraycourse.add(monhoc);
                adapter.notifyDataSetChanged();
                edtMonHoc.setText(""); // Clear the input field
            }
        });

        lvMonhoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arraycourse.remove(position); // xoa adapter
                adapter.notifyDataSetChanged(); // load lại adapter

                if (position == vitri) {  // Check if the deleted item is the selected one
                    edtMonHoc.setText(""); // Clear the input field
                    vitri = -1;  // Reset vitri
                } else if (position < vitri) {
                    vitri--;  // Adjust vitri if necessary
                }

                return true;  // Change to true to indicate the long click was handled
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
