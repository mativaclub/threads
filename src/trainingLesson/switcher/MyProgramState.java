package trainingLesson.switcher;

public class MyProgramState {
    private volatile boolean switcher;//Безопасно будут вызываться из 2х и более потоков
    private volatile boolean finish;

    public boolean isSwitcher() {
        return switcher;
    }

    public void setSwitcher(boolean switcher) {
        this.switcher = switcher;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}