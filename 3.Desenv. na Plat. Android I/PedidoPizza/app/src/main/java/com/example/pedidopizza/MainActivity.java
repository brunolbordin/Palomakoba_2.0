package com.example.pedidopizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLimpar, btnPagar;
    private RadioGroup cbGrupo;
    private CheckBox cbCalabresa, cbMarguerita, cb4Queijos, cbModaCasa, cbPalmito;

    String txtpizza="";
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnPagar = findViewById(R.id.btnPagar);

        cbGrupo = findViewById(R.id.cbGrupo);

        cbCalabresa = findViewById(R.id.cbCalabresa);
        cbMarguerita = findViewById(R.id.cbMarguerita);
        cb4Queijos = findViewById(R.id.cb4Queijos);
        cbModaCasa = findViewById(R.id.cbModaCasa);
        cbPalmito = findViewById(R.id.cbPalmito);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbCalabresa.isChecked()) {
                    txtpizza += "Calabresa;\n";
                    total += 70;
                }
                if (cb4Queijos.isChecked()) {
                    txtpizza += "4 Queijos;\n";
                    total += 70;
                }
                if (cbMarguerita.isChecked()) {
                    txtpizza += "Marguerita;\n";
                    total += 70;
                }
                if (cbPalmito.isChecked()) {
                    txtpizza += "Palmito;\n";
                    total += 70;
                }
                if (cbModaCasa.isChecked()) {
                    txtpizza += "Moda da Casa;\n";
                    total += 70;
                }
                Toast.makeText(MainActivity.this, "Total a pagar: " + total, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, Pagamento.class);
                Bundle params = new Bundle();
                params.putDouble("total", total);
                params.putString("txtpizza",txtpizza);
                i.putExtras(params);
                startActivity(i);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total=0;
                txtpizza="";
                cbModaCasa.setChecked(false);
                cbCalabresa.setChecked(false);
                cbPalmito.setChecked(false);
                cbMarguerita.setChecked(false);
                cb4Queijos.setChecked(false);
            }
        });
    }
}