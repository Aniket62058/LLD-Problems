package utils;

public final class Log {
    public static void info(String s) { System.out.println("[INFO] " + s); }
    public static void warn(String s) { System.out.println("[WARN] " + s); }
    public static void err(String s)  { System.err.println("[ERR ] " + s); }
}