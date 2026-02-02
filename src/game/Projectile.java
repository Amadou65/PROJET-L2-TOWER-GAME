// classe mere
package game;

abstract public class Projectile {

    protected int damage;

    public Projectile(int damage) {
        this.damage = damage;
    }

    public int giveDamage() {
        return damage;
    }
}