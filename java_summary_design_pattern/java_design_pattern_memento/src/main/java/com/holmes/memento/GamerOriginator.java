package com.holmes.memento;

/**
 * @Description: 备忘录的发起者角色
 * @Author: holmes
 * @Version: 1.0.0
 */
public class GamerOriginator {

    private String name;

    private int level;

    private int lp;

    public GamerOriginator(String name, int level, int lp) {
        this.name = name;
        this.level = level;
        this.lp = lp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    /**
     * 保存至备忘录
     *
     * @return
     */
    public Memento saveMemento() {
        return new Memento(this.name, this.level, this.lp);
    }

    /**
     * 从备忘录恢复
     *
     * @param memento
     */
    public void recovery(Memento memento) {
        this.name = memento.getName();
        this.level = memento.getLevel();
        this.lp = memento.getLp();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GamerOriginator{");
        sb.append("name='").append(name).append('\'');
        sb.append(", level=").append(level);
        sb.append(", lp=").append(lp);
        sb.append('}');
        return sb.toString();
    }
}
