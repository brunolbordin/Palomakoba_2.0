package com.example.wheresmybeefalo.activities;

/*
Created by Bruno Bordin
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wheresmybeefalo.R;
import com.example.wheresmybeefalo.apis.CEPService;
import com.example.wheresmybeefalo.helper.EnderecoDAO;
import com.example.wheresmybeefalo.models.CEP;
import com.example.wheresmybeefalo.models.Endereco;
import com.example.wheresmybeefalo.models.MyReceiver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //sensor
    private SensorManager sensorManager;
    Sensor accelerometer;
    SensorEventListener listener;

    private Button btnList;
    private Button btnSearch;
    private EditText cepResponse;

    private Retrofit retrofit;
    private BroadcastReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();

        eventoSensorAcelerometro();

        linkaIdsActivity();

        //search cep
        apiBuscaCep();

        //Button Search to take CEP in api
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarCep();
            }
        });
        //button list
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EnderecoActivity.class);
                startActivity(intent);
            }
        });
    } //fim do onCreate

    private void apiBuscaCep() {
        String urlCep = "https://viacep.com.br/ws/";
        retrofit = new Retrofit.Builder()
                .baseUrl(urlCep)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void linkaIdsActivity() {
        btnSearch = findViewById(R.id.btnSearch);
        btnList = findViewById(R.id.btnList);
        cepResponse = findViewById(R.id.edtCEP);
    }

    private void eventoSensorAcelerometro() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] > 9 ){
                    finish();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }

    private void consultarCep() {
        CEPService cepService = retrofit.create(CEPService.class);
        String cep = cepResponse.getText().toString();
        Call<CEP> call = cepService.consultarCEP(cep);
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful() && response.body().getCep() != null) {
                    try {
                        Toast.makeText(MainActivity.this, "Buscando CEP", Toast.LENGTH_SHORT).show();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CEP cep = response.body();
                    AlertDialog.Builder cepResult = new AlertDialog.Builder(MainActivity.this);
                    cepResult.setTitle("Informações do CEP: " + cep.getCep());
                    cepResult.setIcon(R.mipmap.buffalo);
                    cepResult.setMessage(cep.getLogradouro() + " " + cep.getComplemento() + "\nBairro: " + cep.getBairro() + "\nCidade: " + cep.getLocalidade() + "\nEstado: " + cep.getUf());
                    cepResult.setNegativeButton("Fechar", null);
                    cepResult.setPositiveButton("Salvar Endereço?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                            CEP cep = response.body();
                                            EnderecoDAO enderecoDAO = new EnderecoDAO(getApplicationContext());
                                            String nomeCep = cep.getCep();
                                            String rua = cep.getLogradouro();
                                            String numero = cep.getComplemento();
                                            String bairro = cep.getBairro();
                                            String cidade = cep.getLocalidade();
                                            String uf = cep.getUf();
                                            Endereco endereco = new Endereco();
                                            endereco.setCep(nomeCep);
                                            endereco.setLogradouro(rua);
                                            endereco.setComplemento(numero);
                                            endereco.setBairro(bairro);
                                            endereco.setCidade(cidade);
                                            endereco.setUF(uf);
                                            if (enderecoDAO.salvar(endereco)){
                                                Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), "Erro ao salvar endereco", Toast.LENGTH_SHORT).show();
                                            }
                        }
                    });
                    cepResult.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "CEP não existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(this.myReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        sensorManager.registerListener(listener , accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(listener);
        this.unregisterReceiver(this.myReceiver);
    }

}