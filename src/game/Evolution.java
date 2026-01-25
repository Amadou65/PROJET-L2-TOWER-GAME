// classe mere
abstract class Evolution {

    protected ProjectileTower tower;
    protected String type;
    protected int cost;

    public Evolution(ProjectileTower tower, String type, int cost) {
        this.tower = tower;
        this.type = type;
        this.cost = cost;
    }

    public boolean isAllowed() {
        return true;
    }

    public boolean isPowerEvolution() {
        return false;
    }

    public boolean isCadenceEvolution() {
        return false;
    }

    public boolean isScopeEvolution() {
        return false;
    }

    public boolean isProjectileEvolution() {
        return false;
    }
}

// sous classes
class PowerEvolution extends Evolution {

    public PowerEvolution(ProjectileTower tower, int cost) {
        super(tower, "Power", cost);
    }

    @Override
    public boolean isPowerEvolution() {
        return true;
    }
}

class CadenceEvolution extends Evolution {

    public CadenceEvolution(ProjectileTower tower, int cost) {
        super(tower, "Cadence", cost);
    }

    @Override
    public boolean isCadenceEvolution() {
        return true;
    }
}

class ScopeEvolution extends Evolution {

    public ScopeEvolution(ProjectileTower tower, int cost) {
        super(tower, "Scope", cost);
    }

    @Override
    public boolean isScopeEvolution() {
        return true;
    }
}

class ProjectileEvolution extends Evolution {

    public ProjectileEvolution(ProjectileTower tower, int cost) {
        super(tower, "Projectile", cost);
    }

    @Override
    public boolean isProjectileEvolution() {
        return true;
    }
}
