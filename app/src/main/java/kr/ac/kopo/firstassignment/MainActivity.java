package kr.ac.kopo.firstassignment;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edit0, edit1, edit2;
    TextView textResult;
    ImageView imgView;
    Button btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit0 = findViewById(R.id.edit0);
        edit1 = findViewById(R.id.edit1);//객체 참조값 반환값 받기
        edit2 = findViewById(R.id.edit2);
        btnCal = findViewById(R.id.btn_cal);
        textResult = findViewById(R.id.text_result);
        imgView = findViewById(R.id.img_view);
        btnCal.setOnTouchListener(btnTouchListener);

        applyFocusChangeListener(edit0);
        applyFocusChangeListener(edit1);
        applyFocusChangeListener(edit2);


    }

    View.OnTouchListener btnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            String name = "";
            String num0Str = edit0.getText().toString();
            String num1Str = edit1.getText().toString(); //getText()는 Editable 객체를 반환. String으로 쓰려면 toString() 사용.
            String num2Str = edit2.getText().toString();
            String unit = getString(R.string.measure_result);
            String bmiCategory;

            if (num0Str.isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                edit0.setFocusable(true);
                return true;
            } else {
                name = num0Str;
            }

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                Toast.makeText(getApplicationContext(), "신장과 체중 모두 입력하세요", Toast.LENGTH_SHORT).show();
                edit1.setFocusable(true);
                return true;
            }
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double heightInMeters = num1 / 100.0;
            double result = 0;

            Button btnEvent = (Button) v;

            if (num1 <= 0 || num2 <= 0) {
                Toast.makeText(getApplicationContext(), "신장과 체중을 모두 0보다 크게 입력하세요.", Toast.LENGTH_SHORT).show();
                return true;
            }
            else result = num2 / (heightInMeters * heightInMeters);

            if (result < 18.5) {
                bmiCategory = getString(R.string.bmi1); // 저체중
                imgView.setImageResource(R.drawable.squarepants_character);
            } else if (result < 24.9) {
                bmiCategory = getString(R.string.bmi2); // 정상 체중
                imgView.setImageResource(R.drawable.squidward_tentacles);
            } else if (result < 29.9) {
                bmiCategory = getString(R.string.bmi3); // 과체중
                imgView.setImageResource(R.drawable.patrick_star_character);
            } else {
                bmiCategory = getString(R.string.bmi4); // 비만
                imgView.setImageResource(R.drawable.pearl_the_whale);
            }

            String resultText = getString(R.string.result_format, name, result, unit, bmiCategory);
            textResult.setText(resultText);

            return true;
        }
    };

    private void applyFocusChangeListener(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.shades));
                } else {
                    editText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.title_color));
                }
            }
        });
    }

}