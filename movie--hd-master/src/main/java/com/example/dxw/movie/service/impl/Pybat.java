package com.example.dxw.movie.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;

/**
 * Classname: SSSS
 * Package: com.example.wastereturns
 * Description:
 * Version 1.0
 */
public class Pybat {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        // 设置 PowerShell 命令
//
//
//        String[] cmd = {"runas", "/user:Administrator", "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe", "-Command", "python D:\\movie\\movie--hd-master\\ucf.py"};
//        Process process = Runtime.getRuntime().exec(cmd);
//
//        // 读取输出
//        java.io.InputStream inputStream = process.getInputStream();
//        byte[] buffer = new byte[1024];
//        int read;
//        while ((read = inputStream.read(buffer)) != -1) {
//            System.out.println(new String(buffer, 0, read));
//        }
//
//        // 关闭进程
//        process.getOutputStream().close();
//        process.getInputStream().close();
//        process.getErrorStream().close();
//        int exitCode = process.waitFor();
//        System.out.println("PowerShell process exited with code " + exitCode);
//    }
//}
//






//    public static void main(String[] args) throws IOException {
//        // 设置脚本路径
//        String scriptPath = "D:\\movie/movie--hd-master/ucf.py";
//
//        // 构造 PowerShell 命令
//        String command = "C:/Windows/System32/WindowsPowerShell/v1.0/powershell.exe -ExecutionPolicy ByPass -Command \"& 'D:/anaconda3/Scripts/activate.bat'; 'D:/anaconda3/python.exe'; python D:\\movie/movie--hd-master/ucf.py";
//
//        // 启动进程执行命令
//        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
//        builder.redirectErrorStream(true);
//        Process process = builder.start();
//
//        // 读取命令输出
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//            System.out.println("1111111111111111111111111111111111111");
//        }
//    }}
    public static void main(String[] args) throws SocketException {

        run_cmd("python D:\\movie\\movie--hd-master\\ucf.py");
    }
//"D:\springboot\dxw\bat\db_backup.bat"
    public static void run_cmd(String strcmd) {
        System.out.println(1231);
        Runtime rt = Runtime.getRuntime(); //Runtime.getRuntime()返回当前应用程序的Runtime对象
        Process ps = null;  //Process可以控制该子进程的执行或获取该子进程的信息。

        try {
            ps = rt.exec(strcmd);   //该对象的exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
            ps.waitFor();  //等待子进程完成再往下执行。
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int i = ps.exitValue();  //接收执行完毕的返回值
        if (i == 0) {
            System.out.println("执行完成.");
        } else {
            System.out.println("执行失败.");
        }
        ps.destroy();  //销毁子进程
        ps = null;
    }

}
