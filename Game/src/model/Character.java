package model;

public class Character {
        private String name;
        private int health;
        private int maxHealth;
        private int posX;
        private int posY;
        private boolean isEnemy;
        private Weapon weapon;

        public Character(String name, int maxHealth, int startPosX, int startPosY, boolean isEnemy, Weapon weapon){
            this.name = name;
            this.maxHealth = maxHealth;
            this.health = maxHealth;
            this.posX=startPosX;
            this.posY=startPosY;
            this.isEnemy = isEnemy;
            this.weapon = weapon;
        }

    public Character(String name, int maxHealth, int startPosX, int startPosY, boolean isEnemy, String weaponName, int weaponDamage){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.posX=startPosX;
        this.posY=startPosY;
        this.isEnemy = isEnemy;
        this.weapon = new Weapon(weaponName,weaponDamage);
    }

    public String toString(){
            return this.name + ", " +
                    this.maxHealth  + ", " +
                    this.health  + ", " +
                    this.posX  + ", " +
                    this.posY  + ", " +
                    this.isEnemy  + ", " +
                    this.weapon.toString();
    }
        public String getName() {
            return name;
    }

        public void setName(String name) {
            this.name = name;
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

}
