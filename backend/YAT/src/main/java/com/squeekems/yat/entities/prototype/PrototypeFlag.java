package com.squeekems.yat.entities.prototype;

import jakarta.persistence.*;

@Entity
@Table(name = "prototype_flag")
public class PrototypeFlag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String key;
  private Boolean value;
}
