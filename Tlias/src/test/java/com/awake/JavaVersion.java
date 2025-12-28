package com.awake;

public class JavaVersion {
    public static void main(String[] args) {
        // 获取Java运行时版本
        String javaVersion = System.getProperty("java.version");
        // 打印Java版本
        System.out.println("Current Java Version: " + javaVersion);
    }

}
