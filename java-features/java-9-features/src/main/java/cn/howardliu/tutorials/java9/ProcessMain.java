package cn.howardliu.tutorials.java9;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/12/16 23:15
 */
public class ProcessMain {
    public static void main(String[] args) {
        final ProcessHandle self = ProcessHandle.current();
        final long pid = self.pid();
        System.out.println("PID: " + pid);

        final ProcessHandle.Info procInfo = self.info();

        procInfo.arguments().ifPresent(x -> {
            for (String s : x) {
                System.out.println(s);
            }
        });

        procInfo.commandLine().ifPresent(System.out::println);
        procInfo.startInstant().ifPresent(System.out::println);
        procInfo.totalCpuDuration().ifPresent(System.out::println);

        ProcessHandle.current().children()
                .forEach(procHandle -> {
                    System.out.println(procHandle.pid());
                    System.out.println(procHandle.destroy());
                });
    }
}
