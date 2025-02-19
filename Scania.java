import java.awt.*;

public final class Scania extends Truck {

  private final TruckBed truckBed;

  public Scania() {
    this(Vec2.ZERO);
  }

  public Scania(Vec2 startOffset) {
    super(2, 100, Color.WHITE, "Scania", startOffset);
    truckBed = new TruckBed();
  }

  @Override
  public boolean canMove() {
    return truckBed.getAngle() == 0;
  }

  @Override
  protected double speedFactor() {
    return getEnginePower() * 0.01;
  }

  public void raiseBed(double amount) {
    if (getCurrentSpeed() > 0) {
      throw new IllegalStateException("Cannot raise bed while moving");
    }

    truckBed.raise(amount);
  }

  public void lowerBed(double amount) {
    if (getCurrentSpeed() > 0) {
      throw new IllegalStateException("Cannot lower bed while moving");
    }

    truckBed.lower(amount);
  }

  public double getTruckBedAngle() {
    return truckBed.getAngle();
  }
}
