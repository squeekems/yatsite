package com.squeekems.yat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long itemId;
  @Column
  private String name;
  @Column
  private boolean isGreater;
  @Column
  private String type;
  @Column
  private int damage;
  @Lob
  @Column(length = 65535)
  private String effects;
  @Column
  private int count;

  public Item() {
  }

  public Item(String name, boolean isGreater, String type, int damage, String effects, int count) {
    this.name = name;
    this.isGreater = isGreater;
    this.type = type;
    this.damage = damage;
    this.effects = effects;
    this.count = count;
  }

  public Item(String name, boolean isGreater, String type, String damage, String effects, String count) {
    this.name = name;
    this.isGreater = isGreater;
    this.type = type;
    if (!damage.equals("")) {
      this.damage = Integer.parseInt(damage);
    }
    this.effects = effects;
    if (!count.equals("")) {
      this.count = Integer.parseInt(count);
    } else {
      this.count = 0;
    }
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getGreater() {
    return isGreater;
  }

  public void setGreater(Boolean greater) {
    isGreater = greater;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getDamage() {
    return damage;
  }

  public void setDamage(Integer damage) {
    this.damage = damage;
  }

  public String getEffects() {
    return effects;
  }

  public void setEffects(String effects) {
    this.effects = effects;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "Item{" +
        "itemId=" + itemId +
        ", name='" + name + '\'' +
        ", isGreater=" + isGreater +
        ", type='" + type + '\'' +
        ", damage=" + damage +
        ", effects='" + effects + '\'' +
        ", count=" + count +
        '}';
  }
}
