package com.example.facundo.app01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import modelo.Cliente;
import modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo plazoFijo;
    private Cliente cliente;

    private EditText edtMail;
    private EditText edtCuit;
    private RadioGroup optMoneda;
    private RadioButton optDolar;
    private RadioButton optPesos;
    private EditText edtMonto;
    private SeekBar seekDias;
    private TextView tvDias;
    private Switch swAvisarVencimiento;
    private ToggleButton togAccion;
    private CheckBox chkAceptoTerminos;
    private Button btnHacerPF;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plazoFijo=new PlazoFijo(getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();

        edtMail=findViewById(R.id.edtMail);
        edtCuit=findViewById(R.id.edtCuit);
        optMoneda=findViewById(R.id.optMoneda);
        optDolar=findViewById(R.id.optDolar);
        optPesos=findViewById(R.id.optPesos);
        edtMonto=findViewById(R.id.edtMonto);
        seekDias=findViewById(R.id.seekDias);
        tvDias=findViewById(R.id.tvDias);
        swAvisarVencimiento=findViewById(R.id.swAvisarVencimiento);
        togAccion=findViewById(R.id.togAccion);
        chkAceptoTerminos=findViewById(R.id.chkAceptoTerminos);
        btnHacerPF=findViewById(R.id.btnHacerPF);

        btnHacerPF.setEnabled(false);

        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //TBD
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
