// classe mere
abstract class Projectile {

    protected int damage;

    public Projectile(int damage) {
        this.damage = damage;
    }

    public int giveDamage() {
        return damage;
    }
}

// sous classes
class Dart extends Projectile {

    public Dart() {
        super(10);
    }
}

class SharpDart extends Projectile {

    public SharpDart() {
        super(15);
    }
}

class VerySharpDart extends Projectile {

    public VerySharpDart() {
        super(20);
    }
}

class Needle extends Projectile {

    public Needle() {
        super(12);
    }
}

class Bomb extends Projectile {

    public Bomb() {
        super(30);
    }
}

class ExtraBomb extends Projectile {

    public ExtraBomb() {
        super(45);
    }
}