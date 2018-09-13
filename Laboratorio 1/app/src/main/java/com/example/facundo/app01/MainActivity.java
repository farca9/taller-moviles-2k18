package com.example.facundo.app01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import modelo.Cliente;
import modelo.Moneda;
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
    private TextView tvDiasSeleccionados;
    private TextView edtMensajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plazoFijo=new PlazoFijo(getResources().getStringArray(R.array.tasas));

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
        tvDiasSeleccionados=findViewById(R.id.tvDiasSeleccionados);
        edtMensajes=findViewById(R.id.edtMensajes);

        seekDias.setProgress(10);
        btnHacerPF.setEnabled(false);

        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<10) seekDias.setProgress(10);
                else if(progress>=10 && progress<=180){
                    tvDiasSeleccionados.setText(getResources().getString(R.string.selectedDays)+" "+String.valueOf(progress));
                    plazoFijo.setDias(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        chkAceptoTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnHacerPF.setEnabled(isChecked);
                if(!isChecked) Toast.makeText(getApplicationContext(),R.string.errorTermsToast,Toast.LENGTH_SHORT).show();
            }
        });

        btnHacerPF.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean error=false;
                String errmsg="";

                if(edtMail.getText().toString().isEmpty()){
                    errmsg+=getResources().getString(R.string.emptyMail)+"\n";
                    error=true;
                }
                if (edtCuit.getText().toString().isEmpty()){
                    errmsg+=getResources().getString(R.string.emptyCuit)+"\n";
                    error=true;
                }
                if (edtMonto.getText().toString().isEmpty() || Integer.parseInt(edtMonto.getText().toString())<=0){
                    errmsg+=getResources().getString(R.string.invalidAmount)+"\n";
                    error=true;
                }
                if (seekDias.getProgress()<10){
                    errmsg+=getResources().getString(R.string.invalidDaysSelected)+"\n";
                    error=true;
                }

                if(error){
                    Toast.makeText(getApplicationContext(),R.string.errorValidationToast,Toast.LENGTH_SHORT).show();
                    edtMensajes.setTextColor(Color.RED);
                    edtMensajes.setText(errmsg);
                }
                else
                {
                    plazoFijo.setCliente(new Cliente(edtMail.getText().toString(),edtCuit.getText().toString()));
                    plazoFijo.setAvisarVencimiento(swAvisarVencimiento.isSelected());
                    plazoFijo.setRenovarAutomaticamente(togAccion.isChecked());
                    plazoFijo.setMonto(Double.parseDouble(edtMonto.getText().toString()));
                    if(optDolar.isSelected()) plazoFijo.setMoneda(Moneda.DOLAR);
                    else plazoFijo.setMoneda(Moneda.PESO);

                    edtMensajes.setTextColor(Color.BLUE);
                    edtMensajes.setText(plazoFijo.toString());
                }
            }

        });

    }
}
