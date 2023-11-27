package GUI_MotaTest_5;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer extends Thread{

    /*
        这个音乐播放器的原理我也不知道,之后再学
        这个类原理是通过继承Thread类使每个音乐有自己的线程，不然的话就只能同时播放一个音乐
     */

    //音乐的路径
    private String path;
    Clip clip;

    MusicPlayer(String path)
    {
        this.path = path;

    }

    @Override
    public void run() {
        try
        {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            switch (path)
            {
                case"motaMusic\\0.wav":
                case"motaMusic\\1-7.wav":
                case"motaMusic\\8-14.wav":
                case"motaMusic\\15-18.wav":
                case"motaMusic\\19-24.wav":
                    clip.loop(-1);
                    break;
                default:
                    clip.loop(0);
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void interrupt()
    {
        clip.stop();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
