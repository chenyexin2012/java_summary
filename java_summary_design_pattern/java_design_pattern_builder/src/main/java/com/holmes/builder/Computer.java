package com.holmes.builder;

/**
 * 一个具体的产品-->Computer
 */
public class Computer {

    private Cpu cpu;
    private Memory memory;
    private GraphicsCard graphicsCard;
    private HardDisk hardDisk;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
        this.graphicsCard = builder.graphicsCard;
        this.hardDisk = builder.hardDisk;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Computer{");
        sb.append("cpu=").append(cpu);
        sb.append(", memory=").append(memory);
        sb.append(", graphicsCard=").append(graphicsCard);
        sb.append(", hardDisk=").append(hardDisk);
        sb.append('}');
        return sb.toString();
    }

    /**
     * 建造者
     * 构造Computer对象
     */
    public static class Builder {

        private Cpu cpu;
        private Memory memory;
        private GraphicsCard graphicsCard;
        private HardDisk hardDisk;

        public Builder addCpu(Cpu cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder addMemory(Memory memory) {
            this.memory = memory;
            return this;
        }

        public Builder addGraphicsCard(GraphicsCard graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder addHardDisk(HardDisk hardDisk) {
            this.hardDisk = hardDisk;
            return this;
        }

        public Computer createComputer() {
            return new Computer(this);
        }
    }
}
