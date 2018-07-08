package kvv.education.khasang.java_intern.traffic_light;

/**
 * Светофор.
 * Световор работает в разных режимах определяющих цвет. Светофор всегда работает в каком-либо режиме, т.е всегда светит.
 * Режимы работы световора: 0, 1, 2, ... (COLORS_MODS.length - 1)
 * Световор начинает работать с 0го режима.
 * Светофор работает, повторяя переключения режимов с i на i+1 режим. С последнего режима переключается на 0й
 * i -му режиму работы соответствует цвет COLORS_MODS[i]
 * длительность i -го режима работы PERIODS_MODS[i] моментов времени
 * <p>
 * Можно принудительно переключить светофор в нужный режим, по истечению длительности режима светофор переключится на следующий режим.
 */
public class TrafficLight {

    /**
     * Цвета режимов работы.
     * COLORS_MODS[i] цвет iго режима
     */
    public static final Color[] COLORS_MODS = {Color.GREEN, Color.YELLOW, Color.RED, Color.YELLOW};

    /**
     * Длительность режимов
     * PERIODS_MODS[i] длительность iго режима
     */
    public static final int[] PERIODS_MODS = {2, 3, 5, 3};

    public enum Color {
        RED,
        YELLOW,
        GREEN
    }

    //общее колво светофоров
    private static int countTrafficLight;

    //номер светофора
    private int id;
    //текущий режим работы
    private int currentMode;
    //счетчик прожитых светофором моментов
    private int timer;
    //момент последнего переключения режима
    private int lastSwitchingTime;

    /**
     * Светофор инстанцируется в режиме работы TrafficLight.COLORS_MODS[0]
     * Возможно поменять режим this.setCurrentMode(int)
     */
    public TrafficLight() {
        id = ++countTrafficLight;
        timer = 0;
        setCurrentMode(0);
    }

    /**
     * Установить текущий режим работы.
     * Не приводит к проживанию светофором момента времени
     *
     * @param currentMode д.б. [0,TrafficLight.COLORS_MODS.length - 1]
     */
    public void setCurrentMode(int currentMode) {
        if ((currentMode < 0) || (currentMode > TrafficLight.COLORS_MODS.length - 1)) {
            throw new IllegalArgumentException(String.format("Режим д.б. [0,%d]", TrafficLight.COLORS_MODS.length - 1));
        }
        lastSwitchingTime = timer;
        this.currentMode = currentMode;
    }

    /**
     * Продолжительность существования световора в моментах времени
     *
     * @return
     */
    public int getDurationOfLife() {
        return timer;
    }

    /**
     * Момент времени с начала существования светофора, когда произошло последнее переключение режима работы
     *
     * @return
     */
    public int getLastSwitchingTime() {
        return lastSwitchingTime;
    }

    /**
     * Проживание 1 момента времени светофором
     */
    public void liveOneMoment() {
        timer++;
        if (needSwitching()) {
            switchToNextMode();
        }
    }

    private void switchToNextMode() {
        setCurrentMode((currentMode == TrafficLight.COLORS_MODS.length - 1) ? 0 : currentMode + 1);

    }

    private boolean needSwitching() {
        int currentDuration = timer - lastSwitchingTime;
        int maxDurattion = TrafficLight.PERIODS_MODS[currentMode];
        if (currentDuration == maxDurattion) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return цвет сооветствующий текущему режиму
     */
    public Color defineCurrentColor() {
        return TrafficLight.COLORS_MODS[currentMode];
    }

    /**
     * Уникальный номер светофора
     * @return
     */
    public int getId() {
        return id;
    }


    public void light() {
        final String MSG_FOR_LIGHT = "Светофор №";
        System.out.println(MSG_FOR_LIGHT + id + ": " + defineCurrentColor());
    }

    /**
     * Определяет цвет соответствующий указаному режиму
     * @param mode режим д.б. [0,TrafficLight.COLORS_MODS.length - 1], иначе IndexOutOfBoundsException
     * @return
     */
    public static Color defineColorForMode(int mode) {
        return TrafficLight.COLORS_MODS[mode];
    }
}
