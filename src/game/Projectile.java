// classe mere
package game;
abstract class Projectile {

    protected int damage;

    public Projectile(int damage) {
        this.damage = damage;
    }

    public int giveDamage() {
        return damage;
    }
}






