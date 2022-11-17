package com.example.pagamentodecompra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnTotal;
    private EditText edtValor;
    private TextView txtValor;
    private CheckBox cbArroz, cbLeite, cbOvos, cbCarne, cbPao;

    double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTotal = findViewById(R.id.btnTotal);
        //TextViews
        txtValor = findViewById(R.id.txtValor);
        //CheckBoxes
        cbArroz = findViewById(R.id.cbArroz);
        cbLeite = findViewById(R.id.cbLeite);
        cbOvos = findViewById(R.id.cbOvos);
        cbCarne = findViewById(R.id.cbCarne);
        cbPao = findViewById(R.id.cbPao);

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 0;
                if(cbArroz.isChecked())
                    valor += 3.5;
                if(cbLeite.isChecked())
                    valor += 5.5;
                if(cbCarne.isChecked())
                    valor += 11.3;
                if(cbPao.isChecked())
                    valor += 2.2;
                if(cbOvos.isChecked())
                    valor += 7.5;
                txtValor.setText("Valor: " + valor);
            }
        });
    }
}