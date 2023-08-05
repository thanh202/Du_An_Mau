package thanhlvph23422.fpoly.mob2041_thanhlvph23422;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ManHinhChaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        ImageView logo=findViewById(R.id.ivLogo);

        Glide.with(this).load(R.drawable.book).into(logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManHinhChaoActivity.this,LoginActivity.class));
            }
        },3000);
    }


}
