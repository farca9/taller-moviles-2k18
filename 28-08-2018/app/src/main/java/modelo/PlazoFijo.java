package modelo;

import android.util.Log;

import java.util.Arrays;

public class PlazoFijo {

    private Integer dias;
    private Double monto;
    private Boolean avisarVencimiento;
    private Boolean renovarAutomaticamente;
    private Moneda moneda;
    private String[] tasas;
    private Cliente cliente;

    public PlazoFijo(String[] tasas) {
        Log.d("BancoOnline", tasas + "");
        Log.d("BancoOnline", Arrays.toString(tasas));
        this.tasas = tasas;
        this.monto = 0.0;
        this.dias = 0;
        this.avisarVencimiento = false;
        this.renovarAutomaticamente = false;
        this.moneda = Moneda.PESO;
    }

    public Integer getDias() {
        return dias;
    }

    public Double getMonto() {
        return monto;
    }

    public Boolean getAvisarVencimiento() {
        return avisarVencimiento;
    }

    public Boolean getRenovarAutomaticamente() {
        return renovarAutomaticamente;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public String[] getTasas() {
        return tasas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setAvisarVencimiento(Boolean avisarVencimiento) {
        this.avisarVencimiento = avisarVencimiento;
    }

    public void setRenovarAutomaticamente(Boolean renovarAutomaticamente) {
        this.renovarAutomaticamente = renovarAutomaticamente;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public void setTasas(String[] tasas) {
        this.tasas = tasas;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "PlazoFijo{" +
                "dias=" + dias +
                ", monto=" + monto +
                ", avisarVencimiento=" + avisarVencimiento +
                ", renovarAutomaticamente=" + renovarAutomaticamente +
                ", moneda=" + moneda +
                ", tasa=" + this.calcularTasa().toString() +
                ", cliente=" + cliente +
                ", intereses=" + this.intereses().toString() +
                '}'
                ;
    }

    private Double calcularTasa(){
        if(dias < 30 && monto <= 5000.0) return Double.valueOf(tasas[0]);
        if(dias >= 30 && monto <= 5000.0) return Double.valueOf(tasas[1]);
        if(dias < 30 && monto > 5000.0 && monto < 100000.0) return Double.valueOf(tasas[2]);
        if(dias >= 30 && monto > 5000 && monto < 100000.0) return Double.valueOf(tasas[3]);
        if(dias < 30 && monto >= 100000.0) return Double.valueOf(tasas[4]);
        if(dias >= 30 && monto >= 100000.0) return Double.valueOf(tasas[5]);
        return 0.0;
    }

    public Double intereses(){
        return monto*(Math.pow((1+(this.calcularTasa()/100)),(dias/360.0)) - 1);
    }
}
