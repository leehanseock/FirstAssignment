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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
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

        edit1 = findViewById(R.id.edit1);//객체 참조값 반환값 받기
        edit2 = findViewById(R.id.edit2);
        btnCal = findViewById(R.id.btn_cal);
        textResult = findViewById(R.id.text_result);
        imgView = findViewById(R.id.img_view);
        btnCal.setOnTouchListener(btnTouchListener);
    }

    View.OnTouchListener btnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            String num1Str = edit1.getText().toString(); //getText()는 Editable 객체를 반환. String으로 쓰려면 toString() 사용.
            String num2Str = edit2.getText().toString();

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

            try {
                result = num2 / (heightInMeters * heightInMeters);
            } catch (ArithmeticException e) {
                Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                edit2.setText("");
                edit2.setFocusable(true);
                return true;
            }
            textResult.setText(String.format("계산 결과: 당신의 BMI 지수는 %.1f입니다.",result));
            return true;
        }
    };

}