package pfc.cebem.back.dto.excursion;

public class ExcursionSubtotalDTO {
    private ExcursionDTO excursion;
    private int seats;
    private double subtotal;

    public ExcursionDTO getExcursion() {
        return excursion;
    }
    public void setExcursion(ExcursionDTO excursion) {
        this.excursion = excursion;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}