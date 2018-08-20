package com.ylym.sunscreen.protectsunscreen.model;

public class UvResponse {

  private final double uvIndex;
  private final UvLevel uvLevel;

  public enum UvLevel {
    LOW, MOD, HIGH, VHIGH, EXTR;
  }

  public UvResponse(double uvIndex, UvLevel uvLevel) {
    this.uvIndex = uvIndex;
    this.uvLevel = uvLevel;
  }

  public double getUvIndex() {
    return uvIndex;
  }

  public UvLevel getUvLevel() {
    return uvLevel;
  }
}
