package br.com.unipar.aplicativo1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.CDATASection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import static java.util.Calendar.MARCH;
import static java.util.Calendar.YEAR;


public class TelaConferir extends AppCompatActivity {


    EditText txcpf;
    EditText txrenda;
    double rendam;
    double resultado;
    Button confirmar1;
    TextView txdata;
    Button confirmar2;
    TextView txidade;
    String txdata2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pegar os valores
        txcpf = (EditText) findViewById(R.id.cpf);
        txrenda = (EditText) findViewById(R.id.renda);
        confirmar1 = (Button) findViewById(R.id.confirmar);
        txdata = (TextView) findViewById(R.id.data2);
        confirmar2 = (Button) findViewById(R.id.dataok);
        txidade = (TextView) findViewById(R.id.idade);




        confirmar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar();


            }
        });

        //Botão Confirmar
        confirmar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rendaMensal();



            }
        });
    }

    //Passar Valores para a outra activity
    public void goToNewActivity() {
        Intent intent = new Intent(TelaConferir.this, TelaReceber.class);
        Bundle b = new Bundle();


        b.putString("valor2", txcpf.getText().toString());
        b.putDouble("valor3", resultado);
        b.putString("valor4", txdata.getText().toString());
        b.putString("valor5", txdata2);


        intent.putExtras(b);
        startActivity(intent);

        finish();

    }

    //Calcular quanto ele ira receber, se ira ser aceito
    public void rendaMensal() {
        rendam = Double.parseDouble(txrenda.getText().toString());
        resultado = Math.round((rendam * 0.7));


        if (resultado > 5000.00) {
            Toast.makeText(this, "Acesso negado", Toast.LENGTH_LONG).show();

        } else if (resultado <= 5000.00) {
            goToNewActivity();
        }
    }


    //Data de nascimento
    public void calendar() {
        final Calendar c = Calendar.getInstance();
         final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(TelaConferir.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txdata.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                //Não achei como aumentar 20 dias
                calcularidade(dayOfMonth, month, year);
                txdata2 =((dayOfMonth +2) + "/" + (month +2) + "/" + (year+ 2));

            }
        }, ano, mes, dia);
        datePickerDialog.show();

    }
    //Calcular idade do usuário
    private void calcularidade(int diaN, int mesN, int anoN) {
        Calendar c = Calendar.getInstance();
        int diaA = c.get(Calendar.DAY_OF_MONTH);
        int mesA = c.get(Calendar.MONTH);
        int anoA = c.get(Calendar.YEAR);

        int idade = anoA - anoN;

        if (mesA > mesN) {
            idade--;
        } else if (mesN == mesA) {
            if (diaN > diaA) {
                idade--;
            } else if (idade < 18) {
                Toast.makeText(this, "Acesso negado", Toast.LENGTH_LONG).show();
                finish();
            }

        }
        txidade.setText("Idade:" + idade);
    }


}








