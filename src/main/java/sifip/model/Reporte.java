package sifip.model;

import java.util.HashMap;
import java.util.Map;

public class Reporte {
    private float balance;
    private Map<String, Float> gastosPorCategoria;

    public Reporte() {
        this.balance = 0.0f;
        this.gastosPorCategoria = new HashMap<>();
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Map<String, Float> getGastosPorCategoria() {
        return gastosPorCategoria;
    }

    public void agregarGasto(String categoria, float monto) {
        gastosPorCategoria.put(categoria, gastosPorCategoria.getOrDefault(categoria, 0.0f) + monto);
    }

    @Override
    public String toString() {
        return "Reporte{balance=" + balance + ", gastosPorCategoria=" + gastosPorCategoria + "}";
    }
}