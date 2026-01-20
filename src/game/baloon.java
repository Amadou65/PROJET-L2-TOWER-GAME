package game;
public class Baloon {
    public int life;
    public int speed;
    public int strength;

    public Baloon(int life, int speed, int strength) {
        this.life = life;
        this.speed = speed;
        this.strength = strength;
    }

    public void move() {
        System.out.println("Le ballon se déplace à une vitesse de " + speed);
    }

    public boolean isSpawn() {
        return true;
    }
}
