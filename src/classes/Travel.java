package classes;

import enums.TravelStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Travel {
   private UUID  id;
   private User driver;
   private User traveler;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private TravelStatus status;
   private BigDecimal totalFare;
   private City from;
   private City to;
   public Travel(){

   }

    public Travel(UUID id, User driver, User traveler, LocalDateTime startTime, LocalDateTime endTime, TravelStatus status, BigDecimal totalFare, City from, City to) {
        this.id = id;
        this.driver = driver;
        this.traveler = traveler;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalFare = totalFare;
        this.from = from;
        this.to = to;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public User getTraveler() {
        return traveler;
    }

    public void setTraveler(User traveler) {
        this.traveler = traveler;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(BigDecimal totalFare) {
        this.totalFare = totalFare;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }
}
