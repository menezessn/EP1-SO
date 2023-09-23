public class BCP {
    //classe que define o Bloco de controle de processo com todas as informacoes necessarias do processo
    private String name;
    private int PC;
    private Estado processState;
    private int X = 0;
    private int Y = 0;
    private int waitTime = 0;
    private String program[];

    public BCP(String name, Estado processState, String[] program) {
        this.name = name;
        this.processState = processState;
        this.program = program;
        this.PC = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public Estado getProcessState() {
        return processState;
    }

    public void setProcessState(Estado processState) {
        this.processState = processState;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public String[] getProgram() {
        return program;
    }

    public void setProgram(String[] program) {
        this.program = program;
    }
}
