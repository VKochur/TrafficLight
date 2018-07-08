package kvv.education.khasang.java_intern.traffic_light;

import java.util.Scanner;

/**
 * Пример работы светофора.
 */
public class Main {

    public static void main(String[] args) {
        demo();
    }

    /**
     * Пример работы светофора. Ввод прошедших моментов времени с клавиатуры
     */
    private static void demo() {
        final int CMD_SWITCH = -1;
        final int CMD_EXIT = 0;
        final String MENU = String.format("%s %d = %s , %d = %s, %s", "Введите команду: ", CMD_SWITCH, "Переключить свет", CMD_EXIT, "Выход", "int > 0 - колво прошедших моментов времени");
        TrafficLight trafficLight = new TrafficLight();
        showTrafficLight(trafficLight);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(MENU);
            if (scanner.hasNextInt()) {
                int cmd = scanner.nextInt();
                if (cmd > 0) {
                    for (int i = 0; i < cmd; i++) {
                        trafficLight.liveOneMoment();
                    }
                    showTrafficLight(trafficLight);
                } else {
                    if (cmd == CMD_SWITCH) {
                        Scanner scanner1 = new Scanner(System.in);
                        showMenuForSwitch();
                        if (scanner1.hasNextInt()) {
                            int mode = scanner1.nextInt();
                            try {
                                switchToMode(trafficLight, mode);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        showTrafficLight(trafficLight);
                    } else {
                        if (cmd == CMD_EXIT) {
                            System.out.println("exit");
                            break;
                        } else {
                            System.out.println("Некорректно д.б. > 0");
                        }
                    }
                }
            } else {
                System.out.println("Некорректно д.б. int");
            }
            scanner.nextLine();
        }
    }

    /*
      Показать меню для переключения режима
     */
    private static void showMenuForSwitch() {
        StringBuilder stringBuilder = new StringBuilder("Введите режим на который переключиться:\n");
        for (int i = 0; i < TrafficLight.COLORS_MODS.length; i++) {
            stringBuilder.append(i).append('=').append(TrafficLight.COLORS_MODS[i]).append("\t");
        }
        System.out.println(stringBuilder);
    }

    /*
      Переключить светофор в нужный режим
    */
    private static void switchToMode(TrafficLight trafficLight, int mode) {
        trafficLight.setCurrentMode(mode);
        System.out.println("Переключение на " + TrafficLight.defineColorForMode(mode));
    }

    /*
      Показать как светит светофор
     */
    private static void showTrafficLight(TrafficLight trafficLight) {
        System.out.println("Идет момент времени: " + trafficLight.getDurationOfLife());
        trafficLight.light();
    }
}
