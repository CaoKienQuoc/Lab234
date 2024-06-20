package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraicay; // Data structure for fruits with image, name, and description
    TraiCayAdapter adapter;
    private Toolbar toolbar;
    private FloatingActionButton fabAdd;

    // URL of the image you want to load
    private static final String IMAGE_URL = "https://example.com/image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_view);
        AnhXa(); // Initialize views and data
        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, arrayTraicay);
        lvTraiCay.setAdapter(adapter);

        // Find and set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Display back button on Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set custom title for Toolbar
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText("Danh sách trái cây (Custom_ListView)");

        // Handle click on back button
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Set up FloatingActionButton
        fabAdd = findViewById(R.id.fabAdd);
        new DownloadImageTask().execute(IMAGE_URL); // Load image from URL asynchronously

        // Handle click on ListView item to show edit dialog
        lvTraiCay.setOnItemClickListener((parent, view, position, id) -> showEditDialog(position));

        // Handle long click on ListView item to show delete dialog
        lvTraiCay.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteDialog(position);
            return true; // true: event handled
        });

        // Apply WindowInsets to the main layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(),
                    insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
            return insets.consumeSystemWindowInsets();
        });

        // Handle click on FloatingActionButton to add new fruit
        fabAdd.setOnClickListener(v -> showAddDialog());
    }

    // Initialize views and data
    private void AnhXa() {
        lvTraiCay = findViewById(R.id.lvTraiCay);
        arrayTraicay = new ArrayList<>();
        /*arrayTraicay.add(new TraiCay("Sầu riêng", "Sầu riêng Ri6", R.drawable.thumb1_1200x676_14));
        arrayTraicay.add(new TraiCay("Bơ booth", "Bơ booth Đắk Lắk", R.drawable.sieu_thuc_pham_1_978));
        arrayTraicay.add(new TraiCay("Ổi", "Ổi nữ hoàng", R.drawable.images));
        arrayTraicay.add(new TraiCay("Quýt đường", "Trái cây theo mùa", R.drawable.quyt_duong__1_));
        arrayTraicay.add(new TraiCay("Mận", "Mận An Phước xuất khẩu", R.drawable.mam_an_phuong_xuat_khau_510x510));*/
        arrayTraicay.add(new TraiCay("Apple", "apple.... some description goes here", R.drawable.apple));
        arrayTraicay.add(new TraiCay("banana", "banana.... some description goes here", R.drawable.chuoi));
        arrayTraicay.add(new TraiCay("blueberry", "blueberry.... some description goes here", R.drawable.berry));
        arrayTraicay.add(new TraiCay("corn", "corn.... some description goes here", R.drawable.corn));
        arrayTraicay.add(new TraiCay("grapes", "grapes.... some description goes here", R.drawable.nho));
    }

    // Dialog to edit fruit information
    private void showEditDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chỉnh sửa thông tin");

        // Inflate layout for dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_trai_cay, null);
        builder.setView(dialogView);

        // Initialize views
        EditText inputName = dialogView.findViewById(R.id.editTextTen);
        EditText inputDesc = dialogView.findViewById(R.id.editTextMoTa);
        EditText inputImage = dialogView.findViewById(R.id.editTextHinh);

        // Populate current values
        inputName.setText(arrayTraicay.get(position).getTen());
        inputDesc.setText(arrayTraicay.get(position).getMota());
        inputImage.setText(String.valueOf(arrayTraicay.get(position).getImageUrl())); // Assuming this handles int or String based on your TraiCay class

        // Set up buttons
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String name = inputName.getText().toString();
            String desc = inputDesc.getText().toString();
            String image = inputImage.getText().toString();

            if (!name.isEmpty() && !desc.isEmpty() && !image.isEmpty()) {
                // Update TraiCay object
                arrayTraicay.get(position).setTen(name);
                arrayTraicay.get(position).setMota(desc);
                arrayTraicay.get(position).setImageUrl(image); // This needs to handle int or String based on your TraiCay class

                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Dialog to confirm deletion of fruit
    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa trái cây");
        builder.setMessage("Bạn có chắc chắn muốn xóa trái cây này?");

        builder.setPositiveButton("Xóa", (dialog, which) -> {
            arrayTraicay.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã xóa trái cây", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Dialog to add a new fruit
    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm trái cây mới");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_trai_cay, null);
        builder.setView(dialogView);

        EditText inputName = dialogView.findViewById(R.id.editTextTen);
        EditText inputDesc = dialogView.findViewById(R.id.editTextMoTa);
        EditText inputImage = dialogView.findViewById(R.id.editTextHinh);

        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String name = inputName.getText().toString();
            String desc = inputDesc.getText().toString();
            String image = inputImage.getText().toString();

            if (!name.isEmpty() && !desc.isEmpty() && !image.isEmpty()) {
                try {
                    int imageRes = Integer.parseInt(image); // Thử chuyển đổi thành int

                    // Nếu thành công, sử dụng imageRes như một ID nguồn hình ảnh
                    arrayTraicay.add(new TraiCay(name, desc, imageRes));
                } catch (NumberFormatException e) {
                    // Nếu không thành công, giả sử image là một URL hình ảnh
                    arrayTraicay.add(new TraiCay(name, desc, image));
                }

                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã thêm trái cây mới", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // AsyncTask to download image from URL and set to FloatingActionButton
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                // Set the downloaded bitmap to the FloatingActionButton
                fabAdd.setImageBitmap(bitmap);
            } else {
                // Handle case where bitmap is null (e.g., download failed)
                // Optionally, you can set a default image or handle the error
                fabAdd.setImageResource(R.drawable.images); // Example default image resource
            }
        }
    }

}
