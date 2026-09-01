package jp.ac.meijou.android.s251205063;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205063.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();
            binding.textview.setText(text);
        });

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        prefDataStore = PrefDataStore.getInstance(this);
        prefDataStore.getString("text")
                .ifPresent(text ->{
                    Log.d("meijo", text);
                    binding.textview.setText(text);
                    if("魚".equals(text)) {
                        binding.textview.setText("安藤");
                        binding.imageView.setImageResource(R.drawable.ic_launcher_foreground);
                    } else if ("夏".equals(text)) {
                        binding.textview.setText("バリア");
                        binding.imageView.setImageResource(R.drawable.outline_body_system_24);
                    } else {
                        binding.textview.setText("相棒");
                        binding.imageView.setImageResource(R.drawable.baseline_two_wheeler_24);
                    }
                });

        binding.saveButton.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();
            prefDataStore.setString("text", text);
            if("魚".equals(text)) {
                binding.textview.setText("安藤");
                binding.imageView.setImageResource(R.drawable.ic_launcher_foreground);
            } else if ("夏".equals(text)) {
                binding.textview.setText("バリア");
                binding.imageView.setImageResource(R.drawable.outline_body_system_24);
            } else {
                binding.textview.setText("相棒");
                binding.imageView.setImageResource(R.drawable.baseline_two_wheeler_24);
            }
        });

        binding.ClearButton.setOnClickListener(view -> {
            binding.editTextText.setText(" ");
        });


    }
}