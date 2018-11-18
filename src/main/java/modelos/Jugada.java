package modelos;

public class Jugada {
    private EnumTurno turno;
    private EnumGanador ganador;
    private EnumEstado simbolo;
    private Reja reja;

    public Jugada(){}

    public Jugada(EnumTurno turno, EnumGanador ganador, EnumEstado simbolo, Reja reja) {
        this.turno = turno;
        this.ganador = ganador;
        this.simbolo = simbolo;
        this.reja = reja;
    }

    public EnumTurno getTurno() {
        return turno;
    }

    public void setTurno(EnumTurno turno) {
        this.turno = turno;
    }

    public EnumGanador getGanador() {
        return ganador;
    }

    public void setGanador(EnumGanador jugador) {
        this.ganador = jugador;
    }

    public EnumEstado getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(EnumEstado simbolo) {
        this.simbolo = simbolo;
    }

    public Reja getReja() {
        return reja;
    }

    public void setReja(Reja reja) {
        this.reja = reja;
    }

    @Override
    public String toString() {
        return "Jugada{" +
                "turno=" + turno +
                ", ganador=" + ganador +
                ", simbolo=" + simbolo +
                ", reja=" + reja +
                '}';
    }
}
