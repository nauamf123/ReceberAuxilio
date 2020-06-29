package br.com.unipar.aplicativo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TelaReceber extends AppCompatActivity {

    TextView txdata2;
    TextView txdata1;
    TextView txCpf;
    String cpf3;
    Double renda;
    TextView txRenda;
    String data;
    String data2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_receber);

        //Receber os valores
        receberValores();
        setarText();


    }

    //Setar os valores no TextView
    public void setarText() {
        txdata2 = (TextView) findViewById(R.id.txdata2);
        txdata1 = (TextView) findViewById(R.id.txdata3);
        txCpf = (TextView) findViewById(R.id.cpf1);
        txRenda = (TextView) findViewById(R.id.renda1);
        txCpf.setText(cpf3);
        txRenda.setText(renda.toString());
        txdata1.setText(data);
        txdata2.setText(data2);


    }

    //Receber os valores da primeira actitity
    public void receberValores() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            cpf3 = b.getString("valor2");
            renda = b.getDouble("valor3");
            data = b.getString("valor4");
            data2 = b.getString("valor5");
        }
    }
}

