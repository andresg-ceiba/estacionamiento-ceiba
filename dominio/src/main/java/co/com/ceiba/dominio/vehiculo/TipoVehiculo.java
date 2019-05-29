package co.com.ceiba.dominio.vehiculo;


public enum TipoVehiculo {
    MOTO(500L, 4000L,500,2000L),
    CARRO(1000L, 8000L,Integer.MAX_VALUE,0L);

    TipoVehiculo(Long costoHora, Long costoDia, Integer umbralAltoCilindraje, Long sobreCostoUmbral) {
        this.costoHora = costoHora;
        this.costoDia = costoDia;
        this.umbralAltoCilindraje = umbralAltoCilindraje;
        this.sobreCostoUmbral = sobreCostoUmbral;
    }

    private final Long costoHora;
    private final Long costoDia;
    private final Integer umbralAltoCilindraje;
    private final Long sobreCostoUmbral;

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
}