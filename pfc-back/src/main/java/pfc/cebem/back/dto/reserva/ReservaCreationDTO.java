package pfc.cebem.back.dto.reserva;

public class ReservaCreationDTO {
    private Long excursionId;
    private String email;
    private int numPlazas;
    
    public Long getExcursionId() {
        return excursionId;
    }
    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getNumPlazas() {
        return numPlazas;
    }
    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    
}
