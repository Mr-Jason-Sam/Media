/**   
 * <p><h1>Copyright:</h1><strong><a href="http://www.smart-f.cn">
 * BeiJing Smart Future Technology Co.Ltd. 2015 (c)</a></strong></p>
 */
package jasonsam.media.ffmpeg;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**  
 * <p><h1>Copyright:</h1><strong><a href="http://www.smart-f.cn">
 * BeiJing Smart Future Technology Co.Ltd. 2015 (c)</a></strong></p> 
 *
 * <p>
 * <h1>Reviewer:</h1> 
 * <a href="mailto:jiangjunjie@smart-f.cn">jjj</a>
 * </p>
 * 
 * <p>
 * <h1>History Trace:</h1>
 * <li> 2017年5月25日 下午1:25:38    V1.0.0          jjj         first release</li>
 * </p> 
 * @Title FFmpegCommand.java 
 * @Description please add description for the class 
 * @author jjj
 * @email <a href="jiangjunjie@smart-f.cn">jiangjunjie@smart-f.cn</a>
 * @date 2017年5月25日 下午1:25:38 
 * @version V1.0   
 */
public class FFmpegCommand {

    private static final String  TAG                   = FFmpegCommand.class.getSimpleName();

    public static List<String> getRecordCmd(String srcUrl, String storePath,
            String name, String format, double volume) {
        Log.i(TAG, "getRecordCmd, src:"+srcUrl+", store:"+storePath
                +", name:"+name+", format:"+format+", v"+volume);
        List<String> command= new ArrayList<String>();
        command.add(srcUrl);
        command.add("-vcodec");
        command.add("copy");
        if (volume > 0) {
            command.add("-af");
            command.add("volume="+volume);
        } else {
            command.add("-an");
        }
        command.add(storePath+name/*+format*/);
        return wrapCmd(command);
    }

    public static List<String> getRecordMixCmd(String srcUrl, String storePath,
            String name, String format, double volume, String bgmPath, double mVolume) {
        Log.i(TAG, "getRecordMixCmd, src:"+srcUrl+", store:"+storePath
                +", name:"+name+", format:"+format+", v"+volume
                +", bgmPath:"+bgmPath+", mVolume:"+mVolume);
        List<String> command= new ArrayList<String>();
        command.add(srcUrl);
        command.add("-vcodec");
        command.add("copy");
        command.add("-af");
        command.add("volume="+volume);
        command.add(storePath+name/*+format*/);
        return wrapCmd(command);
    }

    public static List<String> getRecordSegmentCmd(String srcUrl, String storePath,
            String format, double volume, int time) {
        Log.i(TAG, "getRecordSegmentCmd, src:"+srcUrl+", store:"+storePath
                +", format:"+format+", v:"+volume+", t:"+time);
        List<String> command= new ArrayList<String>();
        command.add(srcUrl);
        command.add("-vcodec");
        command.add("copy");
        command.add("-f");
        command.add("segment");
        command.add("-segment_time");
        command.add(""+time);
        if (volume > 0) {
            command.add("-af");
            command.add("volume="+volume);
        } else {
            command.add("-an");
        }
        command.add(storePath/*+format*/);
        return wrapCmd(command);
    }

    public static List<String> getRecordSegmentMixCmd(String srcUrl, String storePath,
            String format, double volume, int time, String bgmPath, double mVolume) {
        Log.i(TAG, "getRecordSegmentMixCmd, src:"+srcUrl+", store:"+storePath
                +", format:"+format+", v:"+volume+", t:"+time
                +", bgmPath:"+bgmPath+", mVolume:"+mVolume);
        List<String> command= new ArrayList<String>();
        command.add(srcUrl);
        command.add("-vcodec");
        command.add("copy");
        command.add("-f");
        command.add("segment");
        command.add("-segment_time");
        command.add(""+time);
        command.add("-af");
        command.add("volume="+volume);
        command.add(storePath/*+format*/);
        return wrapCmd(command);
    }

    public static List<String> getSnapshotCmd(String srcUrl, String storePath,
            String name, String format, int interval) {
        Log.i(TAG, "getSnapshotCmd, src:"+srcUrl+", store:"+storePath
                +", name:"+name+", format:"+format+", interval:"+interval);
        List<String> snapshot = new ArrayList<String>();
        snapshot.add(srcUrl);
        snapshot.add("-an");
        snapshot.add("-f");
        snapshot.add("image2");
        //snapshot.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        //snapshot.add("17"); // 添加起始时间为第17秒
        //snapshot.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        //snapshot.add("0.001"); // 添加持续时间为1毫秒
        //snapshot.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        //snapshot.add("800*280"); // 添加截取的图片大小为350*240
        snapshot.add("-r"); // 设置帧率 1=每秒1张 10=每秒10张 0.1=10秒一张; 此方式可同时指定起始和间隔，但如果程序是arm版的速度会很慢，耗时=最后截取帧所在时间-视频开始时间
        snapshot.add(""+interval);//1
        snapshot.add(storePath+name/*+format*/);
        //MediaConstant.SMART_MEDIA_FORMAT_BMP, MediaConstant.SMART_MEDIA_FORMAT_JPEG
        return wrapCmd(snapshot);
    }

    private static List<String> wrapCmd(List<String> params) {
        List<String> command= new ArrayList<String>();
        command.add(FFmpegConstant.getFfmpegPath());
        command.add("-rtsp_transport");
        command.add("tcp");
        command.add("-i");
        command.addAll(params);
        return command;
    }
}
