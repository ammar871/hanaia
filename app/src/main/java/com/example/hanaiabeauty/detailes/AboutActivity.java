package com.example.hanaiabeauty.detailes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.home.HomeActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView back=findViewById(R.id.back_shop);
        TextView tsxt=findViewById(R.id.textView);
        tsxt.setText("  حنايا بيوتي " +
                "هو محل بيع العطور ومستحضرات التجميل في مدينة عبري " +
                "بالمرتفع ولدينا خدمة توصيل الطلبات للمنازل والتوصيل لجميع دول الخليج ويمكنكم اختيار " +
                " اي منتج واضافته الى السلة ثم العودة للسلة بالقائمة الرئيسية لتاكيد الطلب " +
                "وسوف يتم التواصل معكم خلال 24 ساعة " +
                "وللتواصل 0096898004420 - 00971508197010");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(AboutActivity.this, HomeActivity.class);
                about.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(about);
            }
        });
    }
}
