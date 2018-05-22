package model;

public class Weapon {
    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public Projectile fire(int x, int y){
        return new Projectile(Entity.Id.PROJECTILE, x, y, 20, 20, false, 5);
    }

    public String toString(){
        return this.name  + " " + this.damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
