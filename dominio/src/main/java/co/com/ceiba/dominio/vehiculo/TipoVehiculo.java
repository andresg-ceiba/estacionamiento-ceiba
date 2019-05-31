package co.com.ceiba.dominio.vehiculo;


public enum TipoVehiculo {
    MOTO(500L, 4000L,500,2000L,10L),
    CARRO(1000L, 8000L,Integer.MAX_VALUE,0L,20L);

    TipoVehiculo(Long costoHora, Long costoDia, Integer umbralAltoCilindraje, Long sobreCostoUmbral, Long capacidadMaxima) {
        this.costoHora = costoHora;
        this.costoDia = costoDia;
        this.umbralAltoCilindraje = umbralAltoCilindraje;
        this.sobreCostoUmbral = sobreCostoUmbral;
        this.capacidadMaxima = capacidadMaxima;
    }

    private final Long costoHora;
    private final Long costoDia;
    private final Integer umbralAltoCilindraje;
    private final Long sobreCostoUmbral;
    private final Long capacidadMaxima;

    public Long getCostoHora() {
        return costoHora;
    }

    public Long getCostoDia() {
        return costoDia;
    }

    public Integer getUmbralAltoCilindraje() {
        return umbralAltoCilindraje;
    }

    public Long getSobreCostoUmbral() {
        return sobreCostoUmbral;
    }

    public Long getCapacidadMaxima() {
        return capacidadMaxima;
    }
}