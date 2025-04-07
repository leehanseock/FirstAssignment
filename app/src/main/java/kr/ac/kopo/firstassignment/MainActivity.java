package kr.ac.kopo.firstassignment;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    TextView text_result;

    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnRemainder;

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
        btnPlus= findViewById(R.id.btn_plus);
        btnMinus= findViewById(R.id.btn_minus);
        btnMultiply= findViewById(R.id.btn_multiply);
        btnDivide= findViewById(R.id.btn_divide);
        btnRemainder= findViewById(R.id.btn_remainder);
        text_result = findViewById(R.id.text_result);

//        btnPlus.setOnClickListener(btnListener);
//        btnMinus.setOnClickListener(btnListener);
//        btnMultiply.setOnClickListener(btnListener);
//        btnDivide.setOnClickListener(btnListener);

        btnPlus.setOnTouchListener(btnTouchListener);
        btnMinus.setOnTouchListener(btnTouchListener);
        btnMultiply.setOnTouchListener(btnTouchListener);
        btnDivide.setOnTouchListener(btnTouchListener);
        btnRemainder.setOnTouchListener(btnTouchListener);
    }

    View.OnTouchListener btnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            String num1Str = edit1.getText().toString(); //getText()는 Editable 객체를 반환. String으로 쓰려면 toString() 사용.
            String num2Str = edit2.getText().toString();

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                Toast.makeText(getApplicationContext(), "숫자를 모두 입력하세요", Toast.LENGTH_SHORT).show();
                edit1.setFocusable(true);
                return true;
            }
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            Button btnEvent = (Button) v;

            if (btnEvent == btnPlus) result = num1 + num2;
            else if (btnEvent == btnMinus) result = num1 - num2;
            else if (btnEvent == btnMultiply) result = num1 * num2;
            else if (btnEvent == btnDivide) {
                try {
                    result = num1 / num2;
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                    edit2.setText("");
                    edit2.setFocusable(true);
                    return true;
                }

            }
            else if (btnEvent == btnRemainder) result = num1 % num2;
            ;

            text_result.setText(String.format("계산 결과: %.3f",result));
            return true;
        }
    };
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String num1Str = edit1.getText().toString(); //getText()는 Editable 객체를 반환. String으로 쓰려면 toString() 사용.
            String num2Str = edit2.getText().toString();

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                Toast.makeText(getApplicationContext(), "숫자를 모두 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);
            int result = 0;

            Button btnEvent = (Button) v;

            if (btnEvent == btnPlus) result = num1 + num2;
            else if (btnEvent == btnMinus) result = num1 - num2;
            else if (btnEvent == btnMultiply) result = num1 * num2;
            else if (btnEvent == btnDivide){
                try{ result = num1 / num2;}
                catch(ArithmeticException e){
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                    edit2.setText("");
                    edit2.setFocusable(true);
                    return;
                }

            };

            text_result.setText(String.format("계산 결과: %.3f",result));

        }
    };

}